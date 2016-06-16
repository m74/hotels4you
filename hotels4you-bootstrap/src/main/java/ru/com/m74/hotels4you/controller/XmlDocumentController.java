package ru.com.m74.hotels4you.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

/**
 * @author mixam
 * @since 16.06.16 0:26
 */
@Controller
@RequestMapping
public class XmlDocumentController {

    @RequestMapping("/helo")
    public String helo(Model model, HttpServletRequest request) throws Exception {


//        @RequestMapping("/helo")
//public ModelAndView helo(HttpServletRequest request) throws Exception {
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element root = document.createElement("document");

        for (String word : new String[]{"Hello", "Spring", "Framework"}) {
            Element wordNode = document.createElement("word");
            Text textNode = document.createTextNode(word);
            wordNode.appendChild(textNode);
            root.appendChild(wordNode);
        }


        root.appendChild(document.createTextNode("af as df sd f sdf s df sd "));

        document.appendChild(root);

//        String xmlFile = "index.xml";
//        String contextPath = request.getServletContext().getRealPath("");
//        String xmlFilePath = contextPath + File.separator + xmlFile;
//
//        Source source = new StreamSource(new File(xmlFilePath));
        Source source = new DOMSource(document);


//        ModelAndView mvc = new ModelAndView("default");
//        mvc.addObject("xmlSource", source);



        model.addAttribute("xmlSource", source);
//
//        model.addAttribute("wordList", root);
        return "default";
//        return model;
    }
}
