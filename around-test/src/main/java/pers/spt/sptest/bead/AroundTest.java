package pers.spt.sptest.bead;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * AroundTest
 *
 * @author: hh
 * @since: 2021/6/6 19:58
 */
@Aspect
@Component
public class AroundTest {
    @Pointcut("execution(* pers.spt.sptest.bead.BeanTest.testSout(..))")
    public void test(){}

    @After("test()")
    private void after() {
        System.out.println(" after");
    }

    @Before("test()")
    private void before() {
        System.out.println(" before");
    }

    @Around("test()")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("Around 方法执行前");
        pjp.proceed();
        System.out.println("Around 方法执行后");
    }


}
