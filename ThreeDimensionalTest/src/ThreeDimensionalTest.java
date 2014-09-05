import java.awt.*;
import java.awt.event.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.fixedfunc.*;
import com.jogamp.opengl.util.FPSAnimator;
import java.util.Set;
import java.util.HashSet;

public class ThreeDimensionalTest implements GLEventListener {
    private Set<Integer> keySet = new HashSet<Integer>();
    private Model model;
    private TDMap map;
    private float x = 0.0f;
    private float y = 0.0f;
    private float z = 0.0f;
    private float dy = 0.0f;
    private float cx,cy,cz = 0.0f;
    private float cDirection = 90.0f;
    public Set<Integer> getKeySet(){
        return keySet;
    }
    public static void main(String[] args) { 
        Frame frame = new Frame("3D Stuff");
        frame.setSize(640, 480);
        frame.setResizable(false);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        GLCanvas canvas = new GLCanvas();
        ThreeDimensionalTest tdt = new ThreeDimensionalTest();
        frame.add(canvas);
        canvas.addGLEventListener(tdt);
        frame.setVisible(true);
        canvas.requestFocus();
        FPSAnimator animator = new FPSAnimator(canvas, 30);
        animator.start();
        final Set<Integer> keySet = tdt.getKeySet();
        canvas.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e){keySet.add(e.getKeyCode());}
            public void keyReleased(KeyEvent e){keySet.remove(e.getKeyCode());} 
        });
    }
    
    public void display(GLAutoDrawable glDrawable){
        GL2 gl = glDrawable.getGL().getGL2();
        gl.glClear(GL.GL_DEPTH_BUFFER_BIT | GL.GL_COLOR_BUFFER_BIT);
        
        map.draw(gl,cx,cy,cz,cDirection);

        model.draw(gl, 0.0f - cx, -2.7f - cy, -10.0f - cz, -(cDirection-90));
        if(keySet.contains(KeyEvent.VK_CONTROL)){}
        if(keySet.contains(KeyEvent.VK_A)){
            cDirection += 5.0f;
        }
        if(keySet.contains(KeyEvent.VK_D)){
            cDirection -= 5.0f;
        }
        if(keySet.contains(KeyEvent.VK_W)){
            z = z - 0.1f;
            if(keySet.contains(KeyEvent.VK_CONTROL)){
                z = z + 0.05f;
            }else if(keySet.contains(KeyEvent.VK_SHIFT)){
                z = z - 0.15f;
            }
        }
        if(keySet.contains(KeyEvent.VK_S)){
            z = z + 0.1f;
            if(keySet.contains(KeyEvent.VK_CONTROL)){
                z = z - 0.05f;
            }else if(keySet.contains(KeyEvent.VK_SHIFT)){
                z = z + 0.1f;
            }
        }
        if(keySet.contains(KeyEvent.VK_SPACE)){

            if(y == 0.0f) {
                dy = 0.5f;
            }
        }
        if(keySet.contains(KeyEvent.VK_SHIFT)){
            if(keySet.contains(KeyEvent.VK_SPACE)){
                if(y == -0.0f){
                    dy = 1.0f;
                }
            }
        }

        dy -= 0.1f;
        y = y + dy;
        if(y < -0.0f){
            y = -0.0f;
        }
        cx=x;
        cy=y+2;
        cz=z;
    }

    public void init(GLAutoDrawable glDrawable){
        GL2 gl = glDrawable.getGL().getGL2();
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_LIGHT0);
        float [] zero = {0.0f, 0.0f, 0.0f, 1.0f};
        float [] diffuse = {1.0f, 1.0f, 1.0f, 1.0f};
        float [] position = {0.0f, 10.0f, 0.0f, 1.0f};
        float [] ambient = {0.2f, 0.2f, 0.2f, 1.0f};
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, position, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, diffuse, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, zero, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, ambient, 0);
        model = new Model("test.obj");
        map = new TDMap("Maze.map");
        
    }
    public void reshape(GLAutoDrawable glDrawable, int x, int y, int width, int height){ 
        GL2 gl = glDrawable.getGL().getGL2();
        final float aspect = (float) width / (float) height;
        gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);gl.glLoadIdentity();
        final float fh = 0.5f;
        final float fw = fh * aspect;
        gl.glFrustumf(-fw, fw, -fh, fh, 1.0f, 1000.0f);
        gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
    public void dispose(GLAutoDrawable glDrawable){}
}