package fr.paulduval30.mtgcomunitybuilder.Graphics;

import fr.paulduval30.mtgcomunitybuilder.cards.Card;

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
    private ImageComponent img;
    JLabel cardName;
    JTextArea cardText;

    public ImagePanel()
    {
        try
        {
            this.setBackground(Color.red);
            this.image = ImageIO.read(new URL("https://img.scryfall.com/cards/large/front/7/e/7e41765e-43fe-461d-baeb-ee30d13d2d93.jpg?1547516526"));
            this.img = new ImageComponent(image);
            this.cardName = new JLabel("Snapcaster Mage");
            this.setBackground(Color.blue);
            this.cardText = new JTextArea();
            this.cardText.setEnabled(true);
            this.cardText.setPreferredSize(new Dimension(500,100));
            this.setLayout(new BorderLayout());
            this.add(img, BorderLayout.CENTER);
            this.add(cardName, BorderLayout.NORTH);
            this.add(cardText,BorderLayout.SOUTH);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void setImage(Card card)
    {
        try
        {
            System.out.println(card.getParam("name"));

            this.image = ImageIO.read(new URL(card.getParam("art_crop")));
            this.cardText.setText(card.getParam("text"));
            this.cardName.setText(card.getParam("name"));
            this.img.setImage(image);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
