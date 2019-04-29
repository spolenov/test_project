package com.century.test_project_spolenov.service.response;

import java.util.List;

public abstract class ActionResponse<T> extends BaseResponse{
    //Результат действия с сущностью (сохранение, обновление, удаление и т.д.)
    private ResponseState state;
    private List<T> data;

    public List<T> getData(){
        return data;
    }

    public ActionResponse(List<T> data){
        super(data);
    }

    public ActionResponse(List<T> data, ResponseState state){
        this(data);
        this.state = state;
    }

    public ResponseState getState(){
        return state;
    }
}
