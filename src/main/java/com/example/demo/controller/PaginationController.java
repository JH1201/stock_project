package com.example.demo.controller;

import com.example.demo.domain.CorpInfo;
import com.example.demo.service.PaginationService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PaginationController {

    private final PaginationService paginationService;

    public PaginationController(PaginationService paginationService) {
        this.paginationService = paginationService;
    }

    @GetMapping("/index")
    public String home(@RequestParam(defaultValue = "1")int page, @RequestParam(defaultValue = "20")int size, Model model) {

        Page<CorpInfo> postsPages = paginationService.paging(page, size);



        int blockLimit = 10;
        int totalPageCount = postsPages.getTotalPages();
        int startPage = Math.max(1, (((int) Math.ceil(((double) page / blockLimit))) - 1) * blockLimit + 1);
        int endPage = Math.min(totalPageCount, startPage + blockLimit - 1);


        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("postsPages", postsPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", postsPages.getTotalPages());


        return "index";
    }



    

}
