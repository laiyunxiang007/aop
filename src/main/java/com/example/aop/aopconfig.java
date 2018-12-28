package com.example.aop;

import net.sf.json.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class aopconfig {
    private Logger logger = LoggerFactory.getLogger(aopconfig.class);
    private static final int DEFAULT_TIME_LIMIT = 3000;
    private static final String MSG = "--请求--\n --方法：{}\n --描述：{}\n --位置：{}\n --参数：{}\n --返回：{}\n --耗时：{} ms";

    @Pointcut("execution(* com.example.aop.serivce.*.*(..))")
    public void loginLog(){

    }
    // around 切面强化
    @Around("loginLog()")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
// 记录下请求内容
        logger.info("URL :======= " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : ============" + request.getMethod());
        logger.info("IP : ========" + request.getRemoteAddr());
        logger.info("CLASS_METHOD :=========== " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS :=========== " + joinPoint.getArgs());
        try {
            Object o = joinPoint.proceed();
            logger.info("方法环绕proceed，结果是 :======" + o);
            return o;
        } catch (Throwable e) {
            logger.error(e.toString());
            return null;
        } finally {
            MDC.clear();
        }

    }
    // around 切面强化
//    @Around("executePointCut()")
//    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
//        Object[] args = joinPoint.getArgs();
//        if (logger.isDebugEnabled() || logger.isWarnEnabled()) {
//            StopWatch clock = new StopWatch();
//            clock.start();
//            Object retrunobj = null;
//            try {
//                // 注意和finally中的执行顺序 finally是在return中的计算结束返回前执行
//                return retrunobj = joinPoint.proceed(args);
//            } catch (Exception e) {
//                throw e;
//            } finally {
//                clock.stop();
//                long totalTime = clock.getTotalTimeMillis();
//                // 打印日志
//                handleLog(joinPoint, args, retrunobj, totalTime);
//            }
//        } else {
//            return joinPoint.proceed(args);
//        }
//
//
//    }

}
