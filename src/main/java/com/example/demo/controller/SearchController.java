package com.example.demo.controller;

import com.example.demo.domain.CorpInfo;
import com.example.demo.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }


    @GetMapping("/searchResult")
    public String showSearch(@RequestParam("corp_name") String query, Model model) {
        List<CorpInfo> searchResult = searchService.findByName(query);
        model.addAttribute("searchResult", searchResult);


        return "searchResult";
    }

}
