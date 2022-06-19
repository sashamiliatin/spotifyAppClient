package com.hit.view;

import com.hit.client.Song;
import com.hit.driver.ButtonColumn;
import com.hit.driver.ButtonEditor;
import com.hit.driver.ButtonRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

public class AdminView extends JPanel implements ActionListener {
    private JTable songsTable;
    private GraphicalView gui;
    private String[] songTableColumn = {"Name", "Artist", "Genre", "Link", "Delete","Edit"};
    private JButton backButton, button;
    private JLabel jLabel;

    public AdminView(GraphicalView gui) {
        // uses box layout
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.gui = gui;
        this.jLabel = new JLabel("Song List");
        this.button  = new JButton("Delete");
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
        Action delete = new AbstractAction("Delete") {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int row = Integer.valueOf(e.getActionCommand());
                if (JOptionPane.showConfirmDialog(null, "You will delete this song. Are you sure?", "WARNING",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    System.out.println(table.getModel().getValueAt(row,3));
                    gui.setUserInput((String) table.getModel().getValueAt(row,3));
                } else {
                    //Do nothing
                }
//                System.out.println(table.getModel().getValueAt(row,3));

            }
        };
        Action edit = new AbstractAction("Edit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int row = Integer.valueOf(e.getActionCommand());
                if (JOptionPane.showConfirmDialog(null, "You will edit this song. Are you sure?", "WARNING",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    System.out.println(table.getModel().getValueAt(row,3));
//                    gui.setUserInput((String) table.getModel().getValueAt(row,3));
                } else {
                    //Do nothing
                }
//                System.out.println(table.getModel().getValueAt(row,3));

            }
        };
        ButtonColumn buttonColumn = new ButtonColumn(songsTable, delete, 4,"Delete");
        buttonColumn.setMnemonic(KeyEvent.VK_D);
        ButtonColumn buttonColumn2 = new ButtonColumn(songsTable, edit, 5,"Edit");
        buttonColumn2.setMnemonic(KeyEvent.VK_D);
//        songsTable.getColumn("Action").setCellRenderer(new ButtonRenderer());
//        songsTable.getColumn("Action").setCellEditor(new ButtonEditor(new JCheckBox(),this.button));
    }

    // event listener for back button
//    public void backButton(ActionListener actionListener) {
//        backButton.addActionListener(actionListener);
//    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("back".equals(e.getActionCommand())) {
            this.gui.mainView(this);

        }
        else if("Delete".equals(e.getActionCommand())){
            this.gui.setUserInput("link");
        }

    }
}
