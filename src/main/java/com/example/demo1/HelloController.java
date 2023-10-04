package com.example.demo1;
import java.sql.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelloController implements Initializable {
    @FXML
    private Hyperlink create_account;

    @FXML
    private TextField email;

    @FXML
    private Hyperlink login;

    @FXML
    private Button login_btn;

    @FXML
    private ImageView mybike;

    @FXML
    private ImageView mybike1;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField password1;

    @FXML
    private Button signup_bttn;

    @FXML
    private TextField username;

    @FXML
    private TextField username1;

    @FXML
    private AnchorPane login_form;

    @FXML
    private AnchorPane signup_form;

    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;

    private double x = 0;
    private double y = 0;


    public void exit(){

        System.exit(0);

    }

    public void textfieldDesign(){
        if(username.isFocused()){

            username.setStyle("-fx-background-color:#fff;"
                    + "-fx-border-width:2px");

            password.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");

        }else if(password.isFocused()){

            username.setStyle("-fx-background-color:transparent;"
                    + "-f-border-width:1px");

            password.setStyle("-fx-background-color:#fff;"
                    + "-fx-border-width:2px");

        }

    }
    public void textfieldDesign1(){

        if(username1.isFocused()){

            username1.setStyle("-fx-background-color:#fff;"
                    + "-fx-border-width:2px");

            password1.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");

            password1.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");

        }else if(email.isFocused()){

            username1.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");

            password1.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");

            email.setStyle("-fx-background-color:#fff;"
                    + "-fx-border-width:2px");

        }else if(password1.isFocused()){

            username1.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");

            password1.setStyle("-fx-background-color:#fff;"
                    + "-fx-border-width:2px");

            email.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");

        }

    }
    public void dropShadowEffect(){

        DropShadow original = new DropShadow(30, Color.valueOf("#ae44a5"));

        original.setRadius(30);

        mybike.setEffect(original);

        mybike1.setEffect(original);

        mybike.setOnMouseEntered((MouseEvent event) ->{

            DropShadow shadow = new DropShadow(60, Color.valueOf("#e03ed5"));

            mybike.setCursor(Cursor.HAND);
            mybike.setStyle("-fx-text-fill:#ee5fe4");
            mybike.setEffect(shadow);

        });

        mybike.setOnMouseExited((MouseEvent event) ->{

            DropShadow shadow = new DropShadow(20, Color.valueOf("#ae44a5"));

            shadow.setRadius(30);

            mybike.setStyle("-fx-text-fill:#000");
            mybike.setEffect(shadow);

        });

        mybike1.setOnMouseEntered((MouseEvent event) ->{

            DropShadow shadow = new DropShadow(60, Color.valueOf("#e03ed5"));

            mybike1.setCursor(Cursor.HAND);
            mybike1.setStyle("-fx-text-fill:#ee5fe4");
            mybike1.setEffect(shadow);

        });

        mybike1.setOnMouseExited((MouseEvent event) ->{

            DropShadow shadow = new DropShadow(20, Color.valueOf("#ae44a5"));

            shadow.setRadius(30);

            mybike1.setStyle("-fx-text-fill:#000");
            mybike1.setEffect(shadow);

        });

    }
    public void changeForm(ActionEvent event){

        if(event.getSource() == create_account){

            signup_form.setVisible(true);
            login_form.setVisible(false);

            username1.setText("");
            password1.setText("");
            email.setText("");

        }else if(event.getSource() == login){

            login_form.setVisible(true);
            signup_form.setVisible(false);

            username.setText("");
            password.setText("");

        }

    }
    public void login(){

        connect = database.connectDb();

        String sql = "SELECT * FROM utilisateur WHERE username = ? and password = ?";

        try{

            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username.getText());
            prepare.setString(2, password.getText());

            result = prepare.executeQuery();

            if(result.next()){

                user.username = result.getString("username");

                user.path = result.getString("image");



                login_btn.getScene().getWindow().hide();

                Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));

                Scene scene = new Scene(root);

                Stage stage = new Stage();

                root.setOnMousePressed((MouseEvent event) ->{

                    x = event.getSceneX();
                    y = event.getSceneY();



                });

                root.setOnMouseDragged((MouseEvent event) ->{

                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(0.8);

                });

                root.setOnMouseReleased((MouseEvent event) ->{

                    stage.setOpacity(1);

                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();

            }else{

                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Wrong Username/Password!");
                alert.showAndWait();

            }

        }catch(Exception e){}

    }
    public boolean validationEmail(){
//
        Pattern pattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");

        Matcher match = pattern.matcher(email.getText());

        if(match.find() && match.group().equals(email.getText())){

            return true;

        }else{

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Email");
            alert.showAndWait();

            return false;

        }

    }

    public void signup() {
        connect = database.connectDb();

        String sql = "INSERT INTO utilisateur (username, password, email, image) VALUES (?, ?, ?, ?)";

        try {
            if (username1.getText().isEmpty() || password1.getText().isEmpty() || email.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs !");
                alert.showAndWait();
            } else if (password1.getText().length() < 8) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("Mot de passe invalide (doit avoir au moins 8 caractères) !");
                alert.showAndWait();
            } else {
                if (validationEmail()) {
                    String uri = "C:\\Users\\rawef\\OneDrive\\Desktop\\demo1\\src\\main\\resources\\com\\example\\demo1\\rawef.jpg";
                    user.path = uri;

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, username1.getText());
                    prepare.setString(2, password1.getText());
                    prepare.setString(3, email.getText());
                    prepare.setString(4, user.path);

                    prepare.executeUpdate();

                    email.setText("");
                    username1.setText("");
                    password1.setText("");

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message de réussite");
                    alert.setHeaderText(null);
                    alert.setContentText("Compte créé avec succès !");
                    alert.showAndWait();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les exceptions SQL ici, afficher des messages d'erreur spécifiques
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dropShadowEffect();

    }
}