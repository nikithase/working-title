package gui.canvas.graphic2d;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * a little class who load and holds all graphics
 * 
 * @author Ludwig Biermann
 *
 */
public class TextureLib {

	/**
	 * white Texture for "Weisser Bauer"
	 */
	public static BufferedImage wBauer = null;

	/**
	 * white Texture for "Schwarzer Bauer"
	 */
	public static BufferedImage sBauer = null;

	/**
	 * create and initialize a new Texture Library
	 */
	public TextureLib() {
		try {
			wBauer = ImageIO.read(new File("img/canvas2d/wbauer.png"));
			sBauer = ImageIO.read(new File("img/canvas2d/sbauer.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
