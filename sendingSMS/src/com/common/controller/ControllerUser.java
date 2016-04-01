/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*

package com.common.controller;


import com.common.entity.ClassUser;
import com.common.entity.User;
import com.common.helper.sortAndPage;
import com.common.service.RoleService;
import com.common.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


*/
/**
 * @author SemmEs
 *//*

@Controller
public class ControllerUser {

    private static final Logger loger_all = Logger.getLogger(ControllerUser.class);
    private static final Logger logger_edit = Logger.getLogger("APP");
    private static final Logger logger_delete = Logger.getLogger("EDITUSER");
    private static final Logger logger_test = Logger.getLogger("Test");

    @Autowired
    UserService user;
    @Autowired
    RoleService role;

    @RequestMapping(value = "UserList/ex", method = RequestMethod.GET)
    public ModelAndView ex(sortAndPage sAP) throws Exception {

        ModelAndView model = new ModelAndView("UserListSorting");
        Map<Integer, String> pnn = new HashMap<>();
        List<Integer> pageNumber = new ArrayList<>();
        Page<User> pages;

        System.out.println(sAP);
        sAP.setPage(sAP.getPage() - 1);

        pages = user.findAllPages(sAP);

        for (int j = 1; j <= pages.getTotalPages(); j++) {
            pageNumber.add(j);

            String s = sAP.createHref(j);
            pnn.put(j, s);

        }

        List<User> list = pages.getContent();
        sAP.setPage(sAP.getPage() + 1);



        int step = 1;
        int current = sAP.getPage();

        if(current == 1)step = 2;
        if(current == pages.getTotalPages())step = 2;

        int begin = Math.max(1, current - step);
        int end = Math.min(current + step, pages.getTotalPages());

        model.addObject("firstPage",1);
        model.addObject("lastPage",pages.getTotalPages());

        model.addObject("beginIndex", begin);
        model.addObject("endIndex", end);

        model.addObject("sortAndPage",sAP);
        model.addObject("userList", list);
        return model;

    }

    @RequestMapping(value = "UserList", method = RequestMethod.GET)
    public ModelAndView handleRequest(sortAndPage sAP) throws Exception {
        ModelAndView model = new ModelAndView("UserList");
        Map<Integer, String> pnn = new HashMap<>();
        List<Integer> pageNumber = new ArrayList<>();
        Page<User> pages;

        System.out.println(sAP);
        sAP.setPage(sAP.getPage() - 1);

        pages = user.findAllPages(sAP);

        for (int j = 1; j <= pages.getTotalPages(); j++) {
            pageNumber.add(j);

            String s = sAP.createHref(j);
            pnn.put(j, s);

        }

        List<User> list = pages.getContent();
        sAP.setPage(sAP.getPage() + 1);



        int step = 1;
        int current = sAP.getPage();
        int max = pages.getTotalPages();
        if(pages.getTotalPages()==0){
            current = 1;
            max+=1;
        }

        if(current == 1)step = 2;
        if(current == pages.getTotalPages())step = 2;

        int begin = Math.max(1, current - step);
        int end = Math.min(current + step, max);

        model.addObject("firstPage",1);
        model.addObject("lastPage",max);

        model.addObject("beginIndex", begin);
        model.addObject("endIndex", end);

        model.addObject("sortAndPage",sAP);
        model.addObject("userList", list);

        model.addObject("size", pages.getTotalElements());


        loger_all.info("Ok/ it's worked");
        logger_test.info("Тестируем архив");


        return model;

    }

    @RequestMapping(value = "UserList/sort", method = RequestMethod.GET)
    public ModelAndView sortUser(int i) {
        ModelAndView model = new ModelAndView("userSortTable");
        Page<User> pages = user.findAllPagesAndSort(0, i);//user.getNotAll(n,k);
        List<User> list = pages.getContent();
        List<Integer> pageNumber = new ArrayList<>();
        for (int j = 1; j <= pages.getTotalPages(); j++) {
            pageNumber.add(j);
        }
        model.addObject("listNumberPage", pageNumber);
        model.addObject("userList", list);
        model.addObject("size", pages.getTotalElements());
        return model;
    }

    @RequestMapping(value = "UserList/del", method = RequestMethod.GET)
    public String deleteUser(int id) {
        logger_edit.info("Был удален пользователь:" + user.findByidUser(id).toString());
        user.delete(id);
        return "hi";
    }

    @RequestMapping(value = "UserList/add", method = RequestMethod.GET)
    public @ResponseBody
    String addUser(@Valid ClassUser type, BindingResult result) {
        if (result.hasErrors()) return "err";
        System.out.println(type.toString());
        type.setRoleidRole(1);
        User user1 = new User(type);
        user.addUser(user1);
        logger_delete.info("Выполнялась работа с пользователем:" + user1.toString() );

        return "suc";
    }

    //ошибка на будущее может быть в передачи User, а не ClassUser
    @RequestMapping(value = "UserList/getUserById", method = RequestMethod.GET)
    public @ResponseBody
    User getUserById(int id) {
        User listUsers = user.findByidUser(id);
        return listUsers;
    }

}
*/
