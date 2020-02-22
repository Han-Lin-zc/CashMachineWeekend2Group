package rocks.zipcode.atm;

import rocks.zipcode.atm.bank.Bank;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;

/**
 * @author ZipCodeWilmington
 */
public class CashMachineApp extends Application {

    private TextField field = new TextField();
    private CashMachine cashMachine = new CashMachine(new Bank());
    Stage stage = new Stage();

    private Parent createContent() {
        VBox vbox = new VBox(10);
        vbox.setPrefSize(600, 600);

        TextArea areaInfo = new TextArea();
        TextField cashInput = new TextField();
        cashInput.setMaxWidth(150.0);
        cashInput.setPromptText("Enter amount");

        areaInfo.setText(cashMachine.toString());

        Button btnDeposit = new Button("Deposit");
        btnDeposit.setOnAction(e -> {
            int amount = Integer.parseInt(cashInput.getText());
            cashMachine.deposit(amount);

            areaInfo.setText(cashMachine.toString());
        });

        Button btnWithdraw = new Button("Withdraw");
        btnWithdraw.setOnAction(e -> {
            int amount = Integer.parseInt(cashInput.getText());
            cashMachine.withdraw(amount);

            areaInfo.setText(cashMachine.toString());
        });

        Button btnExit = new Button("Log out");
        btnExit.setOnAction(e -> {
            cashMachine.exit();
            this.stage.setScene(new Scene(beforeContent()));
        });

        FlowPane flowpane = new FlowPane();

        flowpane.getChildren().add(btnDeposit);
        flowpane.getChildren().add(btnWithdraw);
        flowpane.getChildren().add(btnExit);
        vbox.getChildren().addAll(cashInput, areaInfo, flowpane);
        return vbox;
    }

    public Parent beforeContent(){
        VBox vbox = new VBox(10);
        vbox.setPrefSize(600, 600);

        field.clear();
        field.setMaxWidth(150.0);
        field.setPromptText("Please enter your ID");

        Button btnSubmit = new Button("Log in");
        btnSubmit.setOnAction(e -> {
            int id = Integer.parseInt(field.getText());
            cashMachine.login(id);

            this.stage.setScene(new Scene(createContent()));
        });

        Button btnCreate = new Button("Create new account");

        FlowPane flowpane = new FlowPane();
        flowpane.getChildren().add(btnSubmit);
        flowpane.getChildren().add(btnCreate);
        vbox.getChildren().addAll(field, flowpane);

        return vbox;
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        this.stage.setScene(new Scene(beforeContent()));
        this.stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
