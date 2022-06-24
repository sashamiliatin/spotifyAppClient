package com.hit.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class searchSongView extends JPanel implements ActionListener {
//    private JTable songsTable;
    private String[] songTableColumn = {"Name", "Artist", "Genre", "Link", "Add To Playlist"};
    private JButton backButton, searchButton;
    private JLabel jLabel;
//    private Image img;
//    private TextField searchField;
    private GraphicalView gui;
    private Image img;

    public searchSongView( GraphicalView gui) {
        this.gui = gui;
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        jLabel = new JLabel("Search Song Page");
        jLabel.setForeground(Color.blue);
        jLabel.setFont(new Font("Serif", Font.BOLD, 20));
        add(jLabel);
        JToolBar toolBar = new JToolBar();
        add(toolBar);
        backButton = new JButton("Go Back");
        backButton.setActionCommand("back");
        backButton.addActionListener(this);
        searchButton = new JButton("search");
        searchButton.setActionCommand("search");
        searchButton.addActionListener(this);
        toolBar.add(backButton);
//        toolBar.setBounds();
        setLayout(new BorderLayout());

//        jLabel.setForeground(Color.blue);
//        jLabel.setFont(new Font("Serif", Font.BOLD, 20));
//        JToolBar toolBar = new JToolBar();
//        add(toolBar);
//        add(jLabel);
//        toolBar.add(backButton);
//        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
//        this.img = new ImageIcon("src/com/hit/images/background6.jpeg").getImage();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
