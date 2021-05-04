package coursework_ui.controllers;

import coursework_ui.models.Corporate;
import coursework_ui.models.Individual;
import coursework_ui.models.OrderList;
import coursework_ui.models.Transport;
import coursework_ui.utils.RequestManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * Контроллер по работе со страницей редактирования заказа
 */
public class OrderEditPageController {

    @FXML
    private TextField startField;

    @FXML
    private TextField otherField;

    @FXML
    private TextField endField;

    @FXML
    private ChoiceBox<String> orderTypeBox;

    @FXML
    private TextArea descriptionField;

    @FXML
    private CheckBox individualCheck;

    @FXML
    private ChoiceBox<?> individualBox;

    @FXML
    private CheckBox corporateCheck;

    @FXML
    private ChoiceBox<?> corporateBox;

    @FXML
    private ChoiceBox<?> transportBox;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    public OrderEditPageController(){  }

    RequestManager req_manager = new RequestManager();
    private OrderList locOrderList;
    private Transport locTransport;
    private Individual locIndividual;
    private Corporate locCorporate;

    private Stage dialogStage;

    private Boolean okClicked = false;

    private final ObservableList<String> types = FXCollections.observableArrayList("WORKING", "DONE", "CANCELED");

    /**
     * Инициализация контроллера при первом запуске
     */
    @FXML
    public void initialize(Stage editStage, OrderList orderList) {
        this.dialogStage = editStage;
        setLocOrderList(orderList);
        orderTypeBox.setItems(types);
    }

    /**
     * Устанавливает значение того заказа, который создается или редактируется
     * @param locOrderList
     */
    public void setLocOrderList(OrderList locOrderList) {
        this.locOrderList = locOrderList;
        startField.setText(locOrderList.getStartAddress());
        otherField.setText(locOrderList.getOtherAddress());
        endField.setText(locOrderList.getEndAddress());
        orderTypeBox.setValue(locOrderList.getOrderType());
        descriptionField.setText(locOrderList.getDescription());

        //setClient();

    }

    /*
    public void setClient(){
        boolean isIndividualNull = locOrderList.getIndividualId().toString().contains("null");
        boolean isCorporateNull = locOrderList.getCorporateId().toString().contains("null");

        if (!isIndividualNull & isCorporateNull){
            System.out.println("Our Client is Individual");
            individualCheck.setSelected(true);
            corporateCheck.setAllowIndeterminate(true);
            corporateBox.setDisable(true);

            try {
                individualBox.setItems(req_manager.getIndividuals());
                individualBox.setValue(locOrderList.getIndividualId().get());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (isIndividualNull & !isCorporateNull){
            System.out.println("Our Client is Corporate");
            corporateCheck.setSelected(true);
            individualCheck.setAllowIndeterminate(true);
            individualBox.setDisable(true);

            try {
                corporateBox.setItems(req_manager.getCorporates());
                corporateBox.setValue(locOrderList.getCorporateId().get());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (isIndividualNull & isCorporateNull){
            //
        }
    }

     */

    public OrderList getLocOrderList() {
        return locOrderList;
    }

    public void setLocTransport(Transport locTransport) {
        this.locTransport = locTransport;
    }

    public Transport getLocTransport() {
        return locTransport;
    }

    public void setLocIndividual(Individual locIndividual) {
        this.locIndividual = locIndividual;
    }

    public Individual getLocIndividual() {
        return locIndividual;
    }

    public Corporate getLocCorporate() {
        return locCorporate;
    }

    public void setLocCorporate(Corporate locCorporate) {
        this.locCorporate = locCorporate;
    }

    /**
     * Отрабатывет нажатия на кнопку OK
     */
    @FXML
    void handleOk() {
        okClicked = true;
        dialogStage.close();
    }

    /**
     * Отрабатывет нажатия на кнопку Cancel
     */
    @FXML
    void handleCancel() {
        dialogStage.close();
    }


    public boolean isOkClicked() {
        return okClicked;
    }
}
