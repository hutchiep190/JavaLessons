import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.fixedfunc.*;
import com.jogamp.opengl.util.FPSAnimator;

public class Entity{
    private Model model;
    private float x,y,z,direction,dx,dy,dz;
    public Entity(Model model, float x, float y, float z, float direction){
        this.model = model;
        this.x=x;
        this.y=y;
        this.z=z;
        this.direction=direction;
    }
    public void draw(GL2 gl, float cx, float cy, float cz, float cDirection){
        model.draw(gl, x - cx, y - cy, z - cz,  -(cDirection-90));
    }
    public void update(TDMap map){
        x += dx;
        z += dz;
        y += dy;
        if(map.collision(x-1, y, z-1, 2, 5, 2.5f)){
            x -= dx;
            z -= dz;
        }
    }
    public void setDz(float newDz) {
        dz = newDz;
    }
    public void setDx(float newDx) {
        dx = newDx;
    }
}