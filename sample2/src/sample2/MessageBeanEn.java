package sample2;

public class MessageBeanEn implements MessageBean {

	@Override
	public String sayHello(String name) {
	
		return "Hello," + name;
	}

}
