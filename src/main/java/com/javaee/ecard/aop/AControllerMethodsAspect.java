package com.javaee.ecard.aop;

import com.javaee.ecard.model.Result;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用来AController的方法拦截实现的切面类
 */
@Aspect
@Component
public class AControllerMethodsAspect {

    /**
     * 定义AController中的algo方法将被拦截
     */
    @Pointcut("execution(StringBuffer com.javaee.ecard.controller.AController.algo(int,int))")
    public void algoPointcut(){ }

    /**
     * 通知类型为Before，即符合“algoPointcut()”切点表达式匹配的方法调用前先执行doBefore()
     */
    @Before("algoPointcut()")
    public void doBefore(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            System.out.println("parameter"+(i+1) + ":" + args[i]);
        }
//        if(args[1].toString().equals("0"))
//            throw new RuntimeException("parameter2 must be not zero!");
        System.out.println("1. AController.algo() will be called.");
    }

    @Around("algoPointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes)(RequestContextHolder.getRequestAttributes());
        //判断远程IP地址访问是否合法
        if(!isInIPWhiteList(attributes.getRequest()))
            return "不合法的IP访问！";

        try {
            //相当于调用了被拦截的方法
//            Object data = joinPoint.proceed();
            return joinPoint.proceed();

        } catch (Throwable e) {
            //出错则返回错误结果码
            return "计算失败";
        }
    }


    private boolean isInIPWhiteList(HttpServletRequest request){
        String clientIp = request.getRemoteAddr();
        System.out.println("clientIp:"+clientIp);
        //从数据库查询clientIp是否在白名单 或从配置文件， 实现略
        //例如：
        String ipcode = clientIp.split("\\.")[3];
        boolean isOk = Integer.parseInt(ipcode) >= 42;
        System.out.println("ipcode:"+ipcode + " isok:" + isOk);
        return isOk;
//        return clientIp.startsWith("10.42.72");// || clientIp.startsWith("0:0:0:0");
    }

    @AfterReturning(value = "algoPointcut()", returning = "result")
    public void doAfterReturning(Object result){
        StringBuffer rs = (StringBuffer)result;
        rs.insert(0, "计算结果信息： ");
        System.out.println("target method is called, the result is："+result);
    }


    @After("algoPointcut()")
    public void doAfter(){
        System.out.println("target method is called finally！");
    }


    /**
     * 通知类型为Before，即符合“algoPointcut()”切点表达式匹配的方法调用前先执行doBefore()
     */
    @AfterThrowing(value = "algoPointcut()",throwing = "ex")
    public void doAfterThrowing(Exception ex){
        System.out.println("error occur: " + ex.getMessage());
    }
}
