package com.hit.view;

import com.hit.client.Song;
import com.hit.driver.ButtonColumn;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

public class AllSongList extends JPanel implements ActionListener {
    private JTable songsTable;
    private GraphicalView gui;
    private String[] songTableColumn = {"Name", "Artist", "Genre", "Link", "Add To Playlist"};
    private JButton backButton, button;
    private JLabel jLabel;

    public AllSongList(GraphicalView gui) {
        // uses box layout
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.gui = gui;
        this.jLabel = new JLabel("Song List");
        this.button = new JButton("Delete");
        this.button.setText("Delete");
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

    public void loadAllSongs(List<Song> songs) {
        DefaultTableModel defaultTableModel = (DefaultTableModel) songsTable.getModel();
        defaultTableModel.setColumnIdentifiers(songTableColumn);

        for (Song song : songs) {
            String[] row = new String[5];
            row[0] = song.getSongName();
            row[1] = song.getSongArtist();
            row[2] = song.getSongGenre();
            row[3] = song.getSongLink();
            row[4] = "";

            defaultTableModel.addRow(row);

        }
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
        ButtonColumn buttonColumn = new ButtonColumn(songsTable, add, 4, "Add");
        buttonColumn.setMnemonic(KeyEvent.VK_D);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("back".equals(e.getActionCommand())) {
            this.gui.userView();
        }
    }
}
