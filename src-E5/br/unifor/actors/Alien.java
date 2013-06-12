package br.unifor.actors;

import br.unifor.GUI;
import br.unifor.controllers.Cindacta;
import br.unifor.util.ObjLoader;
import br.unifor.util.ObjModel;
import br.unifor.util.TextureHandler;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;
import javax.media.opengl.GL;

public class Alien implements Actor {
    
    private ObjModel obj;
    private TextureHandler txtr;
    
    private int key;
    public Orientacao orientacao;
    private Integer[] posicao;
    private boolean parado;
    private boolean ia;
    
    public float x, z, r;
    float rNorte, rSul, rLeste, rOeste;
    
    public Alien(GL gl) throws IOException {
        super();
        this.obj = ObjLoader.loadObj("br/unifor/models/ASF-HP.obj", gl);
        this.txtr = new TextureHandler("src/br/unifor/textures/vein.jpg");
        this.orientacao = Orientacao.SUL;
        this.key = KeyEvent.VK_DOWN;
        this.posicao = GUI.cind.getPosicaoAlien();
        this.parado = true;
        this.ia = false;
        x = z = r = 0.0f;
        rNorte = rSul = rLeste = rOeste = 0.0f;
    }
    
    public void inserir(GL gl) {
        float posX = (posicao[0] + z) * 2.0f + 1.0f;
        float posZ = (posicao[1] + x) * -2.0f - 0.8f;
        
        txtr.getTexture().enable();
        txtr.getTexture().bind();
        
        gl.glPushMatrix();
            gl.glTranslatef(posX, 1.0f, posZ);
            gl.glRotatef(270.0f + r, 0.0f, 1.0f, 0.0f);
            gl.glScalef(0.25f, 0.25f, 0.25f);
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
                        r = 45.0f / 10.0f;
                    }else if(key == KeyEvent.VK_RIGHT) {
                        r = -45.0f / 10.0f;
                    }

                    for(int i = 0; i < 10; i++) {
                        Thread.sleep(25);
                        Alien.this.r += r;
                    }

                    if(key == KeyEvent.VK_DOWN)
                        orientacao = orientacao.girar180();
                    else if(key == KeyEvent.VK_LEFT)
                        orientacao = orientacao.girar45n();
                    else if(key == KeyEvent.VK_RIGHT)
                        orientacao = orientacao.girar45p();
                    
                    if(Alien.this.r >= 360.0f)
                        Alien.this.r -= 360.0f;

                    if(Alien.this.r <= -360.0f)
                        Alien.this.r += 360.0f;

                    parado = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
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
                                    voar();
                                    break;
                                case 3:
                                    voar();
                                    break;
                                case 4:
                                    virar(KeyEvent.VK_RIGHT);
                                    voar();
                                    break;
                                case 5:
                                    voar();
                                    break;
                                case 6:
                                    virar(KeyEvent.VK_LEFT);
                                    voar();
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