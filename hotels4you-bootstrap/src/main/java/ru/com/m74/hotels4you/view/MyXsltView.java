package ru.com.m74.hotels4you.view;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.web.servlet.view.AbstractView;
import ru.com.m74.hotels4you.dto.HtmlDocument;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author mixam
 * @since 05.01.17 23:18
 */
public class MyXsltView extends AbstractView {

    private XmlMapper xmlMapper;

    public MyXsltView(XmlMapper xmlMapper) {
        this.xmlMapper = xmlMapper;
        setContentType("text/html");
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        HtmlDocument document = (HtmlDocument) map.get("htmlDocument");

        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/xml");

        httpServletResponse.getWriter().println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        httpServletResponse.getWriter().println("<?xml-stylesheet type=\"text/xsl\" href=\"" + document.getStylesheet() + "\"?>");
        xmlMapper.writeValue(httpServletResponse.getWriter(), document);
    }
}
