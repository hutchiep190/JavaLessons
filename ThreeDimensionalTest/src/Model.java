import java.util.*;
import java.io.*;
import javax.media.opengl.*;
import javax.media.opengl.fixedfunc.*;

public class Model {
    private int angle = 0;
    private List<Vertex> vertices = new ArrayList<Vertex>();
    private List<Face> faces = new ArrayList<Face>();
    private List<Vertex> normals = new ArrayList<Vertex>();
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
        private Vertex a,b,c,an,bn,cn;
        public Face(Vertex a, Vertex b, Vertex c, Vertex an, Vertex bn, Vertex cn) {
            this.a=a;
            this.b=b;
            this.c=c;
            this.an=an;
            this.bn=bn;
            this.cn=cn;
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
        public Vertex getAn() {
            return an;
        }

        public Vertex getBn() {
            return bn;
        }

        public Vertex getCn() {
            return cn;
        }
    }
    public void draw(GL2 gl, float x, float y, float z) {
        gl.glLoadIdentity();
        gl.glTranslatef(x,y,z);
        gl.glRotatef(angle++,0.0f,1.0f,0.0f);
        gl.glBegin(GL2.GL_TRIANGLES);
        float [] color = {1.0f,1.0f,1.0f,1.0f};
        gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT, color, 0);
        gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_DIFFUSE, color, 0);
        for(Face face: faces) {
            Vertex vertexA = face.getA();
            Vertex vertexB = face.getB();
            Vertex vertexC = face.getC();
            Vertex normalA = face.getAn();
            Vertex normalB = face.getBn();
            Vertex normalC = face.getCn();

            gl.glNormal3f(normalA.getX(), normalA.getY(), normalA.getZ());
            gl.glVertex3f(vertexA.getX(), vertexA.getY(), vertexA.getZ());
            gl.glNormal3f(normalB.getX(), normalB.getY(), normalB.getZ());
            gl.glVertex3f(vertexB.getX(), vertexB.getY(), vertexB.getZ());
            gl.glNormal3f(normalC.getX(), normalC.getY(), normalC.getZ());
            gl.glVertex3f(vertexC.getX(), vertexC.getY(), vertexC.getZ());
        }
        gl.glEnd();
    }

    private List<Integer> getIntParts(String token) {
        int slashIndex = token.indexOf("//");
        String firstPart = token.substring(0, slashIndex);
        String secondPart = token.substring(slashIndex+2);
        int firstIntPart = Integer.parseInt(firstPart);
        int secondIntPart = Integer.parseInt(secondPart);
        List<Integer> intParts = new ArrayList<Integer>();
        intParts.add(firstIntPart);
        intParts.add(secondIntPart);
        return intParts;
    }

    private void readVertex(Scanner s) {
        float x = s.nextFloat();
        float y = s.nextFloat();
        float z = s.nextFloat();
        vertices.add(new Vertex(x,y,z));
    }

    private void readNormal(Scanner s) {
        float x = s.nextFloat();
        float y = s.nextFloat();
        float z = s.nextFloat();
        normals.add(new Vertex(x,y,z));
    }

    private void readFace(Scanner s) {
        String a = s.next();
        List<Integer> aParts = getIntParts(a);
        String b = s.next();
        List<Integer> bParts = getIntParts(b);
        String c = s.next();
        List<Integer> cParts = getIntParts(c);
        Vertex vertexA = vertices.get(aParts.get(0)-1);
        Vertex vertexB = vertices.get(bParts.get(0)-1);
        Vertex vertexC = vertices.get(cParts.get(0)-1);
        Vertex normalA = normals.get(aParts.get(1)-1);
        Vertex normalB = normals.get(bParts.get(1)-1);
        Vertex normalC = normals.get(cParts.get(1)-1);
        faces.add(new Face(vertexA,vertexB,vertexC,normalA,normalB,normalC));
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
            Scanner ls = new Scanner(line);
            String firstToken = ls.next();
            if(firstToken.equals("v")) {
               readVertex(ls);
            } else if(firstToken.equals("vn")) {
               readNormal(ls);
            } else if(firstToken.equals("f")) {
                readFace(ls);
            }
        }  
    }
}