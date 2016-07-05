package com.carlgira.finder.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ClassStore {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String className;
    private String fullClassName;
    private String productId;
    private String productVersion;

    @OneToOne(targetEntity = Jar.class)
    private Jar jar;

    public ClassStore(){}

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFullClassName() {
        return fullClassName;
    }

    public void setFullClassName(String fullClassName) {
        this.fullClassName = fullClassName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductVersion() {
        return productVersion;
    }

    public void setProductVersion(String productVersion) {
        this.productVersion = productVersion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Jar getJar() {
        return jar;
    }

    public void setJar(Jar jar) {
        this.jar = jar;
    }

    @Override
    public String toString() {
        return "ClassStore{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", fullClassName='" + fullClassName + '\'' +
                ", productId='" + productId + '\'' +
                ", productVersion='" + productVersion + '\'' +
                ", jar= Jar{" +
                    ", name='" + this.jar.getName() + '\'' +
                    ", path='" + this.jar.getPath() + '\'' +
                "}}";
    }
}
