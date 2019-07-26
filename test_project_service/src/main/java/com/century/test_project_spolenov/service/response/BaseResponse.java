package com.century.test_project_spolenov.service.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor
@EqualsAndHashCode
public abstract class BaseResponse<T> implements Response{
    //Просто контейнер с данными
    private List<T> data;

    @Override
    public List<T> getData(){
        return data == null? new ArrayList<>(): data;
    }

    public BaseResponse(List<T> data){
        if(data == null){
            this.data = new ArrayList<>();
            return;
        }
        this.data = data;
    }

    public BaseResponse(T data){
        if(data == null){
            this.data = new ArrayList<>();
            return;
        }
        this.data = Collections.singletonList(data);
    }
}
