package com.century.test_project_spolenov.service.request;

import com.century.test_project_spolenov.model.DocType;

import java.util.List;

public interface Request<T> {
    List<T> getData();
    DocType getDocType();
    void setDocType(DocType docType);

    T getSingleElement();

    default void verifyDataNotEmpty(){
        if(getData() == null){
            throw new IllegalArgumentException("Request data must not be null.");
        }
        if(getData().isEmpty()){
            throw new IllegalArgumentException("Request data must not be empty.");
        }
    }

    default void verifySingleElementData(){
        verifyDataNotEmpty();

        if(getData().size() > 1){
            throw new IllegalArgumentException("Request data must contain single element only.");
        }
    }

    default void verifyDocType(){
        if(getDocType() == null){
            throw new IllegalArgumentException("DocType must not be null.");
        }
    }
}
