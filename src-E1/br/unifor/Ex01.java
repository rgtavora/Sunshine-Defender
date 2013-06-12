package br.unifor;

import com.sun.opengl.util.Animator;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Ex01 implements GLEventListener {
    
    static GLCanvas canvas;
    static JPanel jPanel1;
    static JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7;
    static JTextField jTFx1, jTFy1, jTFz1, jTFx2, jTFy2, jTFz2;
    static JButton jButton1;
    static int p1[], p2[];
    static boolean criar;

    public static void main(String[] args) {
        Frame frame = new Frame("Rodrigo Gato - CG 2013.1");
        canvas = new GLCanvas();

        canvas.addGLEventListener(new Ex01());
        frame.add(canvas);
        frame.setSize(640, 480);
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
        
        jPanel1 = new JPanel();
        FlowLayout jPanel1Layout = new FlowLayout();
        frame.add(jPanel1, BorderLayout.NORTH);
        jPanel1.setPreferredSize(new java.awt.Dimension(624, 64));
        jPanel1.setLayout(jPanel1Layout);
        {
                jLabel1 = new JLabel();
                jPanel1.add(jLabel1);
                jLabel1.setText("p1(");
        }
        {
                jTFx1 = new JTextField();
                jPanel1.add(jTFx1);
                jTFx1.setPreferredSize(new java.awt.Dimension(24, 24));
        }
        {
                jLabel2 = new JLabel();
                jPanel1.add(jLabel2);
                jLabel2.setText(",");
        }
        {
                jTFy1 = new JTextField();
                jPanel1.add(jTFy1);
                jTFy1.setPreferredSize(new java.awt.Dimension(24, 24));
        }
        {
                jLabel3 = new JLabel();
                jPanel1.add(jLabel3);
                jLabel3.setText(",");
        }
        {
                jTFz1 = new JTextField();
                jPanel1.add(jTFz1);
                jTFz1.setPreferredSize(new java.awt.Dimension(24, 24));
        }
        {
                jLabel4 = new JLabel();
                jPanel1.add(jLabel4);
                jLabel4.setText(")                p2(");
        }
        {
                jTFx2 = new JTextField();
                jPanel1.add(jTFx2);
                jTFx2.setPreferredSize(new java.awt.Dimension(24, 24));
        }
        {
                jLabel5 = new JLabel();
                jPanel1.add(jLabel5);
                jLabel5.setText(",");
        }
        {
                jTFy2 = new JTextField();
                jPanel1.add(jTFy2);
                jTFy2.setPreferredSize(new java.awt.Dimension(24, 24));
        }
        {
                jLabel6 = new JLabel();
                jPanel1.add(jLabel6);
                jLabel6.setText(",");
        }
        {
                jTFz2 = new JTextField();
                jPanel1.add(jTFz2);
                jTFz2.setPreferredSize(new java.awt.Dimension(24, 24));
        }
        {
                jLabel7 = new JLabel();
                jPanel1.add(jLabel7);
                jLabel7.setText(")                ");
        }
        {
                jButton1 = new JButton();
                jPanel1.add(jButton1);
                jButton1.setText("Criar Caixa");
                jButton1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        iniciarVetores();
                    }
            });
        }
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
        criar = false;
    }

    public void init(GLAutoDrawable drawable) {
       GL gl = drawable.getGL();
        
        gl.setSwapInterval(1);

        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH);
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
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glTranslatef(0.0f, 0.0f, -8.0f);
        gl.glRotatef(32.0f, 4.0f, 4.0f, 0f);

        if(criar) {
            //face trás
            gl.glBegin(GL.GL_QUADS);
                gl.glColor3f(0.56f, 0.0f, 1.0f);
                gl.glVertex3i(p2[0], p2[1], p2[2]);
                gl.glVertex3i(p1[0], p2[1], p2[2]);
                gl.glVertex3i(p1[0], p1[1], p2[2]);
                gl.glVertex3i(p2[0], p1[1], p2[2]);
            gl.glEnd();
            
            //face frente
            gl.glBegin(GL.GL_QUADS);
                gl.glColor3f(1.0f, 1.0f, 0.0f);
                gl.glVertex3i(p1[0], p2[1], p1[2]);
                gl.glVertex3i(p2[0], p2[1], p1[2]);
                gl.glVertex3i(p2[0], p1[1], p1[2]);
                gl.glVertex3i(p1[0], p1[1], p1[2]);
            gl.glEnd();

            //face esquerda
            gl.glBegin(GL.GL_QUADS);
                gl.glColor3f(0.0f, 0.0f, 1.0f);
                gl.glVertex3i(p1[0], p2[1], p2[2]);
                gl.glVertex3i(p1[0], p2[1], p1[2]);
                gl.glVertex3i(p1[0], p1[1], p1[2]);
                gl.glVertex3i(p1[0], p1[1], p2[2]);
            gl.glEnd();
            
            //face direita
            gl.glBegin(GL.GL_QUADS);
                gl.glColor3f(1.0f, 0.5f, 0.0f);
                gl.glVertex3i(p2[0], p2[1], p1[2]);
                gl.glVertex3i(p2[0], p2[1], p2[2]);
                gl.glVertex3i(p2[0], p1[1], p2[2]);
                gl.glVertex3i(p2[0], p1[1], p1[2]);
            gl.glEnd();
            
            //face cima
            gl.glBegin(GL.GL_QUADS);
                gl.glColor3f(1.0f, 0.0f, 0.0f);
                gl.glVertex3i(p1[0], p1[1], p1[2]);
                gl.glVertex3i(p2[0], p1[1], p1[2]);
                gl.glVertex3i(p2[0], p1[1], p2[2]);
                gl.glVertex3i(p1[0], p1[1], p2[2]);
            gl.glEnd();
            
            //face baixo
            gl.glBegin(GL.GL_QUADS);
                gl.glColor3f(0.0f, 1.0f, 0.0f);
                gl.glVertex3i(p1[0], p2[1], p2[2]);
                gl.glVertex3i(p2[0], p2[1], p2[2]);
                gl.glVertex3i(p2[0], p2[1], p1[2]);
                gl.glVertex3i(p1[0], p2[1], p1[2]);
            gl.glEnd();
        }

        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
    
    public static void iniciarVetores() {
        try {
            p1 = new int[3];
            p1[0] = Integer.parseInt(jTFx1.getText());
            p1[1] = Integer.parseInt(jTFy1.getText());
            p1[2] = Integer.parseInt(jTFz1.getText());

            p2 = new int[3];
            p2[0] = Integer.parseInt(jTFx2.getText());
            p2[1] = Integer.parseInt(jTFy2.getText());
            p2[2] = Integer.parseInt(jTFz2.getText());
            
            criar = true;
            canvas.display();
        }catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(jPanel1,
                    "São aceitos somente números nos campos!",
                    "ERRO!",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}

