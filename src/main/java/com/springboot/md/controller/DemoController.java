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
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping
@Slf4j

public class DemoController extends AbstracController {


    @GetMapping("/demo")
    public void demo(HttpServletResponse response) {
        List<File> jpg = FileUtil.loopFiles("E:\\DOTA2", pathname -> pathname.getName().toLowerCase().endsWith("jpg"));
        String strZipPath = "e:\\DOTA2\\B" + ".zip";
        try (ServletOutputStream outputStream = response.getOutputStream(); ZipOutputStream out = new ZipOutputStream(new FileOutputStream(strZipPath))) {
            for (File file : jpg) {
                out.putNextEntry(new ZipEntry(file.getName()));
                out.write(FileUtil.readBytes(file));
                out.closeEntry();
            }
            out.close();
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(strZipPath));
            //将输入流的数据拷贝到输入流输出
            FileCopyUtils.copy(bis, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //     jpg.forEach(x -> FileUtil.writeFromStream(FileUtil.getInputStream(x), "E:\\DOTA3\\" + "20201023" + x.getName()));
        //    return setSuccessModelMap();
    }

//    public Object zip(HttpRequest request) {
//        {
//
//
//            /**
//             *获取前端传来的项目名称
//             */
//            String proName = request.getParameter("proName");
//            log.info("下载的项目为:" + proName);
//            /**
//             * 1.创建临时文件夹
//             */
////        String rootPath = ("D://linshi//" + proName);
//            log.info(temporaryPath + proName);
//            File temDir = new File(temporaryPath + proName);
//            if (!temDir.exists()) {
//                temDir.mkdirs();
//            }
//
//            /**
//             * 项目文件存放地址
//             */
////        String fileUrl = ("D://upload//uploadmulti//"+proName );
//
//            /**
//             * 2.生成需要下载的文件，存放在临时文件夹内
//             */
//            try {
//                ZipUtils.copyDir(fileUrl + proName, temporaryPath + proName);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            /**
//             * 3.设置response的header
//             */
//            response.setContentType("application/zip");
//            response.setHeader("Content-Disposition", "attachment; filename=uchainfile.zip");
//
//            /**
//             * 4.调用工具类，下载zip压缩包
//             */
//            try {
//                ZipUtils.toZip(temDir.getPath(), response.getOutputStream(), true);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            /**
//             * 5.删除临时文件和文件夹
//             */
//            // 这里我没写递归，直接就这样删除了
//            File[] listFiles = temDir.listFiles();
//            for (int i = 0; i < listFiles.length; i++) {
//                listFiles[i].delete();
//                log.info("正在删除第" + i + "个文件");
//            }
//            temDir.delete();
//            return null;
//        }
//    }
}
