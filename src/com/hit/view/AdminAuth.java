package com.hit.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;


public class AdminAuth extends JPanel implements ActionListener {
    private GraphicalView gui;
    private JLabel lblPass, label;
    private JPasswordField tfPass;
    private JButton btnSubmit, btnBack;
    private Image img;

    private Integer passCount;

    AdminAuth(GraphicalView gui) {
        this.gui = gui;
        btnBack = new JButton("Go Back");
        btnBack.setActionCommand("back");
        btnBack.addActionListener(this);
        this.img = new ImageIcon("src/com/hit/images/background6.jpeg").getImage();

        setLayout(null);
        Border line = BorderFactory.createLineBorder(Color.DARK_GRAY);
        Border empty = new EmptyBorder(20, 20, 20, 20);
        CompoundBorder border = new CompoundBorder(line, empty);
        label = new JLabel("Add Song Page");
        label.setForeground(Color.blue);
        label.setFont(new Font("Serif", Font.BOLD, 20));
        lblPass = new JLabel("Enter Password:");
        lblPass.setForeground(Color.black);
        tfPass = new JPasswordField();
        tfPass.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        btnSubmit = new JButton("Submit");
        btnSubmit.setActionCommand("submit");
        btnSubmit.addActionListener(this);
        lblPass.setBounds(80, 70, 200, 30);
        tfPass.setBounds(250, 70, 200, 30);
        btnSubmit.setBounds(150, 450, 100, 30);
        btnBack.setBounds(270, 450, 100, 30);
        add(lblPass);
        add(tfPass);
        add(btnSubmit);
        add(btnBack);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("submit".equals(e.getActionCommand())) {
            if (isPasswordCorrect(tfPass.getPassword())) {
                this.gui.adminView();
            } else {
                JOptionPane.showMessageDialog(null, "Wrong Password");
            }
            tfPass.setText("");
        } else if ("back".equals(e.getActionCommand())) {
            this.gui.mainView(this);
        }
    }

    private static boolean isPasswordCorrect(char[] input) {
        boolean isCorrect;
        char[] correctPassword = {'n', 'i', 's', 's', 'i', 'm'};

        if (input.length != correctPassword.length) {
            isCorrect = false;
        } else {
            isCorrect = Arrays.equals(input, correctPassword);
        }

        //Zero out the password.
        Arrays.fill(correctPassword, '0');

        return isCorrect;
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(img, 0, 0, null);
    }
}
