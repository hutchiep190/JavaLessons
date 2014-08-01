import java.awt.Graphics;
import java.awt.Image;

public class Tank {

	private Direction turretDirection = Direction.RIGHT;
	private Direction direction;
	private Image tankSprite;
	private Image tankTurretSprite;
	private int x;
	private int y;

	public Tank(int x, int y, Direction direction, Image tankSprite, Image tankTurretSprite) {
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
		direction = Direction.RIGHT;
	}

	public void moveDown() {
		y++;
		direction = Direction.DOWN;
	}

	public void moveLeft() {
		x--;
		direction = Direction.LEFT;
	}

	public void moveUp() {
		y--;
		direction = Direction.UP;
	}

	public void setTurretDirection(Direction turretDirection) {
		this.turretDirection = turretDirection;
	}

	public void draw(Graphics g){
		if(direction == Direction.UP) {
			Utils.drawSprite(g, tankSprite, x, y, 32, 32, 0, 0);
		}
		if(direction == Direction.LEFT) {
			Utils.drawSprite(g, tankSprite, x, y, 32, 32, 96, 0);
		}
		if(direction == Direction.DOWN) {
			Utils.drawSprite(g, tankSprite, x, y, 32, 32, 64, 0);
		}
		if(direction == Direction.RIGHT) {
			Utils.drawSprite(g, tankSprite, x, y, 32, 32, 32, 0);
		}
		if(turretDirection == Direction.UP) {
			Utils.drawSprite(g, tankTurretSprite, x, y, 32, 32, 0, 0);
		}
		if(turretDirection == Direction.LEFT) {
			Utils.drawSprite(g, tankTurretSprite, x, y, 32, 32, 96, 0);
		}
		if(turretDirection == Direction.DOWN) {
			Utils.drawSprite(g, tankTurretSprite, x, y, 32, 32, 64, 0);
		}
		if(turretDirection == Direction.RIGHT) {
			Utils.drawSprite(g, tankTurretSprite, x, y, 32, 32, 32, 0);
		}
	}
}