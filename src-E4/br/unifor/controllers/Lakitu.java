package br.unifor.controllers;

import br.unifor.GUI;

public class Lakitu {
    
    float r;
    float x;
    float y;
    float z;
    
    float posCamX;
    float posCamY;
    float posCamZ;
    
    public Lakitu() {
        super();
        
        this.r = 0.0f;
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
    }
    
    public float getR() {
        r = -GUI.player.saltoR;
        if(r != 0.0f)
            normalizarVizualizacao();
        return r;
    }

    public void setR(float r) {
        this.r = r;
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

    public float getPosCamX() {
        this.posCamX = ((GUI.cind.getPosicaoPlayer()[0] + GUI.player.saltoZ) * -2) - 1.0f;
        float calc = 0.0f;
        
        if(r > 0.0f)
            calc = (3.0f * (r / 90.0f));
        else if(r < 0.0f)
            calc = (3.0f * (r / 90.0f));
        
        if(r > 90.0f)
            calc = 3.0f - (3.0f * ((r - 90.0f) / 90.0f));
        else if(r < -90.0f)
            calc = -3.0f - (3.0f * ((r + 90.0f) / 90.0f));
        
        if(r < -180.0f)
            calc = -(3.0f * ((r + 180.0f) / 90.0f));
        
        if(r > 270.0f)
            calc = -3.0f + (3.0f * ((r - 270.0f) / 90.0f));
        else if(r < -270.0f)
            calc = 3.0f + (3.0f * ((r + 270.0f) / 90.0f));

        return posCamX + calc;
    }

    public void setPosCamX(float posCamX) {
        this.posCamX = posCamX;
    }

    public float getPosCamY() {
        this.posCamY = -2.0f;
        return posCamY;
    }

    public void setPosCamY(float posCamY) {
        this.posCamY = posCamY;
    }

    public float getPosCamZ() {
        this.posCamZ = (GUI.cind.getPosicaoPlayer()[1] + GUI.player.saltoX) * 2;
        float calc = 0.0f;
        
        if(r > 0.0f)
            calc = (3.0f * (r / 90.0f));
        else if(r < 0.0f)
            calc = (3.0f * (-r / 90.0f));
        
        if(r > 90.0f)
            calc = (3.0f * (r / 90.0f));
        
        if(r > 180.0f)
            calc = 6.0f - (3.0f * ((r - 180.0f) / 90.0f));
        else if(r < -180.0f)
            calc = 6.0f + (3.0f * ((r + 180.0f) / 90.0f));
        
        if(r > 270.0f)
            calc = 3.0f - (3.0f * ((r - 270.0f) / 90.0f));

        
        return posCamZ + calc;
    }

    public void setPosCamZ(float posCamZ) {
        this.posCamZ = posCamZ;
    }
    
    private void normalizarVizualizacao() {
        
    }
    
}