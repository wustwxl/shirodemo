package com.wust.web;

import com.wust.utils.FileUtil;
import com.wust.vo.MessageVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/anon/file")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @RequestMapping(value = "/file", method = RequestMethod.GET)
    public String greeting(HttpServletRequest request, HttpServletResponse response) {

        //跳转到 templates 目录下的 file.html
        return "file";
    }

    /**
     * 单个文件上传
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public MessageVo uploadFile(@RequestParam("file") MultipartFile file){

        MessageVo mesVo = new MessageVo();

        FileUtil fileUtil = new FileUtil();

        String state = fileUtil.uploadFile(file);

        return commonMethod(state, mesVo);
    }

    /**
     * 单个文件上传到服务器
     */
    @RequestMapping(value = "/uploadByHttp", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public MessageVo uploadFileByHttp(@RequestParam("file") MultipartFile file){

        MessageVo mesVo = new MessageVo();


        return mesVo;
    }

    /**
     * 多个文件上传
     */
    @RequestMapping(value = "/batchFiles", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public MessageVo batchFiles(HttpServletRequest request){

        MessageVo mesVo = new MessageVo();

        FileUtil fileUtil = new FileUtil();

        String state = fileUtil.batchFiles(request);

        return commonMethod(state, mesVo);

    }

    /**
     * 公用方法
     */
    private MessageVo commonMethod(String state, MessageVo mesVo){

        if("Upload Success".equals(state)){
            logger.info("--------------->上传成功");
            mesVo.setCode(0);
            mesVo.setInfo("Upload File Success.");
        }else{
            logger.info("--------------->上传失败");
            mesVo.setCode(1);
            mesVo.setInfo("Upload File Failed.");
        }

        return mesVo;
    }
}