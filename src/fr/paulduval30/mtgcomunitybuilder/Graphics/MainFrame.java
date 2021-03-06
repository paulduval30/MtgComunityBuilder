package fr.paulduval30.mtgcomunitybuilder.Graphics;

import fr.paulduval30.mtgcomunitybuilder.JSON.JSONParser;
import fr.paulduval30.mtgcomunitybuilder.cards.BaseCarte;
import fr.paulduval30.mtgcomunitybuilder.cards.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainFrame extends JFrame implements ActionListener
{
    private ImagePanel imagePane;
    private JTextField searchField;
    private JButton submit;
    private BaseCarte baseCarte;
    private JComboBox<String> requestedCards;
    public MainFrame()
    {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.requestedCards = new JComboBox<>();
        this.requestedCards.setPreferredSize(new Dimension(300,50));
        JPanel q =  new JPanel();
        q.add(requestedCards, CENTER_ALIGNMENT);
        this.add(q,BorderLayout.EAST);
        this.baseCarte = JSONParser.parse("res/Allcards.json");
        JPanel p = new JPanel();
        this.searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(300,100));
        p.add(searchField);
        this.submit = new JButton("SUBMIT");
        p.add(submit);
        this.add(p, BorderLayout.SOUTH);
        submit.addActionListener(this);
        this.imagePane = new ImagePanel();
        this.add(imagePane, BorderLayout.CENTER);
        this.requestedCards.addActionListener(this);
        this.setVisible(true);
    }

    public static void main(String[] argv)
    {
        new MainFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == this.submit)
        {
            String key = this.searchField.getText().split("#")[0];
            String value = this.searchField.getText().split("#")[1];
            try
            {
                ArrayList<Card> cards = baseCarte.search(key, value);
                this.requestedCards.removeAllItems();
                for(Card c : cards)
                    this.requestedCards.addItem(c.getParam("name"));
                this.repaint();
            }
            catch (Exception e1)
            {
                e1.printStackTrace();
            }
        }
        if(e.getSource() == requestedCards)
        {
            Card c =  this.baseCarte.getCard((String)this.requestedCards.getSelectedItem());
            if(c != null)
                    this.imagePane.setImage(c);
            repaint();
        }
        repaint();
    }
}
