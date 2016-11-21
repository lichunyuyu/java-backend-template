package com.fxmms.www.controller.admin;

import com.fxmms.www.service.MacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by mark on 16/11/2.
 */
@Controller
@RequestMapping("/admin")
public class AdminHomeController {
    @Autowired
    MacService macService;

    /**
     * @return
     * @usage 用户登录成功之后显示主页面
     */
    @RequestMapping("/index")
    public ModelAndView toIndex() {
        return new ModelAndView("/admin/index")
                .addObject("subpage", "home/main");
    }

    @RequestMapping("/adminlist")
    public ModelAndView toAdminListPage() {
        return new ModelAndView("/admin/index")
                .addObject("subpage", "home/datatables");
    }

    /**
     * @return
     * @usage 进入录入单个mac地址页面
     */
    @RequestMapping("/addsinglemac")
    public ModelAndView toAddSingleMacPage() {
        return new ModelAndView("admin/index")
                .addObject("subpage", "home/addmac/addsinglemac");
    }

    /**
     * @return
     * @uasge 进入连续录入mac地址页面
     */
    @RequestMapping("/addserisemacs")
    public ModelAndView toAddSeriseMacsPage() {
        return new ModelAndView("admin/index")
                .addObject("subpage", "home/addmac/addserisemac");
    }

    /**
     * @return
     * @usage 进入非连续录入mac地址页面
     * 采用excel来录入数据
     */
    @RequestMapping("/loadnoordermacs")
    public ModelAndView toAddNoOrderMacsPage() {
        return new ModelAndView("admin/index")
                .addObject("subpage", "home/addmac/addnoordermac");
    }

    /**
     * @return
     * @usage 进入显示总体MAC地址情况页面
     */
    @RequestMapping("/allstatusmacs")
    public ModelAndView toAllMacsStatusPage () {
        return new ModelAndView("admin/index")
                .addObject("subpage", "home/macinfo/allmacstatus");
    }


    /**
     * @return
     * @usage 进入显示正处于初始化状态的MAC地址情况页面
     */
    @RequestMapping("/allinitstatusmacs")
    public ModelAndView toInitStatusMacPage() {
        return new ModelAndView("admin/index")
                .addObject("subpage", "home/macinfo/initmacstatus");
    }


    /**
     * @return
     * @usage 进入显示正处于正在录入状态的MAC地址情况页面
     */
    @RequestMapping("/alldeliveringstatusmacs")
    public ModelAndView toDeliveringStatusMacPage() {
        return new ModelAndView("admin/index")
                .addObject("subpage", "home/macinfo/deliveringmacstatus");
    }


    /**
     * @return
     * @usage 进入显示成功录入的MAC地址情况页面
     */
    @RequestMapping("/allsuccessstatusmacs")
    public ModelAndView toSuccessStatusMacPage() {
        return new ModelAndView("admin/index")
                .addObject("subpage", "home/macinfo/successmacstatus");
    }


    /**
     * @return
     * @usage 进入显示处于录入失败状态的MAC地址情况页面
     */
    @RequestMapping("/allfailedstatusmacs")
    public ModelAndView toFailedStatusMacPage() {
        return new ModelAndView("admin/index")
                .addObject("subpage", "home/macinfo/failmacstatus");
    }

}
