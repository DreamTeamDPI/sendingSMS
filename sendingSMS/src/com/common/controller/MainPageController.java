package com.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Konos on 01.04.2016.
 */

@Controller
public class MainPageController {

    @RequestMapping(value = "/MainPage", method = RequestMethod.GET)
    public String loginPage() {
        return "pages/MainPage";
    }

    @RequestMapping(value = "/Test", method = RequestMethod.GET)
    public String testPage() {
        return "pages/Test";
    }
}
