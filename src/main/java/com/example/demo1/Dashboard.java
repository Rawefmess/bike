package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Dashboard implements Initializable {

    @FXML
    private Button edit;

    @FXML
    private Button evenement;

    @FXML
    private Button historique;

    @FXML
    private Button home;

    @FXML
    private Button incident;

    @FXML
    private Button reservation;

    @FXML
    private Button shop;


    @FXML
    private FontAwesomeIcon edit_icon;

    @FXML
    private Button station;

    @FXML
    private Label userr;

    @FXML
    private TextField username;

    @FXML
    private Circle circle;

    @FXML
    private TextField email;

    @FXML
    private ComboBox<?> gender;

    @FXML
    private AnchorPane navbar;

    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    private Image image;

    public void account() {

        userr.setText(user.username);

    }

    public void exit() {

        System.exit(0);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        account();
        editImageDesign();
        comboBox();
        displayImage();


    }


    private String genderData[] = {"Male", "Female", "Others"};

    public void comboBox() {

        List<String> list = new ArrayList<>();

        for (String data1 : genderData) {

            list.add(data1);

        }

        ObservableList dataList = FXCollections.observableArrayList(list);

        gender.setItems(dataList);

    }

    public void editImageDesign() {

        edit.setVisible(false);

        circle.setOnMouseEntered((MouseEvent event) -> {

            edit.setVisible(true);

        });

        circle.setOnMouseExited((MouseEvent event) -> {

            edit.setVisible(false);

        });

        edit.setOnMouseEntered((MouseEvent event) -> {

            edit.setVisible(true);

        });

        edit.setOnMouseExited((MouseEvent event) -> {

            edit.setVisible(false);
            edit_icon.setFill(Color.valueOf("#fff"));

        });

        edit.setOnMouseClicked((MouseEvent event) -> {

            edit_icon.setFill(Color.rgb(255, 106, 239));

        });

        edit.setOnMousePressed((MouseEvent event) -> {

            edit_icon.setFill(Color.rgb(255, 106, 239));

        });

        edit.setOnMouseReleased((MouseEvent event) -> {

            edit_icon.setFill(Color.valueOf("#fff"));

        });

    }

    public void textfieldRecord() {

//        THIS IS ARE JUST LIKE THE TEXTFIELD ON THE LOGIN AND SIGN UP FORM!
        if (username.isFocused()) {

            username.setStyle("-fx-background-color:#fff; -fx-border-width:2px");
//            DEFAULT TEXTFIELD!
            email.setStyle("-fx-background-color:transparent; -fx-border-width:1px");
            gender.setStyle("-fx-background-color:transparent; -fx-border-width:1px");

        } else if (email.isFocused()) {

            username.setStyle("-fx-background-color:transparent; -fx-border-width:1px");
            email.setStyle("-fx-background-color:#fff; -fx-border-width:2px");
            gender.setStyle("-fx-background-color:transparent; -fx-border-width:1px");


        } else if (gender.isFocused()) {

            username.setStyle("-fx-background-color:transparent; -fx-border-width:1px");
            email.setStyle("-fx-background-color:transparent; -fx-border-width:1px");

            gender.setStyle("-fx-background-color:#fff; -fx-border-width:2px");

        }

    }

    public void insertImage() {

        FileChooser open = new FileChooser();

        open.setTitle("Open image file");

        Stage stage = (Stage) navbar.getScene().getWindow();

        File file = open.showOpenDialog(stage);


        if (file != null) {

            user.path = file.getAbsolutePath();

            System.out.println(file.getAbsolutePath());

            image = new Image(file.toURI().toString(), 85, 85, false, true);

            circle.setFill(new ImagePattern(image));

            changeProfile();

        }

    }

    public void changeProfile() {

        connect = database.connectDb();

        String uri = user.path;

        uri = uri.replace("\\", "\\\\");

        String sql = "UPDATE `account` SET `image` = '"
                + uri + "' WHERE username = '" + user.username + "'";

        try {

            statement = connect.createStatement();
            statement.executeUpdate(sql);

        } catch (Exception e) {
        }

    }

    public void displayImage() {

//        MAKE SURE THAT YOU DIDNT FORGET THE "file:"
        String uri = "file:" + user.path;

        image = new Image(uri, 150, 150, false, true);

        circle.setFill(new ImagePattern(image));


    }
}
