package com.hit.view;

import com.hit.client.Song;
import com.hit.model.Songs;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Observable;

public class GraphicalView extends Observable implements View {
    private MainView mainMenu;
    private UserView userView;
    private AdminView adminView;
    private AllSongList allSongsView;
    private searchSongView searchSongView;

    private AddSongForm addSongForm;

    private AdminAuth adminAuth;

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
        this.frame.setPreferredSize(new Dimension(500, 655));
        this.frame.setResizable(false);
        this.frame.setDefaultCloseOperation(3);
        this.frame.setLocationRelativeTo(null);
        this.panel = new JPanel();
        this.mainMenu = new MainView(this);
        frame.getContentPane().add(this.mainMenu);

        frame.pack();
        frame.setVisible(true);
    }


    public void deleteSongFromDb(String text) {
        songs.remove(text);
        setChanged();
        adminView.loadSongs(songs.getAll());
        notifyObservers("adminDelete");
    }


    public void searchSongs(String text) {
        setChanged();
        searchSongView.loadSearchedSongs(songs.searchSongs(text));
        notifyObservers("searchedSongs");
    }

    public void deleteFromPlaylist(String text) {
        songs.removeFromPlaylist(text);
        setChanged();
        userView.loadSongs(songs.getPlaylist());
        notifyObservers("userDelete");
    }

    public void playSongVideo(URI songLink) throws IOException {
        Desktop.getDesktop().browse(songLink);
    }

    public void addToPlaylist(Song song) {
        songs.addToPlaylist(song);
    }

    public Song createNewSong(String name, String artist, String genre, String link) {
        Song newSong = new Song(name, artist, genre, link);
        return newSong;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void adminView() {
        this.mainMenu.setVisible(false);
        this.adminAuth.setVisible(false);
        this.panel.setVisible(false);
        try {
            this.addSongForm.setVisible(false);
        } catch (NullPointerException exc) {

        }
        this.adminView = new AdminView(this);
        this.adminView.loadSongs(songs.getAll());
        frame.getContentPane().add(this.adminView);
        frame.pack();
        frame.setVisible(true);
    }

    public void adminAuth() {
        this.mainMenu.setVisible(false);
        this.panel.setVisible(false);
        try {
            this.addSongForm.setVisible(false);
            this.adminView.setVisible(false);
        } catch (NullPointerException exc) {

        }
        this.adminAuth = new AdminAuth(this);
        frame.getContentPane().add(this.adminAuth);
        frame.pack();
        frame.setVisible(true);
    }


    public void userView() {
        this.mainMenu.setVisible(false);
        this.panel.setVisible(false);
        try {
            this.searchSongView.setVisible(false);
        } catch (NullPointerException exc) {

        }

        try {
            this.allSongsView.setVisible(false);
        } catch (NullPointerException exc) {

        }
        this.userView = new UserView(this);
        this.userView.loadSongs(songs.getPlaylist());
        frame.getContentPane().add(this.userView);
        frame.pack();
        frame.setVisible(true);
        frameChange();
    }

    public void allSongsView() {
        panel.setVisible(false);
        this.mainMenu.setVisible(false);
        try {
            this.userView.setVisible(false);
            this.searchSongView.setVisible(false);
        } catch (NullPointerException exc) {

        }
        this.allSongsView = new AllSongList(this);
        this.allSongsView.loadAllSongs(songs.getAll());
        frame.getContentPane().add(this.allSongsView);
        frame.pack();
        frame.setVisible(true);
    }

    public void addSongView() {
        panel.setVisible(false);
        this.mainMenu.setVisible(false);
        this.adminView.setVisible(false);
        this.addSongForm = new AddSongForm(this);
        frame.getContentPane().add(this.addSongForm);
        frame.pack();
        frame.setVisible(true);
    }

    public void mainView(JPanel panel) {
        panel.setVisible(false);
        this.mainMenu = new MainView(this);
        frame.getContentPane().add(this.mainMenu);
        frame.pack();
        frame.setVisible(true);
    }

    public void frameChange() {
    }

    public void searchSongGuiView() {
        try {
            this.userView.setVisible(false);
            this.panel.setVisible(false);
        } catch (NullPointerException ex) {
        }
        this.searchSongView = new searchSongView(this);
//        this.searchSongView.loadSearchedSongs();
        frame.getContentPane().add(this.searchSongView);
        frame.pack();
        frame.setVisible(true);
    }

}
