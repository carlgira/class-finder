package com.carlgira.finder.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Jar {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String name;
    private String path;

    //@OneToMany(targetEntity=ClassStore.class, mappedBy="jar", fetch = FetchType.EAGER, cascade= CascadeType.ALL)
    //private List<ClassStore> classStores;

    public Jar() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /*public List<ClassStore> getClassStores() {
        return classStores;
    }

    public void setClassStores(List<ClassStore> classStores) {
        this.classStores = classStores;
    }
    */

    @Override
    public String toString() {
        String result = "Jar{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'';
        return result + "}";
/*                ", classStores=[";
        for(ClassStore c : this.classStores){
            result += "\n " + c.toString();
        }
*/

    }
}
