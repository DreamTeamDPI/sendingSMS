package com.common.controller;

import com.common.PatternSms;
import com.common.filters.FilterSmsStatistic;
import com.common.sms.SMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
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

    @RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
    public void sendFileToClient(HttpServletRequest request, HttpServletResponse response) throws IOException {
// get absolute path of the application
        ServletContext context = request.getServletContext();
        String appPath = context.getRealPath("/uploads/");
        System.out.println("appPath = " + appPath);

        // construct the complete absolute path of the file
        String fullPath = appPath + "patternOfSms.xml";
        File downloadFile = new File(fullPath);
        FileInputStream inputStream = new FileInputStream(downloadFile);

        // get MIME type of the file
        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);

        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());

        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);

        // get output stream of the response
        OutputStream outStream = response.getOutputStream();
        int BUFFER_SIZE = 4096;
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;

        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outStream.close();
    }

    @RequestMapping(value = "/smsSending", method = RequestMethod.POST)
    public ModelAndView getSmsFile(@RequestParam("text") String text, @RequestParam("file") MultipartFile file) {
        if (file == null)
            System.out.println("error");
        else {
            try {
                System.out.println(file.getOriginalFilename() + "  b = " + file.getBytes().length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String uploadsDir = "/uploads/";
        String realPathtoUploads = request.getServletContext().getRealPath(uploadsDir);
        if (!new File(realPathtoUploads).exists()) {
            new File(realPathtoUploads).mkdir();
        }

        //String orgName = file.getOriginalFilename();
        String filePath = realPathtoUploads + "nameOfClient.xml";
        System.out.println(filePath);
        File dest = new File(filePath);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            System.out.println("no ok");
        }


        List<PatternSms> smsList = new ArrayList<>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(dest);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("sms");
            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String textSms = eElement.getElementsByTagName("text").item(0).getTextContent();
                    String phoneSms = eElement.getElementsByTagName("phone").item(0).getTextContent();
                    smsList.add(new PatternSms(textSms, phoneSms));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //dest.delete();
        for (int i = 0; i < smsList.size(); i++)
            System.out.println(smsList.get(i).toString());

        return new ModelAndView("pages/MainPage");
    }


}
