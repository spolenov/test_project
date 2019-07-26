package com.century.test_project_spolenov.model;

import lombok.Getter;

public enum DocType {
    ORDER(1, "order"),
    GOODS(2, "goods");

    @Getter
    private int id;
    @Getter
    private String name;

    DocType(int id, String name){
        this.id = id;
        this.name = name;
    }
}
