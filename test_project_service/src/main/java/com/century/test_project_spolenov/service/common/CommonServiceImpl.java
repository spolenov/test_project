package com.century.test_project_spolenov.service.common;

import com.century.test_project_spolenov.model.DocType;
import com.century.test_project_spolenov.model.Element;
import com.century.test_project_spolenov.service.request.Request;
import com.century.test_project_spolenov.service.response.ActionResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

import static com.century.test_project_spolenov.service.response.ActionResponse.errResponse;
import static com.century.test_project_spolenov.service.response.ActionResponse.okResponse;
import static com.century.test_project_spolenov.service.util.ServiceUtils.*;

@Service
@Slf4j
@Scope("prototype")
public class CommonServiceImpl<T extends Element> implements CommonService<T> {
    private JpaRepository<T, Integer> repository;

    @Getter @Setter
    private DocType eligibleDocType;

    @PostConstruct
    public void init(){
        if (eligibleDocType == null){
            if(this.repository == null){
                return;
            }

            Class clazz = getEntityClass(this.repository.getClass());
            eligibleDocType = clazz == null? null:
                    DocType.valueOf(
                            StringUtils.upperCase(
                                    clazz.getSimpleName()));
        }
    }

    @Override
    public ActionResponse<T> getAll() {
        List<T> elements;

        try{
            elements = repository.findAll();
        } catch (Exception e){
            return errResponse(String.format(
                    "Failed to get all %s",
                    getEntityClassName()),
                    e);
        }

        if(elements.isEmpty()){
            return errResponse(String.format(
                    "No %s found in database.",
                    getEntityClassName()));
        }
        return okResponse(elements);
    }

    @Override
    public ActionResponse<T> getById(int id) {
        ActionResponse<T> response = getExisting(id);

        if(!response.isOk()){
            //Не нашли существующий элемент, обновить не можем.
            return response;
        }

        return okResponse(response.getData().get(0));
    }

    @Override
    public ActionResponse<T> deleteOne(int id) {
        ActionResponse<T> existingResponse = getExisting(id);

        if(!existingResponse.isOk()){
            //Не нашли существующий элемент, удалить не можем.
            return existingResponse;
        }

        try{
            repository.deleteById(id);
        } catch (Exception e){
            return errResponse(String.format(
                    "Failed to delete %s (id = %d)",
                    getEntityClassName(), id), e);
        }
        return okResponse();
    }

    @Override
    public ActionResponse<T> addNew(Request<T> request) {
        ActionResponse<T> response = verifySingleElement(request);
        if(response != null){
            return response;
        }

        T element = request.getSingleElement();
        ActionResponse<T> existingResponse = getExisting(element);

        if(existingResponse.isOk()){
            //Такой элемент уже есть, добавлять нельзя.
            return errResponse(String.format(
                    "Failed to add new element: %s",
                    existingResponse.getDescription()));
        }

        try{
            repository.save(element);
        } catch (Exception e){
            return errResponse(String.format(
                    "Failed to add new %s",
                    getEntityClassName()), e);
        }
        return okResponse();
    }

    @Override
    public ActionResponse<T> updateOne(Request<T> request) {
        ActionResponse<T> response = verifySingleElement(request);
        if(response != null){
            return response;
        }
        T element ;
        try{
            DocType docType = request.getDocType() == null? this.getEligibleDocType(): request.getDocType();
            element = getDataElement(request.getSingleElement(), docType);
        } catch (Exception e){
            log.error("Failed to get data object:", e);
            return errResponse("Failed to get data object", e);
        }

        ActionResponse<T> existingResponse = getExisting(element);

        if(!existingResponse.isOk()){
            //Не нашли существующий элемент, обновить не можем.
            return existingResponse;
        }

        try{
            repository.save(element);
        } catch (Exception e){
            return errResponse(String.format("Failed to update %s (id = %d)",
                    getEntityClassName(),
                    element.getId()), e);
        }
        return okResponse();
    }

    @Override
    public void setRepository(JpaRepository<T, Integer> repository) {
        this.repository = repository;
    }

    private ActionResponse<T> getExisting(int elementId){
        T existing;

        try{
            Optional<T> optional = repository.findById(elementId);
            existing = optional.orElse(null);

            if(existing == null){
                return errResponse(
                        getNotExistsInDatabaseMessage(
                                getEntityClassName(), elementId));
            }
        } catch (Exception e){
            return errResponse(String.format(
                    "Failed to retrieve %s from database " +
                            "(id = %d)",
                    getEntityClassName(),
                    elementId), e);
        }

        return okResponse(existing);
    }

    private ActionResponse<T> getExisting(T element){
        return getExisting(element.getId());
    }

    private String getEntityClassName(){
        String result = "element";

        try{
            Class clazz = getEntityClass(this.repository.getClass());

            if(clazz != null){
                result = clazz.getSimpleName();
            }
        } catch (Exception e){
            log.error("Failed to get entity class name: ", e);
        }
        return StringUtils.uncapitalize(result);
    }
}
