package tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
@Slf4j
@Component
@Aspect
public class LoggingAspect {
    @Pointcut("execution (* tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.services.IServices.addPerson(..))")
    public void methodCall() {}
//
//    @Before("methodCall()")
//    public void methodEntry(JoinPoint joinPoint){
//
//        log.info("In Method : "+joinPoint.getSignature().getName());
//    }
    @After("methodCall()")
    public void outOfMethod(JoinPoint joinPoint){
        log.info("Fin méthode : "+joinPoint.getSignature().getName()+ " avec succès ");
    }
//    @AfterReturning("methodCall()")
//    public void logMethodExitReturn(JoinPoint joinPoint) {
//        String name = joinPoint.getSignature().getName();
//        log.info("AfterReturning " + name + " avec succès ! ");
//    }
//
//    @AfterThrowing(pointcut="methodCall()", throwing="nameEx")
//    public void logMethodExitThrowing(JoinPoint joinPoint, Throwable nameEx) {
//        String name = joinPoint.getSignature().getName();
//        log.info("AfterThrowing of method " + name + " : ");
//        log.error(nameEx.getMessage());
//    }
}
