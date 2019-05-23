package fr.paulduval30.mtgcomunitybuilder;

public class Card{
	private String name;
	private String text;
	public Card(String name, String text)
	{
		this.name = name;
		this.text = text;
	}

	public String getText()
	{
		return this.text;
	}
}