package com.dbbl.payment.controller;

import com.dbbl.payment.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserAccountController {

    @Autowired
    UserAccountRepository  userAccountRepository;

    @GetMapping("/admin/user/create")
    public String createAdminUser(){

        return "admin/admin-user-create";
    }

    @GetMapping("/admin/user")
    public String showAdminUser(){
        return "admin/admin-user-list";
    }
}
