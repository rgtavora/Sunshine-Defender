package br.unifor;

import br.unifor.actors.Player;
import br.unifor.actors.Alien;
import br.unifor.controllers.Deus;
import br.unifor.controllers.Cindacta;
import br.unifor.controllers.Lakitu;
import com.sun.opengl.util.Animator;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureData;
import com.sun.opengl.util.texture.TextureIO;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

public class GUI implements GLEventListener {
    private static Frame frame;
    private static GLCanvas canvas;
    static boolean rotacionar;
    
    public static Cindacta cind;
    static Lakitu lakitu;
    static Deus deus;
    public static Player player;
    static Alien alien;
    
    InputStream isSL0, isSL1, isSL2;
    TextureData tdSL0, tdSL1, tdSL2;
    Texture txtrSL0, txtrSL1, txtrSL2;
    float[] rgba;

    public static void main(String[] args) {
        frame = new Frame("Rodrigo Gato - CG 2013.1");
        canvas = new GLCanvas();

        canvas.addGLEventListener(new GUI());
        frame.add(canvas);
        frame.setSize(800, 600);
        final Animator animator = new Animator(canvas);
        
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new Thread(new Runnable() {
                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                controlarCamera(e);
                controlarPlayer(e);
                
                canvas.display();
            }
        });
        
        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                controlarCamera(e);
                controlarPlayer(e);
                
                canvas.display();
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }

    public void init(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        
        gl.setSwapInterval(1);
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH);
        
        
        try {
            cind = new Cindacta();
            deus = new Deus();
            player = new Player(gl);
            alien = new Alien(gl);
            lakitu = new Lakitu();
            rotacionar = false;
            
            isSL0 = getClass().getClassLoader().getResourceAsStream("br/unifor/textures/space_layer_0.png");
            tdSL0 = TextureIO.newTextureData(isSL0, false, "png");
            txtrSL0 = TextureIO.newTexture(tdSL0);
            isSL1 = getClass().getClassLoader().getResourceAsStream("br/unifor/textures/space_layer_1.png");
            tdSL1 = TextureIO.newTextureData(isSL1, false, "png");
            txtrSL1 = TextureIO.newTexture(tdSL1);
            isSL2 = getClass().getClassLoader().getResourceAsStream("br/unifor/textures/space_layer_2.png");
            tdSL2 = TextureIO.newTextureData(isSL2, false, "png");
            txtrSL2 = TextureIO.newTexture(tdSL2);
            rgba = new float[]{1f, 1f, 1f};
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

        if (height <= 0) {
        
            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(60.0f, h, 1.0, 256.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        
        gl.glRotatef(lakitu.getR(), 0.0f, 1.0f, 0.0f);
        gl.glTranslatef(lakitu.getPosCamX() + lakitu.getX(),
                lakitu.getPosCamY() + lakitu.getY(),
                lakitu.getPosCamZ() - 2.0f + lakitu.getZ());
        
        deus.facaSeLuz(gl);
        
        desenharGrid(gl);

        //Layer espacial 0
        txtrSL0.enable();
        txtrSL0.bind();

        gl.glBegin(GL.GL_POLYGON);
            gl.glTexCoord2f(0.0f, 0.0f);
            gl.glVertex3f(-24.0f, -3.0f, 24.0f);
            gl.glTexCoord2f(1.0f, 0.0f);
            gl.glVertex3f(-24.0f, -3.0f, -88.0f);
            gl.glTexCoord2f(1.0f, 1.0f);
            gl.glVertex3f(88.0f, -3.0f, -88.0f);
            gl.glTexCoord2f(0.0f, 1.0f);
            gl.glVertex3f(88.0f, -3.0f, 24.0f);
        gl.glEnd();

        //Layer espacial 1
        txtrSL1.enable();
        txtrSL1.bind();

        gl.glBegin(GL.GL_POLYGON);
            gl.glTexCoord2f(0.0f, 0.0f);
            gl.glVertex3f(-16.0f, -2.0f, 16.0f);
            gl.glTexCoord2f(1.0f, 0.0f);
            gl.glVertex3f(-16.0f, -2.0f, -80.0f);
            gl.glTexCoord2f(1.0f, 1.0f);
            gl.glVertex3f(80.0f, -2.0f, -80.0f);
            gl.glTexCoord2f(0.0f, 1.0f);
            gl.glVertex3f(80.0f, -2.0f, 16.0f);
        gl.glEnd();

        //Layer espacial 2
        txtrSL2.enable();
        txtrSL2.bind();

        gl.glBegin(GL.GL_POLYGON);
            gl.glTexCoord2f(0.0f, 0.0f);
            gl.glVertex3f(-8.0f, -1.0f, 8.0f);
            gl.glTexCoord2f(1.0f, 0.0f);
            gl.glVertex3f(-8.0f, -1.0f, -72.0f);
            gl.glTexCoord2f(1.0f, 1.0f);
            gl.glVertex3f(72.0f, -1.0f, -72.0f);
            gl.glTexCoord2f(0.0f, 1.0f);
            gl.glVertex3f(72.0f, -1.0f, 8.0f);
        gl.glEnd();

        gl.glEnable(GL.GL_DEPTH_TEST);
        deus.criarSol(gl, glu);
        deus.criarMercurio(gl, glu);
        deus.criarVenus(gl, glu);
        deus.criarTerra(gl, glu);
        deus.criarMarte(gl, glu);
        
        player.inserir(gl);
        alien.inserir(gl);

        if(!rotacionar) {
            deus.iniciarRotacao();
            alien.iniciar();
            rotacionar = true;
        }

        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
    
    public void desenharGrid(GL gl) {
        gl.glBegin(GL.GL_LINES);
            gl.glColor3f(0.0f, 0.0f, 1.0f);
            
            float xi = 8.0f;
            float xf = 56.0f;
            float zi = -8.0f;
            float zf = -56.0f;

            for(float x = 0.0f; x <= 64.0f; x += 2.0f) {
                gl.glVertex3f(x, 0.0f, zi);
                gl.glVertex3f(x, 0.0f, zf);
                
                if(x >= 56.0f) {
                    zi -= 2.0f;
                    zf += 2.0f;
                }else {
                    if(zi < 0.0f)
                        zi += 2.0f;
                    if(zf > -64.0f)
                        zf -= 2.0f;
                }
            }
            
            for(float z = 0.0f; z >= -64.0f; z -= 2.0f) {
                gl.glVertex3f(xi, 0.0f, z);
                gl.glVertex3f(xf, 0.0f, z);
                
                if(z <= -56.0f) {
                    xi += 2.0f;
                    xf -= 2.0f;
                }else {
                    if(xi > 0.0f)
                        xi -= 2.0f;
                    if(xf < 64.0f)
                        xf += 2.0f;
                }
            }
            
            gl.glColor3f(1.0f, 1.0f, 1.0f);
        gl.glEnd();
    }
    
    public static void controlarCamera(KeyEvent e) {
        //Eixo X positivo
        if(e.getKeyChar() == 'y') {
            lakitu.setX(lakitu.getX() + 1.0f);
        }
        //Eixo X negativo
        if(e.getKeyChar() == 'u') {
            lakitu.setX(lakitu.getX() - 1.0f);
        }

        //Eixo Y positivo
        if(e.getKeyChar() == 'h') {
            lakitu.setY(lakitu.getY() + 1.0f);
        }
        //Eixo Y negativo
        if(e.getKeyChar() == 'j') {
            lakitu.setY(lakitu.getY() - 1.0f);
        }

        //Eixo Z positivo
        if(e.getKeyChar() == 'n') {
            lakitu.setZ(lakitu.getZ() + 1.0f);
        }
        //Eixo Z negativo
        if(e.getKeyChar() == 'm') {
            lakitu.setZ(lakitu.getZ() - 1.0f);
        }
    }
    
    public static void controlarPlayer(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_UP:
                player.voar();
                break;
            case KeyEvent.VK_DOWN:
                player.virar(KeyEvent.VK_DOWN);
                break;
            case KeyEvent.VK_LEFT:
                player.virar(KeyEvent.VK_LEFT);
                break;
            case KeyEvent.VK_RIGHT :
                player.virar(KeyEvent.VK_RIGHT);
                break;
        }
    }
}