package com.sam.rabbitmq.webapp.initializer;

import javax.servlet.ServletContext;

import javax.servlet.ServletException;

import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import org.springframework.web.servlet.DispatcherServlet;

public class WebServletConfiguration implements WebApplicationInitializer{
	
	public WebServletConfiguration() {
		System.out.println("WebServletConfiguration ----");
	}
	
	public void onStartup(ServletContext ctx) throws ServletException {

        AnnotationConfigWebApplicationContext webCtx = new AnnotationConfigWebApplicationContext();

        webCtx.register(WebAppInitializer.class);

        webCtx.setServletContext(ctx);

        ServletRegistration.Dynamic servlet = ctx.addServlet("dispatcher", new DispatcherServlet(webCtx));

        servlet.setLoadOnStartup(1);
        servlet.addMapping("/samChatt/*");
        System.out.println("in dispatcher servlet");
    }

}