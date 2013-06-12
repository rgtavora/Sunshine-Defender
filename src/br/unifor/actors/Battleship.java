package br.unifor.actors;

import br.unifor.GUI;
import static br.unifor.GUI.lakitu;
import br.unifor.util.ObjLoader;
import br.unifor.util.ObjModel;
import br.unifor.util.TextureHandler;
import java.io.IOException;
import javax.media.opengl.GL;

public class Battleship implements Actor {
    
    private ObjModel obj;
    private TextureHandler txtr;
    
    private float k;
    private float coe;
    public float x;
    
    public Battleship(GL gl) throws IOException {
        super();
        this.obj = ObjLoader.loadObj("br/unifor/models/Batwing.obj", gl);
        this.txtr = new TextureHandler("src/br/unifor/textures/ship.jpg");
        
        this.k = 0.0f;
        this.coe = 0.025f;
        this.x = 0.0f;
        
    }

    public void inserir(GL gl) {
        txtr.getTexture().enable();
        txtr.getTexture().bind();
        
        gl.glPushMatrix();
            gl.glTranslatef(4.0f + x - GUI.xMenu, 76.0f, -32.0f);
            gl.glRotatef(315.0f + k, 1.0f, 0.0f, 0.0f);
            gl.glRotatef(270.0f, 0.0f, 0.0f, 1.0f);
            gl.glScalef(0.04f, 0.04f, 0.04f);
            obj.render(gl);
        gl.glPopMatrix();
        
        txtr.getTexture().disable();
        
        kinect();
    }

    public void voar() {
        if(x > -48.0f)
            x--;
    }

    public void virar(int k) {}
    
    private void kinect() {
        k += coe;
        if(k >= 3.0f || k <= -3.0f)
            coe = coe * -1;
    }
    
}