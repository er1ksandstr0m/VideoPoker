package videopoker;

import java.io.FileReader;
import java.io.FileWriter;
import com.google.gson.Gson;

public class SaveLoad 
{
	private String path = "SaveGame.json";

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

	public VideoPoker Load()
	{
		try {
			Gson gson = new Gson();
			return gson.fromJson(new FileReader(path), VideoPoker.class);
		}
		catch (Exception e) {
			System.out.println(e);
		}

		return new VideoPoker();
	}
}
