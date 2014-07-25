import java.awt.Graphics;
import java.awt.Image;

public class Utils {

	public static void drawSprite(Graphics g, Image image, int x, int y, int width, int height, int sourceX, int sourceY) {
		g.drawImage(image, x, y, x+width, y+height, sourceX, sourceY, sourceX+width, sourceY+height, null);
	}
}