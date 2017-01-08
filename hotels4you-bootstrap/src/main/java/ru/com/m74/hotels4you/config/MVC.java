package ru.com.m74.hotels4you.config;

import com.ctc.wstx.stax.WstxInputFactory;
import com.ctc.wstx.stax.WstxOutputFactory;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MappingJackson2XmlView;
import ru.com.m74.hotels4you.view.MyXsltView;

/**
 * @author mixam
 * @since 15.06.16 23:58
 */
@Configuration
@ComponentScan({"ru.com.m74.hotels4you"})
@EnableWebMvc
public class MVC extends WebMvcConfigurerAdapter {

    @Autowired
    WebApplicationContext context;

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public ObjectMapper jsonMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper;
    }

    @Override
    public void configureMessageConverters(java.util.List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter(jsonMapper()));
        super.configureMessageConverters(converters);
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        super.configureViewResolvers(registry);

        MappingJackson2JsonView jsonView = new MappingJackson2JsonView(jsonMapper());
        jsonView.setPrettyPrint(true);

        MappingJackson2XmlView xmlView = new MappingJackson2XmlView(xmlMapper());
        xmlView.setPrettyPrint(true);

        registry.enableContentNegotiation(
                new MyXsltView(xmlMapper()),
                jsonView,
                xmlView
        );
    }

    @Bean
    public XmlMapper xmlMapper() {
        XmlFactory factory = new XmlFactory(new WstxInputFactory(), new WstxOutputFactory());
        XmlMapper mapper = new XmlMapper(factory);
        mapper.registerModule(new JaxbAnnotationModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper;
    }

//    @Bean
//    public TransformFilter transformFilter() {
//        return new TransformFilter();
//    }

//    @Bean
//    public XsltViewResolver xsltViewResolver() {
//        XsltViewResolver viewResolver = new XsltViewResolver();
//        viewResolver.setPrefix("/xsl/");
//        viewResolver.setSuffix(".xsl");
//        viewResolver.setOrder(1);
//        viewResolver.setSourceKey("xmlSource");
//        viewResolver.setViewClass(XsltView.class);
//        viewResolver.setUriResolver(getUriResolver());
//        return viewResolver;
//    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}
