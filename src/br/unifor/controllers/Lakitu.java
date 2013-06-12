package br.unifor.controllers;

import br.unifor.GUI;

public class Lakitu {
    
    float x;
    float y;
    float z;
    
    float posCamX;
    float posCamY;
    float posCamZ;
    
    public Lakitu() {
        super();
        
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
    }
    
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }
    
    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
    
    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
    
    public void addX(boolean add) {
        if(add)
            this.x++;
        else
            this.x--;
    }
    
    public void addY(boolean add) {
        if(add)
            this.y++;
        else
            this.y--;
    }
    
    public void addZ(boolean add) {
        if(add)
            this.z++;
        else
            this.z--;
    }

    public float getPosCamX() {
        this.posCamX = (GUI.cind.getPosicaoPlayer()[0] + GUI.player.z) * 2;
        float calc = 1.0f;
        
        if(GUI.player.r > 0.0f && GUI.player.r <= 90.0f)
            calc = 1.0f - (GUI.player.r / 90.0f);
        if(GUI.player.r < 0.0f && GUI.player.r >= -90.0f)
            calc = 1.0f - (GUI.player.r / 90.0f);
        
        if(GUI.player.r > 90.0f && GUI.player.r <= 180.0f)
            calc = (GUI.player.r - 90.0f) / 90.0f;
        if(GUI.player.r < -90.0f && GUI.player.r >= -180.0f)
            calc = 2.0f - (GUI.player.r + 90.0f) / -90.0f;
        
        if(GUI.player.r > 180.0f && GUI.player.r <= 270.0f)
            calc = 1.0f + (GUI.player.r - 180.0f) / 90.0f;
        if(GUI.player.r < -180.0f && GUI.player.r >= -270.0f)
            calc = 1.0f - (GUI.player.r + 180.0f) / -90.0f;
        
        if(GUI.player.r > 270.0f && GUI.player.r <= 360.0f)
            calc = 2.0f - (GUI.player.r - 270.0f) / 90.0f;
        if(GUI.player.r < -270.0f && GUI.player.r >= -360.0f)
            calc = (GUI.player.r + 270.0f) / -90.0f;

        return posCamX + calc;
    }

    public void setPosCamX(float posCamX) {
        this.posCamX = posCamX;
    }

    public float getPosCamY() {
        this.posCamY = 2.0f;
        return posCamY;
    }

    public void setPosCamY(float posCamY) {
        this.posCamY = posCamY;
    }

    public float getPosCamZ() {
        this.posCamZ = (GUI.cind.getPosicaoPlayer()[1] + GUI.player.x) * 2;
        float calc = 0.0f;
        
        if(GUI.player.r > 0.0f && GUI.player.r <= 90.0f)
            calc = GUI.player.r / 90.0f;
        if(GUI.player.r < 0.0f && GUI.player.r >= -90.0f)
            calc = GUI.player.r / -90.0f;
        
        if(GUI.player.r > 90.0f && GUI.player.r <= 180.0f)
            calc = 2.0f * (GUI.player.r / 180.0f);
        if(GUI.player.r < -90.0f && GUI.player.r >= -180.0f)
            calc = 2.0f * (GUI.player.r / -180.0f);
        
        if(GUI.player.r > 180.0f && GUI.player.r <= 270.0f)
            calc = 2.0f - (GUI.player.r - 180.0f) / 90.0f;
        if(GUI.player.r < -180.0f && GUI.player.r >= -270.0f)
            calc = 2.0f - (GUI.player.r + 180.0f) / -90.0f;
        
        if(GUI.player.r > 270.0f && GUI.player.r <= 360.0f)
            calc = 1.0f - (GUI.player.r - 270.0f) / 90.0f;
        if(GUI.player.r < -270.0f && GUI.player.r >= -360.0f)
            calc = 1.0f - (GUI.player.r + 270.0f) / -90.0f;

        return -(posCamZ + calc);
    }

    public void setPosCamZ(float posCamZ) {
        this.posCamZ = posCamZ;
    }

}