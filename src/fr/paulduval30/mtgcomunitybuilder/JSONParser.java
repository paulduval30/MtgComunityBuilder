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
		try
		{
			this.in = new BufferedReader(new FileReader(jsonFile));
			String line = "";
			String load = "";
			List<String> content;
			long time = System.currentTimeMillis();
			content = Files.readAllLines(jsonFile.toPath(), StandardCharsets.UTF_8);
		    byte [] fileBytes = Files.readAllBytes(jsonFile.toPath());
			char singleChar;
			line = new String(fileBytes);
  			System.out.println("casting to object");
			obj = new JSONObject(line);
			System.out.println(System.currentTimeMillis() - time);


		}
		catch(Exception e)
		{

		}

		for(String s : JSONObject.getNames(obj))
 		{	
 			try
 			{
 				cards.put(s, new Card(s,obj.getJSONObject(s).getString("text")));
 			}
 			catch(Exception e)
 			{
 				cards.put(s, new Card(s, "TEXT NOT AVAILABLE"));
 			}
		}

		Scanner sc = new Scanner(System.in);
		String input;
		System.out.println("ASK ME");
		while(!(input = sc.nextLine()).equals("quitter"))
		{
			Card c = this.cards.get(input);
			if(c != null)
				System.out.println(this.cards.get(input).getText());
		}
	}

	public static void main(String[] argv)
	{
		JSONParser j = new JSONParser("C:\\Users\\duvalp\\Downloads\\litiengine-gurk-nukem-master\\MtgComunityBuilder\\res\\Allcards.json");
	}
}
