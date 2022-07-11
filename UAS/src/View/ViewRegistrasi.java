/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ConnectDatabase;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

/**
 *
 * @author calvi
 */
public class ViewRegistrasi extends JFrame implements ActionListener {

    ConnectDatabase conn = new ConnectDatabase();
    JFrame f = new JFrame("");
    JLabel labTitle, labEmail, labNama, labPass, labFc, labCat, namaFc;
    JTextField email, nama, valueFc;
    JPasswordField pass;
    JFileChooser fc;
    JComboBox category;
    JButton buttonFc, register;
    int[] ArrIDCategory;

    ViewRegistrasi() {
        String query = "SELECT * FROM categoryuser";
        conn.connect();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int limit = 0;
            while (rs.next()) {
                limit++;
            }

            ResultSet rs2 = stmt.executeQuery(query);
            int i = 0;
            String ArrNameCategory[] = new String[limit];
            ArrIDCategory = new int[limit];
            while (rs2.next()) {
                String name = rs2.getString("name");
                int id = rs2.getInt("id");
                ArrNameCategory[i] = name;
                ArrIDCategory[i] = id;
                i++;
            }
            category = new JComboBox(ArrNameCategory);
        } catch (SQLException except) {
            except.printStackTrace();
        }
        conn.disconnect();

        fc = new JFileChooser();
        labTitle = new JLabel("MENU REGISTER");
        labEmail = new JLabel("Email :");
        labNama = new JLabel("Name :");
        labPass = new JLabel("Password :");
        labFc = new JLabel("Foto :");
        labCat = new JLabel("Category :");
        namaFc = new JLabel("");
        email = new JTextField("");
        nama = new JTextField("");
        pass = new JPasswordField("");
        valueFc = new JTextField("");
        buttonFc = new JButton("Open File");
        register = new JButton("Register");

        labTitle.setBounds(20, 20, 100, 20);
        labEmail.setBounds(20, 70, 100, 20);
        labNama.setBounds(20, 100, 100, 20);
        labPass.setBounds(20, 130, 100, 20);
        labFc.setBounds(20, 160, 100, 20);
        labCat.setBounds(20, 190, 100, 20);
        email.setBounds(120, 70, 100, 20);
        nama.setBounds(120, 100, 100, 20);
        pass.setBounds(120, 130, 100, 20);
        buttonFc.setBounds(120, 160, 100, 20);
        namaFc.setBounds(220, 160, 200, 20);
        category.setBounds(120, 190, 100, 20);
        register.setBounds(120, 220, 100, 20);

        f.add(labTitle);
        f.add(labEmail);
        f.add(labNama);
        f.add(labPass);
        f.add(labFc);
        f.add(namaFc);
        namaFc.setVisible(false);
        f.add(labCat);
        f.add(email);
        f.add(nama);
        f.add(pass);
        f.add(buttonFc);
        f.add(category);
        f.add(register);
        buttonFc.addActionListener(this);
        register.addActionListener(this);

        f.setSize(600, 600);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new ViewRegistrasi();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonFc) {
            int r = fc.showOpenDialog(null);
            if (r == JFileChooser.APPROVE_OPTION) {
                String Path = fc.getSelectedFile().getAbsolutePath();
                String getName = (fc.getSelectedFile().getAbsolutePath().substring(fc.getSelectedFile().getAbsolutePath().lastIndexOf("\\") + 1));
                valueFc.setText(Path);
                namaFc.setText(getName);
            } else {
                namaFc.setText("Error");
            }
            namaFc.setVisible(true);
        }
        if (e.getSource() == register) {
            int idPilihan = 0;
            String pilihanCategory = category.getSelectedItem().toString();
            String querySearch = "SELECT id FROM categoryuser WHERE name='"+pilihanCategory+"'";
                conn.connect();
                try {
                    Statement stmt = conn.con.createStatement();
                    ResultSet rs = stmt.executeQuery(querySearch);
                    while(rs.next()){
                        idPilihan = rs.getInt("id");
                    }
                } catch (SQLException except) {
                    except.printStackTrace();
                }
                conn.disconnect();
                
            int length = 4;
            String[] value = new String[length];
            value[0] = nama.getText();
            value[1] = email.getText();
            value[2] = pass.getText();
            value[3] = valueFc.getText();
            
            boolean kosong = false;
            int j=0;
            while(j<length && kosong==false){
                if (value[j].isEmpty()) {
                    kosong = true;
                }
                j++;
            }
            
            if (kosong == false) {
                //database
                String values = "'" + value[0] + "'";
                for (int i = 1; i < length; i++) {
                    if(i==3){
                        values = values +", '" + idPilihan + "'";
                    }
                    values = values + ", '" + value[i] + "'";
                }
                String query = "INSERT INTO user(name,email,password,idCategory,photo)"
                        + "VALUES(" + values + ")";
                System.out.println(query);
                conn.connect();
                try {
                    Statement stmt = conn.con.createStatement();
                    stmt.executeUpdate(query);
                } catch (SQLException except) {
                    except.printStackTrace();
                }
                conn.disconnect();

                f.setVisible(false);
                JOptionPane.showMessageDialog(null,"berhasil");
                new ViewMainMenu();
            }else{
                JOptionPane.showMessageDialog(null, "Error, terdapat field kosong");
            }
        }
    }
}
