package org.example;

import java.sql. *;

public class Conexion {

    public static Connection conectar(){
        Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/equipos","root","Nicolas1_");

        }catch (Exception e){
            System.out.println(e);
        }
        return con;
    }
}
