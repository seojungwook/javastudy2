package aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainForAop {

	public static void main(String[] args) {
		String[] config=
			{"aop/aop.xml","aop/applicationContext.xml"};
		ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
		WriteImpl write = ctx.getBean("write",WriteImpl.class);
		write.write();

	}

}
