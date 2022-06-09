package com.hit.view;

import com.hit.client.Song;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminView extends JPanel implements ActionListener {
    private JTable songsTable;// = new JTable(data, columnNames);
    private GraphicalView gui;
    private String[] songTableColumn = {"Name", "Artist","Genre","Link",""};
    private JButton backButton;

    public AdminView() {
        // uses box layout
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        // toolbar for buttons
        JToolBar toolBar = new JToolBar();
        songsTable = new JTable();

        // scroll bar for table
        JScrollPane userTableScroll = new JScrollPane(songsTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        backButton = new JButton("Go Back");
        add(toolBar);
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

    // event listener for back button
    public void backButton(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
