package ru.com.m74.xml.transform;

import org.springframework.web.filter.GenericFilterBean;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author mixam
 */
//@Component
@WebFilter(urlPatterns = "/*")
public class TransformFilter extends GenericFilterBean {

    private URIResolver uriResolver;

    private TransformerFactory transformerFactory;

    private ErrorListenerImpl errorListener = new ErrorListenerImpl();

    private DocumentBuilder builder;

    private String excludes = ".*\\.(css|js|html|xsl|ico|png|gif|jpg|jpeg|woff2|woff|ttf)$";

    @Override
    protected void initFilterBean() throws ServletException {
        try {
            uriResolver = new URIResolver(getServletContext());
            transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setURIResolver(uriResolver);
            transformerFactory.setErrorListener(errorListener);
            builder = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        if (httpServletRequest.getServletPath().matches(excludes)) {
            chain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            BufferedResponse bufferedResponse = new BufferedResponse(httpServletResponse);
            chain.doFilter(httpServletRequest, bufferedResponse);

            if (bufferedResponse.isEmpty()) {
                return;
            }

            Pattern pattern = Pattern.compile("<\\?xml-stylesheet.*\\?>", Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(bufferedResponse.getContentAsString("UTF-8"));

            String stylesheet = null;
            while (matcher.find()) {
                stylesheet = matcher.group(0).replaceAll("(^.*href=)|\"|\\?>", "");
            }

            try {
                Document document = builder.parse(
                        new ByteArrayInputStream(bufferedResponse.getContent()),
                        httpServletRequest.getRequestURI());

                httpServletResponse.setHeader("ETag", null);
                httpServletResponse.setHeader("Last-Modified", null);
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.setContentLength(-1);
                if (httpServletRequest.getParameter("xml") != null || stylesheet == null) {
                    httpServletResponse.setContentType("text/plain");

                    Transformer transformer = transformerFactory.newTransformer();
                    Source source = new DOMSource(document);
                    StreamResult result = new StreamResult(httpServletResponse.getOutputStream());
                    transformer.transform(source, result);
                } else {
                    httpServletResponse.setContentType("text/html");

                    Source xslt = uriResolver.resolve(stylesheet, null);
                    Transformer transformer = transformerFactory.newTransformer(xslt);
                    // проверка на предмет ошибок
                    TransformerException errors[] = errorListener.flash();
                    if (errors.length > 0) {
                        throw errors[0];
                    }

                    Source source = new DOMSource(document);
                    StreamResult result = new StreamResult(httpServletResponse.getOutputStream());
                    transformer.transform(source, result);
                }

            } catch (TransformerException | SAXException e) {
                throw new ServletException(e);
            }
        }


        //    /**
//     * Добавление в документ элементов контекста сервлета.
//     *
//     * @param root
//     * @param request
//     */
//    public static void processDocument(Element root, HttpServletRequest request) {
//        root.addAttribute("contextPath", request.getContextPath());
//
//        HttpSession session = request.getSession();
//
//        // Session attributes
//        Element el = root.addElement("session");
//        Enumeration<String> en = session.getAttributeNames();
//        while (en.hasMoreElements()) {
//            String name = en.nextElement();
//            DOM.addTextElement(el, name, session.getAttribute(name));
//        }
//
//    }
//
//    protected static String getDocumentURI(HttpServletRequest request) {
//        String uri = request.getServletPath();
//        try {
//            return new String(uri.getBytes("iso-8859-1"), "utf-8");
//        } catch (UnsupportedEncodingException e) {
//            return uri;
//        }
//    }
//
//    public static void writeDocument(TransformerFactory factory, Document doc, HttpServletRequest request,
//                                     HttpServletResponse response) throws IOException, ServletException {
//
//        if (doc == null) {
//            Logger.debug(TransformFilter.class, "skip content");
//            return;
//        }
//
//        // String uri = request.getRequestURI();
//        String uri = request.getServletPath();
//        // String uri = request.getPathInfo();
//        int i = uri.lastIndexOf('/');
//        if (i != -1) {
//            uri = uri.substring(0, i) + '/';
//        }
//
//        if (doc.getName() == null)
//            doc.setName(getDocumentURI(request));
//        Logger.debug(TransformFilter.class, "document name: ", doc.getName());
//
//        Element root = doc.getRootElement();
//        root.addAttribute("uri", uri);
//
//        Element path = root.addElement("path");
//
//        String arr[] = uri.split("\\/");
//        uri = "";
//        if (arr.length == 0) {
//            Element elem = path.addElement("elem");
//            elem.addAttribute("href", "/");
//        } else {
//            for (String id : arr) {
//                Element elem = path.addElement("elem");
//                uri += id + "/";
//                // elem.addAttribute("id", id);
//                elem.addAttribute("href", uri);
//            }
//        }
//        processDocument(doc.getRootElement(), request);
//
//        response.setCharacterEncoding(doc.getXMLEncoding());
//        response.setContentLength(-1);
//
//        ResponseResult result = new ResponseResult(request, httpServletResponse);
//        try {
//            result.transform(factory, new DocumentSource(doc));
//        } catch (TransformerException e) {
//            throw new ServletException(e);
//        }
//
//    }
//
    }
}
