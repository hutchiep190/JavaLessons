import java.awt.Graphics;
import java.awt.Image;

public class Tank {

	private int turretDirection;
	private int direction;
	private Image tankSprite;
	private Image tankTurretSprite;
	private int x;
	private int y;

	public Tank(int x, int y, int direction, Image tankSprite, Image tankTurretSprite) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.tankSprite = tankSprite;
		this.tankTurretSprite = tankTurretSprite;
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

	public void moveRight() {
		x++;
		direction = 3;
	}

	public void moveDown() {
		y++;
		direction = 2;
	}

	public void moveLeft() {
		x--;
		direction = 1;
	}

	public void moveUp() {
		y--;
		direction = 0;
	}

	public void setTurretDirection(int turretDirection) {
		this.turretDirection = turretDirection;
	}

	public void draw(Graphics g){
		if(direction == 0) {
			Utils.drawSprite(g, tankSprite, x, y, 32, 32, 0, 0);
		}
		if(direction == 1) {
			Utils.drawSprite(g, tankSprite, x, y, 32, 32, 96, 0);
		}
		if(direction == 2) {
			Utils.drawSprite(g, tankSprite, x, y, 32, 32, 64, 0);
		}
		if(direction == 3) {
			Utils.drawSprite(g, tankSprite, x, y, 32, 32, 32, 0);
		}
		if(turretDirection == 0) {
			Utils.drawSprite(g, tankTurretSprite, x, y, 32, 32, 0, 0);
		}
		if(turretDirection == 1) {
			Utils.drawSprite(g, tankTurretSprite, x, y, 32, 32, 96, 0);
		}
		if(turretDirection == 2) {
			Utils.drawSprite(g, tankTurretSprite, x, y, 32, 32, 64, 0);
		}
		if(turretDirection == 3) {
			Utils.drawSprite(g, tankTurretSprite, x, y, 32, 32, 32, 0);
		}
	}
}