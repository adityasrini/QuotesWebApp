package com.mindfulness.configuration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer {
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		WebApplicationContext context = getContext();

// 		Only activate this when Spring Security or extra dispatcher servlets are added
// 		servletContext.addListener(new ContextLoaderListener(context));
		
		ServletRegistration registration = servletContext
				.addServlet("dispatcher", new DispatcherServlet(context));
		registration.addMapping("/mindfulness/*");
//		registration.setLoadOnStartup(1);
	}
	
	private WebApplicationContext getContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setConfigLocation("com.mindfulness.configuration.WebConfig");
		return context;
	}
	
	
}
