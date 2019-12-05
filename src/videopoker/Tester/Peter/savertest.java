package videopoker.Tester.Peter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

public class savertest {

	public static Fuckoff foff = new Fuckoff();
	private static String path = "SaveFile.json";

	public static void main(String[] args) throws IOException {

		Fuckoff newfoff = new Fuckoff();
		Load(newfoff);
		System.out.println(newfoff.name);
        System.out.println(newfoff.age);
		System.out.println(newfoff.two);
		System.out.println(newfoff.two.idk);
        System.out.println(newfoff.two.hasGivenUpOnLife);
		
	}

	public static void Save(Fuckoff test) throws IOException {
		Gson gson = new Gson();
		String json = gson.toJson(test);

		try {
			FileWriter writer = new FileWriter(path);
			writer.write(json);
			writer.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("Success...");
	}

	public static void Load(Fuckoff blabla) throws FileNotFoundException
	{
		FileReader reader = new FileReader(path);
		Gson gson = new Gson();
		blabla = gson.fromJson(reader.toString(), Fuckoff.class);
	}
}