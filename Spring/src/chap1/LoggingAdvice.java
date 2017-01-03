package chap1;





import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import org.springframework.util.StopWatch;

public class LoggingAdvice implements MethodInterceptor {
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		String methodName = invocation.getMethod().getName();
		//invocation.getMethod().getName():
		//핵심 알고리즘메서드(sayHello())의 이름을 리턴!!!!
		StopWatch sw = new StopWatch();
		sw.start(methodName);
		System.out.println("[log]METHOD:" + methodName + "호출");
		//invocation.proceed(): 핵심알고리즘(sayHello()) 실행 ~
		Object rtnObj = invocation.proceed();
		
		sw.stop();
		System.out.println("[log]METHOD:" + methodName + "종료 ");
		System.out.println("[log]처리시간 :" + sw.getTotalTimeMillis() + "msec");
		return rtnObj;
	}


}
