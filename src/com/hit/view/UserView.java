package com.hit.view;
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.web.WebView;
//import javafx.stage.Stage;

import com.hit.client.Song;
import com.hit.driver.ButtonColumn;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Observer;


public class UserView extends JPanel implements ActionListener {
    private JTable songsTable;
    private GraphicalView gui;
    private String[] songTableColumn = {"Name", "Artist", "Genre", "Link", "Play", "Delete"};
    private JButton backButton, addSong;
    private JLabel jLabel;

    public UserView(GraphicalView gui) {
        this.gui = gui;
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.gui = gui;
        this.jLabel = new JLabel("Playlist");
        // toolbar for buttons
        JToolBar toolBar = new JToolBar();
        songsTable = new JTable();

        // scroll bar for table
        JScrollPane userTableScroll = new JScrollPane(songsTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        backButton = new JButton("Go Back");
        backButton.setActionCommand("back");
        backButton.addActionListener(this);
        addSong = new JButton("Add Song To Playlist");
        addSong.setActionCommand("add");
        addSong.addActionListener(this);
        add(toolBar);
        add(jLabel);
        toolBar.add(backButton);
        toolBar.add(addSong);
        toolBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, toolBar.getMinimumSize().height));
        add(userTableScroll);
    }

    public void loadSongs(List<Song> songs) {
        DefaultTableModel defaultTableModel = (DefaultTableModel) songsTable.getModel();
        defaultTableModel.setColumnIdentifiers(songTableColumn);

        for (Song song : songs) {
            String[] row = new String[6];
            row[0] = song.getSongName();
            row[1] = song.getSongArtist();
            row[2] = song.getSongGenre();
            row[3] = song.getSongLink();
            row[4] = "";
            row[5] = "";
            defaultTableModel.addRow(row);
        }
        Action play = new AbstractAction("Play") {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int row = Integer.valueOf(e.getActionCommand());
                URI songUri = null;
                try {
                    songUri = new URI((String) table.getModel().getValueAt(row, 3));
                } catch (URISyntaxException ex) {
                    try {
                        songUri = new URI("https://" + table.getModel().getValueAt(row, 3));
                    } catch (URISyntaxException exc) {
                        throw new RuntimeException(exc);
                    }
                }
                try {
                    gui.playSongVideo(songUri);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        };
        ButtonColumn buttonColumn = new ButtonColumn(songsTable, play, 4, "Play");
        buttonColumn.setMnemonic(KeyEvent.VK_D);

        Action delete = new AbstractAction("Delete") {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int row = Integer.valueOf(e.getActionCommand());
                if (JOptionPane.showConfirmDialog(null, "You will delete this song. Are you sure?", "WARNING",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    System.out.println(table.getModel().getValueAt(row, 3));
                    gui.deleteFromPlaylist((String) table.getModel().getValueAt(row, 3));
                } else {
                    //Do nothing
                }
            }
        };
        ButtonColumn buttonColumnDel = new ButtonColumn(songsTable, delete, 5, "Delete");
        buttonColumnDel.setMnemonic(KeyEvent.VK_D);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if ("back".equals(e.getActionCommand())) {
            this.gui.mainView(this);
        } else if ("add".equals(e.getActionCommand())) {
            this.gui.allSongsView();
        }

    }
}
