package com.carlgira.finder.web;

import com.carlgira.finder.entity.ClassStore;
import com.carlgira.finder.entity.Product;
import com.carlgira.finder.entity.Version;
import com.carlgira.finder.repository.ClassStoreRepository;
import com.carlgira.finder.repository.ProductRepository;
import com.carlgira.finder.repository.VersionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller(value = "controller")
public class WebController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private VersionRepository versionRepository;

    @Autowired
    private ClassStoreRepository classStoreRepository;

    private static Logger logger = LoggerFactory.getLogger(WebController.class);

    @RequestMapping( path = "/classFinder")
    public ModelAndView classFinder() {

        ModelAndView model = new ModelAndView("classFinder");
        model.addObject("productList",this.productRepository.findAll() );
        model.addObject("versionList",this.versionRepository.findAll() );
        model.addObject("classStore",new ClassStore() );

        return model;
    }

    @RequestMapping(value="/classFinder/result", method= RequestMethod.POST)
    public ModelAndView classFinderResult(@ModelAttribute ClassStore classStore) {

        ModelAndView model = new ModelAndView("result");
        model.addObject("classStore", classStore);

        List<ClassStore> classStores = null;

        if(classStore.getClassName() != null && !classStore.getClassName().trim().isEmpty()){
            classStores = this.classStoreRepository.findByClassNameAndProductIdAndProductVersion(classStore.getClassName(), classStore.getProductId(), classStore.getProductVersion());
        }
        else{
            classStores = this.classStoreRepository.findByFullClassNameAndProductIdAndProductVersion(classStore.getClassName(), classStore.getProductId(), classStore.getProductVersion());
        }

        model.addObject("classStores", classStores);

        return model;
    }


}
