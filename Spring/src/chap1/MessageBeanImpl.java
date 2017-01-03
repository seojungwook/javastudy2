package chap1;

import java.io.IOException;

public class MessageBeanImpl implements MessageBean{
 private String greeting;
 private String subject;
 private FileOutputter outputter;
 
 //������
public MessageBeanImpl(String subject) {
	this.subject =subject;	
}
public void setGreeting(String greeting) {
	this.greeting = greeting;
}
public void setOutputter(FileOutputter outputter) {
	this.outputter = outputter;
}
 @Override
 public String sayHello(String name){
	 String message = greeting + name + subject +"�����ϱ�";
	 try{
		 outputter.output(message);
	 }catch (IOException e){
		 e.printStackTrace();
	 }
	 return message;
 }

 
}
