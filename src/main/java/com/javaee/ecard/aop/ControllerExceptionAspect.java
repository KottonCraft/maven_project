package com.javaee.ecard.aop;

import com.javaee.ecard.model.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Component;

//@Aspect
@Component
public class ControllerExceptionAspect {

    /**
     * 切点表达式：匹配com.javaee.ecard.controller下所有类中带有RequestMapping注解的方法
     */
    @Pointcut("within(com.javaee.ecard.controller..*) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void requestMappeingPointcut(){
    }

    /**
     * 切点表达式匹配到的方法将成为连接点（joinPoint）
     * @param joinPoint
     * @return
     * @throws Throwable
     */
//    @Around("requestMappeingPointcut()")
    public Result doResultAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Result.success(joinPoint.proceed());
        }catch (Throwable ex){
            if(ex instanceof BadSqlGrammarException)
                Result.failed(4888);
        }

        return Result.failed(-1);
    }
}
