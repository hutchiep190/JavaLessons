import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.ArrayList;

public class Bullet {

	private int height;
	private int width;
	private boolean alive = true;
	private Direction direction;
	private Image image;
	private int x;
	private int y;
	public Bullet(Image image, int x, int y, Direction direction) {
		this.image = image;
		this.x = x;
		this.y = y;
		this.direction = direction;
		if(direction == Direction.LEFT || direction == Direction.RIGHT) {
			width = 13;
			height = 10;
		} else {
			width = 10;
			height = 13;
		}
	}
	private boolean bulletHit(Tank tank) {
		if(x - (tank.getX()+32) >= 0) {
    		return false;
    	}
    	if(tank.getX() - (x+width) >= 0) {
    		return false;
    	}
    	if(y - (tank.getY()+32) >= 0) {
    		return false;
    	}
    	if(tank.getY() - (y+height) >= 0) {
    		return false;
    	}
    	return true;
    }
    private Tank bulletHit(List<Tank> tanks) {
    	for(Tank tank : tanks) {
    		if(bulletHit(tank)) {
    			return tank;
    		}
    	}
    	return null;
	}
	public boolean isAlive() {
		return alive;
	}
	public void update(List<Tank> tanks){
		if(direction == Direction.UP) {
			y = y-4;
		}
		if(direction == Direction.LEFT) {
			x = x-4;
		}
		if(direction == Direction.DOWN) {
			y = y+4;
		}
		if(direction == Direction.RIGHT) {
			x = x+4;
		}
		if(direction == Direction.UP_LEFT) {
			x = x-4;
			y = y-4;
		}
		if(direction == Direction.DOWN_LEFT) {
			x = x-4;
			y = y+4;
		}
		if(direction == Direction.DOWN_RIGHT){
			x += 4;
			y += 4;
		}
		if(direction == Direction.UP_RIGHT){
			x += 4;
			y -= 4;
		}
		Tank hitTank = bulletHit(tanks);
		if(hitTank != null) {
			hitTank.takeHealth(1);
			alive = false;
		}
		if(x > 640 || x < -320 || y > 480 || y < -240) {
			alive = false;
		}
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void draw(Graphics g){
		if(direction == Direction.UP) {
			Utils.drawSprite(g, image, x, y, 10, 13, 142, 197);
		}
		if(direction == Direction.LEFT) {
			Utils.drawSprite(g, image, x, y, 13, 10, 121, 215);
		}
		if(direction == Direction.DOWN) {
			Utils.drawSprite(g, image, x, y, 10, 13, 142, 230);
		}
		if(direction == Direction.RIGHT) {
			Utils.drawSprite(g, image, x, y, 13, 10, 159, 215);
		}
		if(direction == Direction.DOWN_LEFT) {
			Utils.drawSprite(g, image, x, y, 13, 13, 121, 229);
		}
		if(direction == Direction.UP_LEFT) {
			Utils.drawSprite(g, image, x, y, 13, 13, 122, 197);
		}
		if(direction == Direction.UP_RIGHT) {
			Utils.drawSprite(g, image, x, y, 13, 13, 158, 198);
		}
		if(direction == Direction.DOWN_RIGHT) {
			Utils.drawSprite(g, image, x, y, 13, 13, 159, 229);
		}
	}
}