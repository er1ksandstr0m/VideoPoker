package videopoker;

import java.io.FileReader;
import java.io.FileWriter;
import com.google.gson.Gson;

public class SaveLoad 
{
	//Filepath där json ska sparas
	private String path = "SaveGame.json";

	//Load för att ladda spelet
	//använder Gson som är googles egna Json grej
	//kör en try catch för att skriva till object
	//skapar en FileWriter som skriver till filen
	public void Save(VideoPoker test)
	{
		Gson gson = new Gson();
		String json = gson.toJson(test);


		
		try {
			FileWriter writer = new FileWriter(path);
			writer.write(json);
			writer.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	//Ladda in Json objectet
	//använder try och catch precis som i save funktionen
	//ifall ett object hittades så laddar den in datan från Gson
	//annars så returnar den en ny Videopoker och Player eftersom den 
	//utgår ifrån att det inte finns något
	//save game.

	public VideoPoker Load()
	{

		try {
			Gson gson = new Gson();
			return gson.fromJson(new FileReader(path), VideoPoker.class);
		}
		catch (Exception e) {
			System.out.println(e);
		}

		return new VideoPoker(new Player());
	}
}
