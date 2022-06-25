package com.hit.model;


import com.hit.client.Client;
import com.hit.client.Song;
import com.hit.view.UserView;

import java.util.*;

public class Songs extends Observable implements Model {
    Client client = new Client();


    public List<Song> getAll() {
        return client.getSongs(false);
    }

    public List<Song> searchSongs(String value) {
        return client.getSong(value, false);
    }

    public void add(Song song) {
        client.saveSong(song, false);
    }

    public List<Song> getPlaylist() {
        return client.getSongs(true);
    }

    public void remove(String songLink) {
        client.deleteSong(songLink, false);
        setChanged();
        notifyObservers();
    }

    public void addToPlaylist(Song song) {
        client.saveSong(song, true);
    }

    public void removeFromPlaylist(String songLink) {
        client.deleteSong(songLink, true);
    }

    public void updatePlayList() {
        setChanged();
    }

    public void updateSongsList() {
        setChanged();
    }

    public void searchSongsList() {
        setChanged();
    }

}
