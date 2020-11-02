package com.springboot.md.controller;

/**
 * @Author 秒度
 * @Email fangxin.md@Gmail.com
 * @Date 2020/9/30 下午3:12
 * @Description
 */

import cn.hutool.core.io.FileUtil;
import com.springboot.md.config.AbstracController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;

@Controller
@Slf4j
public class FileController extends AbstracController implements InitializingBean {


    public FileController() {
        System.out.println("DemoController");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }

    @PostConstruct
    public void init() {
        System.out.println("PostConstruct");
    }

    @PostMapping("/demo")
    @ResponseBody
    public Object demo(@RequestParam("name") String name,
                       @RequestParam("file") MultipartFile file) throws Exception {
        String originalFilename = file.getOriginalFilename();
        System.out.println(name);
        List<File> jpg = FileUtil.loopFiles("E:\\DOTA2", pathname -> pathname.getName().toLowerCase().endsWith("jpg"));
        return null;
    }

}
