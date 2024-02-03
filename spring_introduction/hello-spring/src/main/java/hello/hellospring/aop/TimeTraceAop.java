package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect // 직접 자바 코드로 등록하지 않고 컴포넌트 스캔 기능을 사용하면 SpringConfig 에서 순환 참조가 발생하지 않는다.
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..)) && !target(hello.hellospring.SpringConfig)") // SpringConfig 는 제외해야 함! 빈 객체를 반환하는 메서드는 aop 를 필요로 하고, aop 빈 객체는 일단 만들어져야 하고 하면서 순환 참조 문제 발생!!
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }

    }
}
