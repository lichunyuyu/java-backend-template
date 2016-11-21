package com.fxmms.www.controller.admin;

import com.fxmms.common.ro.ControllerResult;
import com.fxmms.www.schedulejob.CheckMacStatusScheduleJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by mark on 16/11/10.
 * @usage 测试控制类
 */
@Controller
public class AdminTestController {
    @Autowired
    CheckMacStatusScheduleJob checkMacStatusScheduleJob;

    @ResponseBody
    @RequestMapping("/admin/testjobb")
    public ControllerResult testjob() throws Exception{
        return checkMacStatusScheduleJob.checkMacLoadStatusSchedule();
    }

    @ResponseBody
    @RequestMapping("/register")
    public String doRegister(){
        return "register";
    }
}
