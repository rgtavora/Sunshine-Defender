package br.unifor;

import br.unifor.actors.Player;
import br.unifor.actors.Alien;
import br.unifor.actors.Battleship;
import br.unifor.actors.Missile;
import br.unifor.controllers.Deus;
import br.unifor.controllers.Cindacta;
import br.unifor.controllers.HUD;
import br.unifor.controllers.Lakitu;
import br.unifor.util.AudioSample;
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
    public static Deus deus;
    public static Lakitu lakitu;
    public static HUD hud;
    
    public static Player player;
    public static Alien alien;
    static Missile missile;
    static Battleship battleship;
    
    float[] rgba;
    final float rad = 0.01745f;
    public static float xMenu;
    static float xIntro, yIntro, zIntro;
    static float zLogo;
    
    public static boolean showAlien;
    public static boolean showPlayer;
    
    public AudioSample asMenu;
    public AudioSample asIntro;
    public AudioSample asGame;
    public static AudioSample asMissile;
    public static AudioSample asBomb;
    public static AudioSample asAlert;
    
    boolean npMenu, npIntro, npGame;
    
    static boolean onMenu;
    static boolean onDificuldade;
    static boolean onIntro;
    static boolean onGame;
    
    static boolean transformarMD, transformarDI;
    
    public static int dificuldade;
    
    InputStream isIntro, isLogo;
    TextureData tdIntro, tdLogo;
    Texture txtrIntro, txtrLogo;
    
    public static int ttlPaz;
    
    public static boolean gameOver;

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
                painel(e);
            }
        });
        
        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                painel(e);
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
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
            lakitu = new Lakitu();
            hud = new HUD(drawable);
            
            player = new Player(gl);
            alien = new Alien(gl);
            missile = new Missile(gl);
            battleship = new Battleship(gl);
            rotacionar = false;
            
            rgba = new float[]{1f, 1f, 0.3f};
            xMenu = -64.0f;
            xIntro = -32.0f;
            yIntro = -72.0f;
            zIntro = 0.0f;
            
            showAlien = false;
            showPlayer = true;
            
            asMenu = new AudioSample(getClass().getClassLoader().getResourceAsStream("br/unifor/sfx/bttf.wav"));
            asIntro = new AudioSample(getClass().getClassLoader().getResourceAsStream("br/unifor/sfx/sw.wav"));
            asGame = new AudioSample(getClass().getClassLoader().getResourceAsStream("br/unifor/sfx/zarathustra.wav"));
            asMissile = new AudioSample(getClass().getClassLoader().getResourceAsStream("br/unifor/sfx/missile.wav"));
            asBomb = new AudioSample(getClass().getClassLoader().getResourceAsStream("br/unifor/sfx/bomb.wav"));
            asAlert = new AudioSample(getClass().getClassLoader().getResourceAsStream("br/unifor/sfx/mgsalert.wav"));
            
            npMenu = false;
            npIntro = false;
            npGame = false;
            
            onMenu = onDificuldade = onIntro = onGame = false;
            onGame = true;
            
            transformarMD = transformarDI = false;
            
            dificuldade = 0;
            
            isIntro = getClass().getClassLoader().getResourceAsStream("br/unifor/textures/intro.png");
            tdIntro = TextureIO.newTextureData(isIntro, false, "png");
            txtrIntro = TextureIO.newTexture(tdIntro);
            
            isLogo = getClass().getClassLoader().getResourceAsStream("br/unifor/textures/sunshine.png");
            tdLogo = TextureIO.newTextureData(isLogo, false, "png");
            txtrLogo = TextureIO.newTexture(tdLogo);
            
            switch(GUI.dificuldade) {
                case 0: GUI.ttlPaz = 200; break;
                case 1: GUI.ttlPaz = 100; break;
                case 2: GUI.ttlPaz = 50; break;
            }
            
            gameOver = false;
        
        }catch (IOException e) {
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
        gl.glEnable(GL.GL_DEPTH_TEST);
        
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        
        if(onMenu)
            onMenu(gl);
        else if(onDificuldade)
            onDificuldade(gl);
        else if(onIntro)
            onIntro(gl);
        else if(onGame)
            onGame(gl, glu);
        
        desenharGrid(gl);
        
        deus.criarUniverso(gl);
        deus.criarSol(gl, glu);
        deus.criarMercurio(gl, glu);
        deus.criarVenus(gl, glu);
        deus.criarTerra(gl, glu);
        deus.criarMarte(gl, glu);
        
        if(onMenu) {
            hud.escreverLogo();
            hud.escreverMenu();
        }else if(onDificuldade) {
            hud.escreverLogo();
            hud.escreverDificuldade();
        }else if(onGame) {
            hud.escreverScore();
            hud.escreverForcaSol();
            hud.escreverNotificacao();
        }
        if(hud.forcaSol <= 0) {
            hud.escreverGameOver();
            gameOver = true;
        }
        
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {}
    
    private void onMenu(GL gl) {
        if(!npMenu) {
            asMenu.play(false, true);
            npMenu = true;
        }
        
        onDificuldade = onIntro = onGame = false;
        onMenu = true;
        
        gl.glTranslatef(xMenu, -72.0f, 0.0f);
        
        battleship.inserir(gl);

        if(transformarMD) {
            battleship.voar();
            hud.alphaMenu -= 0.05f;
            
            if(battleship.x <= -48.0f && hud.alphaMenu <= 0.0f) {
                onMenu = onIntro = onGame = false;
                onDificuldade = true;
                transformarMD = false;
            }
        }else
            xMenu += 0.01f;
            
    }
    
    private void onDificuldade(GL gl) {
        onMenu = onIntro = onGame = false;
        onDificuldade = true;
        
        gl.glTranslatef(xMenu, -72.0f, 0.0f);
        
        if(transformarDI) {
            hud.alphaDificuldade -= 0.05f;
            hud.alphaLogo -= 0.05f;
            xMenu++;
            
            if(hud.alphaDificuldade <= 0.0f && hud.alphaLogo <= 0.0f && xMenu >= xIntro) {
                onMenu = onDificuldade = onGame = false;
                onIntro = true;
                transformarDI = false;
                asMenu.stop();
            }
        }
    }
    
    private void onIntro(GL gl) {
        onMenu = onDificuldade = onGame = false;
        onIntro = true;
        
        if(!npIntro) {
            asIntro.play(false, true);
            npIntro = true;
        }
        
        gl.glTranslatef(xIntro + lakitu.getX(), yIntro + lakitu.getY(), zIntro + lakitu.getZ());
        
        gl.glPushMatrix();
            txtrLogo.enable();
            txtrLogo.bind();
            gl.glRotatef(-90.0f, 0.0f, 0.0f, 1.0f);
            gl.glBegin(GL.GL_POLYGON);
                gl.glTexCoord2f(0.0f, 0.0f);
                gl.glVertex3f(-73.0f, 30.0f, zLogo);
                gl.glTexCoord2f(1.0f, 0.0f);
                gl.glVertex3f(-73.0f, 34.0f, zLogo);
                gl.glTexCoord2f(1.0f, 1.0f);
                gl.glVertex3f(-71.0f, 34.0f, zLogo);
                gl.glTexCoord2f(0.0f, 1.0f);
                gl.glVertex3f(-71.0f, 30.0f, zLogo);
            gl.glEnd();
            txtrLogo.disable();
            
            txtrIntro.enable();
            txtrIntro.bind();
            gl.glRotatef(-32.0f, 0.0f, 1.0f, 0.0f);
            gl.glBegin(GL.GL_POLYGON);
                gl.glTexCoord2f(0.0f, 0.0f);
                gl.glVertex3f(-48.0f + zLogo + lakitu.getX(), 12.0f, -40.0f);
                gl.glTexCoord2f(1.0f, 0.0f);
                gl.glVertex3f(-48.0f + zLogo + lakitu.getX(), 52.0f, -40.0f);
                gl.glTexCoord2f(1.0f, 1.0f);
                gl.glVertex3f(32.0f + zLogo + lakitu.getX(), 52.0f, -40.0f);
                gl.glTexCoord2f(0.0f, 1.0f);
                gl.glVertex3f(32.0f + zLogo + lakitu.getX(), 12.0f, -40.0f);
            gl.glEnd();
            txtrIntro.disable();
        gl.glPopMatrix();
        
        player.inserir(gl);

        if(zLogo > -120.0f)
            zLogo -= 0.05f;
        else {
            if(xIntro < -lakitu.getPosCamX())
                xIntro++;
            if(yIntro < -lakitu.getPosCamY())
                yIntro++;
            if(zIntro < -(lakitu.getPosCamZ() + 2))
                zIntro++;
            
            deus.hajaLuz(gl);
            
            if(yIntro >= -lakitu.getPosCamY()) {
                onMenu = onDificuldade = onIntro = false;
                onGame = true;
                asIntro.stop();
            }
        }
       
    }
    
    private void onGame(GL gl, GLU glu) {
        if(!npGame) {
            asGame.play(false, true);
            npGame = true;
        }
        
        float rX = (float) (2 * Math.sin(rad * player.r));
        float rZ = (float) (2 * Math.cos(rad * player.r));
        
        if(!gameOver) {
            glu.gluLookAt(lakitu.getPosCamX() + lakitu.getX() - rX, lakitu.getPosCamY() + lakitu.getY(), lakitu.getPosCamZ() + lakitu.getZ() + rZ,
                    lakitu.getPosCamX(), lakitu.getPosCamY(), lakitu.getPosCamZ(), 0.0f, 1.0f, 0.0f);
        }else {
            glu.gluLookAt(lakitu.getPosCamX() + lakitu.getX() - rX, lakitu.getPosCamY() + lakitu.getY(), lakitu.getPosCamZ() + lakitu.getZ() + rZ,
                    cind.getPosicaoSol()[0] * 2.0f, lakitu.getPosCamY(), cind.getPosicaoSol()[1] * -2.0f, 0.0f, 1.0f, 0.0f);
            hud.sugarSol();
        }
        
        if(showPlayer)
            player.inserir(gl);
        
        if(showAlien)
            alien.inserir(gl);
        else if(ttlPaz <= 0){
            cind.removerAlien();
            cind.inserirAlien();
            alien.setPos();
            showAlien = true;
            GUI.hud.setNotif("Nave Alien detectada!!!");
            GUI.hud.setTtl(200);
            asAlert.play();
            alien.iniciar();
        }else
            ttlPaz--;
        
        missile.inserir(gl);

        if(!rotacionar) {
            deus.iniciarRotacao();
            rotacionar = true;
        }
        
        deus.hajaLuz(gl);
        
        
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
    
    public static void painel(KeyEvent e) {
        if(!gameOver) {
            switch(e.getKeyCode()) {
                //controlar navegação
                case KeyEvent.VK_UP: {
                    if(onMenu) {
                        if(hud.getIndexSelectedMenu() == 0)
                            hud.setIndexSelectedMenu(2);
                        else
                            hud.setIndexSelectedMenu(hud.getIndexSelectedMenu() - 1);
                    }else if(onDificuldade) {
                        if(hud.getIndexSelectedDificuldade()== 0)
                            hud.setIndexSelectedDificuldade(2);
                        else
                            hud.setIndexSelectedDificuldade(hud.getIndexSelectedDificuldade() - 1);
                    }else if(onGame)
                        player.voar();
                }break;

                case KeyEvent.VK_DOWN: {
                    if(onMenu) {
                        if(hud.getIndexSelectedMenu() == 2)
                            hud.setIndexSelectedMenu(0);
                        else
                            hud.setIndexSelectedMenu(hud.getIndexSelectedMenu() + 1);
                    }else if(onDificuldade) {
                        if(hud.getIndexSelectedDificuldade()== 2)
                            hud.setIndexSelectedDificuldade(0);
                        else
                            hud.setIndexSelectedDificuldade(hud.getIndexSelectedDificuldade()+ 1);
                    }else if(onGame)
                        player.virar(KeyEvent.VK_DOWN);
                }break;

                case KeyEvent.VK_LEFT: {
                    if(onGame)
                        player.virar(KeyEvent.VK_LEFT);
                }break;

                case KeyEvent.VK_RIGHT: {
                    if(onGame)
                        player.virar(KeyEvent.VK_RIGHT);
                }break;

                case KeyEvent.VK_SPACE: {
                    if(onGame) {
                        missile.show(true);
                        missile.voar();
                    }
                }break;
                case KeyEvent.VK_ENTER: {
                    if(onMenu)
                        switch(hud.getIndexSelectedMenu()) {
                            case 0: transformarMD = true; break;
                            case 1: finalizar(); break;
                        }
                    else if(onDificuldade) {
                        dificuldade = hud.getIndexSelectedDificuldade();
                        transformarDI = true;
                    }else if(onGame) {
                        missile.show(true);
                        missile.voar();
                    }
                }break;
                case KeyEvent.VK_ESCAPE: {
                    finalizar();
                }break;

                //controlar câmera
    //            case KeyEvent.VK_Y: lakitu.setX(lakitu.getX() + 1.0f); break;
    //            case KeyEvent.VK_U: lakitu.setX(lakitu.getX() - 1.0f); break;
    //            case KeyEvent.VK_H: lakitu.setY(lakitu.getY() + 1.0f); break;
    //            case KeyEvent.VK_J: lakitu.setY(lakitu.getY() - 1.0f); break;
    //            case KeyEvent.VK_N: lakitu.setZ(lakitu.getZ() + 1.0f); break;
    //            case KeyEvent.VK_M: lakitu.setZ(lakitu.getZ() - 1.0f); break;
            }
        }
    }
    
    public static void finalizar() {
        System.exit(0);
    }
}