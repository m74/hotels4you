package ru.com.m74.hotels4you;

import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.io.IOException;
import java.util.Properties;


/**
 * @author mixam
 * @since 15.06.16 23:37
 */
public class Starter implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        try {
            Properties props = PropertiesLoaderUtils.loadAllProperties("application.properties");
            props.list(System.out);

            System.getProperties().putAll(props);

        } catch (IOException e) {
            throw new ServletException(e);
        }

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation("ru.com.m74.hotels4you.config");
//        context.setConfigLocation("ru.com.m74.spring.webinitializertest.config.Application");

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
                "dispatcher",
                new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        servletContext
                .addFilter("springSecurityFilterChain", org.springframework.web.filter.DelegatingFilterProxy.class)
                .addMappingForUrlPatterns(null, false, "/*");
//        servletContext.addFilter("charset", CharacterEncodingFilter.class).addMappingForUrlPatterns(null, false, "/*");
    }
}
