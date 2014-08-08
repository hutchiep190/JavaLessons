import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import java.util.ArrayList;

public class Tank {

	private boolean moving = false;
	private Direction turretDirection = Direction.RIGHT;
	private Direction direction;
	private Image tankSprite;
	private Image tankTurretSprite;
	private int x;
	private int y;
	private int hp = 1;

	public Tank(int x, int y, Direction direction, Image tankSprite, Image tankTurretSprite) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.tankSprite = tankSprite;
		this.tankTurretSprite = tankTurretSprite;
	}

	public void takeHealth(int damage) {
		hp -= damage;
	}
	public boolean isAlive() {
		return hp > 0;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setTurretDirection(Direction turretDirection) {
		this.turretDirection = turretDirection;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	private boolean tanksCollide(Tank tankA, Tank tankB) {
    	if(tankB.getX() - tankA.getX() >= 32) {
    		return false;
    	}
    	if(tankA.getX() - tankB.getX() >= 32) {
    		return false;
    	}
    	if(tankB.getY() - tankA.getY() >= 32) {
    		return false;
    	}
    	if(tankA.getY() - tankB.getY() >= 32) {
    		return false;
    	}
    	return true;
    }
    private boolean tanksCollide(List<Tank> tanks) {
    	for(Tank tank : tanks) {
    		if(tank == this) {
    			continue;
    		}
    		if(tanksCollide(tank, this)) {
    			return true;
    		}
    	}
    	return false;
    }

	public void update(List<Tank> tanks){
		if(moving) {
			switch(direction) {
				case UP:
					y--;
					if(tanksCollide(tanks)){
						y++;
					}
					break;
				case LEFT:
					x--;
					if(tanksCollide(tanks)){
						x++;
					}
					break;
				case DOWN:
					y++;
					if(tanksCollide(tanks)){
						y--;
					}
					break;
				case RIGHT:
					x++;
					if(tanksCollide(tanks)){
						x--;
					}
					break;
				case UP_RIGHT:
					direction = Direction.UP;
					update(tanks);
					direction = Direction.RIGHT;
					update(tanks);
					direction = Direction.UP_RIGHT;
					break;
				case UP_LEFT:
					direction = Direction.UP;
					update(tanks);
					direction = Direction.LEFT;
					update(tanks);
					direction = Direction.UP_LEFT;
					break;
				case DOWN_RIGHT:
					direction = Direction.DOWN;
					update(tanks);
					direction = Direction.RIGHT;
					update(tanks);
					direction = Direction.DOWN_RIGHT;
					break;
				case DOWN_LEFT:
					direction = Direction.DOWN;
					update(tanks);
					direction = Direction.LEFT;
					update(tanks);
					direction = Direction.DOWN_LEFT;
				default:
					break;
			}
		}
	}

	public void draw(Graphics g){
		if(direction == Direction.UP || direction == Direction.UP_LEFT || direction == Direction.UP_RIGHT) {
			Utils.drawSprite(g, tankSprite, x, y, 32, 32, 0, 0);
		}
		if(direction == Direction.LEFT) {
			Utils.drawSprite(g, tankSprite, x, y, 32, 32, 96, 0);
		}
		if(direction == Direction.DOWN || direction == Direction.DOWN_LEFT || direction == Direction.DOWN_RIGHT) {
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
		if(turretDirection == Direction.UP_RIGHT) {
			Utils.drawSprite(g, tankTurretSprite, x, y, 32, 32, 128, 0);
		}
		if(turretDirection == Direction.UP_LEFT) {
			Utils.drawSprite(g, tankTurretSprite, x, y, 32, 32, 160, 0);
		}
		if(turretDirection == Direction.DOWN_LEFT) {
			Utils.drawSprite(g, tankTurretSprite, x, y, 32, 32, 192, 0);
		}
		if(turretDirection == Direction.DOWN_RIGHT) {
			Utils.drawSprite(g, tankTurretSprite, x, y, 32, 32, 224, 0);
		}
		
		

	}
}