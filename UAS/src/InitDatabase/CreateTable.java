/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InitDatabase;

import Controller.ConnectDatabase;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author calv
 */
public class CreateTable {

    ConnectDatabase conn = new ConnectDatabase();

    public CreateTable() {
        String query = "CREATE TABLE CategoryUser("
                + "id INT PRIMARY KEY AUTO_INCREMENT,"
                + "name VARCHAR(255));";
        String query2 = "CREATE TABLE User("
                + "id INT PRIMARY KEY AUTO_INCREMENT,"
                + "name VARCHAR(255),"
                + "email VARCHAR(255),"
                + "password VARCHAR(255),"
                + "idCategory INT,"
                + "photo VARCHAR(255));";
        conn.connect();
        try {
            Statement stmt = conn.con.createStatement();
            stmt.execute(query);
            stmt.execute(query2);
            System.out.println("CREATE TABLE SUKSES");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conn.disconnect();
    }
    public static void main(String[] args) {
        new CreateTable();
    }
}
