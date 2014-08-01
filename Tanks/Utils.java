import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.util.Set;

public class Utils {

	public static void drawSprite(Graphics g, Image image, int x, int y, int width, int height, int sourceX, int sourceY) {
		g.drawImage(image, x, y, x+width, y+height, sourceX, sourceY, sourceX+width, sourceY+height, null);
	}
	public static Image loadImage(String fileName) {
		Image image = null;
		try {
    		image = ImageIO.read(new File(fileName));
		} catch (IOException e) {
			System.err.println("File not found: " + fileName);
		}
		return image;
	}
	public static boolean keyJustPressed(int keyCode, Set<Integer> keysPressed, Set<Integer> previousKeysPressed) {
		return keysPressed.contains(keyCode) && !previousKeysPressed.contains(keyCode);
	}
}