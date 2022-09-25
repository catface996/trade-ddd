package com.catface.trade;

import com.catface.common.util.EnvUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

/**
 * @author by catface
 * @date 2020/12/13
 */
@Slf4j
@SpringBootApplication(
				scanBasePackages = {"com.catface"}
)
public class TradeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradeApplication.class, args);
		log.info("TradeApplication start success!");
		System.out.println("TradeApplication start success!");
		System.out.println(EnvUtil.getSwaggerUrl());
		System.out.println(EnvUtil.getDocUrl());
	}
}
