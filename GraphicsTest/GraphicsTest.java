import java.awt.Frame;

public class GraphicsTest {

    public static void main (String[] args) {
        Frame f = new Frame();
        f.addWindowListener(new GraphicsTestWindowAdapter());
        f.setSize(320, 240);
        f.setVisible(true);
    }

}