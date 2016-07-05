package com.carlgira.finder;

import com.carlgira.finder.entity.ClassStore;
import com.carlgira.finder.entity.Jar;
import com.carlgira.finder.entity.Product;
import com.carlgira.finder.entity.Version;
import com.carlgira.finder.repository.ClassStoreRepository;
import com.carlgira.finder.repository.JarRepository;
import com.carlgira.finder.repository.ProductRepository;
import com.carlgira.finder.repository.VersionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ImportUtil implements CommandLineRunner {

    private ProductRepository productRepository;
    private ClassStoreRepository classStoreRepository;
    private JarRepository jarRepository;
    private VersionRepository versionRepository;

    public static final Logger log = LoggerFactory.getLogger(ImportUtil.class);

    private Jar lastJar;

    public ImportUtil(ProductRepository productRepository, ClassStoreRepository classStoreRepository, JarRepository jarRepository, VersionRepository versionRepository) {
        this.productRepository = productRepository;
        this.classStoreRepository = classStoreRepository;
        this.jarRepository = jarRepository;
        this.versionRepository = versionRepository;
    }

    public void importFiles(List<File> files) throws IOException {

        String fileName = files.get(0).getName();
        String productId = fileName.substring(0, fileName.indexOf("-"));
        String versionNumber = fileName.substring(fileName.indexOf("-")+1, fileName.indexOf(".txt"));

        Product product = this.productRepository.findOne(productId);
        if(product != null){
            for(Version v : product.getVersions()){
                if(v.getNumber().equals(versionNumber)){
                    Application.log.error("Product " + productId + " version " + versionNumber + "already imported");
                    return;
                }
            }
        }
        else{
            product = new Product();
            product.setId(productId);
            product.setLabel(productId);
            List<Version> versions1 = new ArrayList<Version>();
            Version v = new Version();
            v.setNumber(versionNumber);
            versions1.add(v);
            product.setVersions(versions1);
            this.productRepository.saveAndFlush(product);
        }

        for(File file : files){
            importFile(file, productId, versionNumber);
        }

        log.info("Import Successful");
    }

    public void importFile(File file, String productId, String version) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line;
        List<ClassStore> classStores = new ArrayList<ClassStore>();
        while( (line = reader.readLine()) != null ){
            if(line.startsWith("ARCHIVE,")){
                if(this.lastJar != null){
                    this.jarRepository.save(this.lastJar);
                    this.classStoreRepository.save(classStores);
                }
                classStores = new ArrayList<ClassStore>();
                this.lastJar = new Jar();
                this.lastJar.setPath(line.substring(line.indexOf(".")));
                this.lastJar.setName(this.lastJar.getPath().substring(this.lastJar.getPath().lastIndexOf("/") + 1,this.lastJar.getPath().length()));
            }
            else{
                if(line.endsWith(".class")){
                    ClassStore classStore = new ClassStore();
                    classStore.setFullClassName(line.replaceAll("/", "."));
                    classStore.setFullClassName(classStore.getFullClassName().replace(".class", ""));
                    classStore.setClassName(classStore.getFullClassName().substring(classStore.getFullClassName().lastIndexOf(".")+1,classStore.getFullClassName().length()));
                    classStore.setProductId(productId);
                    classStore.setProductVersion(version);
                    classStore.setJar(this.lastJar);
                    classStores.add(classStore);
                }
            }
        }
        this.jarRepository.save(this.lastJar);
        this.classStoreRepository.save(classStores);

        this.jarRepository.flush();
        this.classStoreRepository.flush();
    }

    public void deleteProduct(String productId, String version){
        // FIX
    }

    @Override
    public void run(String args[]) throws Exception {

        System.out.println("args");
        for(String a : args){
            System.out.println(a);
        }


        String operation = "";

        try{
            operation = args[0];
        }
        catch (Exception e){
            Application.log.error("Bad parameters");
            return;
        }

        if(operation.equals("import")) {
            List<File> files = new ArrayList<File>();
            for(int i=1;i<args.length;i++){
                File file = new File(args[i]);
                if(file.exists()){
                    files.add(file);
                }
                else {
                    Application.log.error("Bad path, " + args[i]);
                    return ;
                }
            }
            importFiles(files);
        }
        else if(operation.equals("delete")) {
            String productId = "";
            String version = "";
            try{
                productId = args[1];
                version = args[2];
            }
            catch (Exception e){
                Application.log.error("Bad parameters");
                return;
            }
            deleteProduct(productId, version);
        }else if(operation.equals("query")) {
            String q = args[1];
            String param = args[2];

            List<ClassStore> result = new ArrayList<ClassStore>();

            if(q.equals("className")){
                result = this.classStoreRepository.findByClassName(param);
            }

            if(q.equals("fullClassName")){
                result = this.classStoreRepository.findByFullClassName(param);
            }

            log.info("Query " + q + ", numberOfResults " + result.size());
            for(ClassStore c : result){
                log.info(c.toString());
            }
        }
        else if(operation.equals("printAll")){
            printAll();
        }
        else{
            Application.log.error("Bad parameters");
            return;
        }
    }

    public void printAll(){
        for(ClassStore c : this.classStoreRepository.findAll()){
            log.info(c.toString());
        }

        for(Jar j : this.jarRepository.findAll()){
            log.info(j.toString());
        }

        for(Version v : this.versionRepository.findAll()){
            log.info(v.toString());
        }

        for(Product p : this.productRepository.findAll()){
            log.info(p.toString());
        }
    }
}
