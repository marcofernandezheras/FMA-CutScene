package main;

import java.util.ArrayList;
import java.util.List;
import java.io.File;

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

	public static List<String> listFilesForFolder(final File folder, String extension) {
		List<String> ret = new ArrayList<>();
		for (final File fileEntry : folder.listFiles())
		{
			if (!fileEntry.isDirectory())
			{
				String fileExt = fileEntry.getAbsolutePath().substring(fileEntry.getAbsolutePath().lastIndexOf('.')+1);
				if(fileExt.equalsIgnoreCase(extension))
					ret.add(fileEntry.getAbsolutePath());
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		String path = System.getProperty("user.dir");
		String fileSeparator = System.getProperty("file.separator");

		final File imgFolder = new File(path + fileSeparator + IMG_FOLDER);
		if (!imgFolder.exists())
			System.out.println("Error: no existe el directorio de imagenes");

		final File soundsFolder = new File(path + fileSeparator + SOUND_FOLDER);
		if (!soundsFolder.exists())
			System.out.println("Error: n existe el directorio de sonidos");

		System.out.println(listFilesForFolder(imgFolder, IMG_EXTENSION));
		System.out.println(listFilesForFolder(soundsFolder, SOUND_EXTENSION));
	}

}
