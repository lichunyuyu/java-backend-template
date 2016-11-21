package com.fxmms.www.controller.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mark on 16/11/2.
 */
@Controller
@RequestMapping("/customer")
public class CustomerHomeController {
    @RequestMapping("/index")
    public String toIndex() {
        return "customer/success";
    }
}
