package com.hit.view;

import com.hit.client.Song;
import com.hit.model.Songs;

import javax.swing.*;
import java.awt.*;

public class GraphicalView implements View {
    private MainView mainMenu;
    private UserView userView;
    private AdminView adminView;
    private JButton user;
    private JButton admin;
    private JLabel label;
    private JPanel panel;
    private JFrame frame;
    Songs songs = new Songs();

    public GraphicalView() {
    }

    @Override
    public void start() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GraphicalView.this.createAndShowGUI();
            }
        });
    }

    private void createAndShowGUI() {
        this.frame = new JFrame("Spotify App");
        this.frame.setPreferredSize(new Dimension(400, 500));
        this.frame.setDefaultCloseOperation(3);
        this.panel = new JPanel();
        this.mainMenu = new MainView(this);
        frame.getContentPane().add(this.mainMenu);
        frame.pack();
        frame.setVisible(true);
    }

    public void userView() {
        this.mainMenu.setVisible(false);
        this.userView = new UserView(this);
        this.userView.loadSongs(songs.getPlaylist());
        frame.getContentPane().add(this.userView);
        frame.pack();
        frame.setVisible(true);
        frameChange();
    }

    public void setUserInput(String text) {

    }

    public void adminView() {
        this.mainMenu.setVisible(false);
        this.adminView = new AdminView(this);
        this.adminView.loadSongs(songs.getAll());
        frame.getContentPane().add(this.adminView);
        frame.pack();
        frame.setVisible(true);
    }

    public void mainView(JPanel panel) {
//        this.mainMenu.setVisible(false);
//        this.adminView.setVisible(false);
        panel.setVisible(false);
        this.mainMenu = new MainView(this);
        frame.getContentPane().add(this.mainMenu);
        frame.pack();
        frame.setVisible(true);
    }

    public void frameChange() {

    }
}
