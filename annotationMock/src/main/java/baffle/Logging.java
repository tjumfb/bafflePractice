package baffle;

import fileUtil.ProcessFile;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.logging.Logger;

@Aspect
@Component
@Slf4j
public class Logging {
    Logger logger = Logger.getLogger("baffleLog");
    @Value("#{configProperties['power']}")
    boolean tag;
    /** Following is the definition for a pointcut to select
     *  all the methods available. So advice will be called
     *  for all the methods.
     */
    @Pointcut("@annotation(Baffle)")
    private void selectAll(){}
    /**
     * This is the method which I would like to execute
     * before a selected method execution.
     */
    @Before("selectAll()")
    public void beforeAdvice(JoinPoint joinPoint){
    }
    @Around("selectAll()&&@annotation(baffle)")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint, Baffle baffle) throws Throwable {
        if(baffle.value()&&tag) {
            String fileName = ProcessFile.getFileName(joinPoint.getSignature().toString(), joinPoint.getArgs());
            File file = new File(fileName);
            if (file.exists()) {
                logger.info("挡板发动");
                return ProcessFile.getObjectByObjectInput(file);
            } else {
                ProcessFile.createNewFile(joinPoint.getSignature().toString(), joinPoint.getArgs());
                ProcessFile.saveObjectByObjectOutput(joinPoint.proceed(), file);
                logger.info("新结果已保存");
            }
        }
        return joinPoint.proceed();
    }
    /**
     * This is the method which I would like to execute
     * after a selected method execution.
     */
    @After("selectAll()")
    public void afterAdvice(){
    }
    /**
     * This is the method which I would like to execute
     * when any method returns.
     */
    @AfterReturning(pointcut = "selectAll()", returning="retVal")
    public void afterReturningAdvice(JoinPoint joinPoint,Object retVal){
    }
    /**
     * This is the method which I would like to execute
     * if there is an exception raised by any method.
     */
    @AfterThrowing(pointcut = "selectAll()", throwing = "ex")
    public void AfterThrowingAdvice(IllegalArgumentException ex){
        logger.info("There has been an exception: " + ex.toString());
    }
}