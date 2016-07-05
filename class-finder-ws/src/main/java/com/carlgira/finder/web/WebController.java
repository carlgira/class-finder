package com.carlgira.finder.web;

import com.carlgira.finder.entity.Product;
import com.carlgira.finder.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WebController {

    @Autowired
    private ProductRepository productRepository;

    private static Logger logger = LoggerFactory.getLogger(WebController.class);

    @RequestMapping( path = "/classFinder")
    public ModelAndView classFinder() {

        ModelAndView model = new ModelAndView("classFinder");

        return model;
    }

    @ModelAttribute("productList")
    public List<Product> productList(){
        return this.productRepository.findAll();
    }
}
