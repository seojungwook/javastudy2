package chap1;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main1 {
	public static void main(String[] args) {
		String config ="classpath:applicationContext.xml";
		AbstractApplicationContext ctx = 
				new GenericXmlApplicationContext(config);
		//bean ���������� �����ϴ� ��ü ?: chap1.MessageBeanImpl��ü
		//
		//greeting:setGreeting("���Ͽ�,")
		//subject :������ ���·� ���� :MessageBeanImpl("spring"); 
		//outputter : setOutputter(chap1.FileOuputter);
		MessageBean bean = ctx.getBean("messageBean2",MessageBean.class);
		System.out.println(bean.sayHello("ȫ�浿"));
		//AOP �⺻ 
		MessageBeanImpl3 bean3 =ctx.getBean("proxy",MessageBeanImpl3.class) ;
		//proxy : ȯ�漳�� !
		bean3.sayHello();
		
	}
}
