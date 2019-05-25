package fr.paulduval30.mtgcomunitybuilder.cards;

import fr.paulduval30.mtgcomunitybuilder.network.FullResponseBuilder;

import java.util.ArrayList;
import java.util.HashMap;

public class BaseCarte
{
    private HashMap<String, Card> cardsDataBase;

    public BaseCarte()
    {
        this.cardsDataBase = new HashMap<>();
    }

    public Card add(String s, Card c)
    {
        return this.cardsDataBase.put(s, c);
    }

    public Card getCard(String key)
    {
        return this.cardsDataBase.get(key);
    }

    public String getAttributeOfCard(String key, String attribute)
    {
        if(this.cardsDataBase.containsKey(key))
        {
            if(this.cardsDataBase.get(key).getParams().containsKey(attribute))
            {
                return this.cardsDataBase.get(key).getParam(attribute);
            }
        }
        return "ERROR NO SUCH NAME OR ATTRIBUTE";
    }

    public HashMap<String, Card> getBaseCarte()
    {
        return cardsDataBase;
    }

    public ArrayList<Card> search(String key, String value) throws Exception
    {
        ArrayList<Card> cards = new ArrayList<>();
        for(String s : cardsDataBase.keySet())
        {
            if(this.getAttributeOfCard(s,key).contains(value))
            {
                if(!this.getCard(s).getParams().containsKey("png"))
                {
                    HashMap<String, String> urls = FullResponseBuilder.getImage(this.getAttributeOfCard(s, "name")
                            .replace(" ", "+").toLowerCase());
                    for (String ur : urls.keySet())
                    {
                        this.getCard(s).getParams().put(ur, urls.get(ur));
                    }
                }
                cards.add(this.getCard(s));
            }
        }
        return cards;
    }

    public ArrayList<Card> request(String key, String value) throws Exception
    {
        ArrayList<Card> cards = new ArrayList<>();
        for(String s : cardsDataBase.keySet())
        {
            if(this.getAttributeOfCard(s,key).equals(value))
            {
                if(!this.getCard(s).getParams().containsKey("png"))
                {
                    HashMap<String, String> urls = FullResponseBuilder.getImage(this.getAttributeOfCard(s, "name")
                            .replace(" ", "+").toLowerCase());
                    for (String ur : urls.keySet())
                    {
                        this.getCard(s).getParams().put(ur, urls.get(ur));
                    }
                }
                cards.add(this.getCard(s));
            }
        }
        return cards;
    }
}
