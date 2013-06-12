package br.unifor.actors;

import br.unifor.GUI;
import br.unifor.controllers.Cindacta;
import br.unifor.util.ObjLoader;
import br.unifor.util.ObjModel;
import br.unifor.util.TextureHandler;
import java.awt.event.KeyEvent;
import java.io.IOException;
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
    }
    
    public void inserir(GL gl) {
        float posX = (posicao[0] + z) * 2.0f + 1.0f;
        float posZ = (posicao[1] + x) * -2.0f - 0.8f;
        
        gl.glMaterialf(GL.GL_FRONT, GL.GL_SHININESS, 0.5f);
        
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
                        r = 90.0f / 10.0f;
                    }else if(key == KeyEvent.VK_RIGHT) {
                        r = -90.0f / 10.0f;
                    }

                    for(int i = 0; i < 10; i++) {
                        Thread.sleep(25);
                        Alien.this.r += r;
                    }

                    if(key == KeyEvent.VK_DOWN)
                        orientacao = orientacao.girar180();
                    else if(key == KeyEvent.VK_LEFT)
                        orientacao = orientacao.girar90n();
                    else if(key == KeyEvent.VK_RIGHT)
                        orientacao = orientacao.girar90p();
                    
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
                            int vel = 1000;
                            switch(GUI.dificuldade) {
                                case 0: vel = 1000; break;
                                case 1: vel = 750; break;
                                case 2: vel = 500; break;
                            }
                            
                            Thread.sleep(vel);
                            if(posicao[0] < 14) {
                                if(orientacao == Orientacao.NORTE) {
                                    virar(KeyEvent.VK_RIGHT);
                                }else if(orientacao == Orientacao.SUL) {
                                    virar(KeyEvent.VK_LEFT);
                                }else if(orientacao == Orientacao.OESTE) {
                                    virar(KeyEvent.VK_DOWN);
                                }else 
                                voar();
                            }else if(posicao[0] > 17) {
                                if(orientacao == Orientacao.NORTE) {
                                    virar(KeyEvent.VK_LEFT);
                                }else if(orientacao == Orientacao.SUL) {
                                    virar(KeyEvent.VK_RIGHT);
                                }else if(orientacao == Orientacao.LESTE) {
                                    virar(KeyEvent.VK_DOWN);
                                }else 
                                voar();
                            }else if(posicao[1] < 14) {
                                if(orientacao == Orientacao.SUL) {
                                    virar(KeyEvent.VK_DOWN);
                                }else if(orientacao == Orientacao.OESTE) {
                                    virar(KeyEvent.VK_RIGHT);
                                }else if(orientacao == Orientacao.LESTE) {
                                    virar(KeyEvent.VK_LEFT);
                                }else 
                                voar();
                            }else if(posicao[1] > 17) {
                                if(orientacao == Orientacao.NORTE) {
                                    virar(KeyEvent.VK_DOWN);
                                }else if(orientacao == Orientacao.OESTE) {
                                    virar(KeyEvent.VK_LEFT);
                                }else if(orientacao == Orientacao.LESTE) {
                                    virar(KeyEvent.VK_RIGHT);
                                }else 
                                voar();
                            }
                            
                            if(GUI.cind.isAreaSuccao(posicao[0], posicao[1])) {
                                GUI.hud.sugarSol();
                            }
                        }
                        
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }).start();
    }
    
    public void setPos() {
        posicao = GUI.cind.getPosicaoAlien();
    }
    
    public void destruir(int i) {
        parar();
        GUI.cind.removerAlien();
        posicao[0] = 0;
        posicao[1] = 0;
        GUI.showAlien = false;
        GUI.asBomb.play();
        switch(i) {
            case 0: GUI.hud.setNotif("Ameaça Alien neutralizada! Parabéns Capitão!"); break;
            case 1: GUI.hud.setNotif("Ameaça Alien neutralizada! Nave chocou-se com Mercúrio!"); break;
            case 2: GUI.hud.setNotif("Ameaça Alien neutralizada! Nave chocou-se com Vênus!"); break;
            case 3: GUI.hud.setNotif("Ameaça Alien neutralizada! Nave chocou-se com Terra!"); break;
            case 4: GUI.hud.setNotif("Ameaça Alien neutralizada! Nave chocou-se com Marte!"); break;
        }
        
        GUI.hud.addScore(500);
        GUI.hud.setTtl(200);
        switch(GUI.dificuldade) {
            case 0: GUI.ttlPaz = 200; break;
            case 1: GUI.ttlPaz = 100; break;
            case 2: GUI.ttlPaz = 50; break;
        }
    }
}