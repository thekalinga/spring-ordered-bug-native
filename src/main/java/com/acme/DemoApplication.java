package com.acme;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@SpringBootApplication(proxyBeanMethods = false)
public class DemoApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder(DemoApplication.class)
        .bannerMode(Banner.Mode.OFF)
        .web(WebApplicationType.NONE)
        .run(args);
  }

  interface Contract {
    void perform();
  }

  @Component
  @Order(1)
  static class FirstContract implements Contract {
    @Override
    public void perform() {
      System.out.println("First contract: Done");
    }
  }

  @Component
  @Order(555)
  static class SecondContract implements Contract {
    @Override
    public void perform() {
      System.out.println("Second contract: Done");
    }
  }

  @Component
  @Order(9999)
  static class LastContract implements Contract {
    @Override
    public void perform() {
      System.out.println("Last contract: Done");
    }
  }

  @Bean
  ApplicationRunner onInit(List<Contract> contracts) {
    return args -> contracts.forEach(Contract::perform);
  }

}
