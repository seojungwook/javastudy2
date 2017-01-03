package aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

//pojoŬ����  ������ AOPŬ���� 
public class LoggingAspect {
	// around ������� ȣ���
	public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("��Ͻ���");
		StopWatch sw = new StopWatch();
		sw.start();
		//joinPoint.proceed() : �ٽ� �˰��� ȣ�� !!!
		Object retVal = joinPoint.proceed();
		sw.stop();
		System.out.println("�޼��� ���� �ð� :" + sw.getTotalTimeMillis());
		return retVal;
	}
}
