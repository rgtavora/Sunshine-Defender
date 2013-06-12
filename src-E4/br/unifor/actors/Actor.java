package br.unifor.actors;

import javax.media.opengl.GL;

public interface Actor {
    
    final char NORTE = 'N';
    final char SUL = 'S';
    final char LESTE = 'L';
    final char OESTE = 'O';
    
    public void inserir(GL gl);
    public void voar();
    public void virar(int key);
    
}