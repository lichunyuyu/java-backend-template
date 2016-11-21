package com.fxmms.www.service;

import com.fxmms.common.jniutil.GetDownloadIDUtil;
import com.fxmms.common.macutil.CountBetweenMacByMacStr;
import com.fxmms.common.ro.ControllerResult;
import com.fxmms.common.ro.DtoResultWithPageInfo;
import com.fxmms.common.poiutil.ReadExcelUtil;
import com.fxmms.www.dao.AdminDao;
import com.fxmms.www.dao.MacDao;
import com.fxmms.www.dao.TaskDao;
import com.fxmms.www.domain.Admin;
import com.fxmms.www.domain.Mac;
import com.fxmms.www.domain.Task;
import com.fxmms.www.dto.MacDto;
import com.fxmms.www.qo.MacQo;
import com.fxmms.www.thunderinterfaceutil.VisitThunderInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mark on 16/11/7.
 *
 * @usage Mac地址操作业务逻辑层
 */
@Service
public class MacService {
    @Autowired
    MacDao macDao;
    @Autowired
    AdminDao adminDao;
    @Autowired
    TaskDao taskDao;

    /**
     * @param macStr
     * @param username
     * @return mac
     * @usage 判断数据库中是否已经存储过对应的mac
     * 防止数据库中存储多个同样的mac地址
     */
    @Transactional
    public Mac doJudgementBySingleMacStr(String macStr, String username) {
        Mac mac = macDao.getByUniqueKey("macAddr", macStr);
        if (mac == null) {
            //1.单个mac地址转化为downloadId
            String downLoadId = GetDownloadIDUtil.getDownLoadId(macStr);
            Task task = new Task();//单个mac所属task's id
            task.setDate(new Date());
            task.setFlag(0);//录入未成功
            taskDao.save(task);
            Admin admin = adminDao.getByUniqueKey("userName", username);
            mac = new Mac();
            mac.setDownLoadId(downLoadId);
            mac.setAdmin(admin);
            mac.setMacAddr(macStr);
            mac.setDate(new Date());
            //设置mac状态为init状态
            mac.setStatus(0);
            mac.setTask(task);
            macDao.save(mac);
        }
        return mac;
    }

    /**
     * @param macStrList
     * @param username
     * @usage 判断数据库中是否已经存储过对应的mac
     * 防止数据库中存储多个同样的mac地址
     */
    @Transactional
    public void doJudgementBySeriseMacStr(List<String> macStrList, String username) {
        Task task = new Task();//单个mac所属task's id
        task.setDate(new Date());
        task.setFlag(0);//初始化task 状态为录入未成功
        for (String macStr : macStrList) {
            Mac mac = macDao.getByUniqueKey("macAddr", macStr);
            if (mac == null) {
                //1.单个mac地址转化为downloadId
                String downLoadId = GetDownloadIDUtil.getDownLoadId(macStr);
                taskDao.save(task);
                Admin admin = adminDao.getByUniqueKey("userName", username);
                mac = new Mac();
                mac.setDownLoadId(downLoadId);
                mac.setAdmin(admin);
                mac.setMacAddr(macStr);
                mac.setDate(new Date());
                //设置mac状态为init状态
                mac.setStatus(0);
                mac.setTask(task);
                macDao.save(mac);
            }
        }
    }

    /**
     * @param macStr
     * @param username
     * @return 1.单个mac地址转化为downloadId, 并调用迅雷方接口
     * 2.调用接口之前先将地址存储为数据库中一条记录，状态置为0-初始化状态
     * 3.调用完接口根据返回状态，将返回状态为success的数据置为1-正在录入
     */
    @Transactional
    public ControllerResult addSingleMac(String macStr, String username) {
        if (macStr == null || ("".equals(macStr))) {
            return ControllerResult.valueOf(ControllerResult.ERROR, "对不起，MAC地址不能为空");
        }
        if (!CountBetweenMacByMacStr.matchMacAddrByregex(macStr)) {
            return ControllerResult.valueOf(ControllerResult.ERROR, "对不起，MAC地址格式不正确");
        }
        List<String> macStrList = new ArrayList<>();
        macStrList.add(macStr);
        Mac mac = doJudgementBySingleMacStr(macStr, username);
        //调用迅雷录入接口。
        if (VisitThunderInterface.addDownLoadId(macStrList)) {
            Admin admin = adminDao.getByUniqueKey("userName", username);
            if (mac.getStatus() != 2) {
                mac.setStatus(1);
                mac.setDate(new Date());
                mac.setAdmin(admin);
                macDao.update(mac);
            }
            return ControllerResult.valueOf(ControllerResult.SUCCESS, "迅雷录入接口请求成功", mac);
        } else {
            Admin admin = adminDao.getByUniqueKey("userName", username);
            if (mac.getStatus() != 2) {
                mac.setStatus(3);
                mac.setDate(new Date());
                mac.setAdmin(admin);
                macDao.update(mac);
                return ControllerResult.valueOf(ControllerResult.ERROR, "对不起,请求迅雷录入接口失败!<a href='admin/addsinglemac'>重新录入</a>");
            }
            return ControllerResult.valueOf(ControllerResult.ERROR, "此条mac地址已经录入成功");
        }
    }

    /**
     * @param startMacStr
     * @param endMacStr
     * @param username
     * @return
     * @usage 批量区间录入业务逻辑方法
     */
    @Transactional
    public ControllerResult addSeriseMac(String startMacStr, String endMacStr, String username) {
        if (startMacStr == null || ("".equals(startMacStr)) || endMacStr == null || ("".equals(endMacStr))) {
            return ControllerResult.valueOf(ControllerResult.ERROR, "对不起，MAC地址不能为空");
        }
        if (!CountBetweenMacByMacStr.matchMacAddrByregex(startMacStr) || !CountBetweenMacByMacStr.matchMacAddrByregex(endMacStr)) {
            return ControllerResult.valueOf(ControllerResult.ERROR, "对不起，MAC地址格式不正确");
        }
        List<String> macStrList = CountBetweenMacByMacStr.countBetweenMacByMacStr(startMacStr, endMacStr);
        if (macStrList.size() > 1000) {
            return ControllerResult.valueOf(ControllerResult.ERROR, "对不起，MAC区间太长，请拆分后录入。<a href='admin/addserisemacs'>重新录入</a>");
        }
        doJudgementBySeriseMacStr(macStrList, username);
        if (VisitThunderInterface.addDownLoadId(macStrList)) {
            for (String macStr : macStrList) {
                Mac mac = macDao.getByUniqueKey("macAddr", macStr);
                Admin admin = adminDao.getByUniqueKey("userName", username);
                if (mac.getStatus() != 2) {
                    mac.setStatus(1);
                    mac.setDate(new Date());
                    mac.setAdmin(admin);
                    macDao.update(mac);
                }
            }
            return ControllerResult.valueOf(ControllerResult.SUCCESS, "录入成功");
        } else {
            for (String macStr : macStrList) {
                Mac mac = macDao.getByUniqueKey("macAddr", macStr);
                Admin admin = adminDao.getByUniqueKey("userName", username);
                if (mac.getStatus() != 2) {
                    mac.setStatus(3);
                    mac.setDate(new Date());
                    mac.setAdmin(admin);
                    macDao.update(mac);
                }
            }
            return ControllerResult.valueOf(ControllerResult.ERROR, "对不起,请求迅雷录入接口失败!<a href='admin/addserisemacs'>重新录入</a>");
        }
    }

    /**
     * @param macQo
     * @return
     * @usage 获取所有的mac录入状态数据业务逻辑方法
     */
    @Transactional
    public ControllerResult getAllMacStatus(MacQo macQo) {
        DtoResultWithPageInfo<MacDto> info = macDao.queryPageListByCriteriaWithQo(macQo, MacDto.class);
        return ControllerResult.valueOf(ControllerResult.SUCCESS, "获取mac录入状态成功", info);
    }

    /**
     * @param serverFile
     * @param username
     * @return
     * @usage 非连续mac地址录入逻辑方法
     */
    @Transactional
    public ControllerResult addNoOrderMac(File serverFile, String username) {
        ReadExcelUtil readExcelUtil = new ReadExcelUtil();
        try {
            List<String> macStrList = readExcelUtil.readUploadMacFile(serverFile);
            if (macStrList.size() == 0 || macStrList == null) {
                return ControllerResult.valueOf(ControllerResult.ERROR, "对不起，文件中MAC数据不能为空");
            }
            if (macStrList.size() > 1000) {
                return ControllerResult.valueOf(ControllerResult.ERROR, "对不起，文件中数据超过1000条，请进行拆分后上传！");
            }
            for (String inFilemacStr : macStrList) {
                if (!CountBetweenMacByMacStr.matchMacAddrByregex(inFilemacStr)) {
                    return ControllerResult.valueOf(ControllerResult.ERROR, "对不起，文件中有不合法的MAC地址");
                }
            }
            doJudgementBySeriseMacStr(macStrList, username);
            if (VisitThunderInterface.addDownLoadId(macStrList)) {
                for (String macStr : macStrList) {
                    Mac mac = macDao.getByUniqueKey("macAddr", macStr);
                    Admin admin = adminDao.getByUniqueKey("userName", username);
                    if (mac.getStatus() != 2) {
                        mac.setStatus(1);
                        mac.setDate(new Date());
                        mac.setAdmin(admin);
                        macDao.update(mac);
                    }
                }
                return ControllerResult.valueOf(ControllerResult.SUCCESS, "请求迅雷录入接口成功");
            } else {
                for (String macStr : macStrList) {
                    Mac mac = macDao.getByUniqueKey("macAddr", macStr);
                    Admin admin = adminDao.getByUniqueKey("userName", username);
                    if (mac.getStatus() != 2) {
                        mac.setStatus(3);
                        mac.setAdmin(admin);
                        mac.setDate(new Date());
                        macDao.update(mac);
                    }
                }
                return ControllerResult.valueOf(ControllerResult.ERROR, "对不起,请求迅雷录入接口失败!<a href='admin/loadnoordermacs'>重新录入</a>");
            }
        } catch (Exception e) {
            return ControllerResult.valueOf(ControllerResult.ERROR, "文件上传失败");
        }

    }
}