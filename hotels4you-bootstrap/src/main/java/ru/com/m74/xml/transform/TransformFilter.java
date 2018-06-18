package ru.com.m74.xml.transform;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mixam
 */
@WebFilter(urlPatterns = "/*")
public class TransformFilter extends GenericFilterBean {

    private final FilterHelper helper = new FilterHelper();

    private String excludes = ".*\\.(css|js|html|xsl|ico|png|gif|jpg|jpeg|woff2|woff|ttf)$";

    @Override
    protected void initFilterBean() throws ServletException {
        helper.init(getServletContext());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        if (httpServletRequest.getServletPath().matches(excludes)) {
            chain.doFilter(request, response);
        } else {
            helper.transform(httpServletRequest, (HttpServletResponse) response, chain);
        }
    }
}
