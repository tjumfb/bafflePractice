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
    public static Logger logger = Logger.getLogger("baffleLog");
    @Value("#{configProperties['annotationMock.outpath']}")
    String path;
    @Value("#{configProperties['annotationMock.power']}")
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
        System.out.println("before");
    }
    @Around("selectAll()&&@annotation(baffle)")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint, Baffle baffle) throws Throwable {
        joinPoint.proceed();
        if(baffle.value()&&tag) {
            String fileName = ProcessFile.getFileName(joinPoint.getSignature().toString(), joinPoint.getArgs());
            File file = new File(path+fileName);
            if (file.exists()) {
                logger.info("挡板发动");
//                joinPoint.proceed();
                return ProcessFile.getObjectByObjectInput(file);
            }else {
                ProcessFile.createNewFile(path,joinPoint.getSignature().toString(),joinPoint.getArgs());
                Object o = joinPoint.proceed();
                ProcessFile.saveObjectByObjectOutput(o,file);
                return o;
            }
        }else {
            return joinPoint.proceed();
        }
    }

    @AfterReturning(pointcut = "selectAll()")
    public void afterReturningAdvice(JoinPoint joinPoint){
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