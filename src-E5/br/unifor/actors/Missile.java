package br.unifor.actors;

import br.unifor.GUI;
import br.unifor.controllers.Cindacta;
import br.unifor.util.ObjLoader;
import br.unifor.util.ObjModel;
import br.unifor.util.TextureHandler;
import java.io.IOException;
import javax.media.opengl.GL;

public class Missile implements Actor {
    private ObjModel obj;
    private TextureHandler txtr;
    
    public Orientacao orientacao;
    private Integer[] posicao;
    private boolean parado;
    private boolean show;
    
    public float x, z, k, r;
    
    public int ttl;
    
    public Missile(GL gl) throws IOException {
        super();
        this.obj = ObjLoader.loadObj("br/unifor/models/rocketlauncher_shell.obj", gl);
        this.txtr = new TextureHandler("src/br/unifor/textures/missile.png");
        this.orientacao = Orientacao.NORTE;
        this.posicao = GUI.cind.getPosicaoPlayer();
        this.parado = true;
        this.show = false;
        x = z = k = r = 0.0f;
        ttl = 10;
    }

    public void inserir(GL gl) {
        this.r = GUI.player.r;
        
        if(show) {
            float posX = posicao[0] * 2.0f + 1.0f;
            float posZ = posicao[1] * -2.0f - 1.0f;

            txtr.getTexture().enable();
            txtr.getTexture().bind();

            gl.glPushMatrix();
                gl.glTranslatef(posX, 1.0f, posZ);
                gl.glRotatef(180.0f - r, 0.0f, 1.0f, 0.0f);
                gl.glScalef(2.0f, 2.0f, 2.0f);
                obj.render(gl);
            gl.glPopMatrix();

            txtr.getTexture().disable();
        }
    }
    
    public void show(boolean show) {
        this.show = show;
    }

    public void voar() {
        if(parado) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        parado = false;
                        
                        if(ttl == 10) {
                            orientacao = GUI.player.orientacao;
                            posicao = GUI.cind.getPosicaoPlayer();
                            GUI.asMissile.play();
                        }
                        
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
                        
                        if(px >= 0 && px < GUI.cind.getX() && pz >= 0 && pz < GUI.cind.getZ()) {
                            if(GUI.cind.getPosicaoEm(pz, px) == Cindacta.VAZIO) {
                                float movX = (px - posicao[1]) / 10.0f;
                                float movZ = (pz - posicao[0]) / 10.0f;

                                Thread.sleep(5);
                                x = movX;
                                z = movZ;
                                Thread.sleep(5);
                                x += movX;
                                z += movZ;
                                Thread.sleep(5);
                                x += movX;
                                z += movZ;
                                Thread.sleep(5);
                                x += movX;
                                z += movZ;
                                Thread.sleep(5);
                                x += movX;
                                z += movZ;
                                Thread.sleep(5);
                                x += movX;
                                z += movZ;
                                Thread.sleep(5);
                                x += movX;
                                z += movZ;
                                Thread.sleep(5);
                                x += movX;
                                z += movZ;
                                Thread.sleep(5);
                                x += movX;
                                z += movZ;
                                Thread.sleep(5);
                                x = 0.0f;
                                z = 0.0f;

                                GUI.cind.setPosicaoMissile(new Integer[] { pz, px });
                                posicao = GUI.cind.getPosicaoMissile();
                            }
                            if(GUI.deus.showMercurio)
                                if(GUI.cind.getPosicaoEm(pz, px) == Cindacta.MERCURIO) {
                                    GUI.deus.showMercurio = false;
                                    GUI.asBomb.play();
                                    GUI.hud.setNotif("Mercúrio destruído! Você afetou o resfriamento terrestre!");
                                    GUI.hud.addScore(-500);
                                    GUI.hud.setTtl(200);
                                }
                            
                            if(GUI.deus.showVenus)
                                if(GUI.cind.getPosicaoEm(pz, px) == Cindacta.VENUS) {
                                    GUI.deus.showVenus = false;
                                    GUI.asBomb.play();
                                    GUI.hud.setNotif("Vênus destruído! Você afetou os mares!");
                                    GUI.hud.addScore(-500);
                                    GUI.hud.setTtl(200);
                                }
                            
                            if(GUI.deus.showTerra)
                                if(GUI.cind.getPosicaoEm(pz, px) == Cindacta.TERRA) {
                                    GUI.deus.showTerra = false;
                                    GUI.asBomb.play();
                                    GUI.hud.setNotif("Terra destruída! É o fim da humanidade!");
                                    GUI.hud.addScore(-2000);
                                    GUI.hud.setTtl(200);
                                }
                            
                            if(GUI.deus.showMarte)
                                if(GUI.cind.getPosicaoEm(pz, px) == Cindacta.MARTE) {
                                    GUI.deus.showMarte = false;
                                    GUI.asBomb.play();
                                    GUI.hud.setNotif("Marte destruída! A gravidade da Terra foi alterada!");
                                    GUI.hud.addScore(-500);
                                    GUI.hud.setTtl(200);
                                }
                            
                            if(GUI.showAlien)
                                if(GUI.cind.getPosicaoEm(pz, px) == Cindacta.ALIEN) {
                                    GUI.showAlien = false;
                                    GUI.asBomb.play();
                                    GUI.hud.setNotif("Nave Alien destruída! A raça humana ganhou mais tempo!");
                                    GUI.hud.addScore(500);
                                    GUI.hud.setTtl(200);
                                }
                        }

                        parado = true;
                        
                        ttl--;
                        
                        if(ttl > 0)
                            voar();
                        else {
                            show(false);
                            ttl = 10;
                            posicao = GUI.cind.getPosicaoPlayer();
                        }
                        
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public void virar(int key) {}
}
