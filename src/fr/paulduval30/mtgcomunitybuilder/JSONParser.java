package fr.paulduval30.mtgcomunitybuilder;

import java.io.*;
import java.nio.file.*;
import java.nio.charset.*;
import org.json.*;
import java.util.*;

public class JSONParser {
	private File jsonFile;
	private BufferedReader in;
	private JSONObject obj;
	private HashMap<String, Card> cards;

	public JSONParser(String url)
	{
		jsonFile = new File(url);
		this.cards = new HashMap<>();
		long time = System.currentTimeMillis();

		try
		{
			this.in = new BufferedReader(new FileReader(jsonFile));
			String line = "";
			String load = "";
			List<String> content;
			content = Files.readAllLines(jsonFile.toPath(), StandardCharsets.UTF_8);
		    byte [] fileBytes = Files.readAllBytes(jsonFile.toPath());
			char singleChar;
			line = new String(fileBytes);
  			System.out.println("casting to object");
			obj = new JSONObject(line);


		}
		catch(Exception e)
		{

		}
		Scanner sc = new Scanner(System.in);
		for(String s : JSONObject.getNames(obj))
 		{	
 			HashMap<String, String> cardDetail = new HashMap<>();;
			try
			{
				
				JSONObject card = obj.getJSONObject(s);
				if(card.has("name"))
					cardDetail.put("name", card.getString("name"));
				if(card.has("text"))
					cardDetail.put("text", card.getString("text"));
				if(card.has("types"))
					cardDetail.put("types", card.getJSONArray("types").toString());
				if(card.has("manaCost"))
					cardDetail.put("manaCost", card.getString("manaCost"));
				if(card.has("colorIdentity"))
					cardDetail.put("colorIdentity", card.getJSONArray("colorIdentity").toString());
				if(card.has("colors"))
					cardDetail.put("colors", card.getJSONArray("colors").toString());
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			cards.put(s.toUpperCase(), new Card(cardDetail));
		}
		System.out.println(System.currentTimeMillis() - time);


		
		String input;
		System.out.println("ASK ME");
		while(!(input = sc.nextLine()).equals("quitter"))
		{
			time = System.currentTimeMillis();
			for(String s : cards.keySet())
			{
				Card c = cards.get(s);
				if(c.getParam("text") == null)
					continue;
				if(c.getParam("text").toUpperCase().contains(input.toUpperCase()))
				{
					HashMap<String, String> detail = c.getParams();
					for(String param : detail.keySet())
					{
						System.out.println(param + " : " + detail.get(param));
					}
					System.out.println();
				}

			}
			System.out.println("THE SEARCH OF THE WORD " + input + " took : " + (System.currentTimeMillis() - time) + " MS");
		}
	}

	public static void main(String[] argv)
	{
		JSONParser j = new JSONParser("C:\\Users\\duvalp\\Downloads\\litiengine-gurk-nukem-master\\MtgComunityBuilder\\res\\Allcards.json");
	}
}
