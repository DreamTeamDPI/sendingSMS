package com.common.controller;

import com.common.filters.FilterUserStatistic;
import com.common.sms.SMS;
import com.common.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Konos on 01.04.2016.
 */

@Controller
@RequestMapping("/MainPage")
public class MainPageController {

    @RequestMapping(method = RequestMethod.GET)
    public String loginPage() {
        return "pages/MainPage";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView getUser(FilterUserStatistic filterUserStatistic) {
        ModelAndView model = new ModelAndView("pages/Test");

        User user = new User("Umka","bear",1023.2);

        model.addObject("object",user.toString());
        model.addObject("stat",filterUserStatistic.toString());

        return model;
    }

    @RequestMapping(value = "/sms", method = RequestMethod.GET)
    public ModelAndView getSMS() {
        ModelAndView model = new ModelAndView("pages/Test");

        SMS sms = new SMS("+3902*","text","20151010",1,20.1);

        model.addObject("object",sms.toString());

        return model;
    }

    /*@RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView getUser(FilterUserStatistic filterUserStatistic) {
        ModelAndView model = new ModelAndView("pages/Test");

        User user = new User("Umka","bear",1023.2);

        model.addObject("object",user.toString());
        model.addObject("stat",filterUserStatistic.toString());

        return model;
    }

    */

}
