import java.awt.*;
import java.awt.event.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.fixedfunc.*;
import com.jogamp.opengl.util.FPSAnimator;
import java.util.Set;
import java.util.HashSet;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import java.io.File;
import java.io.IOException;

public class ThreeDimensionalTest implements GLEventListener {
    private Set<Integer> keySet = new HashSet<Integer>();
    private Set<Integer> prevKeySet = new HashSet<Integer>();
    private Model model;
    private TDMap map;
    private TDMap roofFloor;
    private TDMap floor;
    private Entity entity;
    private int loadedTexture = 0;
    private float x = 0.0f;
    private float y = 0.0f;
    private float z = 0.0f;
    private float dy = 0.0f;
    private float cx,cy,cz = 0.0f;
    private float cDirection = 90.0f;
    private Texture stoneBrick;
    public boolean birdsEyeView = false;
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
        draw(gl);
        update();
    }
    private void draw(GL2 gl) {
        gl.glClear(GL.GL_DEPTH_BUFFER_BIT | GL.GL_COLOR_BUFFER_BIT);
        
        gl.glBindTexture(GL2.GL_TEXTURE_2D, loadedTexture);

        //roofFloor.draw(gl,cx,cy-3,cz,(cDirection-90));
        //map.draw(gl,cx,cy-2,cz,(cDirection-90));
        //map.draw(gl,cx,cy-1,cz,(cDirection-90));
        map.draw(gl,cx,cy,cz,(cDirection-90));
        //roofFloor.draw(gl,cx,cy+1,cz,(cDirection-90));

        entity.draw(gl,cx,cy,cz,cDirection);
    }
    private void update(){
        float oldX = x;
        float oldZ = z;
        if(keySet.contains(KeyEvent.VK_A)){
            cDirection += 5.0f;
        }
        if(keySet.contains(KeyEvent.VK_D)){
            cDirection -= 5.0f;
        }
        if(keySet.contains(KeyEvent.VK_W)){
            z -= Math.sin(cDirection*Math.PI/180)*0.2;
            x += Math.cos(cDirection*Math.PI/180)*0.2;
        }
        if(keySet.contains(KeyEvent.VK_S)){
            z += Math.sin(cDirection*Math.PI/180)*0.2;
            x -= Math.cos(cDirection*Math.PI/180)*0.2;
        }
        if(keySet.contains(KeyEvent.VK_NUMPAD8)){
            z = z - 0.1f;
            if(keySet.contains(KeyEvent.VK_CONTROL)){
                z = z + 0.05f;
                y = y - 0.2f;
            }else if(keySet.contains(KeyEvent.VK_SHIFT)){
                z = z - 0.15f;
            }
        }
        if(keySet.contains(KeyEvent.VK_NUMPAD2)){
            z = z + 0.1f;
            if(keySet.contains(KeyEvent.VK_CONTROL)){
                z = z - 0.05f;
                y = y - 0.2f;
            }else if(keySet.contains(KeyEvent.VK_SHIFT)){
                z = z + 0.1f;
            }
        }
        if(keySet.contains(KeyEvent.VK_LEFT)){
            x = x - 0.1f;
            if(keySet.contains(KeyEvent.VK_CONTROL)){
                x = x + 0.05f;
                y = y - 0.2f;
            }else if(keySet.contains(KeyEvent.VK_SHIFT)){
                x = x - 0.05f;
            }
        }
        if(keySet.contains(KeyEvent.VK_RIGHT)){
            x = x + 0.1f;
            if(keySet.contains(KeyEvent.VK_CONTROL)){
                x = x - 0.05f;
                y = y - 0.2f;
            }else if(keySet.contains(KeyEvent.VK_SHIFT)){
                x = x + 0.05f;
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
        float direction = entity.getDirection();
        if(keySet.contains(KeyEvent.VK_Y)){
            // entity.setDz(0.1f);
            entity.setDx((float)-Math.cos((double)direction*Math.PI/180)*0.2f);
            entity.setDz((float)Math.sin((double)direction*Math.PI/180)*0.2f);
        }else if(keySet.contains(KeyEvent.VK_H)){
            // entity.setDz(-0.1f);
            entity.setDx((float)Math.cos((double)direction*Math.PI/180)*0.2f);
            entity.setDz((float)-Math.sin((double)direction*Math.PI/180)*0.2f);
        }else{
            // entity.setDz(0);
            entity.setDx(0.0f);
            entity.setDz(0.0f);
        } 
        if(keySet.contains(KeyEvent.VK_J)){
            // entity.setDx(0.1f);
            entity.setDtheta(-2.0f);
        }else if(keySet.contains(KeyEvent.VK_G)){
            // entity.setDx(-0.1f);
            entity.setDtheta(2.0f);
        }else{
            // entity.setDx(0);
            entity.setDtheta(0);
        }

        if(keySet.contains(KeyEvent.VK_1)&&!prevKeySet.contains(KeyEvent.VK_1)){
            birdsEyeView = !birdsEyeView;
        }
        
        prevKeySet = (Set<Integer>)((HashSet)keySet).clone();
        
        entity.update(map);

        dy -= 0.1f;
        y = y + dy;
        if(y < -0.0f){
            y = -0.0f;
        }
        if(map.cubeHere(x,z)){
            x = oldX;
            z = oldZ;
        }
        cx=x;
        if(birdsEyeView){
            cy=y+10;
        }else{
            cy=y+2;
        }
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
        model = new Model("Militia2.obj", gl);
        map = new TDMap("Maze.map");
        roofFloor = new TDMap("RoofFloor.map");
        floor = new TDMap("Floor.map");
        entity = new Entity(model, 4.0f, 0.0f, -9.0f,
                            -1.0f, 0.0f, -1.0f, 
                            1.5f, 3.0f, 1.5f, 90.0f);

        z = cz = -10;
        cDirection = 270;

        loadedTexture = Utils.loadTexture(gl, "greybrickwall000.png");
        
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
