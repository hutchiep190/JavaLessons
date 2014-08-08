import java.util.Set;
import java.awt.Graphics;

public interface GameState {
    
    public void draw(Graphics g);

    public void reset();

    public void update(Set<Integer> keysPressed, Set<Integer> previousKeysPressed);
}