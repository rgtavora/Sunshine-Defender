package br.unifor;

import br.unifor.actors.Player;
import br.unifor.actors.Alien;
import br.unifor.controllers.Deus;
import br.unifor.controllers.Cindacta;
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
    static float xCamera, yCamera, zCamera;
    static float yEye;
    static boolean rotacionar;
    
    public static Cindacta cind;
    static Deus deus;
    static Player player;
    static Alien alien;
    
    InputStream isSL0, isSL1, isSL2, isG;
    TextureData tdSL0, tdSL1, tdSL2, tdG;
    Texture txtrSL0, txtrSL1, txtrSL2, txtrG;
    float[] rgba;
    
    static float xPlayer, zPlayer;

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
                controlarAlien(e);
                
                canvas.display();
            }
        });
        
        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                controlarCamera(e);
                controlarPlayer(e);
                controlarAlien(e);
                
                canvas.display();
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
        
        xPlayer = zPlayer = 0.0f;
    }

    public void init(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        
        gl.setSwapInterval(1);
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH);
        
        
        try {
            cind = new Cindacta();
            deus = new Deus(cind);
            player = new Player(gl);
            alien = new Alien(gl);
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
            isG = getClass().getClassLoader().getResourceAsStream("br/unifor/textures/space_grid.png");
            tdG = TextureIO.newTextureData(isG, false, "png");
            txtrG = TextureIO.newTexture(tdG);
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

        gl.glTranslatef(-32.0f + xCamera, -26.0f + yCamera, -40.0f + zCamera);
        glu.gluLookAt(0.0f, 8.0f + yEye, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);

        //Layer espacial 0
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, rgba, 0);
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, rgba, 0);
        gl.glMaterialf(GL.GL_FRONT, GL.GL_SHININESS, 0.5f);

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
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, rgba, 0);
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, rgba, 0);
        gl.glMaterialf(GL.GL_FRONT, GL.GL_SHININESS, 0.5f);

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
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, rgba, 0);
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, rgba, 0);
        gl.glMaterialf(GL.GL_FRONT, GL.GL_SHININESS, 0.5f);

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

        //Layer Grid
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, rgba, 0);
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, rgba, 0);
        gl.glMaterialf(GL.GL_FRONT, GL.GL_SHININESS, 0.5f);

        txtrG.enable();
        txtrG.bind();

        gl.glBegin(GL.GL_POLYGON);
            gl.glTexCoord2f(0.0f, 0.0f);
            gl.glVertex3f(0.0f, 0.0f, 0.0f);
            gl.glTexCoord2f(1.0f, 0.0f);
            gl.glVertex3f(0.0f, 0.0f, -64.0f);
            gl.glTexCoord2f(1.0f, 1.0f);
            gl.glVertex3f(64.0f, 0.0f, -64.0f);
            gl.glTexCoord2f(0.0f, 1.0f);
            gl.glVertex3f(64.0f, 0.0f, 0.0f);
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
    
    public static void controlarCamera(KeyEvent e) {
        //Eixo X positivo
        if(e.getKeyChar() == 'y') {
            xCamera += 1.0f;
        }
        //Eixo X negativo
        if(e.getKeyChar() == 'u') {
            xCamera -= 1.0f;
        }

        //Eixo Y positivo
        if(e.getKeyChar() == 'h') {
            yCamera += 1.0f;
        }
        //Eixo Y negativo
        if(e.getKeyChar() == 'j') {
            yCamera -= 1.0f;
        }

        //Eixo Z positivo
        if(e.getKeyChar() == 'n') {
            zCamera += 1.0f;
        }
        //Eixo Z negativo
        if(e.getKeyChar() == 'm') {
            zCamera -= 1.0f;
        }

        //Olho y positivo
        if(e.getKeyChar() == 'f') {
            yEye += 1.0f;
            if(yEye > 12.0f)
                yEye = 12.0f;
        }
        //Olho Y negativo
        if(e.getKeyChar() == 'g') {
            yEye -= 1.0f;
            if(yEye < -4.0f)
                yEye = -4.0f;
        }
    }
    
    public static void controlarPlayer(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_UP:
                player.voarFrente();
                break;
            case KeyEvent.VK_DOWN:
                player.voarTras();
                break;
            case KeyEvent.VK_LEFT:
                player.voarEsquerda();
                break;
            case KeyEvent.VK_RIGHT :
                player.voarDireita();
                break;
        }
    }
    
    public static void controlarAlien(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W:
                alien.parar();
                alien.voarFrente();
                alien.iniciar();
                break;
            case KeyEvent.VK_S:
                alien.parar();
                alien.voarTras();
                break;
            case KeyEvent.VK_A:
                alien.parar();
                alien.voarEsquerda();
                alien.iniciar();
                break;
            case KeyEvent.VK_D :
                alien.parar();
                alien.voarDireita();
                alien.iniciar();
                break;
        }
    }
}