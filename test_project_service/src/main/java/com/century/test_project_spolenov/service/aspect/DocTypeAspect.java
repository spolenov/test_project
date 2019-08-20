package com.century.test_project_spolenov.service.aspect;

import com.century.test_project_spolenov.service.common.BaseService;
import com.century.test_project_spolenov.service.request.Request;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class DocTypeAspect {
    @Around(value = "execution(" +
            "* " +
            "com.century.test_project_spolenov.service..*(" +
            "com.century.test_project_spolenov.service.request.Request+))")
    public Object setDocType(ProceedingJoinPoint joinPoint) throws Throwable {
        Object ret ;
        setArgDocType(joinPoint);

        ret = joinPoint.proceed(joinPoint.getArgs());
        return ret;
    }

    private void setArgDocType(ProceedingJoinPoint joinPoint){
        try {
            Object callerService = joinPoint.getThis();

            Request request = (Request)joinPoint.getArgs()[0];
            request.setDocType(((BaseService)callerService).getEligibleDocType());

        } catch (Exception e) {
            log.debug("Failed to set docType:", e);
        }
    }
}
