package br.com.fiap.financaspro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CateoriaController {

    @RequestMapping(method = RequestMethod.GET, path = "/categoria", produces = "application/json")
    @ResponseBody
    public String index(){
        return """
                [
                    {
                        "id" : 1,
                        "nome" : "Alimentaçaõ",
                        "icone" : "fast-food"
                    },
                    {
                        "id" : 2,
                        "nome" : "Educação",
                        "icone" : "book"
                    }
                ]
                """;
    }
}
