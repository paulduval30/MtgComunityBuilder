package fr.paulduval30.mtgcomunitybuilder.cards;

import java.util.*;

public class Card{
	private HashMap<String, Object> params;

	public Card(HashMap<String, Object> params)
	{
		this.params = params;
	}

	public String getParam(String key)
	{
			return this.params.get(key).toString();
	}

	public HashMap<String, Object> getParams()
	{
		return params;
	}

	@Override
	public String toString()
	{
		String sRet = "";
		for(String s : params.keySet())
			sRet += "- " + s + " : " + params.get(s) + "\n";
		return sRet;
	}
}