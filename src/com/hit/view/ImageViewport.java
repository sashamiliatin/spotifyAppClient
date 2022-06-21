package com.hit.view;

import javax.swing.*;
import java.awt.*;

public class ImageViewport extends JViewport {
    private Image background;
    public ImageViewport(Image background) {
        this.background = background;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            Rectangle bounds = getViewRect();
            g.drawImage(background, 0, 0, this);
        }
    }
}
