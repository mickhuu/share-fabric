package cn.edu.gxmzu.ai.mickhu.aop;

import cn.edu.gxmzu.ai.mickhu.bean.DBContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
/**
 * @author mickhu
 * @describe
 * @date 2022-04-09 0:12
 */
@Aspect
@Component
public class DataSourceAop {

    @Pointcut("!@annotation(cn.edu.gxmzu.ai.mickhu.annotation.Master) " +
            "&& (execution(* cn.edu.gxmzu.ai.mickhu.service..*.select*(..)) " +
            "|| execution(* cn.edu.gxmzu.ai.mickhu.service..*.get*(..)))")
    public void readPointcut() {

    }

    @Pointcut("@annotation(cn.edu.gxmzu.ai.mickhu.annotation.Master) " +
            "|| execution(* cn.edu.gxmzu.ai.mickhu.service..*.insert*(..)) " +
            "|| execution(* cn.edu.gxmzu.ai.mickhu.service..*.add*(..)) " +
            "|| execution(* cn.edu.gxmzu.ai.mickhu.service..*.update*(..)) " +
            "|| execution(* cn.edu.gxmzu.ai.mickhu.service..*.edit*(..)) " +
            "|| execution(* cn.edu.gxmzu.ai.mickhu.service..*.delete*(..)) " +
            "|| execution(* cn.edu.gxmzu.ai.mickhu.service..*.remove*(..))")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.master();
    }


    /**
     * 另一种写法：if...else...  判断哪些需要读从数据库，其余的走主数据库
     */
//    @Before("execution(* cn.edu.gxmzu.ai.mickhu.service.impl.*.*(..))")
//    public void before(JoinPoint jp) {
//        String methodName = jp.getSignature().getName();
//
//        if (StringUtils.startsWithAny(methodName, "get", "select", "find")) {
//            DBContextHolder.slave();
//        }else {
//            DBContextHolder.master();
//        }
//    }
}