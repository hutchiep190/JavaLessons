import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.fixedfunc.*;
import com.jogamp.opengl.util.FPSAnimator;

public class Entity{
    private Model model;
    private float x,y,z,direction,dx,dy,dz,dtheta;
    private float xoff, yoff, zoff;
    private float width, height, depth;
    public Entity(Model model, float x, float y, float z,
                  float xoff, float yoff, float zoff,
                  float width, float height, float depth, float direction){
        this.model = model;
        this.x=x;
        this.y=y;
        this.z=z;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.xoff = xoff;
        this.yoff = yoff;
        this.zoff = zoff;
        this.direction=direction;
    }
    public void draw(GL2 gl, float cx, float cy, float cz, float cDirection){
        if (ThreeDimensionalTest.topView) {
            model.drawWithBoundingBox(gl, x - cx, y, z - cz,  -(cDirection-90), direction);
        } else {
            model.drawWithBoundingBox(gl, x - cx, y - cy, z - cz,  -(cDirection-90), direction);
        }
        if (ThreeDimensionalTest.topView) {
            Utils.drawBoundingBox(gl, x+xoff, y+yoff, z+zoff,
                                  x+xoff+width, y+yoff+height, z+zoff+depth,
                                  -cx, -20, -cz, -(cDirection-90), 90.0f, 1.0f, 0.0f, 0.0f);
        } else {
            Utils.drawBoundingBox(gl, x+xoff, y+yoff, z+zoff,
                                  x+xoff+width, y+yoff+height, z+zoff+depth,
                                  -cx, -cy, -cz, -(cDirection-90), 90.0f, 1.0f, 0.0f, 0.0f);
        }
    }
    public float getDirection() {
        return direction;
    }
    public void update(TDMap map){
        x += dx;
        z += dz;
        y += dy;
        direction += dtheta;
        if(map.collision(x+xoff, y+yoff, z+zoff, width, height, depth)){
            x -= dx;
            z -= dz;
        }
    }
    public float getX() { return x; }
    public float getZ() { return z; }
    public void setDz(float newDz) {
        dz = newDz;
    }
    public void setDx(float newDx) {
        dx = newDx;
    }
    public void setDtheta(float dtheta) {
        this.dtheta = dtheta;
    }
}
