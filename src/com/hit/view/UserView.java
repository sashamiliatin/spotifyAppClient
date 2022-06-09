package com.hit.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;

public class UserView extends JPanel implements ActionListener {
    private GraphicalView gui;
    private JLabel jLabel1 ;
    private JTextArea textArea;
    private JScrollPane jScrollPane1;
    private JButton userSongs;
    public JTextField input;


    public UserView(GraphicalView gui) {
        this.gui = gui;
        this.initComponents();
    }

    private void initComponents() {
        this.userSongs = new JButton("Get Songs");
        this.userSongs.setActionCommand("searchUserSong");
        String[] columnNames = {"Name", "Artist","Genre","Link",""};
        Object[][] data =
                {
                };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
//        JTable table = new JTable( model );
        this.setPreferredSize(new Dimension(200, 300));
        this.setLayout(new FlowLayout());
        this.jLabel1 = new JLabel("User Menu");
        this.input = new JTextField("input Text");
        this.userSongs = new JButton("Search");
        this.userSongs.addActionListener(this);
//        this.textArea = new JTextArea("A text area is a \"plain\" text component, which means that although it can display text in any font, all of the text is in the same font.");
//        this.textArea.setColumns(20);
//        this.textArea.setLineWrap(true);
//        this.textArea.setRows(5);
//        this.textArea.setWrapStyleWord(true);
//        this.textArea.setEditable(false);
//        this.jScrollPane1 = new JScrollPane(table);
        this.add(this.jLabel1);
//        this.add(this.jScrollPane1);
        this.add(this.input);
        this.add(this.userSongs);


//        this.add(table);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    this.gui.setUserInput(input.getText());

    }
    public void getSongs(){
        String[] columnNames = {"Name", "Artist","Genre","Link",""};
        Object[][] data =
                {
                };

    }
}
