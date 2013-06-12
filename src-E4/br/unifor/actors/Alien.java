package br.unifor.actors;

import br.unifor.GUI;
import br.unifor.util.ObjLoader;
import br.unifor.util.ObjModel;
import br.unifor.util.TextureHandler;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;
import javax.media.opengl.GL;

public class Alien implements Actor {
    
    ObjModel obj;
    TextureHandler tex;
    private static int key;
    
    char orientacao;
    Integer[] posicao;
    boolean parado;
    boolean ia;
    
    float saltoX, saltoZ, saltoR;
    float rNorte, rSul, rLeste, rOeste;
    
    public Alien(GL gl) throws IOException {
        super();
        this.obj = ObjLoader.loadObj("br/unifor/obj/ASF-HP.obj", gl);
        this.tex = new TextureHandler("src/br/unifor/textures/vein.jpg");
        this.orientacao = SUL;
        this.key = KeyEvent.VK_DOWN;
        this.posicao = GUI.cind.getPosicaoAlien();
        this.parado = true;
        this.ia = false;
        saltoX = saltoZ = saltoR = 0.0f;
        rNorte = rSul = rLeste = rOeste = 0.0f;
    }
    
    public void inserir(GL gl) {
        float x = (posicao[0] + saltoZ) * 2.0f + 1.0f;
        float z = (posicao[1] + saltoX) * -2.0f - 0.8f;
        
        tex.getTexture().enable();
        tex.getTexture().bind();
        
        gl.glPushMatrix();
            gl.glTranslatef(x, 1.0f, z);
            gl.glScalef(0.25f, 0.25f, 0.25f);
            gl.glRotatef(270.0f + saltoR, 0.0f, 1.0f, 0.0f);
            obj.render(gl);
        gl.glPopMatrix();
    }
    
    public void voar() {
        if(parado) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        parado = false;
                        
                        int px = posicao[1];
                        int pz = posicao[0];

                        if(orientacao == NORTE)
                            px = posicao[1] + 1;
                        else if(orientacao == SUL)
                            px = posicao[1] - 1;
                        else if(orientacao == LESTE)
                            pz = posicao[0] + 1;
                        else if(orientacao == OESTE)
                            pz = posicao[0] - 1;
                        
                        if(px >= 0 && px < GUI.cind.getX() &&
                                (GUI.cind.getPosicaoEm(posicao[0], px) == 'V' ||
                                GUI.cind.getPosicaoEm(posicao[0], px) == 'A') &&
                                pz >= 0 && pz < GUI.cind.getZ() &&
                                (GUI.cind.getPosicaoEm(pz, posicao[1]) == 'V' ||
                                GUI.cind.getPosicaoEm(pz, posicao[1]) == 'A')) {
                            
                            float movX = (px - posicao[1]) / 10.0f;
                            float movZ = (pz - posicao[0]) / 10.0f;

                            Thread.sleep(25);
                            saltoX = movX;
                            saltoZ = movZ;
                            Thread.sleep(25);
                            saltoX += movX;
                            saltoZ += movZ;
                            Thread.sleep(25);
                            saltoX += movX;
                            saltoZ += movZ;
                            Thread.sleep(25);
                            saltoX += movX;
                            saltoZ += movZ;
                            Thread.sleep(25);
                            saltoX += movX;
                            saltoZ += movZ;
                            Thread.sleep(25);
                            saltoX += movX;
                            saltoZ += movZ;
                            Thread.sleep(25);
                            saltoX += movX;
                            saltoZ += movZ;
                            Thread.sleep(25);
                            saltoX += movX;
                            saltoZ += movZ;
                            Thread.sleep(25);
                            saltoX += movX;
                            saltoZ += movZ;
                            Thread.sleep(25);
                            saltoX = 0.0f;
                            saltoZ = 0.0f;
                            
                            GUI.cind.setPosicaoAlien(new Integer[] { pz, px });
                            posicao = GUI.cind.getPosicaoAlien();
                        }

                        parado = true;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public void virar(int key) {
        Alien.key = key;
        if(parado) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        parado = false;
                        float r = 0.0f;
                        
                        if(Alien.key == KeyEvent.VK_DOWN) {
                            r = 180.0f / 10.0f;
                        }else if(Alien.key == KeyEvent.VK_LEFT) {
                            r = 90.0f / 10.0f;
                        }else if(Alien.key == KeyEvent.VK_RIGHT) {
                            r = -90.0f / 10.0f;
                        }

                        Thread.sleep(25);
                        saltoR += r;
                        Thread.sleep(25);
                        saltoR += r;
                        Thread.sleep(25);
                        saltoR += r;
                        Thread.sleep(25);
                        saltoR += r;
                        Thread.sleep(25);
                        saltoR += r;
                        Thread.sleep(25);
                        saltoR += r;
                        Thread.sleep(25);
                        saltoR += r;
                        Thread.sleep(25);
                        saltoR += r;
                        Thread.sleep(25);
                        saltoR += r;
                        Thread.sleep(25);
                        saltoR += r;
                        
                        
                        if(Alien.key == KeyEvent.VK_DOWN) {
                            if(orientacao == NORTE)
                                orientacao = SUL;
                            else if(orientacao == SUL)
                                orientacao = NORTE;
                            else if(orientacao == LESTE)
                                orientacao = OESTE;
                            else if(orientacao == OESTE)
                                orientacao = LESTE;
                        }else if(Alien.key == KeyEvent.VK_LEFT) {
                            if(orientacao == NORTE)
                                orientacao = OESTE;
                            else if(orientacao == SUL)
                                orientacao = LESTE;
                            else if(orientacao == LESTE)
                                orientacao = NORTE;
                            else if(orientacao == OESTE)
                                orientacao = SUL;
                        }else if(Alien.key == KeyEvent.VK_RIGHT) {
                            if(orientacao == NORTE)
                                orientacao = LESTE;
                            else if(orientacao == SUL)
                                orientacao = OESTE;
                            else if(orientacao == LESTE)
                                orientacao = SUL;
                            else if(orientacao == OESTE)
                                orientacao = NORTE;
                        }
                        if(saltoR >= 360.0f)
                            saltoR -= 360.0f;
                        
                        if(saltoR <= -360.0f)
                            saltoR += 360.0f;
                        
                        parado = true;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
    
    public void iniciar() {
        ia = true;
        inteligenciaArtificial();
    }
    
    public void parar() {
        ia = false;
    }
    
    private void inteligenciaArtificial() {
        new Thread(new Runnable() {
                public void run() {
                    try {
                        while(ia) {
                            Thread.sleep(1000);
                            int mov = new Random().nextInt(8);

                            switch(mov) {
                                case 0:
                                    voar();
                                    break;
                                case 1:
                                    voar();
                                    break;
                                case 2:
                                    virar(KeyEvent.VK_DOWN);
                                    break;
                                case 3:
                                    voar();
                                    break;
                                case 4:
                                    virar(KeyEvent.VK_RIGHT);
                                    break;
                                case 5:
                                    voar();
                                    break;
                                case 6:
                                    virar(KeyEvent.VK_LEFT);
                                    break;
                                case 7:
                                    voar();
                                    break;
                            }
                        }
                        
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }).start();
    }
}