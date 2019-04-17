package com.century.test_project_spolenov.service.response;

public enum ResponseState {
    OK(1, "OK"),
    ERROR(2, "ERROR");

    private int id;
    private String name;

    ResponseState(int id, String name){
        this.id = id;
        this.name = name;
    }
}
