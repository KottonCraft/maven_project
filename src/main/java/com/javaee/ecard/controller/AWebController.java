package com.javaee.ecard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AWebController {

    @RequestMapping("/a")
    public String index(Model model){
        model.addAttribute("description",
                "火把，特写镜头，油，火把燃烧，火焰舞动，森林，超高清分辨率，写实风格，暖色调，火光照亮，粗糙木棒");

        List<String> list = new ArrayList<String>();
        list.add("row1");
        list.add("row2");
        list.add("row3");
        model.addAttribute("list",
                list);

        return "aindex";
    }
}
