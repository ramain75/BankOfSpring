package spring.training.weekone.helloworld.annotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * spring.training.weekone.hellowworld.HelloProp with annotation as 
 * a (very) gentle introduction to wiring via annotation 
 * 
 * @author thierry
 *
 */
// marks this POJO as a spring component , candidate to be "discovered"
@Component
public class HelloAnnotation {
	@Value("Stranger")
	private String theThing;
	
	protected void setTheThing(String s) {
		this.theThing =s ;
	}
	protected String getTheThing() {
		return this.theThing;
	}
	public void sayTheThing() {
		System.out.println("Hello " + this.theThing);
	}
	
}
