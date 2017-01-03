package chap1;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp {
	public static void main(String[] args) {
		//classpath: src�� ������ �ȴ� .
		// /src/applicationContext.xml
	String config= "classpath:applicationContext.xml";//xml�� ��ġ
	/*
	 * �����̳� : ����� ��ü�� ��ü�� �����ϴ� ��ü 
	 * beanFactory - �⺻ �����̳� (��ü�� �������ִ´� .)4.0���� Deprecated(�������� ....)
	 *                AOP ��ɸ� ����Ҽ����� DI�� ��밡�� �ϴ� . ��ü ���� ��ä�� �����Ѵ� . 
	 * ApplicationContext - buildFactory �� �������� ���̽�(buildFactory��� �� +@) 
	 *                       AOP��� ��밡��  ��ü�� �̸� �����ؼ� �޸𸮿� ��� ��Ŵ .
	 * WebApplicationContext- ApplicationContext�� ���� �������̽� 
	 *                         web ȯ�濡�� ��밡���� �����̳� !!!!!
	 */
	AbstractApplicationContext ctx =
			new GenericXmlApplicationContext(config);
	MessageBean bean = 
			(MessageBean)ctx.getBean("messageBean");//�����̳��� ��~
	System.out.println(bean.sayHello("ȫ�浿"));
	//
	Project proj = 
			ctx.getBean("Project",Project.class);
	proj.build();

	}

}
