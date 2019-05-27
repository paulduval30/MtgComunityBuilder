package fr.paulduval30.mtgcomunitybuilder.Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageComponent extends JComponent
{
    private BufferedImage image;

    public ImageComponent(BufferedImage image)
    {
        this.image = image;
        this.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
    }

    public void setImage(BufferedImage image)
    {
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(image,0,0,null);
    }
}
