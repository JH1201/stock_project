package com.example.demo.controller;

import com.example.demo.domain.CorpInfo;
import com.example.demo.domain.CorpOverview;
import com.example.demo.service.CorpOverviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class OverviewController {

    CorpOverviewService corpOverviewService;

    public OverviewController(CorpOverviewService corpOverviewService) {
        this.corpOverviewService = corpOverviewService;
    }

    @GetMapping("/corpOverview")
    public String getCorpOverview(@RequestParam String corpCode, Model model) {
        try {
            List<CorpOverview> corpOverview = corpOverviewService.getCorpOverview(corpCode);
            model.addAttribute("corpOverview", corpOverview);
            return "corpOverview"; // corpOverview.html로 이동
        } catch (Exception e) {
            // 에러 발생 시 처리
            e.printStackTrace();
            return "error"; // 에러 페이지로 이동
        }
    }


}
