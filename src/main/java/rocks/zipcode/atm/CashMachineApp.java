package rocks.zipcode.atm;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
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

        root.getChildren().addAll(field, btnSubmit,btnCreate);

        return root;
    }

    public Parent accntContent(){
        StackPane root = new StackPane(menuBar);

        Menu menu1 = new Menu("File");
        Menu menu2 = new Menu("Account");
        MenuItem createAccount = new MenuItem("Create Account");
        MenuItem exitApp = new MenuItem("Exit");
        menu1.getItems().add(exitApp);
        menu2.getItems().add(createAccount);
        menuBar.getMenus().addAll(menu1,menu2);

        Cursor.cursor("CLOSED_HAND");

        exitApp.setOnAction(event -> {
            accntStage.close();
        });

//        Need to figure out how to set background image;
//        BackgroundImage myBI = new BackgroundImage(new Image("icon_pattern.png",300,300,true,true),
//                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
//                root.setBackground(new Background(myBI));


        Button btnBasic = new Button("Basic");
        btnBasic.setOnAction(e -> {
        //add action here
        });

        Button btnPremium = new Button("Premium");
        btnBasic.setOnAction(e -> {
        //add action here
        });

        Button btnSavings = new Button("Savings");
        btnBasic.setOnAction(e -> {
        //add action here
        });

        Button btnHavak = new Button("Havak");
        btnBasic.setOnAction(e -> {
        //add action here
        });


        btnBasic.setTranslateX(-92);
        btnPremium.setTranslateX(-35);
        btnSavings.setTranslateX(30);
        btnHavak.setTranslateX(87);

        root.getChildren().addAll(btnBasic,btnPremium,btnSavings,btnHavak);

        StackPane.setAlignment(menuBar,Pos.TOP_CENTER);
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
