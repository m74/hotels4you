package ru.com.m74.hotels4you.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

/**
 * @author mixam
 * @since 07.01.17 18:44
 */
@Controller
public class AuthenticationController {

    @Autowired
    private ObjectMapper jsonMapper;

    @RequestMapping(value = "/loginza", method = RequestMethod.POST, produces = "application/json")
    public void loginza(@RequestParam String token) throws IOException {
        // h4y.ru
        // String wid = "47519";
        // String skey = "f95aa95e9ed347bf2a80fefab47ed216";

        // test.h4y.ru
        // wid = "47521";
        // skey = "7a1a0ce5ed36f29472b38bd633c9e94b";

        String url = "http://loginza.ru/api/authinfo?token=" + token;
//        String str = IOUtils.toString();
        Map response = jsonMapper.readValue(new URL(url), Map.class);

        response.get("error_type");
        response.get("error_message");

        // String str =
        // Toolkit.readStr(c.getInputStream());
        // "{'uid':'100001186343932','identity':'http://www.facebook.com/profile.php?id=100001186343932','email':'mixam@h4y.ru','dob':'1974-01-01','name':{'first_name':'Максим','last_name':'Смирнов','full_name':'Максим
        // Смирнов'},'gender':'M','provider':'http://www.facebook.com/','photo':'https://graph.facebook.com/100001186343932/picture'}";

    }
}
