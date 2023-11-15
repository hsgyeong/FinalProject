package sist.last.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    @GetMapping ("/product/search-main")
    public String searchMainForm(){

        return "/product/searchMainPage";
    }
}
