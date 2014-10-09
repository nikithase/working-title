package gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TextureLib {

	public static BufferedImage wBauer = null;
	public static BufferedImage sBauer = null;
	
	public TextureLib(){
		try {
			wBauer = ImageIO.read(new File( "img/canvas2d/wbauer.png" ));
			sBauer = ImageIO.read(new File( "img/canvas2d/sbauer.png" ));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
