package com.carlgira.finder.repository;


import com.carlgira.finder.entity.ClassStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassStoreRepository extends JpaRepository<ClassStore, Long> {

    List<ClassStore> findByClassName(String className);

    List<ClassStore> findByFullClassName(String fullClassName);

    List<ClassStore> findByClassNameAndProductIdAndProductVersion(String className, String productId, String productVersion);

    List<ClassStore> findByFullClassNameAndProductIdAndProductVersion(String fullClassName, String productId, String productVersion);
}
