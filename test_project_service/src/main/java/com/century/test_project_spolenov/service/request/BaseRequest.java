package com.century.test_project_spolenov.service.request;

import com.century.test_project_spolenov.model.DocType;
import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class BaseRequest<T> implements Request<T>{
    @Getter
    private List<T> data;
    @Getter @Setter
    private DocType docType;

    public BaseRequest(List<T> data){
        if(data == null){
            this.data = new ArrayList<>();
            return;
        }
        this.data = data;
    }

    public BaseRequest(T data){
        if(data == null){
            this.data = new ArrayList<>();
            return;
        }

        this.data = Collections.singletonList(data);
    }

    public T getSingleElement(){
        return data.get(0);
    }

    public void setData(List<T> data){
        this.data = data;
    }
}
