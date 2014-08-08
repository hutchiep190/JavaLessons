import java.util.Set;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.Image;
import java.awt.Color;

public class Splash implements GameState {
    
    private Application app;
    private Image backgroundImage;
    private int t = 0;

    public void reset(){}

    public Splash(Application app) {
        this.app = app;
        backgroundImage = Utils.loadImage("Tanks.png");
    }

    public void draw(Graphics g){
        Utils.drawSprite(g, backgroundImage, 0, 0, 320, 240, 0, 0);
    }

    public void update(Set<Integer> keysPressed, Set<Integer> previousKeysPressed){
        
        t++;

        if(!keysPressed.isEmpty()){
            app.switchState(Menu.class);
        }
        if(t > 50) {
            app.switchState(Menu.class);
        }
    }
}