package rocks.zipcode.atm;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import rocks.zipcode.atm.bank.AccountData;
import rocks.zipcode.atm.bank.Bank;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author ZipCodeWilmington
 */
public class CashMachineApp extends Application {

    private MenuBar menuBar = new MenuBar();
    private TextField field = new TextField();
    private TextField nameField = new TextField();
    private TextField emailField = new TextField();
    private TextField depositField = new TextField();
    private CashMachine cashMachine = new CashMachine(new Bank());

    Stage stage = new Stage();
    Stage accntStage = new Stage();

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
            this.stage.setScene(new Scene(beforeContent(),300,300));
        });

        FlowPane flowpane = new FlowPane();

        flowpane.getChildren().add(btnDeposit);
        flowpane.getChildren().add(btnWithdraw);
        flowpane.getChildren().add(btnExit);
        vbox.getChildren().addAll(cashInput, areaInfo, flowpane);
        return vbox;
    }

    public Parent beforeContent(){
        StackPane root = new StackPane(menuBar);
        Text title = new Text();
        title.setText("Havak Bank");
        title.setFont(Font.font("Helvatica", 20));
        title.setTextAlignment(TextAlignment.CENTER);
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
        btnCreate.setOnAction(event -> {
            this.accntStage.setScene((new Scene(accntContent(), 300,300)));
            this.accntStage.show();
        });

        btnSubmit.setMaxWidth(150.0);
        btnCreate.setMaxWidth(150.0);
        field.setTranslateY(-55);
        btnSubmit.setTranslateY(-26);
        btnCreate.setTranslateY(2);
        title.setTranslateY(-100);

        root.getChildren().addAll(field, btnSubmit,btnCreate,title);

        return root;
    }

    public Parent accntContent(){
        StackPane root = new StackPane();

        Text title = new Text();
        title.setText("Create Account");
        title.setFont(Font.font("Helvatica", 20));
        title.setTextAlignment(TextAlignment.CENTER);


        nameField.clear();
        nameField.setMaxWidth(155.0);
        nameField.setPromptText("Please enter your name");

        emailField.clear();
        emailField.setMaxWidth(155.0);
        emailField.setPromptText("Please enter your email");

        depositField.clear();
        depositField.setMaxWidth(155.0);
        depositField.setPromptText("Please enter your deposit");

        ComboBox comboBox = new ComboBox();

        comboBox.getItems().add("Basic");
        comboBox.getItems().add("Premium");
        comboBox.getItems().add("Savings");
        comboBox.getItems().add("Havak");

        comboBox.setMaxWidth(155.0);


//SUBMIT NEW ACCOUNT
        Button btnSubmit = new Button("Submit");
        btnSubmit.setOnAction(e -> {
            int deposit = Integer.parseInt(depositField.getText());
            String name = nameField.getText();
            String email = emailField.getText();
            String accountType = (String) comboBox.getValue();


//Need to create logic to make sure we get the info we need to create new account

            cashMachine.getBank().addAccount(name,email,deposit,accountType);

            depositField.clear();
            nameField.clear();
            emailField.clear();
        });

//Styling for buttons
        title.setTranslateY(-100);
        nameField.setTranslateY(-65);
        emailField.setTranslateY(-35);
        depositField.setTranslateY(-5);
        comboBox.setTranslateY(25);
        btnSubmit.setTranslateY(55);


        root.getChildren().addAll(nameField,emailField,depositField,title,btnSubmit,comboBox);

        return root;
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        this.stage.setScene(new Scene(beforeContent(),300,300));
        this.stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
