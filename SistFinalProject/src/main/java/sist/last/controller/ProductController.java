package sist.last.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

    @GetMapping ("/product/search-main")
    public String searchMainForm(@RequestParam String keyword, Model model){
        model.addAttribute("keyword", keyword);
        return "/product/searchMainPage";
    }
}
