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

    private TextField field = new TextField();
    private TextField nameField = new TextField();
    private TextField emailField = new TextField();
    private TextField depositField = new TextField();
    private CashMachine cashMachine = new CashMachine(new Bank());

    Stage stage = new Stage();
    Stage accntStage = new Stage();

    private Parent createContent() {
        VBox vbox = new VBox(15);
        vbox.setPrefSize(600, 600);
        Text areaInfo = new Text();
        TextField cashInput = new TextField();
        cashInput.setMaxWidth(200);
        cashInput.setPromptText("Enter amount");
        Text errorInfo = new Text();
        errorInfo.setFont(Font.font("Helvatica", 12));
        errorInfo.setFill(Color.RED);

        areaInfo.setText(cashMachine.toString());

        Button btnDeposit = new Button("Deposit");
        btnDeposit.setOnAction(e -> {
            float amount = Float.parseFloat(cashInput.getText());
            cashMachine.deposit(amount);

            areaInfo.setText(cashMachine.toString());
            errorInfo.setText(cashMachine.getErrorMessage());
        });

        Button btnWithdraw = new Button("Withdraw");
        btnWithdraw.setOnAction(e -> {

            int amount = Integer.parseInt(cashInput.getText());
            cashMachine.withdraw(amount);

            areaInfo.setText(cashMachine.toString());
            if(cashMachine.getAccountData().getBalance() < 0){
                errorInfo.setText("You have over drafted " + cashMachine.getAccountData().getBalance());
            }else {
                errorInfo.setText(cashMachine.getErrorMessage());
            }
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
        btnExit.setLayoutY(400);
        vbox.getChildren().addAll(areaInfo, cashInput, flowpane, errorInfo);
        return vbox;
    }

    public Parent beforeContent(){
        StackPane root = new StackPane();
        Text title = new Text();
        title.setText("Havak Bank");
        title.setFont(Font.font("Helvatica", 20));
        title.setTextAlignment(TextAlignment.CENTER);
        field.clear();
        field.setMaxWidth(150.0);
        field.setPromptText("Please enter your ID");

        //Error Message
        Text errorInfo = new Text();
        errorInfo.setFont(Font.font("Helvatica", 12));
        errorInfo.setFill(Color.RED);

        Button btnSubmit = new Button("Log in");
        btnSubmit.setOnAction(e -> {
            if(field.getText().isEmpty()){
                errorInfo.setText("ERROR: Please enter a valid ID or create an account!");
            } else if(!numberOrNot(field.getText())){
                errorInfo.setText("ERROR: Please enter an actual number!");
            } else if(cashMachine.getBank().checkId(Integer.parseInt(field.getText()))){
                errorInfo.setText("ERROR: Please enter a valid ID!");
            }  else {
                int id = Integer.parseInt(field.getText());
                cashMachine.login(id);
                this.stage.setScene(new Scene(createContent()));
            }
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
        errorInfo.setTranslateY(25);

        root.getChildren().addAll(field, btnSubmit,btnCreate,title,errorInfo);

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

        //Error Message
        Text errorInfo = new Text();
        errorInfo.setFont(Font.font("Helvatica", 12));
        errorInfo.setFill(Color.RED);


        //SUBMIT NEW ACCOUNT
        Button btnSubmit = new Button("Submit");
        btnSubmit.setOnAction(e -> {
            int deposit = 0;
            String text = depositField.getText();
            if(depositField.getText().isEmpty() || text == ""){
                errorInfo.setText("ERROR: Please enter a number to deposit!");
            } else if(!numberOrNot(depositField.getText())){
                errorInfo.setText("ERROR: Please enter an actual number!");
            } else { deposit = Integer.parseInt(depositField.getText());
            }

            String name = nameField.getText();
            String email = emailField.getText();
            String accountType = (String) comboBox.getValue();

            if( name.isEmpty()){
                errorInfo.setText("ERROR: Please enter a name!");
            } else if(email.isEmpty()){
                errorInfo.setText("ERROR: Please enter an email!");
            } else if (deposit < 200){
                errorInfo.setText("ERROR: Please deposit an amount over $200!");
            } else if (accountType == null || accountType == ""){
                errorInfo.setText("ERROR: Please select an account type!");
            }  else {
                int id = cashMachine.getBank().getNewId();
                cashMachine.getBank().addAccount(name,email,deposit,accountType,id);
                depositField.clear();
                nameField.clear();
                emailField.clear();
                errorInfo.setText("Your account "+ id + "! \n" +
                                    "If you lose it your ID, please call call: \n" +
                                    "302 - 256 -5203!");
            }
        });


        //Styling for buttons
        title.setTranslateY(-100);
        nameField.setTranslateY(-65);
        emailField.setTranslateY(-35);
        depositField.setTranslateY(-5);
        comboBox.setTranslateY(25);
        btnSubmit.setTranslateY(55);
        errorInfo.setTranslateY(90);

        //Grab all nodes and add them to StackPane
        root.getChildren().addAll(nameField,emailField,depositField,title,btnSubmit,comboBox,errorInfo);

        return root;
    }

    boolean numberOrNot(String checkNum)
    {
        try
        {
            Integer.parseInt(checkNum);
        }
        catch(NumberFormatException ex)
        {
            return false;
        }
        return true;
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
