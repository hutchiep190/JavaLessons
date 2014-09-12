import com.jogamp.common.nio.Buffers;
import java.util.*;
import java.io.*;
import javax.media.opengl.*;
import javax.media.opengl.fixedfunc.*;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;

public class Model {
    private int angle = 0;
    private int texId;
    private List<Vertex> vertices = new ArrayList<Vertex>();
    private List<Face> faces = new ArrayList<Face>();
    private List<Vertex> normals = new ArrayList<Vertex>();
    private Map<String, Material> materials = new HashMap<String, Material>();
    private List<float[]> texCoords = new ArrayList<float[]>();
    private class Material {
        private String name;
        private float [] ambient,diffuse,specular;
        public Material(String name, float [] ambient, float [] diffuse, float [] specular) {
            this.name=name;
            this.ambient=ambient;
            this.diffuse=diffuse;
            this.specular=specular;
        }
        public float [] getAmbient() {
            return ambient;
        }
        public float [] getDiffuse() {
            return diffuse;
        }
        public float [] getSpecular() {
            return specular;
        }
    }
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
        private Material mtl;
        private float[] at, bt, ct;
        public Face(Material mtl,
                    Vertex a, Vertex b, Vertex c,
                    Vertex an, Vertex bn, Vertex cn,
                    float[] at, float[] bt, float[] ct) {
            this.mtl=mtl;
            this.a=a;
            this.b=b;
            this.c=c;
            this.an=an;
            this.bn=bn;
            this.cn=cn;
            this.at = at;
            this.bt = bt;
            this.ct = ct;
        }
        public Material getMtl() {
            return mtl;
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

        public float[] getAt() {
            return at;
        }

        public float[] getBt() {
            return bt;
        }

        public float[] getCt() {
            return ct;
        }
    }
    public void draw(GL2 gl, float x, float y, float z, float direction) {
        gl.glLoadIdentity();
        gl.glRotatef(direction, 0.0f, 1.0f, 0.0f);
        gl.glTranslatef(x,y,z);
        gl.glEnable(GL2.GL_TEXTURE_2D);
        gl.glBindTexture(GL2.GL_TEXTURE_2D, texId);
        gl.glBegin(GL2.GL_TRIANGLES);
        float [] color = {1.0f,1.0f,1.0f,1.0f};
        for(Face face: faces) {
            Material material = face.getMtl();
            gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT, material.getAmbient(), 0);
            gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_DIFFUSE, material.getDiffuse(), 0);
            gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, material.getSpecular(), 0);
            Vertex vertexA = face.getA();
            Vertex vertexB = face.getB();
            Vertex vertexC = face.getC();
            Vertex normalA = face.getAn();
            Vertex normalB = face.getBn();
            Vertex normalC = face.getCn();

            gl.glNormal3f(normalA.getX(), normalA.getY(), normalA.getZ());
            gl.glTexCoord2d(face.getAt()[0], face.getAt()[1]);
            gl.glVertex3f(vertexA.getX(), vertexA.getY(), vertexA.getZ());
            gl.glNormal3f(normalB.getX(), normalB.getY(), normalB.getZ());
            gl.glTexCoord2d(face.getBt()[0], face.getBt()[1]);
            gl.glVertex3f(vertexB.getX(), vertexB.getY(), vertexB.getZ());
            gl.glNormal3f(normalC.getX(), normalC.getY(), normalC.getZ());
            gl.glTexCoord2d(face.getCt()[0], face.getCt()[1]);
            gl.glVertex3f(vertexC.getX(), vertexC.getY(), vertexC.getZ());
        }
        gl.glEnd();
        gl.glDisable(GL2.GL_TEXTURE_2D);
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

    private void readFace(Scanner s, String mtlName) {
        String a = s.next();
        String b = s.next();
        String c = s.next();
        String[] aParts = a.split("/");
        String[] bParts = b.split("/");
        String[] cParts = c.split("/");
        Vertex vertexA = vertices.get(Integer.parseInt(aParts[0])-1);
        Vertex vertexB = vertices.get(Integer.parseInt(bParts[0])-1);
        Vertex vertexC = vertices.get(Integer.parseInt(cParts[0])-1);
        Vertex normalA = normals.get(Integer.parseInt(aParts[2])-1);
        Vertex normalB = normals.get(Integer.parseInt(bParts[2])-1);
        Vertex normalC = normals.get(Integer.parseInt(cParts[2])-1);
        float[] texA = texCoords.get(Integer.parseInt(aParts[1])-1);
        float[] texB = texCoords.get(Integer.parseInt(bParts[1])-1);
        float[] texC = texCoords.get(Integer.parseInt(cParts[1])-1);
        faces.add(new Face(materials.get(mtlName),
                           vertexA,vertexB,vertexC,
                           normalA,normalB,normalC,
                           texA, texB, texC));
    }

    private float [] readColor(Scanner s) {
        float r = s.nextFloat();
        float g = s.nextFloat();
        float b = s.nextFloat();
        float [] color = {r,g,b,1};
        return color;
    }

    private void readTexCoord(Scanner s) {
        float u = s.nextFloat();
        float v = s.nextFloat();
        float[] texCoord = { u, v };
        texCoords.add(texCoord);
    }

    private void readMaterial(String mn, Scanner ms) {
        System.out.println("Loading " + mn + "...");
        if(!ms.hasNextLine()){
            return;
        }
        float [] ambient = null;
        float [] diffuse = null;
        float [] specular = null;
        String line = ms.nextLine();
        while(!line.isEmpty()){
            Scanner ls = new Scanner(line);
            String token = ls.next();
            if(token.equals("Ka")){
                ambient = readColor(ls);
            } else if(token.equals("Kd")){
                diffuse = readColor(ls);
            } else if(token.equals("Ks")){
                specular = readColor(ls);
            }
            if(ms.hasNextLine()) {
                line = ms.nextLine();
            } else {
                line = "";
            }
        }
        materials.put(mn, new Material(mn, ambient, diffuse, specular));
    }

    private void readMaterials(Scanner s) {
        String materialFilename = s.next();
        System.out.println("Loading " + materialFilename + "...");
        File file;
        Scanner ms = null;
        try{ 
            file = new File(materialFilename);
            ms = new Scanner(file);
        }catch(FileNotFoundException e) {
            System.err.println("File " + materialFilename + " not found.");
        }
        String line = null;
        while(ms.hasNextLine()) {
            line = ms.nextLine();
            Scanner ls = new Scanner(line);
            if(ls.hasNext()) {
                String firstToken = ls.next();
                if(firstToken.equals("newmtl")) {
                    String materialName = ls.next();
                    readMaterial(materialName,ms);
                } 
            }
        }
    }

    public Model(String filename, GL2 gl){
        this.texId = texId;
        System.out.println("Loading file " + filename + "...");
        File file;
        Scanner s = null;
        String mfu = null;
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
            } else if(firstToken.equals("vt")) {
                readTexCoord(ls);
            } else if(firstToken.equals("f")) {
                readFace(ls, mfu);
            } else if(firstToken.equals("mtllib")) {
                readMaterials(ls);
            } else if(firstToken.equals("usemtl")) {
                mfu = ls.next();
            }
        }  

        texId = Utils.loadTexture(gl, "Militia-Texture.jpg");
    }
}
