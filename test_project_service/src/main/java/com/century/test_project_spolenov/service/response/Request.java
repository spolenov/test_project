package com.century.test_project_spolenov.service.response;

import java.util.List;

public interface Request<T> {
    List<T> getData();

    default void assertDataEmptiness(){
        if(getData() == null){
            throw new IllegalArgumentException("Request data must not be null.");
        }
        if(getData().isEmpty()){
            throw new IllegalArgumentException("Request data must not be empty.");
        }
    }

    default void assertDataHasSingleElement(){
        assertDataEmptiness();

        if(getData().size() > 1){
            throw new IllegalArgumentException("Request data must contain single element only.");
        }
    }
}
