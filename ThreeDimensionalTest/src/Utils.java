import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;
import java.io.*;
import javax.media.opengl.*;
import com.jogamp.common.nio.Buffers;

public class Utils {

    public static int loadTexture(GL2 gl, String filename) {
        int[] tmp = new int[1];
        gl.glGenTextures(1, tmp, 0);
        gl.glBindTexture(GL2.GL_TEXTURE_2D, tmp[0]);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S, GL2.GL_CLAMP);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_T, GL2.GL_CLAMP);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER,
                           GL2.GL_LINEAR);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER,
                           GL2.GL_LINEAR);
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(filename));
        } catch (IOException e) {
            System.err.println("File not found: " + filename);
        }
        int imgWidth = image.getWidth();
        int imgHeight = image.getHeight();
        ByteBuffer imgBuf = Buffers.newDirectByteBuffer(imgWidth*imgHeight * 4);
        for (int j = 0; j < imgHeight; ++j) {
            for (int i = 0; i < imgWidth; ++i) {
                int p = image.getRGB(i, imgHeight - 1 - j);
                imgBuf.put((byte)((p >> 16) & 0xff));
                imgBuf.put((byte)((p >> 8) & 0xff));
                imgBuf.put((byte)((p >> 0) & 0xff));
                imgBuf.put((byte)((p >> 24) & 0xff));
            }
        }
        imgBuf.rewind();
        gl.glTexImage2D(GL2.GL_TEXTURE_2D, 0, GL2.GL_RGBA, imgWidth,
                        imgHeight, 0, GL2.GL_RGBA, GL2.GL_UNSIGNED_BYTE, imgBuf);
        return tmp[0];
    }

    public static void drawBoundingBox(GL2 gl, float x1, float y1, float z1,
                                       float x2, float y2, float z2,
                                       float x, float y, float z,
                                       float cDirection,
                                       float direction,
                                       float r, float g, float b) {
        gl.glLoadIdentity();
        if (ThreeDimensionalTest.topView) {
            gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
        } else {
            gl.glRotatef(cDirection, 0.0f, 1.0f, 0.0f);
        }
        gl.glTranslatef(x,y,z);
        gl.glRotatef(direction - 90.0f, 0.0f, 1.0f, 0.0f);
        gl.glDisable(GL2.GL_LIGHTING);
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_LINE);
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(r, g, b);

        gl.glVertex3f(x1, y1, z1);
        gl.glVertex3f(x2, y1, z1);
        gl.glVertex3f(x2, y1, z2);
        gl.glVertex3f(x1, y1, z2);

        gl.glVertex3f(x1, y2, z1);
        gl.glVertex3f(x2, y2, z1);
        gl.glVertex3f(x2, y2, z2);
        gl.glVertex3f(x1, y2, z2);

        gl.glVertex3f(x1, y1, z1);
        gl.glVertex3f(x1, y2, z1);
        gl.glVertex3f(x1, y2, z2);
        gl.glVertex3f(x1, y1, z2);

        gl.glVertex3f(x2, y1, z1);
        gl.glVertex3f(x2, y2, z1);
        gl.glVertex3f(x2, y2, z2);
        gl.glVertex3f(x2, y1, z2);

        gl.glEnd();
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
        gl.glEnable(GL2.GL_LIGHTING);
    }
}
