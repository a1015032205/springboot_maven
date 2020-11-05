package com.springboot.md.controller;

/**
 * @Author 秒度
 * @Email fangxin.md@Gmail.com
 * @Date 2020/9/30 下午3:12
 * @Description
 */

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.springboot.md.config.AbstracController;
import com.springboot.md.dao.JavaJob51Mapper;
import com.springboot.md.pojo.JavaJob51;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/FileController")
public class FileController extends AbstracController implements InitializingBean {

    @Resource
    private JavaJob51Mapper mapper;

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

    @GetMapping("/downXls")
    public void downXls(HttpServletResponse response) throws Exception{
        ExcelWriter writer = ExcelUtil.getWriter(true);
        List<JavaJob51> limit = mapper.getLimit();
        writer.setOnlyAlias(true);
        writer.addHeaderAlias("companyName", "公司名称");
        writer.addHeaderAlias("jobName", "工作");
        writer.addHeaderAlias("money", "薪资");
        writer.addHeaderAlias("city", "城市");
        writer.write(limit, true);

        writer.autoSizeColumnAll();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition","attachment;filename=test.xlsx");
        ServletOutputStream out=response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(out);
    }

}
