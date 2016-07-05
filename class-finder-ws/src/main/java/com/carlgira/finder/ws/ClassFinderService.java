package com.carlgira.finder.ws;

import com.carlgira.finder.entity.ClassStore;
import com.carlgira.finder.entity.Product;
import com.carlgira.finder.repository.ClassStoreRepository;
import com.carlgira.finder.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class ClassFinderService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ClassStoreRepository classStoreRepository;

    public static final Logger LOGGER = LoggerFactory.getLogger(ClassFinderService.class);

    public ClassFinderService() {
    }

    @RequestMapping("/product/list")
    public List<Product> listOfProducts() throws Exception {
        return this.productRepository.findAll();
    }

    @RequestMapping("/product/detail")
    public Product productDetail(@RequestParam(value = "id", required = true) String productId) throws Exception {
        return this.productRepository.findOne(productId);
    }

    @RequestMapping("/{id}/{version}/className")
    public List<ClassStore> searchByClassName(@PathVariable("id") String productId,
                                              @PathVariable("version") String version,
                                              @RequestParam(value = "q", required = true) String className) throws Exception {
        return this.classStoreRepository.findByClassNameAndProductIdAndProductVersion(className, productId, version);
    }

    @RequestMapping("/{id}/{version}/fullClassName")
    public List<ClassStore> searchByFullClassName(@PathVariable("id") String productId,
                                               @PathVariable("version") String version,
                                               @RequestParam(value = "q", required = true) String fullClassName) throws Exception {
        return this.classStoreRepository.findByFullClassNameAndProductIdAndProductVersion(fullClassName, productId, version);
    }
}
