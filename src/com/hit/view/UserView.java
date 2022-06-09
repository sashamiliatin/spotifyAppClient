package com.hit.view;

import com.hit.client.Song;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observer;

public class UserView extends JPanel implements ActionListener {
    private JTable songsTable;
    private GraphicalView gui;
    private String[] songTableColumn = {"Name", "Artist","Genre","Link",""};
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
        add(toolBar);
        add(jLabel);
        toolBar.add(backButton);
        toolBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, toolBar.getMinimumSize().height));
        add(userTableScroll);
    }

    public void loadSongs(List<Song> songs) {
        DefaultTableModel defaultTableModel = (DefaultTableModel) songsTable.getModel();
        defaultTableModel.setColumnIdentifiers(songTableColumn);

        for (Song song : songs) {
            String[] row = new String[4];
            row[0] = song.getSongName();
            row[1] = song.getSongArtist();
            row[2] = song.getSongGenre();
            row[3] = song.getSongLink();

            defaultTableModel.addRow(row);

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if("back".equals(e.getActionCommand())){
            this.gui.mainView(this);

        }
        else if ("search".equals(e.getActionCommand())){
            this.gui.mainView(this);
        }

    }
}
