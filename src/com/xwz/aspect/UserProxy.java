package com.xwz.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Service;

// 增强类
@Service //扫描
@Aspect //生成代理对象
public class UserProxy {

  // 这个是前置通知,被增强类的add方法
  @Before(value = "execution(* com.xwz.aspect.UserAspect.add(..))")
  public void before() {
    System.out.println("在add之前执行了...");
  }
  @After(value = "execution(* com.xwz.aspect.UserAspect.add(..))")
  public void after() {
    System.out.println("在add之后执行了...");
  }
  @AfterReturning(value = "execution(* com.xwz.aspect.UserAspect.add(..))")
  public void afterReturning() {
    System.out.println("afterReturning执行了...");
  }
  // 异常通知
  @AfterThrowing(value = "execution(* com.xwz.aspect.UserAspect.add(..))")
  public void afterThrowing() {
    System.out.println("afterThrowing执行了...");
  }
  // 环绕通知
  @Around(value = "execution(* com.xwz.aspect.UserAspect.add(..))")
  public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    System.out.println("Around在add之前执行了...");
    proceedingJoinPoint.proceed();
    System.out.println("Around在add之后执行了...");
  }

}
