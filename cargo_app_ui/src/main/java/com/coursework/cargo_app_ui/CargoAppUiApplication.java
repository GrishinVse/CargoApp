package com.coursework.cargo_app_ui;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CargoAppUiApplication {

    public static void main(String[] args) {
        //SpringApplication.run(CargoAppUiApplication.class, args);
        Application.launch(UIApplication.class, args);
    }

}
