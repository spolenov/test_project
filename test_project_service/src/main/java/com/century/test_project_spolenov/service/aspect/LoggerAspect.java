package com.century.test_project_spolenov.service.aspect;

import com.century.test_project_spolenov.service.response.ActionResponse;
import com.century.test_project_spolenov.service.util.ServiceUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggerAspect {
    @Around(value = "execution(" +
            "com.century.test_project_spolenov.service.response.ActionResponse+ " +
            "com.century.test_project_spolenov.service..*(..))")
    public Object logAction(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Object ret = null;

        ret = joinPoint.proceed(args);
        ServiceUtils.logResponse((ActionResponse) ret);
        return ret;
    }
}
