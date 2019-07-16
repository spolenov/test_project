package com.century.test_project_spolenov.service.response;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public abstract class BaseResponse<T> implements Response{
    //Просто контейнер с данными
    private List<T> data;

    public List<T> getData(){
        return data;
    }

    public BaseResponse(List<T> data){
        if(data == null){
            this.data = new ArrayList<>();
            return;
        }
        this.data = data;
    }
}
