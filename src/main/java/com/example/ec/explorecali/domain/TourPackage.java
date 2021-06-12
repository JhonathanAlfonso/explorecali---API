package com.example.ec.explorecali.domain;

import javax.persistence.*;

@Entity
public class TourPackage {

    @Id
    private String code;

    @Column
    private String name;

    public TourPackage(String code, String name) {
        this.code = code;
        this.name = name;
    }

    protected TourPackage() {
    }
}

