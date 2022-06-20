package com.hit.view;

import com.hit.client.Song;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddSongForm extends JPanel implements ActionListener {
    private GraphicalView gui;
    private JLabel lblName, lblArtist, lblGenre, lblLink;
    private JTextField tfName, tfArtist, tfGenre, tfLink;
    private JButton btnClear, btnSubmit, btnBack, dummyBtn;
//    final static boolean shouldFill = true;

    public AddSongForm(GraphicalView gui) {
        this.gui = gui;
        setLayout(new BorderLayout(30, 30));
        JToolBar toolBar = new JToolBar();

        btnBack = new JButton("Go Back");
        btnBack.setActionCommand("back");
        btnBack.addActionListener(this);
        toolBar.add(btnBack);
        add(toolBar, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 20));
        add(formPanel, BorderLayout.CENTER);

        Border line = BorderFactory.createLineBorder(Color.DARK_GRAY);
        Border empty = new EmptyBorder(20, 20, 20, 20);
        CompoundBorder border = new CompoundBorder(line, empty);


        lblName = new JLabel("Song Name");
        lblName.setBorder(BorderFactory.createEmptyBorder(0, 60, 0, 0));
        formPanel.add(lblName);

        tfName = new JTextField();
        tfName.setBorder(border);
        formPanel.add(tfName);

        lblArtist = new JLabel("Artist");
        lblArtist.setBorder(BorderFactory.createEmptyBorder(0, 60, 0, 0));
        formPanel.add(lblArtist);

        tfArtist = new JTextField();
        tfArtist.setBorder(border);
        formPanel.add(tfArtist);

        lblGenre = new JLabel("Genre");
        lblGenre.setBorder(BorderFactory.createEmptyBorder(0, 60, 0, 0));
        formPanel.add(lblGenre);

        tfGenre = new JTextField();
        tfGenre.setBorder(border);
        formPanel.add(tfGenre);

        lblLink = new JLabel("Link");
        lblLink.setBorder(BorderFactory.createEmptyBorder(0, 60, 0, 0));
        formPanel.add(lblLink);

        tfLink = new JTextField();
        tfLink.setBorder(border);
        formPanel.add(tfLink);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 30));
        add(buttonPanel, BorderLayout.SOUTH);

        btnSubmit = new JButton("submit");
        btnSubmit.addActionListener(this);
        btnSubmit.setActionCommand("submit");
        buttonPanel.add(btnSubmit);

        btnClear = new JButton("Clear");
        btnClear.addActionListener(this);
        btnClear.setActionCommand("clear");
        buttonPanel.add(btnClear);
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
}
