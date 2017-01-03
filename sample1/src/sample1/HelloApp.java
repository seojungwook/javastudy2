package sample1;

public class HelloApp {

	public static void main(String[] args) {
		MessageBean bean = new MessageBean();
		System.out.println(bean.sayHello("±è±æµ¿"));
        MessageBeanEn bean2 = new MessageBeanEn();
        System.out.println(bean2.sayHello("kildong hong"));
	}

}
