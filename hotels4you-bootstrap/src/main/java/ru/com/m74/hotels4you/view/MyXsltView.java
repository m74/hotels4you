package ru.com.m74.hotels4you.view;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.web.servlet.view.AbstractView;
import ru.com.m74.hotels4you.dto.HtmlDocument;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.util.Map;

/**
 * @author mixam
 * @since 05.01.17 23:18
 */
public class MyXsltView extends AbstractView {

    private URIResolver uriResolver;
    private TransformerFactory transformerFactory;
    private XmlMapper xmlMapper;

    public MyXsltView() {
        setContentType("text/html");
    }

    public URIResolver getUriResolver() {
        return uriResolver;
    }

    public void setUriResolver(URIResolver uriResolver) {
        this.uriResolver = uriResolver;
    }

    public TransformerFactory getTransformerFactory() {
        return transformerFactory;
    }

    public void setTransformerFactory(TransformerFactory transformerFactory) {
        this.transformerFactory = transformerFactory;
    }

    public XmlMapper getXmlMapper() {
        return xmlMapper;
    }

    public void setXmlMapper(XmlMapper xmlMapper) {
        this.xmlMapper = xmlMapper;
    }


//    private Object findValue(Map<String, Object> map) {
//        Object value = null;
//        Iterator iterator = map.entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry entry = (Map.Entry) iterator.next();
//            if (!(entry.getValue() instanceof BindingResult) && !((String) entry.getKey()).equals(JsonView.class.getName())) {
//                if (value != null) {
//                    throw new IllegalStateException("Model contains more than one object to render, only one is supported");
//                }
//
//                value = entry.getValue();
//            }
//        }
//
//        return value;
//    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        HtmlDocument document = (HtmlDocument) map.get("htmlDocument");

        if (httpServletRequest.getParameter("xml") != null || !(document instanceof HtmlDocument)) {
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/xml");

            httpServletResponse.getWriter().println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            xmlMapper.writeValue(httpServletResponse.getWriter(), document);
        } else {
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("text/html");

            Source xslt = uriResolver.resolve(document.getStylesheet(), null);
            Transformer transformer = transformerFactory.newTransformer(xslt);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            xmlMapper.writeValue(out, document);
            Source source = new StreamSource(new ByteArrayInputStream(out.toByteArray()));

            StreamResult result = new StreamResult(httpServletResponse.getOutputStream());
            transformer.transform(source, result);
        }
    }
}
