package com.bankofspring.hello;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


/**
 * @author slc
 *
 */
@Service("annotationSimpleHelloImpl")
public class SimpleHelloImpl implements HelloService, InitializingBean {

	@Autowired
	private String article;
	
	/**
   * {@inheritDoc}
   */
  @Override
  public String sayHello() {
  	return "Hello " + article;
  }

  /**
   * @return the article
   */
  public String getArticle() {
  	return article;
  }

	
  /**
   * @param article the article to set
   */
  public void setArticle( String article ) {
  	this.article = article;
  }

	/**
   *
   */
  @Override
  public void afterPropertiesSet() throws Exception {
  	Assert.notNull( article, "An article is required" );
  }
	
}
