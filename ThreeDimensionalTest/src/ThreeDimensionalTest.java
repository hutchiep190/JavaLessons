import java.awt.*;
import java.awt.event.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.fixedfunc.*;
import com.jogamp.opengl.util.FPSAnimator;

public class ThreeDimensionalTest implements GLEventListener {
    private Model tank;
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
        frame.add(canvas);
        canvas.addGLEventListener(new ThreeDimensionalTest());
        frame.setVisible(true);
        canvas.requestFocus();
        FPSAnimator animator = new FPSAnimator(canvas, 60);
        animator.start();
    }
    public void drawCube(GL2 gl, float x, float y, float z){
        gl.glLoadIdentity();
        gl.glTranslatef(x,y,z);
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(1.0f, 0.5f, 0.0f);
        gl.glVertex3f(-1.0f,1.0f,1.0f);
        gl.glVertex3f(1.0f,1.0f,1.0f);
        gl.glVertex3f(1.0f,-1.0f,1.0f);
        gl.glVertex3f(-1.0f,-1.0f,1.0f);
        gl.glColor3f(0.5f,0.25f,0.0f);
        gl.glVertex3f(-1.0f,1.0f,1.0f);
        gl.glVertex3f(-1.0f,1.0f,-1.0f);
        gl.glVertex3f(-1.0f,-1.0f,-1.0f);
        gl.glVertex3f(-1.0f,-1.0f,1.0f);
        gl.glColor3f(0.5f,0.25f,0.0f);
        gl.glVertex3f(1.0f,1.0f,1.0f);
        gl.glVertex3f(1.0f,1.0f,-1.0f);
        gl.glVertex3f(1.0f,-1.0f,-1.0f);
        gl.glVertex3f(1.0f,-1.0f,1.0f);
        gl.glColor3f(0.75f,0.375f,0.0f);
        gl.glVertex3f(-1.0f,1.0f,1.0f);
        gl.glVertex3f(-1.0f,1.0f,-1.0f);
        gl.glVertex3f(1.0f,1.0f,-1.0f);
        gl.glVertex3f(1.0f,1.0f,1.0f);
        gl.glEnd();
    }
    public void display(GLAutoDrawable glDrawable){
        
        GL2 gl = glDrawable.getGL().getGL2();
        gl.glClear(GL.GL_DEPTH_BUFFER_BIT | GL.GL_COLOR_BUFFER_BIT);
        
        tank.draw(gl, 3.0f, -2.7f, -10.0f);
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
        tank = new Model("tank.obj");
        
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