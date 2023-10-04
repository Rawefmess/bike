package com.example.demo1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {
    public static Connection connectDb(){

        try{

            Class.forName("com.mysql.jdbc.Driver");

            Connection connect =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev4se1", "root", "");

            return connect;

        }catch(Exception e){e.printStackTrace();}

        return null;
    }

}
