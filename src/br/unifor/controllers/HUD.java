package br.unifor.controllers;

import br.unifor.GUI;
import com.sun.opengl.util.j2d.TextRenderer;
import java.awt.Font;
import javax.media.opengl.GLAutoDrawable;

public class HUD {
    
    private GLAutoDrawable drawable;
    private TextRenderer escritor;
    String font;
    int style;
    int size;

    int score;
    public int forcaSol;
    String notif;
    int ttl;
    
    int indexSelectedMenu;
    int indexSelectedDificuldade;
    
    public float alphaLogo;
    public float alphaMenu;
    public float alphaDificuldade;

    public HUD(GLAutoDrawable drawable) {
        super();
        this.drawable = drawable;
        font = "SansSerif";
        style = Font.BOLD;
        size = 20;
        
        escritor = new TextRenderer(new Font(font, style, size));
        
        score = 0;
        forcaSol = 100;
        notif = "Notificação";
        ttl = 0;
        indexSelectedMenu = 0;
        indexSelectedDificuldade = 1;
        
        alphaLogo = 1.0f;
        alphaMenu = 0.8f;
        alphaDificuldade = 0.8f;
    }
    
    public int getIndexSelectedMenu() {
        return this.indexSelectedMenu;
    }
    
    public int getIndexSelectedDificuldade() {
        return this.indexSelectedDificuldade;
    }

    public void setNotif(String notif) {
        this.notif = notif;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }
    
    public void setIndexSelectedMenu(int indexSelectedMenu) {
        this.indexSelectedMenu = indexSelectedMenu;
    }
    
    public void setIndexSelectedDificuldade(int indexSelectedDificuldade) {
        this.indexSelectedDificuldade = indexSelectedDificuldade;
    }
    
    public void addScore(int points) {
        this.score += points;
    }
    
    public void sugarSol() {
        if(forcaSol > 0)
            this.forcaSol--;
        else if(GUI.deus.tamSol > 0.0f) {
            GUI.deus.tamSol -= 0.005f;
            GUI.deus.luzSol -= 0.005f;
        }
    }
    
    public void escreverScore() {
        escritor.beginRendering(drawable.getWidth(), drawable.getHeight());
            escritor.setColor(0.0f, 0.0f, 1.0f, 0.8f);
            escritor.draw("Score: " + String.format("%04d", score), 24, drawable.getHeight() - 24);
        escritor.endRendering();
    }
    
    public void escreverForcaSol() {
        escritor.beginRendering(drawable.getWidth(), drawable.getHeight());
            if(forcaSol <= 20)
                escritor.setColor(1.0f, 0.0f, 0.0f, 0.8f);
            else if(forcaSol <= 50)
                escritor.setColor(1.0f, 1.0f, 0.0f, 0.8f);
            else
                escritor.setColor(0.0f, 1.0f, 0.0f, 0.8f);
            escritor.draw("Força do Sol: " + forcaSol + ",0%", 24, drawable.getHeight() - 48);
        escritor.endRendering();
    }
    
    public void escreverNotificacao() {
        if(ttl > 0) {
            escritor.beginRendering(drawable.getWidth(), drawable.getHeight());
                escritor.setColor(1.0f, 0.0f, 0.0f, ttl / 100.0f);
                escritor.draw(notif, 24, 24);
            escritor.endRendering();
            ttl--;
        }
    }
    
    public void escreverLogo() {
        escritor.beginRendering(drawable.getWidth(), drawable.getHeight());
            escritor = new TextRenderer(new Font(font, style, 32));
            escritor.setColor(0.0f, 1.0f, 0.0f, alphaLogo);
            escritor.draw("Sunshine Defender", 24, drawable.getHeight() - 40);
        escritor.endRendering();
        
        escritor = new TextRenderer(new Font(font, style, size));
    }
    
    public void escreverMenu() {
        escritor.beginRendering(drawable.getWidth(), drawable.getHeight());
            if(indexSelectedMenu == 0)
                escritor.setColor(1.0f, 0.0f, 0.0f, alphaMenu);
            else
                escritor.setColor(0.0f, 0.0f, 1.0f, alphaMenu);
            escritor.draw("Iniciar Jogo", drawable.getWidth() / 2, drawable.getHeight() / 2 - 80);
            
            if(indexSelectedMenu == 1)
                escritor.setColor(1.0f, 0.0f, 0.0f, alphaMenu);
            else
                escritor.setColor(0.0f, 0.0f, 1.0f, alphaMenu);
            escritor.draw("Sair", drawable.getWidth() / 2, drawable.getHeight() / 2 - 120);
        escritor.endRendering();
    }
    
    public void escreverDificuldade() {
        escritor.beginRendering(drawable.getWidth(), drawable.getHeight());
            escritor.setColor(0.0f, 0.0f, 1.0f, alphaDificuldade);
            escritor.draw("Dificuldade:", drawable.getWidth() / 2, drawable.getHeight() / 2 - 40);
            
            if(indexSelectedDificuldade == 0)
                escritor.setColor(1.0f, 0.0f, 0.0f, alphaDificuldade);
            else
                escritor.setColor(0.0f, 0.0f, 1.0f, alphaDificuldade);
            escritor.draw("Fácil", drawable.getWidth() / 2 + 8, drawable.getHeight() / 2 - 80);
            
            if(indexSelectedDificuldade == 1)
                escritor.setColor(1.0f, 0.0f, 0.0f, alphaDificuldade);
            else
                escritor.setColor(0.0f, 0.0f, 1.0f, alphaDificuldade);
            escritor.draw("Normal", drawable.getWidth() / 2 + 8, drawable.getHeight() / 2 - 120);
            
            if(indexSelectedDificuldade == 2)
                escritor.setColor(1.0f, 0.0f, 0.0f, alphaDificuldade);
            else
                escritor.setColor(0.0f, 0.0f, 1.0f, alphaDificuldade);
            escritor.draw("Difícil", drawable.getWidth() / 2 + 8, drawable.getHeight() / 2 - 160);
        escritor.endRendering();
    }
    
    public void escreverGameOver() {
        escritor = new TextRenderer(new Font(font, style, 48));
        escritor.beginRendering(drawable.getWidth(), drawable.getHeight());
            escritor.setColor(1.0f, 0.0f, 0.0f, 1.0f);
            escritor.draw("Game Over!", drawable.getWidth() / 2 - 144, drawable.getHeight() / 2);
        escritor.endRendering();
        escritor = new TextRenderer(new Font(font, style, size));
    }
    
}