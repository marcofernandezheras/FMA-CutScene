package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.io.File;

public class EntryPoint {

	

	static int currentImageIndex = 0;
	static int currentSoundIndex = 0;

	public static List<String> listFilesForFolder(final File folder, String extension) {
		List<String> ret = new ArrayList<>();
		for (final File fileEntry : folder.listFiles())
		{
			if (!fileEntry.isDirectory())
			{
				String fileExt = fileEntry.getAbsolutePath()
						.substring(fileEntry.getAbsolutePath().lastIndexOf('.') + 1);
				if (fileExt.equalsIgnoreCase(extension))
					ret.add(fileEntry.getAbsolutePath());
			}
		}
		return ret;
	}

	public static void main(String[] args) throws InterruptedException {
		String path = System.getProperty("user.dir");
		String fileSeparator = System.getProperty("file.separator");

		final File imgFolder = new File(path + fileSeparator + Constants.IMG_FOLDER);
		if (!imgFolder.exists())
		{
			System.out.println("Error: no existe el directorio de imagenes");
			return;
		}

		final File soundsFolder = new File(path + fileSeparator + Constants.SOUND_FOLDER);
		if (!soundsFolder.exists())
		{
			System.out.println("Error: no existe el directorio de sonidos");
			return;
		}

		List<String> images = listFilesForFolder(imgFolder, Constants.IMG_EXTENSION);
		if (images.isEmpty())
		{
			System.out.println("Error: directorio de imagenes vacio");
			return;
		}

		List<String> sounds = listFilesForFolder(soundsFolder, Constants.SOUND_EXTENSION);
		if (sounds.isEmpty())
		{
			System.out.println("Error: directorio de sonidos vacio");
			return;
		}

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				try
				{
					for (int i = 0; i < Constants.IMAGES_PER_LOOP; i++)
					{
						new ImageFrame(images.get(currentImageIndex)).setVisible(true);
						currentImageIndex = (currentImageIndex + 1) % images.size();
						currentSoundIndex = (currentSoundIndex + 1) % sounds.size();
						Thread.sleep(Constants.SHOW_TIME);
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}, 0, Constants.DELAY_TIME);
	}
}
