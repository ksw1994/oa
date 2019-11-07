package com.bootdo.system.controller;

import com.bootdo.system.service.DownloadService;
import org.activiti.rest.editor.model.ModelEditorJsonRestResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@Controller
@RequestMapping("/download")
public class DownloadController {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ModelEditorJsonRestResource.class);

    @Autowired
    private DownloadService downloadService;

    //下载简历
    @RequestMapping(value = "/downloadResume")
    public String download(String resumeId,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //id : 要导出的简历id
        //String fileanme = request.getParameter("filename");
       // String fileName = URLEncoder.encode( "报销信息导出.doc","utf-8");
       // String path = "/home/ksw/De ktop/word/报销信息导出.doc";
       // File file = new File(path);
        File file = downloadService.downloadResume(resumeId);
        if (file.exists()) {
            // 设置强制下载不打开
            response.setContentType("application/force-download");
            response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(file.getName(),"utf-8"));
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            OutputStream outputStream = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                outputStream = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    outputStream.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                outputStream.flush();
                outputStream.close();
                return "success";
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage());
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        LOGGER.error(e.getMessage());
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        LOGGER.error(e.getMessage());
                    }
                }
            }
        }
        return "file doesn't found!";
    }
}
