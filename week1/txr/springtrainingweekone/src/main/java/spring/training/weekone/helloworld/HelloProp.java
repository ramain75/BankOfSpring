package spring.training.weekone.helloworld;

public class HelloProp {
	private String prop;

	public String getProp() {
		return prop;
	}

	public void setProp(String prop) {
		this.prop = prop;
	}
	public void sayHelloProp() {
		System.out.println("Hello "+prop);
	}
}
