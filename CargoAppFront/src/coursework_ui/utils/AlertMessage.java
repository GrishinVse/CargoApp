package coursework_ui.utils;

import javafx.scene.control.Alert;

public class AlertMessage {
    public static void getAlert(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR!");
        alert.setHeaderText("Fields are incorrect");
        alert.setContentText(message);

        alert.showAndWait();
    }
}
