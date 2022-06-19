package com.hit.view;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import javax.media.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;


public class youtubePanel extends JPanel {

    public JPanel youtubePanel(String mediaUrl) throws CannotRealizeException, IOException, NoPlayerException {
        NativeInterface.open();
        JPanel wbPanel = new JPanel(new BorderLayout());
        JWebBrowser wb = new JWebBrowser();
        wbPanel.add(wb,BorderLayout.CENTER);
        wb.setBarsVisible(false);
        wb.navigate(mediaUrl);
        return wbPanel;
    }

}
