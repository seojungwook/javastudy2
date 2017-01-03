package aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

//pojo클래스  형식의 AOP클래스 
public class LoggingAspect {
	// around 방식으로 호출됨
	public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("기록시작");
		StopWatch sw = new StopWatch();
		sw.start();
		//joinPoint.proceed() : 핵심 알고리즘 호출 !!!
		Object retVal = joinPoint.proceed();
		sw.stop();
		System.out.println("메서드 실행 시간 :" + sw.getTotalTimeMillis());
		return retVal;
	}
}
