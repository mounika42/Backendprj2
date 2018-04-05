package com.niit.configuration;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{


    public WebAppInitializer(){
    	System.out.println("WebAppInitializer");
    }
    

	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{DBConfiguration.class};
	}

	
	protected Class<?>[] getServletConfigClasses() {
		return new Class[]{WebAppConfig.class};	}

	
	protected String[] getServletMappings() {
		return new String[]{"/"};
		}
	
	

}
