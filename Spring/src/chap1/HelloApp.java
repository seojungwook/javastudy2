package chap1;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp {
	public static void main(String[] args) {
		//classpath: src가 기준이 된다 .
		// /src/applicationContext.xml
	String config= "classpath:applicationContext.xml";//xml을 위치
	/*
	 * 컨테이너 : 사용할 객체를 객체를 저장하는 객체 
	 * beanFactory - 기본 컨테이너 (객체만 가지고있는다 .)4.0이후 Deprecated(쓰지말길 ....)
	 *                AOP 기능를 사용할수없음 DI만 사용가능 하다 . 객체 사용시 객채를 생성한다 . 
	 * ApplicationContext - buildFactory 의 하위인터 페이스(buildFactory기능 에 +@) 
	 *                       AOP기능 사용가능  객체를 미리 생성해서 메모리에 대기 시킴 .
	 * WebApplicationContext- ApplicationContext의 하위 인터페이스 
	 *                         web 환경에서 사용가능한 컨테이너 !!!!!
	 */
	AbstractApplicationContext ctx =
			new GenericXmlApplicationContext(config);
	MessageBean bean = 
			(MessageBean)ctx.getBean("messageBean");//컨테이너의 라벨~
	System.out.println(bean.sayHello("홍길동"));
	//
	Project proj = 
			ctx.getBean("Project",Project.class);
	proj.build();

	}

}
