/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author calvi
 */
public class ViewMainMenu extends JFrame implements ActionListener {

    private boolean exit;
    JFrame f = new JFrame();
    JButton button1, button2, button3;

    public ViewMainMenu() {
        button1 = new JButton("Login Pengguna");
        button2 = new JButton("Registrasi Pengguna Baru");
        button3 = new JButton("Lihat Data Pengguna Berdasarkan Kategori Dipilih");
        button1.setBounds(50, 50, 300, 100);
        button2.setBounds(50, 200, 300, 100);
        button3.setBounds(50, 350, 300, 100);
        f.add(button1);
        f.add(button2);
        f.add(button3);
        f.setSize(600, 600);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);

        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            new ViewLogin();
            f.setVisible(false);
        }
        if (e.getSource() == button2) {
            new ViewRegistrasi();
            f.setVisible(false);
        }
        if (e.getSource() == button3) {
            new ViewKategori(0);
            f.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new ViewMainMenu();
    }
}
