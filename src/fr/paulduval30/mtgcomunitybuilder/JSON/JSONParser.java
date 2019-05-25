package fr.paulduval30.mtgcomunitybuilder.JSON;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import fr.paulduval30.mtgcomunitybuilder.cards.BaseCarte;
import fr.paulduval30.mtgcomunitybuilder.cards.Card;
import fr.paulduval30.mtgcomunitybuilder.network.FullResponseBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class JSONParser {

	public JSONParser(String url)
	{

	}

	public static BaseCarte parse(String url)
	{
		File jsonFile = new File(url);
		BaseCarte cards = new BaseCarte();
		HashMap<String, Map<String, String>> jsonData = new HashMap<>();
		long time = System.currentTimeMillis();

		try
		{
			BufferedReader in = new BufferedReader(new FileReader(jsonFile));
			Gson gson = new Gson();
			JsonReader jsonReader = new JsonReader(new FileReader(jsonFile));

			jsonData = gson.fromJson(jsonReader, jsonData.getClass());
			for(String s : jsonData.keySet())
			{
				cards.add(s, new Card(new HashMap<String,Object>(jsonData.get(s))));
			}
			System.out.println(System.currentTimeMillis() - time);

			Scanner sc = new Scanner(System.in);
			String input;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return cards;
	}

	public static void main(String[] argv)
	{
		JSONParser j = new JSONParser("res/Allcards.json");
	}
}
