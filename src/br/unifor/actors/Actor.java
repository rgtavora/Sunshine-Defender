package br.unifor.actors;

import javax.media.opengl.GL;

public interface Actor {
    
    public enum Orientacao {
        NORTE,
        NORDESTE,
        LESTE,
        SUDESTE,
        SUL,
        SUDOESTE,
        OESTE,
        NOROESTE;


        public Orientacao girar45p() {
            int index = (this.ordinal() + 1) % values().length;
            return values()[index]; 
        }
        
        public Orientacao girar45n() {
            int index = (this.ordinal() + 7) % values().length;
            return values()[index]; 
        }
        
        public Orientacao girar90p() {
            int index = (this.ordinal() + 2) % values().length;
            return values()[index]; 
        }
        
        public Orientacao girar90n() {
            int index = (this.ordinal() + 6) % values().length;
            return values()[index]; 
        }
        
        public Orientacao girar180() {
            int index = (this.ordinal() + 4) % values().length;
            return values()[index]; 
        }
    }
    
    public void inserir(GL gl);
    public void voar();
    public void virar(int k);
    
}