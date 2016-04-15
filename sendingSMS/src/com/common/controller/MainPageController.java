package com.common.controller;

import com.common.filters.FilterPaymentStatistic;
import com.common.filters.FilterSmsStatistic;
import com.common.payments.Payment;
import com.common.sms.SMS;
import com.common.xmlparser.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
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

    @RequestMapping(value = "/smsSend", method = RequestMethod.GET)
    public ModelAndView getSmsSend() {
        ModelAndView modelAndView = new ModelAndView("pages/SmsSendPage");

        modelAndView.addObject("content",1);
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
    public ModelAndView getSmsFile(@RequestParam(value = "phone", required = false) String phone,
                                   @RequestParam(value = "text", required = false) String text,
                                   @RequestParam(value = "file", required = false) MultipartFile file) {
        ModelAndView model = new ModelAndView("pages/SmsSendPage");
        if (file == null) {
            if(phone.isEmpty() || text.isEmpty()){
                return new ModelAndView("pages/Test");
            } else {
                System.out.println(phone + "  " + text);
            }
        } else {
            try {
                InputStream inputStream = file.getInputStream();
                JAXBContext jaxbContext = JAXBContext.newInstance(Message.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                Message message = (Message) jaxbUnmarshaller.unmarshal(inputStream);
                System.out.println(message);
            }  catch (Exception e) {
                return new ModelAndView("pages/Test");
            }
        }
        model.addObject("inputForControl",1);
        model.addObject("content",1);
        return model;
    }

    @RequestMapping(value = "/smsStatistic", method = RequestMethod.GET)
    public ModelAndView getSmsStatistic(FilterSmsStatistic filterSmsStatistic) {
        ModelAndView model = new ModelAndView("pages/StatisticSMSPage");

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
        model.addObject("content",2);
        return model;
    }

    @RequestMapping(value = "/FilterSms", method = RequestMethod.POST)
    public ModelAndView getFilterSms(FilterSmsStatistic filterSmsStatistic) {
        ModelAndView model = new ModelAndView("pages/StatisticSMSPage");
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

    @RequestMapping(value = "/paymentsStatistic", method = RequestMethod.GET)
    public ModelAndView getPaymentsStatistic(FilterPaymentStatistic filterPaymentStatistic) {
        ModelAndView model = new ModelAndView("pages/PaymentsPage");

        List<Payment> paymentList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            paymentList.add(new Payment("20.20.2020",10.23,13,"Smth in last col"));
            paymentList.add(new Payment("21.20.2020",11.23,13,"Smth in last col"));
            paymentList.add(new Payment("21.20.2020",11.23,13,"Smth in last col"));
            paymentList.add(new Payment("22.20.2020",12.23,13,"Smth in last col"));
            paymentList.add(new Payment("23.20.2020",13.23,13,"Smth in last col"));

        }
        model.addObject("paymentFilter", filterPaymentStatistic);
        model.addObject("paymentList", paymentList);
        model.addObject("content",3);
        return model;
    }

    @RequestMapping(value = "/FilterPayment", method = RequestMethod.POST)
    public ModelAndView getFilterPayment(FilterPaymentStatistic filterPaymentStatistic) {
        ModelAndView model = new ModelAndView("pages/PaymentsPage");

        System.out.println(filterPaymentStatistic.toString());

        List<Payment> paymentList = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            paymentList.add(new Payment("20.20.2020",10.23,13,"Smth in last col"));
            paymentList.add(new Payment("21.20.2020",11.23,13,"Smth in last col"));
            paymentList.add(new Payment("21.20.2020",11.23,13,"Smth in last col"));

        }
        model.addObject("paymentFilter", filterPaymentStatistic);
        model.addObject("paymentList", paymentList);
        return model;
    }

    @RequestMapping(value = "/Settings", method = RequestMethod.GET)
    public ModelAndView getSettingPage(){
        ModelAndView modelAndView = new ModelAndView("pages/SettingPage");
        modelAndView.addObject("content",4);
        return modelAndView;
    }
    @RequestMapping(value = "/saveSetting", method = RequestMethod.POST)
    public ModelAndView setSetting(@Valid String phone, String text) {
        ModelAndView model =  new ModelAndView("pages/SettingPage");
        System.out.println(phone+" " + text);
        model.addObject("inputForControl",1);
        model.addObject("content",4);
        return model;
    }
    
}
