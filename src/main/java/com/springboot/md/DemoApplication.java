package com.springboot.md;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.io.IOException;

@SpringBootApplication
@Slf4j
@MapperScan("com.springboot.md.dao")
public class DemoApplication extends SpringBootServletInitializer {


    public DemoApplication() {

    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DemoApplication.class);
    }

//    public DemoApplication(Class... primarySources) {
//        super(primarySources);
//    }

    public static void main(String[] args) throws IOException {
        SpringApplication.run(DemoApplication.class, args);
//        String filePath = "E:\\java\\WorkSpace\\" + "application.yml";
//        InputStream ism = new FileInputStream(filePath);
//        Properties properties = new Properties();
//        properties.load(ism);
        //  DemoApplication demoApplication = new DemoApplication(DemoApplication.class);
        // demoApplication.setDefaultProperties(properties);
        // demoApplication.run(args);
    }


//    @Override
//    protected void refresh(ConfigurableApplicationContext applicationContext) {
//        log.info("================  应用预检完成，准备启动 =================");
//        Assert.isInstanceOf(AbstractApplicationContext.class, applicationContext);
//        super.refresh(applicationContext);
//    }
//
//    @Override
//    protected void afterRefresh(ConfigurableApplicationContext context, ApplicationArguments args) {
//        log.info("================  容器刷新成功，即将启动 =================");
//    }
}
