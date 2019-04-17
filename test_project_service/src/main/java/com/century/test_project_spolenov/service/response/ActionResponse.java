package com.century.test_project_spolenov.service.response;

public abstract class ActionResponse<T> extends BaseResponse{
    //Результат действия с сущностью (сохранение, обновление, удаление и т.д.)
    private ResponseState state;
    private T data;

    public T getData(){
        return data;
    }

    public ActionResponse(T data){
        this.data = data;
    }

    public ActionResponse(T data, ResponseState state){
        this(data);
        this.state = state;
    }

    public ResponseState getState(){
        return state;
    }

    public abstract String getDataInfo();
}
