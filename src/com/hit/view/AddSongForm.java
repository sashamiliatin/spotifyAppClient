package com.hit.view;
import com.hit.client.Song;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class AddSongForm extends JPanel implements ActionListener   {
    private GraphicalView gui;
    private JLabel lblName, lblArtist, lblGenre, lblLink,label;
    private JTextField tfName, tfArtist, tfGenre, tfLink;
    private JButton btnClear, btnSubmit, btnBack, dummyBtn;
    private Image img;

    AddSongForm(GraphicalView gui)
    {
        this.gui = gui;
        btnBack = new JButton("Go Back");
        btnBack.setActionCommand("back");
        btnBack.addActionListener(this);
        this.img =new ImageIcon("src/com/hit/images/background5.jpeg").getImage();

        setLayout(null);
        Border line = BorderFactory.createLineBorder(Color.DARK_GRAY);
        Border empty = new EmptyBorder(20, 20, 20, 20);
        CompoundBorder border = new CompoundBorder(line, empty);
        label = new JLabel("Add Song Page");
        label.setForeground(Color.blue);
        label.setFont(new Font("Serif", Font.BOLD, 20));
        lblName = new JLabel("Song Name:");
        lblName.setForeground(Color.blue);
        lblArtist = new JLabel("Artist:");
        lblArtist.setForeground(Color.blue);
        lblGenre = new JLabel("Genre:");
        lblGenre.setForeground(Color.blue);
        lblLink = new JLabel("Link:");
        lblLink.setForeground(Color.blue);
        tfName = new JTextField();
        tfName.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        tfArtist = new JTextField();
        tfArtist.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        tfGenre = new JTextField();
        tfGenre.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        tfLink = new JTextField();
        tfLink.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        btnSubmit = new JButton("Submit");
        btnSubmit.setActionCommand("submit");
        btnClear = new JButton("Clear");
        btnClear.setActionCommand("clear");
        btnSubmit.addActionListener(this);
        btnClear.addActionListener(this);
        label.setBounds(100, 30, 400, 30);
        lblName.setBounds(80, 70, 200, 30);
        tfName.setBounds(250, 70, 200, 30);
        lblArtist.setBounds(80, 110, 200, 30);
        tfArtist.setBounds(250, 110, 200, 30);
        lblGenre.setBounds(80, 150, 200, 30);
        tfGenre.setBounds(250, 150, 200, 30);
        lblLink.setBounds(80, 190, 200, 30);
        tfLink.setBounds(250, 190, 200, 30);
        btnSubmit.setBounds(150, 450, 100, 30);
        btnClear.setBounds(270, 450, 100, 30);
        btnBack.setBounds(220, 550, 100, 30);
        add(label);
        add(lblName);
        add(lblArtist);
        add(tfName);
        add(lblGenre);
        add(lblLink);
        add(tfArtist);
        add(tfLink);
        add(tfGenre);
        add(btnSubmit);
        add(btnClear);
        add(btnBack);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("back".equals(e.getActionCommand())) {
            this.gui.adminView();
        } else if ("submit".equals(e.getActionCommand())) {
            if (tfName.getText().isEmpty() || tfArtist.getText().isEmpty() || tfGenre.getText().isEmpty() || tfLink.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Data Missing");
            } else {
                Song newSong = gui.createNewSong(
                        tfName.getText(),
                        tfArtist.getText(),
                        tfGenre.getText(),
                        tfLink.getText()
                );
                gui.addSong(newSong);
                this.gui.adminView();
            }
        } else {
            this.tfName.setText(null);
            this.tfArtist.setText(null);
            this.tfGenre.setText(null);
            this.tfLink.setText(null);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(img, 0, 0, null);
    }
   }


