import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class Bullet {

	private int direction;
	private Image image;
	private int x;
	private int y;
	public Bullet(Image image, int x, int y, int direction) {
		this.image = image;
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	public void update(){
		if(direction == 0) {
			y = y-4;
		}
		if(direction == 1) {
			x = x-4;
		}
		if(direction == 2) {
			y = y+4;
		}
		if(direction == 3) {
			x = x+4;
		}
	}
	private void drawSprite(Graphics g, int width, int height, int sourceX, int sourceY) {
		g.drawImage(image, x, y, x+width, y+height, sourceX, sourceY, sourceX+width, sourceY+height, null);
	}
	public void draw(Graphics g){
		if(direction == 0) {
			drawSprite(g, 10, 13, 142, 197);
		}
		if(direction == 1) {
			drawSprite(g, 13, 10, 121, 215);
		}
		if(direction == 2) {
			drawSprite(g, 10, 13, 142, 230);
		}
		if(direction == 3) {
			drawSprite(g, 13, 10, 159, 215);
		}
	}
}