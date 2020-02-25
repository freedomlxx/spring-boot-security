package com.lee.security.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


@Aspect
@Component
public class PringArgsAspect {
    /**
     * 日志.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PringArgsAspect.class);

    /**
	 * 定义切面表达式.
	 */
    @Pointcut("execution(public * com.lee.security.controller..*.*(..))")
    public void pointCutExpress(){}
    
    /**
     * 拦截contoller层方法，方法执行前，进行参数校验.
     * @param joinPoint
     * @throws Throwable
     */
    @Before("pointCutExpress()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
    	// 获得切入方法参数
    	Object[] args = joinPoint.getArgs();
    	// 获得切入的方法
        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        
        //把HttpServletRequest和HttpServletResponse排除掉，JSON.toJSONString会报错
        List<Object> paramList = new ArrayList<Object>();
        
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof HttpServletRequest) {               
                continue;
            }
            if (args[i] instanceof HttpServletResponse) {        
                continue;
            }
            paramList.add(args[i]);
        }
        
        LOGGER.info("方法:["+method+"]，参数:["+JSON.toJSONString(paramList)+"]");
    }

//    @Around("pointCutExpress()")
//    public Object logTome(ProceedingJoinPoint pjp) throws Throwable {
//        long begin = System.currentTimeMillis();
//        String method = pjp.getSignature().getName();
//        String className = pjp.getTarget().getClass().getName();
//        // 获得切入方法参数
//        Object[] args = pjp.getArgs();
//        //把HttpServletRequest和HttpServletResponse排除掉，JSON.toJSONString会报错
//        List<Object> paramList = new ArrayList<Object>();
//
//        for (int i = 0; i < args.length; i++) {
//            if (args[i] instanceof HttpServletRequest) {
//                continue;
//            }
//            if (args[i] instanceof HttpServletResponse) {
//                continue;
//            }
//            paramList.add(args[i]);
//        }
//        Object ret = null;
//            ret = pjp.proceed();
//        LOGGER.info("耗时打印:方法:["+method+"]，参数:["+JSON.toJSONString(paramList)+"]，耗时:["
//                + (System.currentTimeMillis() - begin) + "]毫秒");
//        return ret;
//    }
    
}
