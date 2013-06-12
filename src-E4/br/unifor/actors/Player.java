package br.unifor.actors;

import br.unifor.GUI;
import br.unifor.util.ObjLoader;
import br.unifor.util.ObjModel;
import br.unifor.util.TextureHandler;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.media.opengl.GL;

public class Player implements Actor {
    
    private ObjModel obj;
    private TextureHandler tex;
    
    private static int key;
    public char orientacao;
    private Integer[] posicao;
    private boolean parado;
    
    public float saltoX, saltoZ, saltoR;
    
    public Player(GL gl) throws IOException {
        super();
        this.obj = ObjLoader.loadObj("br/unifor/obj/Batwing.obj", gl);
        this.tex = new TextureHandler("src/br/unifor/textures/ship.jpg");
        this.orientacao = NORTE;
        Player.key = KeyEvent.VK_UP;
        this.posicao = GUI.cind.getPosicaoPlayer();
        this.parado = true;
        saltoX = saltoZ = saltoR = 0.0f;
    }
    
    public void inserir(GL gl) {
        float x = (posicao[0] + saltoZ) * 2.0f + 1.0f;
        float z = (posicao[1] + saltoX) * -2.0f - 1.0f;
        
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
                                GUI.cind.getPosicaoEm(posicao[0], px) == 'P') &&
                                pz >= 0 && pz < GUI.cind.getZ() &&
                                (GUI.cind.getPosicaoEm(pz, posicao[1]) == 'V' ||
                                GUI.cind.getPosicaoEm(pz, posicao[1]) == 'P')) {
                            
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
                            
                            GUI.cind.setPosicaoPlayer(new Integer[] { pz, px });
                            posicao = GUI.cind.getPosicaoPlayer();
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
                        
                        
                        if(Player.key == KeyEvent.VK_DOWN) {
                            if(orientacao == NORTE)
                                orientacao = SUL;
                            else if(orientacao == SUL)
                                orientacao = NORTE;
                            else if(orientacao == LESTE)
                                orientacao = OESTE;
                            else if(orientacao == OESTE)
                                orientacao = LESTE;
                        }else if(Player.key == KeyEvent.VK_LEFT) {
                            if(orientacao == NORTE)
                                orientacao = OESTE;
                            else if(orientacao == SUL)
                                orientacao = LESTE;
                            else if(orientacao == LESTE)
                                orientacao = NORTE;
                            else if(orientacao == OESTE)
                                orientacao = SUL;
                        }else if(Player.key == KeyEvent.VK_RIGHT) {
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
}