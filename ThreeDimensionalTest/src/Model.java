import java.util.*;
import java.io.*;
import javax.media.opengl.*;
import javax.media.opengl.fixedfunc.*;

public class Model {
    private List<Vertex> vertices = new ArrayList<Vertex>();
    private List<Face> faces = new ArrayList<Face>();
    private class Vertex {
        private float x,y,z;
        public Vertex(float x, float y, float z) {
            this.x=x;
            this.y=y;
            this.z=z;
        }
        public float getX() {
            return x;
        }
        public float getY() {
            return y;
        }
        public float getZ() {
            return z;
        }
    }
    private class Face {
        private Vertex a,b,c;
        public Face(Vertex a, Vertex b, Vertex c) {
            this.a=a;
            this.b=b;
            this.c=c;
        }
        public Vertex getA() {
            return a;
        }
        public Vertex getB() {
            return b;
        }
        public Vertex getC() {
            return c;
        }
    }
    public void draw(GL2 gl, float x, float y, float z) {
        gl.glLoadIdentity();
        gl.glTranslatef(x,y,z);
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glColor3f(1.0f, 0.5f, 0.0f);
        for(Face face: faces) {
            Vertex vertexA = face.getA();
            Vertex vertexB = face.getB();
            Vertex vertexC = face.getC();

            gl.glVertex3f(vertexA.getX(), vertexA.getY(), vertexA.getZ());
            gl.glVertex3f(vertexB.getX(), vertexB.getY(), vertexB.getZ());
            gl.glVertex3f(vertexC.getX(), vertexC.getY(), vertexC.getZ());
        }
        gl.glEnd();
    }
    public Model(String filename){
        System.out.println("Loading file " + filename + "...");
        File file;
        Scanner s = null;
        try{ 
            file = new File(filename);
            s = new Scanner(file);
        }catch(FileNotFoundException e) {
            System.err.println("File " + filename + " not found.");
        }
        String line = null;
        while(s.hasNextLine()) {
            line = s.nextLine();
            System.out.println(line);
            if(line.startsWith("v")) {
               Scanner s2 = new Scanner(line);
               s2.next();
               float x = s2.nextFloat();
               float y = s2.nextFloat();
               float z = s2.nextFloat();
               vertices.add(new Vertex(x,y,z));
            }
            if(line.startsWith("f")) {
                Scanner s2 = new Scanner(line);
               s2.next();
               int a = s2.nextInt();
               int b = s2.nextInt();
               int c = s2.nextInt();
               Vertex vertexA = vertices.get(a-1);
               Vertex vertexB = vertices.get(b-1);
               Vertex vertexC = vertices.get(c-1);
               faces.add(new Face(vertexA,vertexB,vertexC));
            }
        }  
    }
}