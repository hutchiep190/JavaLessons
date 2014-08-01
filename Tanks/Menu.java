import java.util.Set;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.Image;
import java.awt.Color;

public class Menu implements GameState {
    
    private Application app;
    private Image backgroundImage;

    public Menu(Application app) {
        this.app = app;
        backgroundImage = Utils.loadImage("Tanks.png");
    }

    public void draw(Graphics g){
        Utils.drawSprite(g, backgroundImage, 0, 0, 320, 240, 0, 0);
        g.setColor(new Color(0x6B,0x8E,0x23));
        g.drawString("Start!", 140, 100);
    }

    public void update(Set<Integer> keysPressed, Set<Integer> previousKeysPressed){
        if(keysPressed.contains(KeyEvent.VK_ESCAPE)) {
            app.setRunning(false);
        }
        if(keysPressed.contains(KeyEvent.VK_ENTER)){
            app.switchState(TanksSession.class);
        }
    }
}