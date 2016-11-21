package com.fxmms.www.controller.admin;

import com.fxmms.common.ro.ControllerResult;
import com.fxmms.common.security.ScottSecurityUtil;
import com.fxmms.common.util.EncodeUtil;
import com.fxmms.www.domain.Admin;
import com.fxmms.www.qo.AdminQo;
import com.fxmms.www.qo.MacQo;
import com.fxmms.www.service.AdminService;
import com.fxmms.www.service.MacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by mark on 16/11/8.
 *
 * @usage 录入mac地址控制类
 */
@Controller
@RequestMapping("/admin")
public class AdminLoadMacController {
    @Autowired
    MacService macService;
    @Autowired
    AdminService adminService;

    /**
     * @param macAddr
     * @return
     * @usage 录入单条mac地址
     */
    @ResponseBody
    @RequestMapping("/loadSingleMac")
    public ControllerResult doAddSingleMac(String macAddr) {
        String username = ScottSecurityUtil.getLoginName();
        return macService.addSingleMac(macAddr, username);
    }

    /**
     * @param startMacStr,endMacStr
     * @return
     * @usage 录入区间mac地址
     */
    @ResponseBody
    @RequestMapping("/loadserisemacs")
    public ControllerResult doAddSerisesMacs(String startMacStr,String endMacStr) {
        String username = ScottSecurityUtil.getLoginName();
        return macService.addSeriseMac(startMacStr, endMacStr, username);
    }


    /**
     * @param macQo
     * @param page
     * @param limit
     * @param searches
     * @return
     * @throws Exception
     * @usage 以日起倒序形式显示mac地址录入情况，最近录入的mac地址最先显示
     */
    @ResponseBody
    @RequestMapping("/getallmacstatus")
    public ControllerResult getAllMacStatus(MacQo macQo, @RequestParam("page") int page,
                                            @RequestParam("limit") int limit,
                                            @RequestParam("searches") String searches) throws Exception {
        //进行获取参数的编解码
        String newSearches = EncodeUtil.encodeStr(searches);
        macQo.setPage(page);
        macQo.setRows(limit);
        macQo.setSearchesParm(newSearches);
        List<Integer> adminIdList = adminService.getAdminIdList(newSearches);
        macQo.setAdminIdList(adminIdList);
        System.out.println(newSearches + "模糊查询参数－－－－");
        return macService.getAllMacStatus(macQo);
    }

    /**
     * @param macQo
     * @param page
     * @param limit
     * @param searches
     * @return
     * @throws Exception
     * @usage 以日起倒序形式显示mac地址录入情况，最近录入的初始化状态的mac地址最先显示
     */
    @ResponseBody
    @RequestMapping("/macstatus")
    public ControllerResult getInitacStatus(MacQo macQo, @RequestParam("page") int page,
                                            @RequestParam("limit") int limit,
                                            @RequestParam("searches") String searches,
                                            @RequestParam("status") String status) throws Exception {
        //进行获取参数的编解码
        String newSearches = EncodeUtil.encodeStr(searches);
        macQo.setPage(page);
        macQo.setRows(limit);
        macQo.setMacStatus(status);
        macQo.setSearchesParm(newSearches);
        List<Integer> adminIdList = adminService.getAdminIdList(newSearches);
        macQo.setAdminIdList(adminIdList);
        System.out.println(newSearches + "模糊查询参数－－－－");
        return macService.getAllMacStatus(macQo);
    }


    /**
     * @return
     * @usage 测试定时任务
     */
    @ResponseBody
    @RequestMapping("/add")
    public String testAdd() {
        Admin admin = new Admin();
        admin.setIsDelete(0);
        admin.setEnable(1);
        admin.setPassword("123456");
        admin.setRole("admin");
        admin.setUserName("刘涛");
        adminService.insertAdmin(admin);
        return "测试成功";
    }

    /**
     * @param adminQo
     * @param page
     * @param limit
     * @param searches
     * @return
     * @throws Exception
     * @usage Jquery显示管理员列表
     */
    @ResponseBody
    @RequestMapping("/adminList")
    public ControllerResult getAdminList(AdminQo adminQo, @RequestParam("page") int page,
                                         @RequestParam("limit") int limit,
                                         @RequestParam("searches") String searches) throws Exception {
        //进行获取参数的编解码
        String newSearches = EncodeUtil.encodeStr(searches);
        adminQo.setPage(page);
        adminQo.setRows(limit);
        adminQo.setSearchesParm(newSearches);
        System.out.println(newSearches + "模糊查询参数－－－－");
        return adminService.getAdminList(adminQo);
    }


}
