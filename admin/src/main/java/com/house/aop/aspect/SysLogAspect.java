package com.house.aop.aspect;

import com.alibaba.fastjson.JSON;
import com.house.aop.annotation.MyLog;
import com.house.constant.Constant;
import com.house.dao.SysLogDao;
import com.house.pojo.SysLog;
import com.house.utils.IdWorker;
import com.house.utils.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * AOP切面
 */
@Aspect
@Component
public class SysLogAspect {

    private static Logger logger = LoggerFactory.getLogger(SysLogAspect.class);

    @Autowired
    private IdWorker idWorker;
    @Resource
    private SysLogDao sysLogDao;


    /**
     * 配置切入点（配置为自定义注解）
     */
    @Pointcut("@annotation(com.house.aop.annotation.MyLog)")
    public void logPointCut(){};

    /**
     * 环绕通知
     */
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 开始时间
        long startTime = System.currentTimeMillis();
        //执行方法
        Object proceed = joinPoint.proceed();
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - startTime;

        // 保存日志
        try {
            saveLog(joinPoint,time);
        } catch (Exception e) {
            logger.error("【记录日志】，{}",e);
        }
        return proceed;
    }

    /**
     * 日志入库
     * @param joinPoint
     * @param time
     */
    private void saveLog(ProceedingJoinPoint joinPoint,long time) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        String className = joinPoint.getTarget().getClass().getName();// 获取控制器，包含报包名
        String methodName = signature.getName();// 获取方法名
        Object[] args = joinPoint.getArgs();// 获取参数

        // 打印该方法耗时时间
        logger.info("请求{}.{}耗时{}毫秒",className,methodName,time);

        SysLog sysLog = new SysLog();
        sysLog.setId(String.valueOf(idWorker.nextId()));
        sysLog.setMethod(className + "." + methodName);
        try {// 可能会出现转换错误
            sysLog.setParams(args.length != 0 ?JSON.toJSONString(args) : "");
        } catch (Exception e) {}
        // 获取自定义注解的字段值
        MyLog myLog = signature.getMethod().getAnnotation(MyLog.class);
        if (myLog != null){
            // 注解上的描述
            sysLog.setOperation(myLog.title() + "-" + myLog.action());
        }
        // 获取 ip
        String ip = request.getRemoteAddr();
        sysLog.setIp(ip);

        logger.info("【Ip】{}，【接口地址】{}，【请求方式】{}，【入参】：{}",sysLog.getIp(),request.getRequestURL(),request.getMethod(),sysLog.getParams());
        String token = request.getHeader(Constant.ACCESS_TOKEN);
        Claims claims = JwtTokenUtil.getInstance().parseToken(token);
        String userId=null;
        String username=null;
        if (claims != null){
            userId = claims.getSubject();
            username = (String) claims.get(Constant.JWT_USER_NAME);
        }
        sysLog.setUserId(userId);
        sysLog.setUsername(username);
        sysLog.setTime((int) time);
        sysLog.setCreateTime(new Date());

        logger.info("【日志数据】，{}",sysLog.toString());
        sysLogDao.insertLogInfo(sysLog);
    }
}
