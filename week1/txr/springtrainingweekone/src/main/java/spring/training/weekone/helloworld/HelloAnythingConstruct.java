package spring.training.weekone.helloworld;

/*
 * a basic Hello World type POJO, we pass what to say via the constructor
 */
public class HelloAnythingConstruct {
	private String whatToSay;
	public  HelloAnythingConstruct(String whatToSay) {
		this.whatToSay = whatToSay;
	}
	public void sayAnything() {
		System.out.println("Hello "+whatToSay);
	}
}
