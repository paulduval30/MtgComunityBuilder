package fr.paulduval30.mtgcomunitybuilder;

import java.util.*;

public class Card{

	HashMap<String, String> params;
	public Card(HashMap<String, String> params)
	{
		this.params = params;
	}

	public String getParam(String value)
	{
		return this.params.get(value);
	}

	public HashMap<String,String> getParams()
	{
		return (HashMap<String,String>)this.params.clone();
	}
}