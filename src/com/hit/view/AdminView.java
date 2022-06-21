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

public class AdminView extends JPanel implements ActionListener {
    private JTable songsTable;
    private GraphicalView gui;
    private String[] songTableColumn = {"Name", "Artist", "Genre", "Link", "Delete"};
    private JButton backButton, button, addSong;
    private JLabel jLabel;
    private Image img;


    public AdminView(GraphicalView gui) {
        this.img =new ImageIcon("src/com/hit/images/background1.jpeg").getImage();

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
        addSong = new JButton("Add New Song");
        addSong.setActionCommand("add");
        addSong.addActionListener(this);
        add(toolBar);
        add(jLabel);
        toolBar.add(backButton);
        toolBar.add(addSong);
        toolBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, toolBar.getMinimumSize().height));
        userTableScroll.setViewport(new ImageViewport(img));
        userTableScroll.setViewportView(songsTable);
        add(userTableScroll);

    }

    public void loadSongs(List<Song> songs) {
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
        songsTable.getColumn("Link").setMinWidth(0);
        songsTable.getColumn("Link").setMaxWidth(0);
        songsTable.getColumn("Link").setWidth(0);
        songsTable.setBackground(new Color(255, 255, 255, 0));
        Action delete = new AbstractAction("Delete") {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int row = Integer.valueOf(e.getActionCommand());
                if (JOptionPane.showConfirmDialog(null, "You will delete this song. Are you sure?", "WARNING",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    System.out.println(table.getModel().getValueAt(row, 3));
                    gui.deleteSongFromDb((String) table.getModel().getValueAt(row, 3));
                } else {
                    //Do nothing
                }
            }
        };

        ButtonColumn buttonColumn = new ButtonColumn(songsTable, delete, 4, "Delete");
        buttonColumn.setMnemonic(KeyEvent.VK_D);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("back".equals(e.getActionCommand())) {
            this.gui.mainView(this);
        } else if ("add".equals(e.getActionCommand())) {
            this.gui.addSongView();
        }

    }
}
