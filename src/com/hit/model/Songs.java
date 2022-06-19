package com.hit.model;


import com.hit.client.Client;
import com.hit.client.Song;

import java.util.*;

public class Songs extends Observable implements Model {
    Client client = new Client();

    public List<Song> getAll() {
        return client.getSongs(false);
    }

    public void add(Song song) {
        client.saveSong(song, false);
    }

    public List<Song> getPlaylist() {
        return client.getSongs(true);
    }

    public void remove(Song song) {
        client.deleteSong(song.getSongLink(), false);
    }

    public void addToPlaylist(Song song) {
        client.saveSong(song, true);
    }

    public void removeFromPlaylist(Song song) {
        client.deleteSong(song.getSongLink(), true);
    }


}
