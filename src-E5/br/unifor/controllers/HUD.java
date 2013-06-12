package br.unifor.controllers;

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
    int forcaSol;
    String notif;
    int ttl;

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
    }

    public void setNotif(String notif) {
        this.notif = notif;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }
    
    public void addScore(int points) {
        this.score += points;
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
            escritor.draw("Força do Sol: " + forcaSol + "%", 24, drawable.getHeight() - 48);
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
    
}