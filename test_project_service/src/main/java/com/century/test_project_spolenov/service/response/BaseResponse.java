package com.century.test_project_spolenov.service.response;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class BaseResponse<T> implements Response{
    //Просто контейнер с данными
    private T data;

    public T getData(){
        return data;
    }

    public BaseResponse(T data){
        this.data = data;
    }

    public abstract String getDataInfo();
}
