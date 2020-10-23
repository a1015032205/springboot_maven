package com.springboot.md.controller;

/**
 * @Author 秒度
 * @Email fangxin.md@Gmail.com
 * @Date 2020/9/30 下午3:12
 * @Description
 */

import cn.hutool.core.io.FileUtil;
import com.springboot.md.config.AbstracController;
import com.springboot.md.pojo.MyEverything;
import com.springboot.md.pojo.YouLove;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping
@Slf4j
@SuppressWarnings("all")
public class DemoController extends AbstracController {

    public static final String I_GO = "I have nothing";
    public static int currentMe = 0, youWantLove1 = 10, time = 0, endTime = 10, currentTime = 0, togetherTime = 0;

    private AtomicInteger youWantLove = new AtomicInteger(10);

    @GetMapping("/demo")
    public Object demo() {
        List<File> jpg = FileUtil.loopFiles("E:\\DOTA2", pathname -> pathname.getName().toLowerCase().endsWith("jpg"));
        jpg.forEach(x -> FileUtil.writeFromStream(FileUtil.getInputStream(x), "E:\\DOTA3\\" + "20201023" + x.getName()));
        return setSuccessModelMap();
    }

    @GetMapping("/test1")
    public Object test1() {
        YouLove yourHeart = new YouLove();













                                                                        do currentMe++;
                                                                        while (youWantLove.compareAndSet(currentMe, youWantLove.get()));
                                                                        return yourHeart;


























    }

    @GetMapping("/test")
    public void test() {

        int currentMe = 0, youWantLove = 10, time = 0, endTime = 10, currentTime = 0, togetherTime = 0;
        while (currentMe < youWantLove)
            for (int startTime = currentTime; currentTime <= endTime; currentTime++)
                currentMe++;

        togetherTime++;


    }

    public Boolean youWant(MyEverything everything) {
        return false;
    }


}
