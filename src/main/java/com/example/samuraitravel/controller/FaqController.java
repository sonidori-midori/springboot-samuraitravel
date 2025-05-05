package com.example.samuraitravel.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.samuraitravel.service.FaqService;

@Controller
public class FaqController {

    private final FaqService faqService;

    // コンストラクタインジェクションでサービスを注入
    public FaqController(FaqService faqService) {
        this.faqService = faqService;
    }

    @GetMapping("/faqs")
    public String index(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "page", defaultValue = "0") int page,
            Model model) {

        Pageable pageable = PageRequest.of(page, 5); // 1ページ5件ずつ
        Page<?> faqs;

        if (keyword != null && !keyword.isEmpty()) {
            faqs = faqService.findAllFaqs(keyword, pageable);
            model.addAttribute("keyword", keyword);
        } else {
            faqs = faqService.getAllFaqs(pageable);
        }

        // データをビューに渡す
        model.addAttribute("faqs", faqs);

        // 表示するビュー（src/main/resources/templates/user/faq.html）
        return "user/faq";
    }
}
