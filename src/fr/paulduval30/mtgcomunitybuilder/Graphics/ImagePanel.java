package fr.paulduval30.mtgcomunitybuilder.Graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImagePanel extends JPanel
{
    private BufferedImage image;

    public ImagePanel()
    {
        try
        {
            this.image = ImageIO.read(new URL("https://img.scryfall.com/cards/large/front/7/e/7e41765e-43fe-461d-baeb-ee30d13d2d93.jpg?1547516526"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void setImage(String url)
    {
        try
        {
            this.image = ImageIO.read(new URL(url));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(image,0,0,null);
    }
}
