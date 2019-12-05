package videopoker.Tester.Peter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

public class savertest {

	public static Fuckoff foff = new Fuckoff();
	private static String path = "VideoPoker.git/SaveFile.json";

	public static void main(String[] args) throws IOException {

		Save(foff);

		// Fuckoff newfoff = Load();
		// System.out.println(newfoff.name);
        // System.out.println(newfoff.age);
		// System.out.println(newfoff.two.idk);
        // System.out.println(newfoff.two.hasGivenUpOnLife);
	}

	public static void Save(Fuckoff test) throws IOException 
	{
		Gson gson = new Gson();
		String json = gson.toJson(test);

		try {
			FileWriter writer = new FileWriter(path);
			writer.write(json);

			//kanske ta bort Close
			writer.close();
		} 
		catch (Exception e) {
			System.out.println(e);
		}
	}

	public static Fuckoff Load() throws FileNotFoundException
	{
		Gson gson = new Gson();
		return gson.fromJson(new FileReader(path), Fuckoff.class);
	}
}