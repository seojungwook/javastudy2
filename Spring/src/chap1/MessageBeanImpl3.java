package chap1;

public class MessageBeanImpl3 {
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public void sayHello() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		System.out.println("오하이오," + name);
	}
}
