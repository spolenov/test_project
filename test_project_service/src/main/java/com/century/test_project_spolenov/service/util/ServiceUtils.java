package com.century.test_project_spolenov.service.util;

import com.century.test_project_spolenov.model.DocType;
import com.century.test_project_spolenov.service.request.Request;
import com.century.test_project_spolenov.service.response.ActionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import static com.century.test_project_spolenov.service.response.ActionResponse.errResponse;
import static com.century.test_project_spolenov.service.response.ActionResponse.okResponse;

@Slf4j
public class ServiceUtils {
    private ServiceUtils(){
    }

    private static <T> ActionResponse<T> verifyException(Runnable runnable){
        try{
            runnable.run();
        } catch (Exception e){
            return errResponse("Validation failed", e);
        }

        return okResponse();
    }

    public static<T> ActionResponse<T> verifySingleElement(Request<T> request){
        ActionResponse<T> errResponse = verifyException(request::verifySingleElementData);

        if(!errResponse.isOk()){
            return errResponse;
        }
        return null;
    }

    public static String getNotExistsInDatabaseMessage(String className, int id){
        return String.format(
                "%s with id = %d " +
                        "does not exist in database.",
                StringUtils.capitalize(className), id);
    }

    public static String getDataSizeDescription(List data){
        return String.format("Data size is %s", data == null?
                "<data is null>":
                String.valueOf(data.size()));
    }

    public static <T> Class<T> getEntityClass(Class clazz) {
        Type[] interfaces = clazz.getInterfaces();

        for (Type t : interfaces) {
            if (t instanceof Class<?>) {
                Class<?> testClass = (Class<?>) t;

                if (testClass.getPackage().getName().startsWith("com.century.test_project_spolenov")) {

                    // Repositories should implement only ONE interface from application packages

                    Type genericInterface = testClass.getGenericInterfaces()[0];

                    return (Class<T>) ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
                }
            }
        }

        return null;
    }

    public static void logResponse(ActionResponse response){
        if(response == null){
            log.warn("Response is null!");
            return;
        }
        if(!response.isOk()){
            log.error("{}: {}", response.getState(), response.getDescription());
            return;
        }
        log.info("{}: {}", response.getState(), response.getDescription());
    }

    public static <T> T getDataElement(Object object, DocType docType) throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.century.test_project_spolenov.model." +
                StringUtils.uncapitalize(docType.getName()) + "." +
                StringUtils.capitalize(docType.getName()));

        return (T) new ObjectMapper().convertValue(object, clazz);
    }
}
