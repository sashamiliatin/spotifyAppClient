package com.hit.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JPanel implements ActionListener {
    private UserView userView;
    private AdminView adminView;
    private GraphicalView gui;
    protected JButton admin;
    protected JButton user;
    private JLabel jLabel1;
    private JPanel panel;

    public MainView(GraphicalView gui) {
        this.gui = gui;
        this.initComponents();
    }

    private void initComponents() {
        this.setPreferredSize(new Dimension(200, 300));
        this.setLayout(new FlowLayout());
        this.jLabel1 = new JLabel("Main Menu");
        this.admin = new JButton("Admin");
        this.user = new JButton("User");
        this.user.setActionCommand("user");
        this.admin.setActionCommand("admin");
        this.admin.addActionListener(this);
        this.user.addActionListener(this);
        this.add(this.jLabel1);
        this.add(this.user);
        this.add(this.admin);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if ("user".equals(e.getActionCommand())) {
            this.gui.userView();

        } else {
            this.gui.adminView();
        }

    }


}
