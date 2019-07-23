package com.century.test_project_spolenov.service.response;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public abstract class ActionResponse<T> extends BaseResponse{
    //Результат действия с сущностью (сохранение, обновление, удаление и т.д.)
    private ResponseState state;

    public List<T> getData(){
        return super.getData();
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

    public static Response okResponse(){
        return new ActionResponse() {
            @Override
            public String getDescription() {
                return BaseResponse.okResponse().getDescription();
            }

            @Override
            public ResponseState getState(){
                return ResponseState.OK;
            }
        };
    }

    public static Response errResponse(String description, Exception e){
        return new ActionResponse() {
            @Override
            public String getDescription() {
                return String.format("%s: %s", description, e.getLocalizedMessage());
            }

            @Override
            public ResponseState getState(){
                return ResponseState.ERROR;
            }
        };
    }

    public static Response errResponse(String description){
        return new ActionResponse() {
            @Override
            public String getDescription() {
                return description;
            }

            @Override
            public ResponseState getState(){
                return ResponseState.ERROR;
            }
        };
    }
}
