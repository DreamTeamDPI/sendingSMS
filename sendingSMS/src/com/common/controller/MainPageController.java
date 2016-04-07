package com.common.controller;

import com.common.filters.FilterUserStatistic;
import com.common.sms.SMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Konos on 01.04.2016.
 */

@Controller
@RequestMapping("/MainPage")
public class MainPageController {

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView loginPage() {
        ModelAndView model = new ModelAndView("pages/MainPage");


        return model;
    }

    @RequestMapping(value = "/smsStatistic", method = RequestMethod.GET)
    public ModelAndView getSmsStatistic(FilterUserStatistic filterUserStatistic) {
        ModelAndView model = new ModelAndView("pages/MainPage");

        List<SMS> smsList = new ArrayList<>();
        smsList.add(new SMS(1,"+3932141202*","texasdasdasdasdt","2015.10.10",1,20.1));
        smsList.add(new SMS(3,"+3921302*","texaasdasdasdasdasdasssssdsdasdt","2015.10.10",0,20.1));
        smsList.add(new SMS(2,"+39123402*","texasdasdt","2015.10.10",0,20.1));
        smsList.add(new SMS(4,"+3912341202*","teasdsadxt","2015.10.10",1,20.1));
        model.addObject("smsList",smsList);

        return model;
    }

    @RequestMapping(value = "/sms", method = RequestMethod.GET)
    public ModelAndView getSMS() {
        ModelAndView model = new ModelAndView("pages/MainPage");

        String uploadsDir = "/uploads/";
        String realPathtoUploads =  request.getServletContext().getRealPath(uploadsDir);
        if(! new File(realPathtoUploads).exists()) {
            new File(realPathtoUploads).mkdir();
        }

        //String orgName = file.getOriginalFilename();
        String filePath = realPathtoUploads + "test.txt";
        System.out.println(filePath);
        File dest = new File(filePath);
        try {
            dest.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

       /*   to save file
       String orgName = file.getOriginalFilename();
        String filePath = realPathtoUploads + orgName;
        File dest = new File(filePath);
        file.transferTo(dest);*/

        return model;
    }

}
