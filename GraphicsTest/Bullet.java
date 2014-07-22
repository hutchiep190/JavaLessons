import java.awt.Graphics;
import java.awt.Image;

public class Bullet {

	private Image image;
	private int x;
	private int y;
	public Bullet(Image image, int x, int y) {
		this.image = image;
		this.x = x;
		this.y = y;
	}
	public void update(){
		y = y-4;
	}
	public void draw(Graphics g){
		g.drawImage(image, x, y, x + 10, y + 13, 142, 197, 142+10, 197+13, null);
	}
}