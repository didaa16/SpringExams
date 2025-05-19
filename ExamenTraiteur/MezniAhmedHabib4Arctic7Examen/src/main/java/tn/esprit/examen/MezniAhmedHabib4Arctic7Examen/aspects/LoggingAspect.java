package tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
@Slf4j
@Component
@Aspect
public class LoggingAspect {
    @Pointcut("execution (* tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.services.IServices.MontantAPayerParClient(..))")
    public void methodCall() {}
    @After("methodCall()")
    public void outOfMethod(JoinPoint joinPoint){
        log.info("Out of Method : "+joinPoint.getSignature().getName() + " : montant facture calculé");
    }
//
//    @Before("methodCall()")
//    public void methodEntry(JoinPoint joinPoint){
//
//        log.info("In Method : "+joinPoint.getSignature().getName());
//    }
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
