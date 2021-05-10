package com.newwing.fenxiao.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.newwing.fenxiao.entities.Config;
import com.newwing.fenxiao.service.IConfigService;

public class InitListener implements ServletContextListener {
	
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	public void contextInitialized(ServletContextEvent arg0) {
		WebApplicationContext rwp = WebApplicationContextUtils
				.getRequiredWebApplicationContext(arg0.getServletContext());

		IConfigService configService = (IConfigService) rwp.getBean("configService");
		Config config = (Config) configService.findById(Config.class, 1);
		arg0.getServletContext().setAttribute("config", config);
	}
	
}