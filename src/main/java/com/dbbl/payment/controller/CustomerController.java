package com.dbbl.payment.controller;

import com.dbbl.payment.constants.MessageType;
import com.dbbl.payment.service.exception.CustomerNotFoundException;
import com.dbbl.payment.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/customer")
    public String showCustomer(Model model){
        model.addAttribute("customers", customerService.findAll());
        return "pages/customer/customer-list";
    }

    @GetMapping("/customer/delete/{customerId}")
    public String deleteCustomer(@PathVariable Long customerId, RedirectAttributes redirectAttributes){
        try {
            customerService.deleteCustomer(customerId);
        }catch (CustomerNotFoundException e){
            redirectAttributes.addAttribute("messageType", MessageType.ERROR);
            redirectAttributes.addAttribute("message", e.getLocalizedMessage());
        }
        return "redirect:/customer";
    }
}
