package br.unifor.actors;

import br.unifor.GUI;
import br.unifor.controllers.Cindacta;
import br.unifor.util.ObjLoader;
import br.unifor.util.ObjModel;
import br.unifor.util.TextureHandler;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.media.opengl.GL;

public class Player implements Actor {
    
    private ObjModel obj;
    private TextureHandler txtr;
    
    private int key;
    public Orientacao orientacao;
    private Integer[] posicao;
    private boolean parado;
    
    public float x, z, k, r;
    
    public Player(GL gl) throws IOException {
        super();
        this.obj = ObjLoader.loadObj("br/unifor/models/Batwing.obj", gl);
        this.txtr = new TextureHandler("src/br/unifor/textures/ship.jpg");
        this.orientacao = Orientacao.NORTE;
        this.key = KeyEvent.VK_UP;
        this.posicao = GUI.cind.getPosicaoPlayer();
        this.parado = true;
        x = z = k = r = 0.0f;
        flutuar();
    }
    
    public void inserir(GL gl) {
        float posX = (posicao[0] + z) * 2.0f + 1.0f;
        float posZ = (posicao[1] + x) * -2.0f - 1.0f;
        
        txtr.getTexture().enable();
        txtr.getTexture().bind();
        
        gl.glPushMatrix();
            gl.glTranslatef(posX, 1.0f, posZ);
            gl.glRotatef(270.0f, 1.0f, 0.0f, 0.0f);
            gl.glRotatef(k, 0.0f, 1.0f, 0.0f);
            gl.glRotatef(180.0f - r, 0.0f, 0.0f, 1.0f);
            gl.glScalef(0.003f, 0.003f, 0.003f);
            obj.render(gl);
        gl.glPopMatrix();
        
        txtr.getTexture().disable();
    }
    
    public void voar() {
        if(parado) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        parado = false;
                        
                        int px = posicao[1];
                        int pz = posicao[0];

                        if(orientacao == Orientacao.NORTE)
                            px = posicao[1] + 1;
                        else if(orientacao == Orientacao.SUL)
                            px = posicao[1] - 1;
                        else if(orientacao == Orientacao.LESTE)
                            pz = posicao[0] + 1;
                        else if(orientacao == Orientacao.OESTE)
                            pz = posicao[0] - 1;
                        else if(orientacao == Orientacao.NORDESTE) {
                            px = posicao[1] + 1;
                            pz = posicao[0] + 1;
                        }else if(orientacao == Orientacao.SUDESTE) {
                            px = posicao[1] - 1;
                            pz = posicao[0] + 1;
                        }else if(orientacao == Orientacao.SUDOESTE) {
                            px = posicao[1] - 1;
                            pz = posicao[0] - 1;
                        }else if(orientacao == Orientacao.NOROESTE) {
                            px = posicao[1] + 1;
                            pz = posicao[0] - 1;
                        }
                        
                        if(px >= 0 && px < GUI.cind.getX() &&
                                pz >= 0 && pz < GUI.cind.getZ() &&
                                GUI.cind.getPosicaoEm(pz, px) == Cindacta.VAZIO) {
                            
                            float movX = (px - posicao[1]) / 10.0f;
                            float movZ = (pz - posicao[0]) / 10.0f;

                            Thread.sleep(25);
                            x = movX;
                            z = movZ;
                            Thread.sleep(25);
                            x += movX;
                            z += movZ;
                            Thread.sleep(25);
                            x += movX;
                            z += movZ;
                            Thread.sleep(25);
                            x += movX;
                            z += movZ;
                            Thread.sleep(25);
                            x += movX;
                            z += movZ;
                            Thread.sleep(25);
                            x += movX;
                            z += movZ;
                            Thread.sleep(25);
                            x += movX;
                            z += movZ;
                            Thread.sleep(25);
                            x += movX;
                            z += movZ;
                            Thread.sleep(25);
                            x += movX;
                            z += movZ;
                            Thread.sleep(25);
                            x = 0.0f;
                            z = 0.0f;

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
    
    public void virar(int k) {
        key = k;
        new Thread(new Runnable() {
            public void run() {
                try {
                    parado = false;
                    float r = 0.0f;

                    if(key == KeyEvent.VK_DOWN) {
                        r = 180.0f / 10.0f;
                    }else if(key == KeyEvent.VK_LEFT) {
                        r = -45.0f / 10.0f;
                    }else if(key == KeyEvent.VK_RIGHT) {
                        r = 45.0f / 10.0f;
                    }

                    for(int i = 0; i < 10; i++) {
                        Thread.sleep(25);
                        Player.this.r += r;
                    }

                    if(key == KeyEvent.VK_DOWN)
                        orientacao = orientacao.girar180();
                    else if(key == KeyEvent.VK_LEFT)
                        orientacao = orientacao.girar45n();
                    else if(key == KeyEvent.VK_RIGHT)
                        orientacao = orientacao.girar45p();
                    
                    if(Player.this.r >= 360.0f)
                        Player.this.r -= 360.0f;

                    if(Player.this.r <= -360.0f)
                        Player.this.r += 360.0f;

                    parado = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    
    private void flutuar() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    float coe = 0.05f;
                    while(true){
                        Thread.sleep(100);
                        if(k >= 0.5f)
                            coe = -0.05f;
                        if(k <= -0.5f)
                            coe = 0.05f;
                        
                        k += coe;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}