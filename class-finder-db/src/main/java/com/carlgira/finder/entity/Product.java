package com.carlgira.finder.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {

    @Id
    private String id;

    private String label;

    @OneToMany(targetEntity = Version.class, fetch = FetchType.EAGER, cascade= CascadeType.ALL)
    private List<Version> versions;

    public Product(){
        this.versions = new ArrayList<Version>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Version> getVersions() {
        return versions;
    }

    public void setVersions(List<Version> versions) {
        this.versions = versions;
    }

    @Override
    public String toString() {
        String result = "Product{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                ", versions=[ ";
        for(Version v : this.versions){
            result += "\n " + v.toString();
        }
        return result + "]}";
    }
}
