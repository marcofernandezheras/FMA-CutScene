package main;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

@SuppressWarnings("serial")
public class ImageFrame extends JFrame {
	
	public ImageFrame(String imageFile, String soundFile) throws IOException {
		super();
		//Quitamos bordes de la ventana
		this.setUndecorated(true);
		
		//Pantalla completa
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		
		//Encima de otras aplicaciones
		this.setAlwaysOnTop(true);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		//Cargamos la imagen
		BufferedImage myPicture = ImageIO.read(new File(imageFile));
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		add(picLabel);

		//Preparamos el auto cierre
		ImageFrame that = this;
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				that.dispatchEvent(new WindowEvent(that, WindowEvent.WINDOW_CLOSING));
			}
		}, Constants.SHOW_TIME);
		
		//Reproducidos el audio
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				try
				{
					new Player(new FileInputStream(soundFile)).play();
				}
				catch (FileNotFoundException | JavaLayerException e)
				{
					e.printStackTrace();
				}
			}
		}, 250);		
		
	}
}
