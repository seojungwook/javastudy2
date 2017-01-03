package chap1;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main1 {
	public static void main(String[] args) {
		String config ="classpath:applicationContext.xml";
		AbstractApplicationContext ctx = 
				new GenericXmlApplicationContext(config);
		//bean 참조변수가 참조하는 객체 ?: chap1.MessageBeanImpl객체
		//
		//greeting:setGreeting("니하오,")
		//subject :생성자 형태로 주입 :MessageBeanImpl("spring"); 
		//outputter : setOutputter(chap1.FileOuputter);
		MessageBean bean = ctx.getBean("messageBean2",MessageBean.class);
		System.out.println(bean.sayHello("홍길동"));
		//AOP 기본 
		MessageBeanImpl3 bean3 =ctx.getBean("proxy",MessageBeanImpl3.class) ;
		//proxy : 환경설정 !
		bean3.sayHello();
		
	}
}
