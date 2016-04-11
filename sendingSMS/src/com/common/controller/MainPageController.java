package com.common.controller;

import com.common.filters.FilterSmsStatistic;
import com.common.sms.SMS;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Konos on 01.04.2016.
 */

@Controller
@RequestMapping("/MainPage")
public class MainPageController {


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
            smsList.add(new SMS("+3932141202*", "texasdasdasdasdt", "2015.10.10", 1, 20.1));
            smsList.add(new SMS("+3921302*", "texaasdasdasdasdasdasssssdsdasdt", "2015.10.10", 0, 20.1));
            smsList.add(new SMS("+3921302*", "texaasdasdasdasdasdasssssdsdasdt", "2015.10.10", 0, 20.1));
            smsList.add(new SMS("+39123402*", "texasdasdt", "2015.10.10", 0, 20.1));
            smsList.add(new SMS("+3912341202*", "teasdsadxt", "2015.10.10", 1, 20.1));
        }
        model.addObject("smsList", smsList);
        model.addObject("filterSms", filterSmsStatistic);
        return model;
    }

    @RequestMapping(value = "/FilterSms", method = RequestMethod.POST)
    public ModelAndView getFilterSms(FilterSmsStatistic filterSmsStatistic) {
        ModelAndView model = new ModelAndView("pages/MainPage");
        System.out.println(filterSmsStatistic.toString());
        List<SMS> smsList = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            smsList.add(new SMS("+3932141202*", "texasdasdasdasdt", "2015.10.10", 1, 20.1));
            smsList.add(new SMS("+3921302*", "texaasdasdasdasdasdasssssdsdasdt", "2015.10.10", 0, 20.1));
        }
        model.addObject("smsList", smsList);
        model.addObject("filterSms", filterSmsStatistic);
        return model;
    }

    @RequestMapping(value = "/smsSend", method = RequestMethod.GET)
    public ModelAndView getSmsSend() {
        ModelAndView modelAndView = new ModelAndView("pages/SmsSendPage");

        return modelAndView;
    }

    @RequestMapping(value = "/smsSending", method = RequestMethod.POST)
    public ModelAndView getSmsFile(@RequestParam("text") String text, @RequestParam("file") MultipartFile file) {

        System.out.println("hello " + text);
        if (file == null)
            System.out.println("error");
        else {
            try {
                System.out.println(file.getOriginalFilename() + "  b = " + file.getBytes().length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        SMS sms = new SMS();
        try {
            //File inputFile = new File();
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse();
            doc.getDocumentElement().normalize();
            System.out.println("Root element :"
                    + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("sms");
            System.out.println("----------------------------");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :"
                        + nNode.getNodeName());
                /*if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Student roll no : "
                            + eElement.getAttribute("rollno"));
                    System.out.println("First Name : "
                            + eElement
                            .getElementsByTagName("firstname")
                            .item(0)
                            .getTextContent());
                    System.out.println("Last Name : "
                            + eElement
                            .getElementsByTagName("lastname")
                            .item(0)
                            .getTextContent());
                    System.out.println("Nick Name : "
                            + eElement
                            .getElementsByTagName("nickname")
                            .item(0)
                            .getTextContent());
                    System.out.println("Marks : "
                            + eElement
                            .getElementsByTagName("marks")
                            .item(0)
                            .getTextContent());
                }*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("pages/Test");
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
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("message",
                        "You failed to upload " + name + " => " + e.getMessage());
            }
        } else {
            redirectAttributes.addFlashAttribute("message",
                    "You failed to upload " + name + " because the file was empty");
        }

        return "redirect:upload";
    }

}
