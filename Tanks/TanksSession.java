import java.awt.Frame;
import java.awt.Color;
import javax.swing.Timer;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.Set;
import java.util.HashSet;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class TanksSession implements GameState {

	private Application app;
	private Image backroundImage;
	private Image tankTurretSprite;
	private Image tankSprite;
	private Image bulletSprite;
	private List<Bullet> bullets = new ArrayList<Bullet>();
	private List<Tank> tanks = new ArrayList<Tank>();
	private Tank player;

	public TanksSession(Application app){
		
		backroundImage = Utils.loadImage("tan-camo.png");
		bulletSprite = Utils.loadImage("bulletsprites.png");
		tankTurretSprite = Utils.loadImage("tankTurretSprite.png");
		tankSprite = Utils.loadImage("tankSprite.png");

		player = new Tank(0, 0, Direction.RIGHT, tankSprite, tankTurretSprite);
		tanks.add(player);

		this.app = app;
    }

	public void update(Set<Integer> keysPressed,Set<Integer> previousKeysPressed) {
		for(int i = 0; i < bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			if(bullet == null){
				continue;
			}
			if(bullet.getX() > 640 || bullet.getX() < -320 || bullet.getY() > 480 || bullet.getY() < -240) {
				bullets.set(i, null);
			}
		}
		bullets.removeAll(Collections.singleton(null));

		if(keysPressed.contains(KeyEvent.VK_ESCAPE)) {
            app.switchState(Menu.class);
        }
		if(keysPressed.contains(KeyEvent.VK_RIGHT)) {
			player.moveRight();
		}
		if(keysPressed.contains(KeyEvent.VK_LEFT)) {
			player.moveLeft();
		}
		if(keysPressed.contains(KeyEvent.VK_DOWN)) {
			player.moveDown();
		}
		if(keysPressed.contains(KeyEvent.VK_UP)) {
			player.moveUp();
		}

		if(Utils.keyJustPressed(KeyEvent.VK_W, keysPressed, previousKeysPressed)) {
			player.setTurretDirection(Direction.UP);
			bullets.add(new Bullet(bulletSprite, player.getX()+11, player.getY()-13, 0));
		}
		if(Utils.keyJustPressed(KeyEvent.VK_A, keysPressed, previousKeysPressed)) {
			player.setTurretDirection(Direction.LEFT);
			bullets.add(new Bullet(bulletSprite, player.getX()-13, player.getY()+11, 1));
		}
		if(Utils.keyJustPressed(KeyEvent.VK_S, keysPressed, previousKeysPressed)) {
			player.setTurretDirection(Direction.DOWN);
			bullets.add(new Bullet(bulletSprite, player.getX()+11, player.getY()+32, 2));
		}
		if(Utils.keyJustPressed(KeyEvent.VK_D, keysPressed, previousKeysPressed)) {
			player.setTurretDirection(Direction.RIGHT);
			bullets.add(new Bullet(bulletSprite, player.getX()+32, player.getY()+11, 3));
		}
		for(Bullet bullet : bullets){
			bullet.update();
		}
	}
	private void clearScreen(Graphics g){
		Utils.drawSprite(g, backroundImage, 0, 0, 320, 240, 0, 0);
	}
	public void draw(Graphics g){
		clearScreen(g);
		for(Tank tank : tanks) {
			tank.draw(g);
		}
		for(Bullet bullet : bullets) {
			bullet.draw(g);
		}
	}
}