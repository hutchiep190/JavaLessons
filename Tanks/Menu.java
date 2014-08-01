import java.util.Set;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
public class Menu implements GameState {
    
    private Application app;
    private Image backgroundImage;
    private int cursor = 0;

    public Menu(Application app) {
        this.app = app;
        backgroundImage = Utils.loadImage("Menu.png");
    }

    public void draw(Graphics g){
        Utils.drawSprite(g, backgroundImage, 0, 0, 320, 240, 0, 0);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Castellar", Font.PLAIN, 20));
        if(cursor == 0) {
            g.setColor(Color.WHITE);
        }
        g.drawString("Start!", 140, 100);
        g.setColor(Color.BLACK);
        if(cursor == 1) {
            g.setColor(Color.WHITE);
        }
        g.drawString("Quit!", 140, 130);
    }

    public void update(Set<Integer> keysPressed, Set<Integer> previousKeysPressed){
        if(Utils.keyJustPressed(KeyEvent.VK_DOWN, keysPressed, previousKeysPressed)) {
            cursor = cursor + 1;
            if(cursor > 1) {
                cursor = 0;
            }
        }
        if(Utils.keyJustPressed(KeyEvent.VK_UP, keysPressed, previousKeysPressed)) {
            cursor = cursor - 1;
            if(cursor < 0) {
                cursor = 1;
            }
        }
        if(keysPressed.contains(KeyEvent.VK_ESCAPE)) {
            app.setRunning(false);
        }
        if(keysPressed.contains(KeyEvent.VK_ENTER)){
            if(cursor == 0) {
                app.switchState(TanksSession.class);
            }
            if(cursor == 1) {
                System.exit (0);
            }
        }
    }
}