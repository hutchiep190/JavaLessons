import java.awt.Graphics;
import java.awt.Image;

public class Bullet {

	private Image image;
	private int x;
	private int y;
    private int direction;
	public Bullet(Image image, int x, int y, int direction) {
		this.image = image;
		this.x = x;
		this.y = y;
        this.direction = direction;
	}
	public void update(){
        if (direction == 0) {
            y = y-4;
        }
        if (direction == 1) {
            x = x+4;
        }
        if (direction == 2) {
            y = y+4;
        }
        if (direction == 3) {
            x = x-4;
        }
	}
	public void draw(Graphics g){
        if (direction == 0) {
            g.drawImage(image, x, y, x + 10, y + 13, 142, 197, 142+10, 197+13, null);
        }
        if (direction == 1) {
            g.drawImage(image, x, y, x + 13, y + 10, 159, 215, 159+13, 215+10, null);
        }
        if (direction == 2) {
            g.drawImage(image, x, y, x + 10, y + 13, 142, 230, 142+10, 230+13, null);
        }
        if (direction == 3) {
            g.drawImage(image, x, y, x + 13, y + 10, 121, 215, 121+13, 215+10, null);
        }
	}
}