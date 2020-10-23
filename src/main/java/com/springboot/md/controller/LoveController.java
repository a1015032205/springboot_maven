package com.springboot.md.controller;


import com.springboot.md.pojo.YouLove;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("all")
public class LoveController {
    private static int currentMe = 0, youWantLove = 10, time = 0, endTime = 10, currentTime = 0, weLoveTime = 0;
    int ram = 0;
    int oom = 0;

    public static void main(String[] args) {
        test();
    }

    public static void test() {


        while (currentMe < youWantLove)
            for (; currentTime <= endTime; currentTime++) currentMe++;
        while (currentMe == youWantLove && endTime >= currentTime) weLoveTime++;


    }























































    public static void test() {





















                    while (currentMe < youWantLove)
                        for (; currentTime <= endTime; currentTime++) currentMe++;
                    while (currentMe == youWantLove && endTime >= currentTime) weLoveTime++;






























    }
    @GetMapping("/test1")
    public Object test1() {
        YouLove yourHeart = new YouLove();
        AtomicInteger youWantLove = new AtomicInteger(10);





















                                do currentMe++;
                                while (youWantLove.compareAndSet(currentMe, youWantLove.get()));
                                return yourHeart;


    }







































}
