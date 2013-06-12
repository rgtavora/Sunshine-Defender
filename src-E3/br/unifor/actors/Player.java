package br.unifor.actors;

import br.unifor.GUI;
import br.unifor.util.ObjLoader;
import br.unifor.util.ObjModel;
import br.unifor.util.TextureHandler;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.media.opengl.GL;

public class Player implements Actor {
    
    ObjModel obj;
    TextureHandler tex;
    
    char orientacao;
    Integer[] posicao;
    boolean parado;
    
    float saltoX, saltoZ, saltoR;
    float rNorte, rSul, rLeste, rOeste;
    
    static int key;
    
    public Player(GL gl) throws IOException {
        super();
        this.obj = ObjLoader.loadObj("br/unifor/obj/Batwing.obj", gl);
        this.tex = new TextureHandler("src/br/unifor/textures/ship.jpg");
        this.orientacao = NORTE;
        this.posicao = GUI.cind.getPosicaoPlayer();
        this.parado = true;
        saltoX = saltoZ = saltoR = 0.0f;
        rNorte = rSul = rLeste = rOeste = 0.0f;
        key = KeyEvent.VK_UP;
    }
    
    public void inserir(GL gl) {
        float x = (posicao[0] + saltoX) * 2.0f + 1.0f;
        float z = (posicao[1] + saltoZ) * -2.0f - 1.0f;
        
        tex.getTexture().enable();
        tex.getTexture().bind();
        
        gl.glPushMatrix();
            gl.glTranslatef(x, 1.0f, z);
            gl.glRotatef(270.0f, 1.0f, 0.0f, 0.0f);
            gl.glRotatef(180.0f + saltoR, 0.0f, 0.0f, 1.0f);
            gl.glScalef(0.003f, 0.003f, 0.003f);
            obj.render(gl);
        gl.glPopMatrix();
    }
    
    public void voarFrente() {
        if(parado) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        parado = false;
                        
                        int pp = posicao[1] + 1;
                        if((pp >= GUI.cind.getX()) || (GUI.cind.getPosicaoEm(posicao[0], pp) != 'V'))
                            pp = posicao[1];
                        else {
                            float r = 0.0f;
                            if(orientacao == SUL)
                                r = -180.0f / 10.0f;
                            else if(orientacao == LESTE)
                                r = 90.0f / 10.0f;
                            else if(orientacao == OESTE)
                                r = -90.0f / 10.0f;
                            
                            Thread.sleep(25);
                            saltoZ = 0.1f;
                            saltoR += r;
                            Thread.sleep(25);
                            saltoZ = 0.2f;
                            saltoR += r;
                            Thread.sleep(25);
                            saltoZ = 0.3f;
                            saltoR += r;
                            Thread.sleep(25);
                            saltoZ = 0.4f;
                            saltoR += r;
                            Thread.sleep(25);
                            saltoZ = 0.5f;
                            saltoR += r;
                            Thread.sleep(25);
                            saltoZ = 0.6f;
                            saltoR += r;
                            Thread.sleep(25);
                            saltoZ = 0.7f;
                            saltoR += r;
                            Thread.sleep(25);
                            saltoZ = 0.8f;
                            saltoR += r;
                            Thread.sleep(25);
                            saltoZ = 0.9f;
                            saltoR += r;
                            Thread.sleep(25);
                            saltoZ = 0.0f;
                            saltoR += r;
                        }
                        GUI.cind.setPosicaoPlayer(new Integer[] { posicao[0], pp });
                        posicao = GUI.cind.getPosicaoPlayer();

                        orientacao = NORTE;
                        parado = true;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
    
     public void voarTras() {
        if(parado) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        parado = false;
                        
                        int pp = posicao[1] - 1;
                        if((pp < 0) || (GUI.cind.getPosicaoEm(posicao[0], pp) != 'V'))
                            pp = posicao[1];
                        else {
                            float r = 0.0f;
                            if(orientacao == NORTE) {
                                r = 180.0f / 10.0f;
                            }else if(orientacao == LESTE)
                                r = -90.0f / 10.0f;
                            else if(orientacao == OESTE)
                                r = 90.0f / 10.0f;

                            Thread.sleep(25);
                            saltoZ = -0.1f;
                            saltoR += r;
                            Thread.sleep(25);
                            saltoZ = -0.2f;
                            saltoR += r;
                            Thread.sleep(25);
                            saltoZ = -0.3f;
                            saltoR += r;
                            Thread.sleep(25);
                            saltoZ = -0.4f;
                            saltoR += r;
                            Thread.sleep(25);
                            saltoZ = -0.5f;
                            saltoR += r;
                            Thread.sleep(25);
                            saltoZ = -0.6f;
                            saltoR += r;
                            Thread.sleep(25);
                            saltoZ = -0.7f;
                            saltoR += r;
                            Thread.sleep(25);
                            saltoZ = -0.8f;
                            saltoR += r;
                            Thread.sleep(25);
                            saltoZ = -0.9f;
                            saltoR += r;
                            Thread.sleep(25);
                            saltoZ = 0.0f;
                            saltoR += r;
                        }
                        GUI.cind.setPosicaoPlayer(new Integer[] { posicao[0], pp });
                        posicao = GUI.cind.getPosicaoPlayer();
                        
                        orientacao = SUL;
                        parado = true;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
    
    public void voarDireita() {
        if(parado) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        parado = false;
                        
                        int pp = posicao[0] + 1;
                        if((pp >= GUI.cind.getY()) || (GUI.cind.getPosicaoEm(pp, posicao[1]) != 'V'))
                            pp = posicao[0];
                        else {
                            float r = 0.0f;
                            if(orientacao == NORTE)
                                r = -90.0f / 10.0f;
                            else if(orientacao == SUL)
                                r = 90.0f / 10.0f;
                            else if(orientacao == OESTE)
                                r = 180.0f / 10.0f;

                            Thread.sleep(25);
                            saltoX = 0.1f;
                            saltoR += r;
                            Thread.sleep(25);
                            saltoX = 0.2f;
                            saltoR += r;
                            Thread.sleep(25);
                            saltoX = 0.3f;
                            saltoR += r;
                            Thread.sleep(25);
                            saltoX = 0.4f;
                            saltoR += r;
                            Thread.sleep(25);
                            saltoX = 0.5f;
                            saltoR += r;
                            Thread.sleep(25);
                            saltoX = 0.6f;
                            saltoR += r;
                            Thread.sleep(25);
                            saltoX = 0.7f;
                            saltoR += r;
                            Thread.sleep(25);
                            saltoX = 0.8f;
                            saltoR += r;
                            Thread.sleep(25);
                            saltoX = 0.9f;
                            saltoR += r;
                            Thread.sleep(25);
                            saltoX = 0.0f;
                            saltoR += r;
                        }
                        GUI.cind.setPosicaoPlayer(new Integer[] { pp, posicao[1] });
                        posicao = GUI.cind.getPosicaoPlayer();
                        
                        orientacao = LESTE;
                        parado = true;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        
    }
     
    public void voarEsquerda() {
        if(parado) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        parado = false;
                        
                        int pp = posicao[0] - 1;
                        if((pp < 0) || (GUI.cind.getPosicaoEm(pp, posicao[1]) != 'V'))
                            pp = posicao[0];
                        else {
                            float r = 0.0f;
                            if(orientacao == NORTE)
                                r = 90.0f / 10.0f;
                            else if(orientacao == SUL)
                                r = -90.0f / 10.0f;
                            else if(orientacao == LESTE)
                                r = 180.0f / 10.0f;

                            Thread.sleep(25);
                            saltoX = -0.1f;
                            saltoR += r;
                            Thread.sleep(25);
                            saltoX = -0.2f;
                            saltoR += r;
                            Thread.sleep(25);
                            saltoX = -0.3f;
                            saltoR += r;
                            Thread.sleep(25);
                            saltoX = -0.4f;
                            saltoR += r;
                            Thread.sleep(25);
                            saltoX = -0.5f;
                            saltoR += r;
                            Thread.sleep(25);
                            saltoX = -0.6f;
                            saltoR += r;
                            Thread.sleep(25);
                            saltoX = -0.7f;
                            saltoR += r;
                            Thread.sleep(25);
                            saltoX = -0.8f;
                            saltoR += r;
                            Thread.sleep(25);
                            saltoX = -0.9f;
                            saltoR += r;
                            Thread.sleep(25);
                            saltoX = 0.0f;
                            saltoR += r;
                        }
                        GUI.cind.setPosicaoPlayer(new Integer[] { pp, posicao[1] });
                        posicao = GUI.cind.getPosicaoPlayer();
                        
                        orientacao = OESTE;
                        parado = true;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
    
    public void mudarOrientacao(int key) {
        Player.key = key;
        if(parado) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        parado = false;
                        float r = 0.0f;
                        
                        if(Player.key == KeyEvent.VK_DOWN) {
                            r = 180.0f / 10.0f;
                        }else if(Player.key == KeyEvent.VK_LEFT) {
                            r = 90.0f / 10.0f;
                        }else if(Player.key == KeyEvent.VK_RIGHT) {
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
                        
                        orientacao = OESTE;
                        parado = true;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}