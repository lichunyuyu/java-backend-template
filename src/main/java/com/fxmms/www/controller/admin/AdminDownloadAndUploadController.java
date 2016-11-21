package com.fxmms.www.controller.admin;

import com.fxmms.common.ro.ControllerResult;
import com.fxmms.common.security.ScottSecurityUtil;
import com.fxmms.www.service.MacService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

/**
 * Created by mark on 16/11/9.
 *
 * @usage 实现MAC地址文件上传分析以及下载
 */
@Controller
@RequestMapping("/admin")
public class AdminDownloadAndUploadController {
    @Autowired
    MacService macService;

    /**
     * @param
     * @param
     * @return
     * @throws IOException
     * @usage 1.前台表单上传文件，上传文件之后设置上传文件按照日期存储，同一日期上传的文件放置在同一个文件夹
     * 文件夹名字为年＊100+日。
     * 2.存储完成之后进行分析，或者后台读到文件内容之后进行分析，请求迅雷接口
     * 3.请求完毕之后显示到前台页面最近上传的MAC地址的状态。
     */
    @ResponseBody
    @RequestMapping("/anylisuploadmacfile")
    public ControllerResult uploadMacFile(@RequestParam("file") CommonsMultipartFile file) {
        String userName = ScottSecurityUtil.getLoginName();
        if (!file.isEmpty()) {
            try {
                // 文件存放服务端的位置
                String rootPath = "/Users/mark/mms/src/main/webapp/res/upload-mac-file/";
                Calendar now = Calendar.getInstance();
                int year = now.get(Calendar.YEAR);
                int month = now.get(Calendar.MONTH);
                int day = now.get(Calendar.DAY_OF_MONTH);
                String dayDirName = String.valueOf(year * 1000 + month * 100 + day);
                File dir = new File(rootPath + dayDirName + "/");
                if (!dir.exists())
                    dir.mkdirs();
                // 写文件到服务器
                File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
                file.transferTo(serverFile);
                //对上传文件进行分析，请求迅雷接口
                return macService.addNoOrderMac(serverFile, userName);
            } catch (Exception e) {
                return ControllerResult.valueOf(ControllerResult.ERROR, "上传失败");
            }
        } else {
            return ControllerResult.valueOf(ControllerResult.ERROR, "上传失败！文件不能为空");
        }
    }


    /**
     * @param filePath
     * @param request
     * @return
     * @throws IOException
     * @usage 下载模板文件
     */
    @RequestMapping("/downloadmacexecledemo")
    public ResponseEntity<byte[]> download(@RequestParam("filePath") String filePath, HttpServletRequest request) throws IOException {
        String dfileName = new String(filePath.getBytes("gb2312"), "iso8859-1");
        String targetDirectory = request.getSession().getServletContext().getRealPath("") + "/res/mac-template-file/";
        String realPath = targetDirectory + dfileName;
        File file = new File(realPath);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.setContentDispositionFormData("attachment", dfileName);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.OK);
    }
}
