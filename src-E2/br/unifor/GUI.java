package br.unifor;

import br.unifor.util.OBJModel;
import com.sun.opengl.util.Animator;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GUI implements GLEventListener {
    
    static GLCanvas canvas;
    static JButton jButton1, jButton2, jButton3;
    static JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, jLabel9;
    static JPanel jPanel1;
    static JSeparator jSeparator1;
    static JSpinner jSpinner1;
    static JTextField jTFx1, jTFy1, jTFz1, jTFx2, jTFy2, jTFz2, jTFobj;
    static int p1[], p2[];
    static boolean caixa, object;
    static float xKeyboard, yKeyboard, zKeyboard;
    static float velocidade;
    static OBJModel obj;

    public static void main(String[] args) {
        final Frame frame = new Frame("Rodrigo Gato - CG 2013.1");
        canvas = new GLCanvas();

        canvas.addGLEventListener(new GUI());
        frame.add(canvas, BorderLayout.CENTER);
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
        
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //Eixo X positivo
                if(e.getKeyChar() == 'y') {
                    xKeyboard += velocidade;
                }
                //Eixo X negativo
                if(e.getKeyChar() == 'u') {
                    xKeyboard -= velocidade;
                }
                
                //Eixo Y positivo
                if(e.getKeyChar() == 'h') {
                    yKeyboard += velocidade;
                }
                //Eixo Y negativo
                if(e.getKeyChar() == 'j') {
                    yKeyboard -= velocidade;
                }
                
                //Eixo Z positivo
                if(e.getKeyChar() == 'n') {
                    zKeyboard += velocidade;
                }
                //Eixo Z negativo
                if(e.getKeyChar() == 'm') {
                    zKeyboard -= velocidade;
                }
                
                canvas.display();
            }
        });
        
        jPanel1 = new JPanel();
        frame.add(jPanel1, BorderLayout.NORTH);
        jPanel1.setPreferredSize(new java.awt.Dimension(624, 64));
        jPanel1.setLayout(null);
        {
            jLabel1 = new JLabel();
            jPanel1.add(jLabel1);
            jLabel1.setText("p1(");
            jLabel1.setBounds(8, 8, 20, 24);
        }
        {
            jTFx1 = new JTextField();
            jPanel1.add(jTFx1);
            jTFx1.setText("1");
            jTFx1.setBounds(28, 8, 24, 24);
        }
        {
            jLabel2 = new JLabel();
            jPanel1.add(jLabel2);
            jLabel2.setText(",");
            jLabel2.setBounds(52, 8, 8, 24);
        }
        {
            jTFy1 = new JTextField();
            jPanel1.add(jTFy1);
            jTFy1.setPreferredSize(new java.awt.Dimension(24, 24));
            jTFy1.setText("1");
            jTFy1.setBounds(60, 8, 24, 24);
        }
        {
            jLabel3 = new JLabel();
            jPanel1.add(jLabel3);
            jLabel3.setText(",");
            jLabel3.setBounds(84, 8, 8, 24);
        }
        {
            jTFz1 = new JTextField();
            jPanel1.add(jTFz1);
            jTFz1.setPreferredSize(new java.awt.Dimension(24, 24));
            jTFz1.setText("2");
            jTFz1.setBounds(92, 8, 24, 24);
        }
        {
            jLabel4 = new JLabel();
            jPanel1.add(jLabel4);
            jLabel4.setText(")");
            jLabel4.setBounds(116, 8, 8, 24);
        }
        {
            jLabel5 = new JLabel();
            jPanel1.add(jLabel5);
            jLabel5.setText("p2(");
            jLabel5.setBounds(8, 36, 20, 24);
        }
        {
            jTFx2 = new JTextField();
            jPanel1.add(jTFx2);
            jTFx2.setPreferredSize(new java.awt.Dimension(24, 24));
            jTFx2.setText("2");
            jTFx2.setBounds(28, 36, 24, 24);
        }
        {
            jLabel6 = new JLabel();
            jPanel1.add(jLabel6);
            jLabel6.setText(",");
            jLabel6.setBounds(52, 36, 8, 24);
        }
        {
            jTFy2 = new JTextField();
            jPanel1.add(jTFy2);
            jTFy2.setPreferredSize(new java.awt.Dimension(24, 24));
            jTFy2.setText("2");
            jTFy2.setBounds(60, 36, 24, 24);
        }
        {
            jLabel7 = new JLabel();
            jPanel1.add(jLabel7);
            jLabel7.setText(",");
            jLabel7.setBounds(84, 36, 8, 24);
        }
        {
            jTFz2 = new JTextField();
            jPanel1.add(jTFz2);
            jTFz2.setPreferredSize(new java.awt.Dimension(24, 24));
            jTFz2.setText("1");
            jTFz2.setBounds(92, 36, 24, 24);
        }
        {
            jLabel8 = new JLabel();
            jPanel1.add(jLabel8);
            jLabel8.setText(")");
            jLabel8.setBounds(116, 36, 8, 24);
        }
        {
            jButton1 = new JButton();
            jPanel1.add(jButton1);
            jButton1.setText("Criar Caixa");
            jButton1.setBounds(132, 8, 112, 52);
            jButton1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    iniciarVetores();
                    frame.requestFocus();
                }
            });
        }
        {
            jSeparator1 = new JSeparator();
            jPanel1.add(jSeparator1);
            jSeparator1.setBounds(252, 8, 8, 52);
            jSeparator1.setOrientation(SwingConstants.VERTICAL);
        }
        {
            jTFobj = new JTextField();
            jPanel1.add(jTFobj);
            jTFobj.setBounds(260, 8, 232, 24);
            jTFobj.setEditable(false);
        }
        {
            jButton2 = new JButton();
            jPanel1.add(jButton2);
            jButton2.setText("Selecionar OBJ");
            jButton2.setBounds(492, 8, 128, 24);
            jButton2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    JFileChooser jFCobj = new JFileChooser();
                    FileNameExtensionFilter fnef = new FileNameExtensionFilter("Arquivos OBJ", "obj");
                    jFCobj.setFileFilter(fnef);
                    if(jFCobj.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                        jTFobj.setText(jFCobj.getSelectedFile().getAbsolutePath());
                        jButton3.setEnabled(true);
                        frame.requestFocus();
                    }
                }
            });
        }
        {
            jButton3 = new JButton();
            jPanel1.add(jButton3);
            jButton3.setText("Inserir OBJ");
            jButton3.setBounds(492, 36, 128, 24);
            jButton3.setEnabled(false);
            jButton3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    obj = new OBJModel(jTFobj.getText());
                    object = true;
                    canvas.display();
                    frame.requestFocus();
                }
            });
        }
        {
            jLabel9 = new JLabel();
            jPanel1.add(jLabel9);
            jLabel9.setText("Velocidade de rolagem");
            jLabel9.setBounds(260, 36, 136, 24);
        }
        {
            jSpinner1 = new JSpinner();
            jPanel1.add(jSpinner1);
            jSpinner1.setModel(new SpinnerListModel(new String[] { "1", "5" , "10", "50", "100" }));
            jSpinner1.setBounds(396, 36, 40, 24);
            jSpinner1.setValue("50");
            jSpinner1.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    velocidade = Float.parseFloat(jSpinner1.getValue().toString()) / 10.0f;
                }
            });
        }
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
        caixa = false;
        object = false;
        xKeyboard = yKeyboard = zKeyboard = 0.0f;
        velocidade = Float.parseFloat(jSpinner1.getValue().toString()) / 10.0f;
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
        glu.gluPerspective(48.0f, h, 1.0, 512.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        
        gl.glTranslatef(0.0f + xKeyboard, 0.0f + yKeyboard, -40.0f + zKeyboard);
        
        if(object)
            obj.drawModel(gl);
        
        if(caixa)
            criarCaixa(gl);

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
            
            caixa = true;
            canvas.display();
        }catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(jPanel1,
                    "São aceitos somente números nos campos!",
                    "ERRO!",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void criarCaixa(GL gl) {
        gl.glEnable(GL.GL_DEPTH_TEST);
        
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
}