package chap1;





import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import org.springframework.util.StopWatch;

public class LoggingAdvice implements MethodInterceptor {
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		String methodName = invocation.getMethod().getName();
		//invocation.getMethod().getName():
		//�ٽ� �˰���޼���(sayHello())�� �̸��� ����!!!!
		StopWatch sw = new StopWatch();
		sw.start(methodName);
		System.out.println("[log]METHOD:" + methodName + "ȣ��");
		//invocation.proceed(): �ٽɾ˰���(sayHello()) ���� ~
		Object rtnObj = invocation.proceed();
		
		sw.stop();
		System.out.println("[log]METHOD:" + methodName + "���� ");
		System.out.println("[log]ó���ð� :" + sw.getTotalTimeMillis() + "msec");
		return rtnObj;
	}


}
