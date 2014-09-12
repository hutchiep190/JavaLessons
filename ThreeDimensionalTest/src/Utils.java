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
}
