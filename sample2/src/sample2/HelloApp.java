package sample2;

public class HelloApp {

	public static void main(String[] args) {
		MessageBean bean = new MessagBeanKr();
		System.out.println(bean.sayHello("ȫ�浿"));
		bean = new MessageBeanEn();
		System.out.println(bean.sayHello("killdong hong"));
	}

}
