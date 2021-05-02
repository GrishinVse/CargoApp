package coursework_ui.controllers;

import coursework_ui.Main;
import javafx.fxml.FXML;

public class AboutPageController {

    public AboutPageController(){    }

    /**
     * Инициализация контроллера при первом запуске
     */
    @FXML
    public void initialize() { }

    /**
     * Метод работающий с ссылкой в интерфейсе, позволяет открыть GitHub
     */
    public void handleGit(){
        Main.hostServices.showDocument("https://github.com/GrishinVse/CargoApp");
        System.out.println("Transfer to GitHub site!");
    }

}
