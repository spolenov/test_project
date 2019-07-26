package com.century.test_project_spolenov.service.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.century.test_project_spolenov.service.util.ServiceUtils.getDataSizeDescription;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class ActionResponse<T> extends BaseResponse<T>{
    //Результат действия с сущностью (сохранение, обновление, удаление и т.д.)
    //Возможные варианты создания экземпляра - через errResponse(), okResponse()
    @Getter
    private ResponseState state;

    private ActionResponse(List<T> data){
        super(data);
    }

    ActionResponse(List<T> data, ResponseState state){
        this(data);
        this.state = state;
    }

    ActionResponse(ResponseState state){
        this(new ArrayList<>());
        this.state = state;
    }

    public static <T> ActionResponse<T> okResponse(List<T> data){
        return new ActionResponse<T>(data, ResponseState.OK) {
            @Override
            public String getDescription() {
                if(data == null || !data.isEmpty()){
                    return "Executed successfully";
                }
                return getDataSizeDescription(data);
            }
        };
    }

    public static <T> ActionResponse<T> okResponse(T data){
        return new ActionResponse<T>(Collections.singletonList(data), ResponseState.OK) {
            @Override
            public String getDescription() {
                return getDataSizeDescription(getData());
            }
        };
    }

    public static <T> ActionResponse<T> okResponse(){
        return okResponse(null);
    }

    public static <T> ActionResponse<T> errResponse(String description, Exception e){
        return new ActionResponse <T>(ResponseState.ERROR) {
            @Override
            public String getDescription() {
                return String.format("%s: %s", description, e.getLocalizedMessage());
            }
        };
    }

    public static <T> ActionResponse<T> errResponse(String description){
        return new ActionResponse<T>(ResponseState.ERROR) {
            @Override
            public String getDescription() {
                return description;
            }
        };
    }

    public boolean isOk(){
        if(this.getState() == null){
            return false;
        }
        return this.getState().equals(ResponseState.OK);
    }
}
