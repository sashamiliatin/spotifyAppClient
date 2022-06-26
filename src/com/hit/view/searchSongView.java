package com.hit.view;

import com.hit.client.Song;
import com.hit.driver.ButtonColumn;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class searchSongView extends JPanel implements ActionListener {
    private GraphicalView gui;
    private JTable songsTable;

    private String[] songTableColumn = {"Name", "Artist", "Genre", "Link", "Add"};
    private JLabel lblName, label;
    private JTextField tfName;
    private JButton btnClear, btnSearch, btnBack;
    private Image img;

    public searchSongView(GraphicalView gui) {
        this.gui = gui;

        this.img = new ImageIcon("src/com/hit/images/background6.jpeg").getImage();

        songsTable = new JTable();
        JScrollPane searchTableScroll = new JScrollPane(songsTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        searchTableScroll.setBounds(0, 120, 500, 300);

        DefaultTableModel defaultTableModel = (DefaultTableModel) songsTable.getModel();
        defaultTableModel.setRowCount(0);
        defaultTableModel.setColumnIdentifiers(songTableColumn);

        setLayout(null);
        Border line = BorderFactory.createLineBorder(Color.DARK_GRAY);
        Border empty = new EmptyBorder(20, 20, 20, 20);
        CompoundBorder border = new CompoundBorder(line, empty);
        label = new JLabel("Search Song Page");
        label.setForeground(Color.black);
        label.setFont(new Font("Serif", Font.BOLD, 20));
        lblName = new JLabel("Search Parameter:");
        lblName.setForeground(Color.black);
        tfName = new JTextField();
        tfName.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        btnBack = new JButton("Go Back");
        btnBack.setActionCommand("back");
        btnBack.addActionListener(this);
        btnSearch = new JButton("Submit");
        btnSearch.setActionCommand("search");
        btnClear = new JButton("Clear");
        btnClear.setActionCommand("clear");
        btnSearch.addActionListener(this);
        btnClear.addActionListener(this);
        label.setBounds(100, 30, 400, 30);
        lblName.setBounds(80, 70, 200, 30);
        tfName.setBounds(250, 70, 200, 30);
        btnSearch.setBounds(150, 450, 100, 30);
        btnClear.setBounds(270, 450, 100, 30);
        btnBack.setBounds(220, 550, 100, 30);
        add(label);
        add(lblName);
        add(tfName);
        add(btnSearch);
        add(btnClear);
        add(btnBack);
//        searchTableScroll.setViewport(new ImageViewport(img));
        searchTableScroll.setViewportView(songsTable);
        searchTableScroll.setOpaque(false);
        searchTableScroll.getViewport().setOpaque(false);
        add(searchTableScroll);
    }

    public void loadSearchedSongs(List<Song> songs) {
        DefaultTableModel defaultTableModel = (DefaultTableModel) songsTable.getModel();
        defaultTableModel.setRowCount(0);
        defaultTableModel.setColumnIdentifiers(songTableColumn);
        songsTable.setOpaque(false);
        for (Song song : songs) {
            String[] row = new String[5];
            row[0] = song.getSongName();
            row[1] = song.getSongArtist();
            row[2] = song.getSongGenre();
            row[3] = song.getSongLink();
            row[4] = "";
            defaultTableModel.addRow(row);
        }
        songsTable.getColumn("Link").setMinWidth(0);
        songsTable.getColumn("Link").setMaxWidth(0);
        songsTable.getColumn("Link").setWidth(0);
//        songsTable.setOpaque(false);
        songsTable.setBackground(new Color(255, 255, 255, 0));
        Action add = new AbstractAction("Add") {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int row = Integer.valueOf(e.getActionCommand());
                Song newSong = gui.createNewSong(
                        (String) table.getModel().getValueAt(row, 0),
                        (String) table.getModel().getValueAt(row, 1),
                        (String) table.getModel().getValueAt(row, 2),
                        (String) table.getModel().getValueAt(row, 3)
                );
                gui.addToPlaylist(newSong);
            }

        };
        ButtonColumn buttonColumnDel = new ButtonColumn(songsTable, add, 4, "Add");
        buttonColumnDel.setMnemonic(KeyEvent.VK_D);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("back".equals(e.getActionCommand())) {
            this.gui.userView();
        } else if ("search".equals(e.getActionCommand())) {
            if (!(tfName.getText()).isEmpty()) {
                this.gui.searchSongs(tfName.getText());
            }
        } else {
            this.tfName.setText(null);
            DefaultTableModel defaultTableModel = (DefaultTableModel) songsTable.getModel();
            defaultTableModel.setRowCount(0);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, null);
    }
}