package com.fxmms.www.controller.superadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mark on 16/11/2.
 */
@Controller
@RequestMapping("/superadmin")
public class SuperAdminHomeController {
    @RequestMapping("/index")
    public String toIndex() {
        return "superadmin/success";
    }
}
