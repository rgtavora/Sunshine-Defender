package br.unifor.controllers;

import br.unifor.GUI;
import java.util.ArrayList;
import java.util.Random;

public class Cindacta {
    
    public static final char VAZIO = 'V';
    public static final char ESPACO = 'E';
    public static final char SOL = 'S';
    public static final char MERCURIO = 'C';
    public static final char VENUS = 'N';
    public static final char TERRA = 'T';
    public static final char MARTE = 'M';
    public static final char PLAYER = 'P';
    public static final char ALIEN = 'A';
    public static final char MISSILE = 'I';
    
    final int X = 32;
    final int Z = 32;
    
    char[][] sistemaSolar;
    
    ArrayList<Integer[]> rotacaoSol;
    ArrayList<Integer[]> rotacaoMercurio;
    ArrayList<Integer[]> rotacaoVenus;
    ArrayList<Integer[]> rotacaoTerra;
    ArrayList<Integer[]> rotacaoMarte;
    ArrayList<Integer[]> areaSuccao;
    
    Integer[] posicaoSol;
    Integer[] posicaoMercurio;
    Integer[] posicaoVenus;
    Integer[] posicaoTerra;
    Integer[] posicaoMarte;
    Integer[] posicaoPlayer;
    Integer[] posicaoAlien;
    Integer[] posicaoMissile;
    
    public Cindacta() {
        sistemaSolar = new char[X][Z];
        
        rotacaoSol = new ArrayList<Integer[]>();
        rotacaoMercurio = new ArrayList<Integer[]>();
        rotacaoVenus = new ArrayList<Integer[]>();
        rotacaoTerra = new ArrayList<Integer[]>();
        rotacaoMarte = new ArrayList<Integer[]>();
        areaSuccao = new ArrayList<Integer[]>();
        
        iniciarSistemaSolar();
        
        definirRotacaoSol();
        definirRotacaoMercurio();
        definirRotacaoVenus();
        definirRotacaoTerra();
        definirRotacaoMarte();
        definirAreaSuccao();
        
        inserirEspacoProfundo();
        inserirSol();
        inserirMercurio();
        inserirVenus();
        inserirTerra();
        inserirMarte();
        inserirPlayer();
        inserirAlien();
        posicaoMissile = null;
    }
    
    public int getX() {
        return X;
    }
    
    public int getZ() {
        return Z;
    }
    
    public Integer[] getPosicaoSol() {
        return posicaoSol;
    }
    
    public Integer[] getPosicaoMercurio() {
        return posicaoMercurio;
    }
    
    public Integer[] getPosicaoVenus() {
        return posicaoVenus;
    }
     
    public Integer[] getPosicaoTerra() {
        return posicaoTerra;
    }
     
    public Integer[] getPosicaoMarte() {
        return posicaoMarte;
    }
    
    public Integer[] getPosicaoPlayer() {
        return posicaoPlayer;
    }
    
    public Integer[] getPosicaoAlien() {
        return posicaoAlien;
    }
    
    public Integer[] getPosicaoMissile() {
        return posicaoMissile;
    }
    
    public void setPosicaoPlayer(Integer[] posicaoPlayer) {
        sistemaSolar[this.posicaoPlayer[0]][this.posicaoPlayer[1]] = VAZIO;
        this.posicaoPlayer = posicaoPlayer;
        sistemaSolar[this.posicaoPlayer[0]][this.posicaoPlayer[1]] = PLAYER;
    }
    
    public void setPosicaoAlien(Integer[] posicaoAlien) {
        sistemaSolar[this.posicaoAlien[0]][this.posicaoAlien[1]] = VAZIO;
        this.posicaoAlien = posicaoAlien;
        sistemaSolar[this.posicaoAlien[0]][this.posicaoAlien[1]] = ALIEN;
    }
    
    public void setPosicaoMissile(Integer[] posicaoMissile) {
        if(this.posicaoMissile != null)
            sistemaSolar[this.posicaoMissile[0]][this.posicaoMissile[1]] = VAZIO;
        
        this.posicaoMissile = posicaoMissile;
        sistemaSolar[this.posicaoMissile[0]][this.posicaoMissile[1]] = MISSILE;
    }
    
    public char getPosicaoEm(int x, int z) {
        return sistemaSolar[x][z];
    }
     
    public Integer[] getProximaPosicaoMercurio() {
        int i = posicaoMercurio[0] + 1;
        if(i > (rotacaoMercurio.size() - 1))
            i = 0;
        return new Integer[] { i, rotacaoMercurio.get(i)[0], rotacaoMercurio.get(i)[1] };
    }
    
    public Integer[] getProximaPosicaoVenus() {
        int i = posicaoVenus[0] + 1;
        if(i > (rotacaoVenus.size() - 1))
            i = 0;
        return new Integer[] { i, rotacaoVenus.get(i)[0], rotacaoVenus.get(i)[1] };
    }
     
    public Integer[] getProximaPosicaoTerra() {
        int i = posicaoTerra[0] + 1;
        if(i > (rotacaoTerra.size() - 1))
            i = 0;
        return new Integer[] { i, rotacaoTerra.get(i)[0], rotacaoTerra.get(i)[1] };
    }
     
    public Integer[] getProximaPosicaoMarte() {
        int i = posicaoMarte[0] + 1;
        if(i > (rotacaoMarte.size() - 1))
            i = 0;
        return new Integer[] { i, rotacaoMarte.get(i)[0], rotacaoMarte.get(i)[1] };
    }
    
    public boolean isAreaSuccao(int x, int y) {
        for(int i = 0; i < areaSuccao.size(); i++) {
            if(areaSuccao.get(i)[0] == x && areaSuccao.get(i)[1] == y)
                return true;
        }
        return false;
    }
    
    private void iniciarSistemaSolar() {
        for(int i = 0; i < X; i++)
            for(int j = 0; j < Z; j++)
                sistemaSolar[i][j] = VAZIO;
    }
    
    private void definirAreaSuccao() {
        areaSuccao.add(new Integer[] { 14, 14 });
        areaSuccao.add(new Integer[] { 14, 15 });
        areaSuccao.add(new Integer[] { 14, 16 });
        areaSuccao.add(new Integer[] { 14, 17 });
        areaSuccao.add(new Integer[] { 15, 17 });
        areaSuccao.add(new Integer[] { 16, 17 });
        areaSuccao.add(new Integer[] { 17, 17 });
        areaSuccao.add(new Integer[] { 17, 16 });
        areaSuccao.add(new Integer[] { 17, 15 });
        areaSuccao.add(new Integer[] { 17, 14 });
        areaSuccao.add(new Integer[] { 16, 14 });
        areaSuccao.add(new Integer[] { 15, 14 });
    }
    
    private void definirRotacaoSol() {
        rotacaoSol.add(new Integer[] { 15, 15 });
        rotacaoSol.add(new Integer[] { 16, 15 });
        rotacaoSol.add(new Integer[] { 15, 16 });
        rotacaoSol.add(new Integer[] { 16, 16 });
    }
    
    private void definirRotacaoMercurio() {
        rotacaoMercurio.add(new Integer[] { 13, 13 });
        rotacaoMercurio.add(new Integer[] { 14, 12 });
        rotacaoMercurio.add(new Integer[] { 15, 12 });
        rotacaoMercurio.add(new Integer[] { 16, 12 });
        rotacaoMercurio.add(new Integer[] { 17, 12 });
        rotacaoMercurio.add(new Integer[] { 18, 13 });
        rotacaoMercurio.add(new Integer[] { 19, 14 });
        rotacaoMercurio.add(new Integer[] { 19, 15 });
        rotacaoMercurio.add(new Integer[] { 19, 16 });
        rotacaoMercurio.add(new Integer[] { 19, 17 });
        rotacaoMercurio.add(new Integer[] { 18, 18 });
        rotacaoMercurio.add(new Integer[] { 17, 19 });
        rotacaoMercurio.add(new Integer[] { 16, 19 });
        rotacaoMercurio.add(new Integer[] { 15, 19 });
        rotacaoMercurio.add(new Integer[] { 14, 19 });
        rotacaoMercurio.add(new Integer[] { 13, 18 });
        rotacaoMercurio.add(new Integer[] { 12, 17 });
        rotacaoMercurio.add(new Integer[] { 12, 16 });
        rotacaoMercurio.add(new Integer[] { 12, 15 });
        rotacaoMercurio.add(new Integer[] { 12, 14 });
    }
    
    private void definirRotacaoVenus() {
        rotacaoVenus.add(new Integer[] { 14, 9 });
        rotacaoVenus.add(new Integer[] { 15, 9 });
        rotacaoVenus.add(new Integer[] { 16, 9 });
        rotacaoVenus.add(new Integer[] { 17, 9 });
        rotacaoVenus.add(new Integer[] { 18, 9 });
        rotacaoVenus.add(new Integer[] { 19, 10 });
        rotacaoVenus.add(new Integer[] { 20, 11 });
        rotacaoVenus.add(new Integer[] { 21, 12 });
        rotacaoVenus.add(new Integer[] { 22, 13 });
        rotacaoVenus.add(new Integer[] { 22, 14 });
        rotacaoVenus.add(new Integer[] { 22, 15 });
        rotacaoVenus.add(new Integer[] { 22, 16 });
        rotacaoVenus.add(new Integer[] { 22, 17 });
        rotacaoVenus.add(new Integer[] { 22, 18 });
        rotacaoVenus.add(new Integer[] { 21, 19 });
        rotacaoVenus.add(new Integer[] { 20, 20 });
        rotacaoVenus.add(new Integer[] { 19, 21 });
        rotacaoVenus.add(new Integer[] { 18, 22 });
        rotacaoVenus.add(new Integer[] { 17, 22 });
        rotacaoVenus.add(new Integer[] { 16, 22 });
        rotacaoVenus.add(new Integer[] { 15, 22 });
        rotacaoVenus.add(new Integer[] { 14, 22 });
        rotacaoVenus.add(new Integer[] { 13, 22 });
        rotacaoVenus.add(new Integer[] { 12, 21 });
        rotacaoVenus.add(new Integer[] { 11, 20 });
        rotacaoVenus.add(new Integer[] { 10, 19 });
        rotacaoVenus.add(new Integer[] { 9, 18 });
        rotacaoVenus.add(new Integer[] { 9, 17 });
        rotacaoVenus.add(new Integer[] { 9, 16 });
        rotacaoVenus.add(new Integer[] { 9, 15 });
        rotacaoVenus.add(new Integer[] { 9, 14 });
        rotacaoVenus.add(new Integer[] { 9, 13 });
        rotacaoVenus.add(new Integer[] { 10, 12 });
        rotacaoVenus.add(new Integer[] { 11, 11 });
        rotacaoVenus.add(new Integer[] { 12, 10 });
        rotacaoVenus.add(new Integer[] { 13, 9 });
    }
    
    private void definirRotacaoTerra() {
        rotacaoTerra.add(new Integer[] { 12, 6 });
        rotacaoTerra.add(new Integer[] { 11, 7 });
        rotacaoTerra.add(new Integer[] { 10, 8 });
        rotacaoTerra.add(new Integer[] { 9, 8 });
        rotacaoTerra.add(new Integer[] { 8, 9 });
        rotacaoTerra.add(new Integer[] { 8, 10 });
        rotacaoTerra.add(new Integer[] { 7, 11 });
        rotacaoTerra.add(new Integer[] { 6, 12 });
        rotacaoTerra.add(new Integer[] { 6, 13 });
        rotacaoTerra.add(new Integer[] { 6, 14 });
        rotacaoTerra.add(new Integer[] { 6, 15 });
        rotacaoTerra.add(new Integer[] { 6, 16 });
        rotacaoTerra.add(new Integer[] { 6, 17 });
        rotacaoTerra.add(new Integer[] { 6, 18 });
        rotacaoTerra.add(new Integer[] { 6, 19 });
        rotacaoTerra.add(new Integer[] { 7, 20 });
        rotacaoTerra.add(new Integer[] { 8, 21 });
        rotacaoTerra.add(new Integer[] { 8, 22 });
        rotacaoTerra.add(new Integer[] { 9, 23 });
        rotacaoTerra.add(new Integer[] { 10, 23 });
        rotacaoTerra.add(new Integer[] { 11, 24 });
        rotacaoTerra.add(new Integer[] { 12, 25 });
        rotacaoTerra.add(new Integer[] { 13, 25 });
        rotacaoTerra.add(new Integer[] { 14, 25 });
        rotacaoTerra.add(new Integer[] { 15, 25 });
        rotacaoTerra.add(new Integer[] { 16, 25 });
        rotacaoTerra.add(new Integer[] { 17, 25 });
        rotacaoTerra.add(new Integer[] { 18, 25 });
        rotacaoTerra.add(new Integer[] { 19, 25 });
        rotacaoTerra.add(new Integer[] { 20, 24 });
        rotacaoTerra.add(new Integer[] { 21, 23 });
        rotacaoTerra.add(new Integer[] { 22, 23 });
        rotacaoTerra.add(new Integer[] { 23, 22 });
        rotacaoTerra.add(new Integer[] { 23, 21 });
        rotacaoTerra.add(new Integer[] { 24, 20 });
        rotacaoTerra.add(new Integer[] { 25, 19 });
        rotacaoTerra.add(new Integer[] { 25, 18 });
        rotacaoTerra.add(new Integer[] { 25, 17 });
        rotacaoTerra.add(new Integer[] { 25, 16 });
        rotacaoTerra.add(new Integer[] { 25, 15 });
        rotacaoTerra.add(new Integer[] { 25, 14 });
        rotacaoTerra.add(new Integer[] { 25, 13 });
        rotacaoTerra.add(new Integer[] { 25, 12 });
        rotacaoTerra.add(new Integer[] { 24, 11 });
        rotacaoTerra.add(new Integer[] { 23, 10 });
        rotacaoTerra.add(new Integer[] { 23, 9 });
        rotacaoTerra.add(new Integer[] { 22, 8 });
        rotacaoTerra.add(new Integer[] { 21, 8 });
        rotacaoTerra.add(new Integer[] { 20, 7 });
        rotacaoTerra.add(new Integer[] { 19, 6 });
        rotacaoTerra.add(new Integer[] { 18, 6 });
        rotacaoTerra.add(new Integer[] { 17, 6 });
        rotacaoTerra.add(new Integer[] { 16, 6 });
        rotacaoTerra.add(new Integer[] { 15, 6 });
        rotacaoTerra.add(new Integer[] { 14, 6 });
        rotacaoTerra.add(new Integer[] { 13, 6 });
    }
    
    private void definirRotacaoMarte() {
        rotacaoMarte.add(new Integer[] { 12, 3 });
        rotacaoMarte.add(new Integer[] { 11, 4 });
        rotacaoMarte.add(new Integer[] { 10, 4 });
        rotacaoMarte.add(new Integer[] { 9, 5 });
        rotacaoMarte.add(new Integer[] { 8, 6 });
        rotacaoMarte.add(new Integer[] { 7, 7 });
        rotacaoMarte.add(new Integer[] { 6, 8 });
        rotacaoMarte.add(new Integer[] { 5, 9 });
        rotacaoMarte.add(new Integer[] { 4, 10 });
        rotacaoMarte.add(new Integer[] { 4, 11 });
        rotacaoMarte.add(new Integer[] { 3, 12 });
        rotacaoMarte.add(new Integer[] { 3, 13 });
        rotacaoMarte.add(new Integer[] { 3, 14 });
        rotacaoMarte.add(new Integer[] { 3, 15 });
        rotacaoMarte.add(new Integer[] { 3, 16 });
        rotacaoMarte.add(new Integer[] { 3, 17 });
        rotacaoMarte.add(new Integer[] { 3, 18 });
        rotacaoMarte.add(new Integer[] { 3, 19 });
        rotacaoMarte.add(new Integer[] { 4, 20 });
        rotacaoMarte.add(new Integer[] { 4, 21 });
        rotacaoMarte.add(new Integer[] { 5, 22 });
        rotacaoMarte.add(new Integer[] { 6, 23 });
        rotacaoMarte.add(new Integer[] { 7, 24 });
        rotacaoMarte.add(new Integer[] { 8, 25 });
        rotacaoMarte.add(new Integer[] { 9, 26 });
        rotacaoMarte.add(new Integer[] { 10, 27 });
        rotacaoMarte.add(new Integer[] { 11, 27 });
        rotacaoMarte.add(new Integer[] { 12, 28 });
        rotacaoMarte.add(new Integer[] { 13, 28 });
        rotacaoMarte.add(new Integer[] { 14, 28 });
        rotacaoMarte.add(new Integer[] { 15, 28 });
        rotacaoMarte.add(new Integer[] { 16, 28 });
        rotacaoMarte.add(new Integer[] { 17, 28 });
        rotacaoMarte.add(new Integer[] { 18, 28 });
        rotacaoMarte.add(new Integer[] { 19, 28 });
        rotacaoMarte.add(new Integer[] { 20, 27 });
        rotacaoMarte.add(new Integer[] { 21, 27 });
        rotacaoMarte.add(new Integer[] { 22, 26 });
        rotacaoMarte.add(new Integer[] { 23, 25 });
        rotacaoMarte.add(new Integer[] { 24, 24 });
        rotacaoMarte.add(new Integer[] { 25, 23 });
        rotacaoMarte.add(new Integer[] { 26, 22 });
        rotacaoMarte.add(new Integer[] { 27, 21 });
        rotacaoMarte.add(new Integer[] { 27, 20 });
        rotacaoMarte.add(new Integer[] { 28, 19 });
        rotacaoMarte.add(new Integer[] { 28, 18 });
        rotacaoMarte.add(new Integer[] { 28, 17 });
        rotacaoMarte.add(new Integer[] { 28, 16 });
        rotacaoMarte.add(new Integer[] { 28, 15 });
        rotacaoMarte.add(new Integer[] { 28, 14 });
        rotacaoMarte.add(new Integer[] { 28, 13 });
        rotacaoMarte.add(new Integer[] { 28, 12 });
        rotacaoMarte.add(new Integer[] { 27, 11 });
        rotacaoMarte.add(new Integer[] { 27, 10 });
        rotacaoMarte.add(new Integer[] { 26, 9 });
        rotacaoMarte.add(new Integer[] { 25, 8 });
        rotacaoMarte.add(new Integer[] { 24, 7 });
        rotacaoMarte.add(new Integer[] { 23, 6 });
        rotacaoMarte.add(new Integer[] { 22, 5 });
        rotacaoMarte.add(new Integer[] { 21, 4 });
        rotacaoMarte.add(new Integer[] { 20, 4 });
        rotacaoMarte.add(new Integer[] { 19, 3 });
        rotacaoMarte.add(new Integer[] { 18, 3 });
        rotacaoMarte.add(new Integer[] { 17, 3 });
        rotacaoMarte.add(new Integer[] { 16, 3 });
        rotacaoMarte.add(new Integer[] { 15, 3 });
        rotacaoMarte.add(new Integer[] { 14, 3 });
        rotacaoMarte.add(new Integer[] { 13, 3 });
    }
    
    private void inserirEspacoProfundo() {
        sistemaSolar[0][0] = ESPACO;
        sistemaSolar[0][1] = ESPACO;
        sistemaSolar[0][2] = ESPACO;
        sistemaSolar[0][3] = ESPACO;
        sistemaSolar[1][0] = ESPACO;
        sistemaSolar[1][1] = ESPACO;
        sistemaSolar[1][2] = ESPACO;
        sistemaSolar[2][0] = ESPACO;
        sistemaSolar[2][1] = ESPACO;
        sistemaSolar[3][0] = ESPACO;
        sistemaSolar[28][0] = ESPACO;
        sistemaSolar[29][0] = ESPACO;
        sistemaSolar[29][1] = ESPACO;
        sistemaSolar[30][0] = ESPACO;
        sistemaSolar[30][1] = ESPACO;
        sistemaSolar[30][2] = ESPACO;
        sistemaSolar[31][0] = ESPACO;
        sistemaSolar[31][1] = ESPACO;
        sistemaSolar[31][2] = ESPACO;
        sistemaSolar[31][3] = ESPACO;
        sistemaSolar[0][28] = ESPACO;
        sistemaSolar[0][29] = ESPACO;
        sistemaSolar[0][30] = ESPACO;
        sistemaSolar[0][31] = ESPACO;
        sistemaSolar[1][29] = ESPACO;
        sistemaSolar[1][30] = ESPACO;
        sistemaSolar[1][31] = ESPACO;
        sistemaSolar[2][30] = ESPACO;
        sistemaSolar[2][31] = ESPACO;
        sistemaSolar[3][31] = ESPACO;
        sistemaSolar[28][31] = ESPACO;
        sistemaSolar[29][30] = ESPACO;
        sistemaSolar[29][31] = ESPACO;
        sistemaSolar[30][29] = ESPACO;
        sistemaSolar[30][30] = ESPACO;
        sistemaSolar[30][31] = ESPACO;
        sistemaSolar[31][28] = ESPACO;
        sistemaSolar[31][29] = ESPACO;
        sistemaSolar[31][30] = ESPACO;
        sistemaSolar[31][31] = ESPACO;
    }
    
    private void inserirSol() {
        for(int i = 0; i < rotacaoSol.size(); i++)
            sistemaSolar[rotacaoSol.get(i)[0]][rotacaoSol.get(i)[1]] = SOL;
        posicaoSol = new Integer[] { rotacaoSol.get(rotacaoSol.size() - 1)[0], rotacaoSol.get(rotacaoSol.size() - 1)[1] };
    }
    
    private void inserirMercurio() {
        int i = new Random().nextInt(rotacaoMercurio.size());
        posicaoMercurio = new Integer[] { i, rotacaoMercurio.get(i)[0], rotacaoMercurio.get(i)[1] };
        sistemaSolar[posicaoMercurio[1]][posicaoMercurio[2]] = MERCURIO;
    }
    
    public void destruirMercurio() {
        sistemaSolar[posicaoMercurio[1]][posicaoMercurio[2]] = VAZIO;
    }
    
    private void inserirVenus() {
        int i = new Random().nextInt(rotacaoVenus.size());
        posicaoVenus = new Integer[] { i, rotacaoVenus.get(i)[0], rotacaoVenus.get(i)[1] };
        sistemaSolar[posicaoVenus[1]][posicaoVenus[2]] = VENUS;
    }
    
    public void destruirVenus() {
        sistemaSolar[posicaoVenus[1]][posicaoVenus[2]] = VAZIO;
    }
    
    private void inserirTerra() {
        int i = new Random().nextInt(rotacaoTerra.size());
        posicaoTerra = new Integer[] { i, rotacaoTerra.get(i)[0], rotacaoTerra.get(i)[1] };
        sistemaSolar[posicaoTerra[1]][posicaoTerra[2]] = TERRA;
    }
    
    public void destruirTerra() {
        sistemaSolar[posicaoTerra[1]][posicaoTerra[2]] = VAZIO;
    }
    
    private void inserirMarte() {
        int i = new Random().nextInt(rotacaoMarte.size());
        posicaoMarte = new Integer[] { i, rotacaoMarte.get(i)[0], rotacaoMarte.get(i)[1] };
        sistemaSolar[posicaoMarte[1]][posicaoMarte[2]] = MARTE;
    }
    
    public void destruirMarte() {
        sistemaSolar[posicaoMarte[1]][posicaoMarte[2]] = VAZIO;
    }
    
    private void inserirPlayer() {
        posicaoPlayer = new Integer[] { 4, 4 };
        sistemaSolar[posicaoPlayer[0]][posicaoPlayer[1]] = PLAYER;
    }
    
     public void inserirAlien() {
         int r = new Random().nextInt(4);
         int x = 0, y = 0;
         
         switch(r) {
             case 0: {
                 x = new Random().nextInt(23) + 4;
                 y = 31;
             }break;
             case 1: {
                 x = new Random().nextInt(23) + 4;
                 y = 0;
             }break;
             case 2: {
                 x = 31;
                 y = new Random().nextInt(23) + 4;
             }break;
             case 3: {
                 x = 0;
                 y = new Random().nextInt(23) + 4;
             }break;
         }
         if(sistemaSolar[x][y] == VAZIO) {
             posicaoAlien = new Integer[] { x, y };
             sistemaSolar[posicaoAlien[0]][posicaoAlien[1]] = ALIEN;
         }else {
             inserirAlien();
         }
    }
     
     public void removerAlien() {
         sistemaSolar[posicaoAlien[0]][posicaoAlien[1]] = VAZIO;
     }
    
    public void rotacionarMercurio() {
        int i = posicaoMercurio[0] + 1;
        if(i > (rotacaoMercurio.size() - 1))
            i = 0;
        sistemaSolar[posicaoMercurio[1]][posicaoMercurio[2]] = VAZIO;
        
        if(sistemaSolar[rotacaoMercurio.get(i)[0]][rotacaoMercurio.get(i)[1]] == ALIEN)
            GUI.alien.destruir(1);
        else if(sistemaSolar[rotacaoMercurio.get(i)[0]][rotacaoMercurio.get(i)[1]] == PLAYER)
            GUI.player.destruir();
        
        posicaoMercurio = new Integer[] { i, rotacaoMercurio.get(i)[0], rotacaoMercurio.get(i)[1] };
        sistemaSolar[posicaoMercurio[1]][posicaoMercurio[2]] = MERCURIO;
        
    }
    
    public void rotacionarVenus() {
        int i = posicaoVenus[0] + 1;
        if(i > (rotacaoVenus.size() - 1))
            i = 0;
        sistemaSolar[posicaoVenus[1]][posicaoVenus[2]] = VAZIO;
        
        if(sistemaSolar[rotacaoVenus.get(i)[0]][rotacaoVenus.get(i)[1]] == ALIEN)
            GUI.alien.destruir(2);
        else if(sistemaSolar[rotacaoVenus.get(i)[0]][rotacaoVenus.get(i)[1]] == PLAYER)
            GUI.player.destruir();
        
        posicaoVenus = new Integer[] { i, rotacaoVenus.get(i)[0], rotacaoVenus.get(i)[1] };
        sistemaSolar[posicaoVenus[1]][posicaoVenus[2]] = VENUS;
        
    }
    
    public void rotacionarTerra() {
        int i = posicaoTerra[0] + 1;
        if(i > (rotacaoTerra.size() - 1))
            i = 0;
        sistemaSolar[posicaoTerra[1]][posicaoTerra[2]] = VAZIO;
        
        if(sistemaSolar[rotacaoTerra.get(i)[0]][rotacaoTerra.get(i)[1]] == ALIEN)
            GUI.alien.destruir(3);
        else if(sistemaSolar[rotacaoTerra.get(i)[0]][rotacaoTerra.get(i)[1]] == PLAYER)
            GUI.player.destruir();
        
        posicaoTerra = new Integer[] { i, rotacaoTerra.get(i)[0], rotacaoTerra.get(i)[1] };
        sistemaSolar[posicaoTerra[1]][posicaoTerra[2]] = TERRA;
        
    }
    
    public void rotacionarMarte() {
        int i = posicaoMarte[0] + 1;
        if(i > (rotacaoMarte.size() - 1))
            i = 0;
        sistemaSolar[posicaoMarte[1]][posicaoMarte[2]] = VAZIO;
        
        if(sistemaSolar[rotacaoMarte.get(i)[0]][rotacaoMarte.get(i)[1]] == ALIEN)
            GUI.alien.destruir(4);
        else if(sistemaSolar[rotacaoMarte.get(i)[0]][rotacaoMarte.get(i)[1]] == PLAYER)
            GUI.player.destruir();
        
        posicaoMarte = new Integer[] { i, rotacaoMarte.get(i)[0], rotacaoMarte.get(i)[1] };
        sistemaSolar[posicaoMarte[1]][posicaoMarte[2]] = MARTE;
        
    }
}