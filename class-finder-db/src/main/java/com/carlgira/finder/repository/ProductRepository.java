package com.carlgira.finder.repository;

import com.carlgira.finder.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  ProductRepository extends JpaRepository<Product, String> {

    List<Product> findById(String id);
}
