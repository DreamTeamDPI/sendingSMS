package com.common.controller;

import com.common.filters.FilterSmsStatistic;
import com.common.sms.SMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
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
    public ModelAndView getSmsStatistic(FilterSmsStatistic filterSmsStatistic) {
        ModelAndView model = new ModelAndView("pages/MainPage");

        List<SMS> smsList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            smsList.add(new SMS(1, "+3932141202*", "texasdasdasdasdt", "2015.10.10", 1, 20.1));
            smsList.add(new SMS(3, "+3921302*", "texaasdasdasdasdasdasssssdsdasdt", "2015.10.10", 0, 20.1));
            smsList.add(new SMS(3, "+3921302*", "texaasdasdasdasdasdasssssdsdasdt", "2015.10.10", 0, 20.1));
            smsList.add(new SMS(2, "+39123402*", "texasdasdt", "2015.10.10", 0, 20.1));
            smsList.add(new SMS(4, "+3912341202*", "teasdsadxt", "2015.10.10", 1, 20.1));
        }
        model.addObject("smsList", smsList);
        model.addObject("filterSms",filterSmsStatistic);
        return model;
    }
    @RequestMapping(value = "/FilterSms", method = RequestMethod.POST)
    public ModelAndView getFilterSms(FilterSmsStatistic filterSmsStatistic) {
        ModelAndView model = new ModelAndView("pages/MainPage");
        System.out.println(filterSmsStatistic.toString());
        List<SMS> smsList = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            smsList.add(new SMS(1, "+3932141202*", "texasdasdasdasdt", "2015.10.10", 1, 20.1));
            smsList.add(new SMS(3, "+3921302*", "texaasdasdasdasdasdasssssdsdasdt", "2015.10.10", 0, 20.1));
        }
        model.addObject("smsList", smsList);
        model.addObject("filterSms",filterSmsStatistic);
        return model;
    }

    @RequestMapping(value = "/smsSend", method = RequestMethod.GET)
    public ModelAndView getSmsSend() {
        ModelAndView modelAndView = new ModelAndView("pages/SmsSendPage");

        return modelAndView;
    }
    @RequestMapping(value = "/smsSend", method = RequestMethod.POST)
    public ModelAndView getSmsFile(@RequestParam("file") MultipartFile file) {
        ModelAndView modelAndView = new ModelAndView("pages/Test");
        BufferedReader br = null;

        try {
            byte[] bytes = file.getBytes();

            System.out.println(bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }


        return modelAndView;
    }
    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    public String handleFileUpload(@RequestParam("name") String name,
                                   @RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        if (name.contains("/")) {
            redirectAttributes.addFlashAttribute("message", "Folder separators not allowed");
            return "redirect:upload";
        }
        if (name.contains("/")) {
            redirectAttributes.addFlashAttribute("message", "Relative pathnames not allowed");
            return "redirect:upload";
        }

        if (!file.isEmpty()) {
            try {
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(new File("test.txt")));
                FileCopyUtils.copy(file.getInputStream(), stream);
                stream.close();
                redirectAttributes.addFlashAttribute("message",
                        "You successfully uploaded !");
            }
            catch (Exception e) {
                redirectAttributes.addFlashAttribute("message",
                        "You failed to upload " + name + " => " + e.getMessage());
            }
        }
        else {
            redirectAttributes.addFlashAttribute("message",
                    "You failed to upload " + name + " because the file was empty");
        }

        return "redirect:upload";
    }

}
