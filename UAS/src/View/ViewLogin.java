/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ConnectDatabase;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author calvi
 */
public class ViewLogin extends JFrame implements ActionListener {

    ConnectDatabase conn = new ConnectDatabase();
    JFrame f = new JFrame("");
    JTextField email;
    JPasswordField pass;
    JLabel labEmail, labPass, labError;
    JButton login, back;

    ViewLogin() {
        email = new JTextField("");
        pass = new JPasswordField("");
        labEmail = new JLabel("Email :");
        labPass = new JLabel("Password :");
        login = new JButton("Login");
        back = new JButton("Back");
        labError = new JLabel("Username/Pass Incorrect");
        
        labEmail.setBounds(20,20,100,20);
        labPass.setBounds(20,40,100,20);
        email.setBounds(120, 20, 140, 20);
        pass.setBounds(120, 40, 140, 20);
        login.setBounds(270, 20, 100, 40);
        back.setBounds(380, 20, 100, 40);
        labError.setBounds(120,60,200,20);
        
        f.add(email);
        f.add(pass);
        f.add(labEmail);
        f.add(labPass);
        f.add(login);
        f.add(back);
        f.add(labError);
        labError.setVisible(false);
        f.setSize(600, 600);
        login.addActionListener(this);
        back.addActionListener(this);

        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        new ViewLogin();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            String searchEmail = email.getText();
            String searchPass = pass.getText();
            int id=0;
            String query = "SELECT * FROM user WHERE email='" + searchEmail + "' AND password='"+ searchPass+"'";
            conn.connect();
            try {
                Statement stmt = conn.con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    id = rs.getInt("id");
                }
                if(id!=0){
                    new ViewKategori(id);
                    f.setVisible(false);
                }else{
                    labError.setVisible(true);
                }
            } catch (SQLException except) {
                except.printStackTrace();
            }
            conn.disconnect();
        }
        if (e.getSource() == back) {
            new ViewMainMenu();
            f.setVisible(false);
        }
    }
}
