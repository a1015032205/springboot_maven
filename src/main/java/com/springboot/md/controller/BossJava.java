package com.springboot.md.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import com.springboot.md.config.AbstracController;
import com.springboot.md.dao.BossJavaJobMapper;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author 秒度111
 * @Date 2019/7/19 0019 22:09
 */
@RestController
@Slf4j
public class BossJava extends AbstracController {

    private static final List<String> list1 = CollUtil.newArrayList("不限", "高中", "大专", "本科", "硕士", "博士");
    private static final List<String> list2 = CollUtil.newArrayList("不限", "未融资", "天使轮", "A轮", "B轮", "C轮", "D轮及以上", "已上市", "不需要融资");
    private static List<Map<String, Objects>> relust = CollUtil.newArrayList();

    @Autowired
    private ThreadPoolTaskExecutor executor;

    @Resource
    private BossJavaJobMapper mapper;

    @RequestMapping(value = "initBoos")
    public void init() {
        //设置webdriver路径
        System.setProperty("webdriver.chrome.driver", Objects.requireNonNull(BossJava.class.getClassLoader().getResource("chromedriver.exe")).getPath());
        //指定要爬取的地址
        String httpUrl = "127.0.0.1:11000";  // 代理IP示例
        // 设置代理IP
        ChromeOptions options = new ChromeOptions();
        //  options.addArguments("--proxy-server=http://" + httpUrl);
        // 开启开发者模式
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        WebDriver webDriver = new ChromeDriver(options);
        // 窗口最大化
        webDriver.manage().window().maximize();
        webDriver.get("https://www.zhipin.com/c100010000-p100101/?page=1&ka=page-1");
        //通过Xpath选择元素
//        //选择地区
//        are(webDriver, "武汉");
//        //选择基本信息
//        option(webDriver, "工作经验", "3年及以下");
//        option(webDriver, "学历要求", "本科");
//        option(webDriver, "融资阶段", "不限");
//        option(webDriver, "公司规模", "不限");
//        option(webDriver, "行业领域", "不限");
//        //选择排序
//        mon(webDriver, "最新", "5k-10k", "全职");
        //解析页面中的元素
        extractJob(webDriver);
    }


    public void demo1(Map<String, Object> map, WebElement jobElement) {
        //公司性质  互联网
        String type = jobElement.findElement(By.className("company-text")).findElement(By.className("false-link")).getText();
        //   log.info("公司性质======================》[{}]", type);
        map.put("type", type);
    }

    public void demo2(Map<String, Object> map, WebElement jobElement) {
        //招聘人
        String name = jobElement.findElement(By.className("name")).getText();
        String substring1 = name.substring(0, 3);
        //  log.info("招聘人======================》[{}]", substring1);
        map.put("name", substring1);
    }

    public void demo3(Map<String, Object> map, WebElement jobElement) {

        //公司名称
        String companyName = jobElement.findElement(By.className("company-text")).findElement(By.className("name")).getText();
        //   log.info("公司名称======================》[{}]", companyName);
        map.put("companyName", companyName);
    }

    public void demo4(Map<String, Object> map, WebElement jobElement) {
        // 职位名称
        String jobName = jobElement.findElement(By.className("job-name")).getText();
        //  log.info("职位名称======================》[{}]", jobName);
        map.put("jobName", jobName);
    }

    public void demo5(Map<String, Object> map, WebElement jobElement) {
        //工资  8k-14k
        String money = jobElement.findElement(By.className("red")).getText();
        //   log.info("薪资======================》[{}]", money);
        map.put("money", money);
    }

    public void demo6(Map<String, Object> map, WebElement jobElement) {
        //地区 [漕河泾]
        String are = jobElement.findElement(By.className("job-area")).getText();
        String[] split = are.split("·");
        map.put("city", split[0]); //城市
        map.put("are", split[1]); //区域
        if (split.length > 2) {
            map.put("street", split[2]); //街道
        }
        //    log.info("地区======================》[{}]", are);
    }

    public void demo7(Map<String, Object> map, WebElement jobElement) {
        String education = ((RemoteWebElement) jobElement).findElementByTagName("p").getText();
        for (String s : list1) {
            if (education.equalsIgnoreCase(s)) {
                //年限
                String year = education.substring(0, education.length() - 2);
                //学历
                String s1 = education.split(year)[1];
                map.put("year", year);
                map.put("education", s1);
                //    log.info("年限要求======================》[{}]", year);
                //      log.info("学历要求======================》[{}]", s1);
                break;
            }
        }
    }

    public void demo8(Map<String, Object> map, WebElement jobElement) {

        //福利
        String welfare = jobElement.findElement(By.className("info-desc")).getText();
        //    log.info("待遇秒速======================》[{}]", welfare);
        map.put("welfare", welfare);
    }

    public void demo9(Map<String, Object> map, WebElement jobElement) {

        //  技术栈   Java
        List<WebElement> list = jobElement.findElements(By.className("tag-item"));
        String collect = list.stream().map(WebElement::getText).collect(Collectors.joining(","));
        map.put("technology", collect);
        //    log.info("技术栈======================》[{}]", collect);

    }

    public void demo10(Map<String, Object> map, WebElement jobElement) {
        //互联网不需要融资500-999人
        String p = jobElement.findElement(By.className("company-text")).findElement(By.tagName("p")).getText();
        for (String s : list2) {
            if (p.contains(s)) {
                String[] split1 = p.split(s);
                //人数
                String num = split1[1];
                map.put("num", num);
                map.put("Listed", s);
                //       log.info("人数=====================》[{}]", num);
                //       log.info("上市情况=====================》[{}]", s);
            }
        }

    }

    private void extractJob(WebDriver webDriver) {
        List<WebElement> elements = webDriver.findElements(By.className("job-primary"));
        List<Map<String, Object>> collect = elements.parallelStream().map(jobElement -> {
            Map<String, Object> map = MapUtil.newConcurrentHashMap(128);
            executor.execute(() -> demo1(map, jobElement));
            executor.execute(() -> demo2(map, jobElement));
            executor.execute(() -> demo3(map, jobElement));
            executor.execute(() -> demo4(map, jobElement));
            executor.execute(() -> demo5(map, jobElement));
            executor.execute(() -> demo6(map, jobElement));
            executor.execute(() -> demo7(map, jobElement));
            executor.execute(() -> demo8(map, jobElement));
            executor.execute(() -> demo9(map, jobElement));
            executor.execute(() -> demo10(map, jobElement));
            return map;
        }).collect(Collectors.toList());

        //解析下一页
        try {
            WebElement pagerNext = webDriver.findElement(By.className("page"));
            //下一页不可点  next disabled
            if (!pagerNext.getAttribute("class").contains("next")) {
                pagerNext.click();
                System.out.println("--------------------解析下一页-----------------------");
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                extractJob(webDriver);
            }
        } catch (Exception e) {

        }

    }

    /**
     * 选择区域
     *
     * @param webDriver
     * @param address
     */
    private void are(WebDriver webDriver, String address) {
        WebElement areElement = webDriver.findElement(By.xpath("//*[@id='filterCollapse']/div[1]/div[2]/li/div[2]/div/a[contains(text(),'" + address + "')]"));
        //*[@id="filterCollapse"]/div[1]/div[2]/li/div[2]/div/a[2]
        areElement.click();
    }

    /**
     * 选择要求
     *
     * @param webDriver
     * @param chosenTitle
     * @param containsTitle
     */
    private void option(WebDriver webDriver, String chosenTitle, String containsTitle) {
        WebElement containsElement = webDriver.findElement(By.xpath("//li[@class='multi-chosen']//span[contains(text(),'" + chosenTitle + "')]"));
        WebElement optionElement = containsElement.findElement(By.xpath("../a[contains(text(),'" + containsTitle + "')]"));
        optionElement.click();
    }

    /**
     * 选择工资
     *
     * @param webDriver
     * @param state
     * @param money
     * @param jobStyle
     */
    private void mon(WebDriver webDriver, String state, String money, String jobStyle) {
        WebElement element = webDriver.findElement(By.xpath(" //*[@id='order']/li/div[1]/a[contains(text(),'" + state + "')]"));
        element.click();
        WebElement moneyElement = webDriver.findElement(By.xpath("//div[@class='item salary selectUI']//div[@class='selectUI-text text']//span[contains(text(),'不限')]"));
        moneyElement.click();
        WebElement numElement = moneyElement.findElement(By.xpath("..//a[contains(text(),'" + money + "')]"));
        numElement.click();
        webDriver.findElement(By.xpath("//*[@id='order']/li/div[3]/div/span")).click();
        WebElement workElement = webDriver.findElement(By.xpath("//*[@id='order']/li/div[3]/div/ul/li[2]/a[contains(text(),'" + jobStyle + "')]"));
        workElement.click();
    }

}
