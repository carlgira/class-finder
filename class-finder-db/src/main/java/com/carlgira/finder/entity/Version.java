package com.carlgira.finder.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Version {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String number;

    public Version(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Version{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }
}
