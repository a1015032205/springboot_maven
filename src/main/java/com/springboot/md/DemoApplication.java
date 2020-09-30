package com.springboot.md;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.util.Assert;

@SpringBootApplication
@Slf4j
public class DemoApplication extends SpringApplication {

	public DemoApplication() {

	}

	public DemoApplication(Class... primarySources) {
		super(primarySources);
	}

	public static void main(String[] args) {
		DemoApplication demoApplication = new DemoApplication(DemoApplication.class);
		demoApplication.run(args);
	}


	@Override
	protected void refresh(ConfigurableApplicationContext applicationContext) {
		log.info("================  应用预检完成，准备启动 =================");
		Assert.isInstanceOf(AbstractApplicationContext.class, applicationContext);
		super.refresh(applicationContext);
	}

	@Override
	protected void afterRefresh(ConfigurableApplicationContext context, ApplicationArguments args) {
		log.info("================  容器刷新成功，即将启动 =================");
	}
}