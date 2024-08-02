package io.deeplay.camp.view.GUI.fabric;

import javax.swing.*;
import java.awt.*;

public class ImageFactory {
    private static final String BACKGROUND_PATH = ("img/galaxy.jpeg");

    private final Image background;

    public Image getBackground() {
        return background;
    }

    public ImageFactory() {
        this.background = new ImageIcon(BACKGROUND_PATH).getImage();
    }
}
