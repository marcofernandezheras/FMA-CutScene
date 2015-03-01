package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.io.File;
import java.io.IOException;

public class EntryPoint {

	/**
	 * Subcarpeta donde iran las imagenes
	 */
	private static final String IMG_FOLDER = "img";

	/**
	 * Extensin de las imagenes: jpg, png...
	 */
	private static final String IMG_EXTENSION = "png";

	/**
	 * Subcarpeta donde iran los sonidos
	 */
	private static final String SOUND_FOLDER = "sounds";

	/**
	 * Extension de los archivos de audio
	 */
	private static final String SOUND_EXTENSION = "mp3";

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

		final File imgFolder = new File(path + fileSeparator + IMG_FOLDER);
		if (!imgFolder.exists()){
			System.out.println("Error: no existe el directorio de imagenes");
			return;
		}

		final File soundsFolder = new File(path + fileSeparator + SOUND_FOLDER);
		if (!soundsFolder.exists()){
			System.out.println("Error: no existe el directorio de sonidos");
			return;
		}

		List<String> images = listFilesForFolder(imgFolder, IMG_EXTENSION);
		if(images.isEmpty()){
			System.out.println("Error: directorio de imagenes vacio");
			return;
		}
		
		List<String> sounds = listFilesForFolder(soundsFolder, SOUND_EXTENSION);
		if(images.isEmpty()){
			System.out.println("Error: directorio de sonidos vacio");
			return;
		}	
				
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {			
					try
					{
						new ImageFrame(images.get(currentImageIndex),6000).setVisible(true);
						currentImageIndex = (currentImageIndex + 1) % images.size();
						currentSoundIndex = (currentSoundIndex + 1) % sounds.size();
					}
					catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				

			}
		}, 0, 5000);
	}
}
