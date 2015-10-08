package org.vaadin.spring.tutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.vaadin.spring.sidebar.annotation.EnableSideBar;

@EnableAutoConfiguration
@EnableSideBar
@ComponentScan
public class TutorialApplication {

    public static void main(String[] args) {
        SpringApplication.run(TutorialApplication.class, args);
    }
}
