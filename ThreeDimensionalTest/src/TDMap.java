import java.io.*;
import java.util.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.fixedfunc.*;
import com.jogamp.opengl.util.FPSAnimator;

public class TDMap {
    private List<List<Integer>> buildData = new ArrayList<List<Integer>>();
    public TDMap(String mapFile){
        Scanner ms;
        File mf = new File(mapFile);
        try{
            ms = new Scanner(mf);
        }catch(FileNotFoundException e){
            System.err.println("Error, file " + mapFile + " not found.");
            return;
        }
        while(ms.hasNextLine()) {
            String mapFileLine = ms.nextLine();
            List<Integer> mapLineIntegers = new ArrayList<Integer>();
            Scanner mfls = new Scanner(mapFileLine);
            while(mfls.hasNextInt()) {
                mapLineIntegers.add(mfls.nextInt());
            }
            buildData.add(mapLineIntegers);
        }
    }
    public void draw(GL2 gl, float cx, float cy, float cz, float cDirection){
        for(int i = 0; i < buildData.size(); i++){
           List<Integer> list = buildData.get(i);
           for(int j = 0; j < list.size(); j++){
                int value = list.get(j);
                if(value == 1){
                    drawCube(gl,j-cx,-cy,i-cz,-(cDirection-90));
                }
            }
        }
    }
    private void drawCube(GL2 gl, float x, float y, float z, float direction){
        gl.glLoadIdentity();
        gl.glRotatef(direction,0.0f,1.0f,0.0f);
        gl.glTranslatef(x,y,z);
        gl.glBegin(GL2.GL_QUADS);
        gl.glNormal3f(0.0f,0.0f,1.0f);
        gl.glColor3f(1.0f, 0.5f, 0.0f);
        gl.glVertex3f(0.0f,1.0f,1.0f);
        gl.glVertex3f(1.0f,1.0f,1.0f);
        gl.glVertex3f(1.0f,0.0f,1.0f);
        gl.glVertex3f(0.0f,0.0f,1.0f);
        gl.glNormal3f(-1.0f,0.0f,0.0f);
        gl.glColor3f(0.5f,0.25f,0.0f);
        gl.glVertex3f(0.0f,1.0f,1.0f);
        gl.glVertex3f(0.0f,1.0f,0.0f);
        gl.glVertex3f(0.0f,0.0f,0.0f);
        gl.glVertex3f(0.0f,0.0f,1.0f);
        gl.glNormal3f(1.0f,0.0f,0.0f);
        gl.glColor3f(0.5f,0.25f,0.0f);
        gl.glVertex3f(1.0f,1.0f,1.0f);
        gl.glVertex3f(1.0f,1.0f,0.0f);
        gl.glVertex3f(1.0f,0.0f,0.0f);
        gl.glVertex3f(1.0f,0.0f,1.0f);
        gl.glNormal3f(0.0f,1.0f,0.0f);
        gl.glColor3f(0.75f,0.375f,0.0f);
        gl.glVertex3f(0.0f,1.0f,1.0f);
        gl.glVertex3f(0.0f,1.0f,0.0f);
        gl.glVertex3f(1.0f,1.0f,0.0f);
        gl.glVertex3f(1.0f,1.0f,1.0f);
        gl.glNormal3f(0.0f,-1.0f,0.0f);
        gl.glColor3f(0.75f,0.375f,0.0f);
        gl.glVertex3f(0.0f,0.0f,1.0f);
        gl.glVertex3f(0.0f,0.0f,0.0f);
        gl.glVertex3f(1.0f,0.0f,0.0f);
        gl.glVertex3f(1.0f,0.0f,1.0f);
        gl.glNormal3f(0.0f,0.0f,-1.0f);
        gl.glColor3f(1.0f, 0.5f, 0.0f);
        gl.glVertex3f(0.0f,1.0f,0.0f);
        gl.glVertex3f(1.0f,1.0f,0.0f);
        gl.glVertex3f(1.0f,0.0f,0.0f);
        gl.glVertex3f(0.0f,0.0f,0.0f);
        gl.glEnd();

    }
}