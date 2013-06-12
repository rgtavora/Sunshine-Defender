package br.unifor.controllers;

import br.unifor.GUI;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureData;
import com.sun.opengl.util.texture.TextureIO;
import java.io.IOException;
import java.io.InputStream;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

public class Deus {
    
    InputStream isSL0, isSL1, isSL2;
    InputStream isSol, isMercurio, isVenus, isTerra, isMarte;
    
    TextureData tdSL0, tdSL1, tdSL2;
    TextureData tdSol, tdMercurio, tdVenus, tdTerra, tdMarte;
    
    Texture txtrSL0, txtrSL1, txtrSL2;
    Texture txtrSol, txtrMercurio, txtrVenus, txtrTerra, txtrMarte;
    
    Integer[] pMercurio;
    Integer[] pVenus;
    Integer[] pTerra;
    Integer[] pMarte;
    
    float[] rgba;
    float saltoMercurioX, saltoMercurioZ;
    float saltoVenusX, saltoVenusZ;
    float saltoTerraX, saltoTerraZ;
    float saltoMarteX, saltoMarteZ;
    float rotacaoSol;
    
    public boolean showMercurio, showVenus, showTerra, showMarte;
     
    public Deus() throws IOException {
        super();
        
        this.isSL0 = getClass().getClassLoader().getResourceAsStream("br/unifor/textures/space_layer_0.png");
        this.tdSL0 = TextureIO.newTextureData(isSL0, false, "png");
        this.txtrSL0 = TextureIO.newTexture(tdSL0);
        this.isSL1 = getClass().getClassLoader().getResourceAsStream("br/unifor/textures/space_layer_1.png");
        this.tdSL1 = TextureIO.newTextureData(isSL1, false, "png");
        this.txtrSL1 = TextureIO.newTexture(tdSL1);
        this.isSL2 = getClass().getClassLoader().getResourceAsStream("br/unifor/textures/space_layer_2.png");
        this.tdSL2 = TextureIO.newTextureData(isSL2, false, "png");
        this.txtrSL2 = TextureIO.newTexture(tdSL2);
        
        this.isSol = getClass().getClassLoader().getResourceAsStream("br/unifor/textures/sun.png");
        this.tdSol = TextureIO.newTextureData(isSol, false, "png");
        this.txtrSol = TextureIO.newTexture(tdSol);
        this.isMercurio = getClass().getClassLoader().getResourceAsStream("br/unifor/textures/mercury.png");
        this.tdMercurio = TextureIO.newTextureData(isMercurio, false, "png");
        this.txtrMercurio = TextureIO.newTexture(tdMercurio);
        this.isVenus = getClass().getClassLoader().getResourceAsStream("br/unifor/textures/venus.png");
        this.tdVenus = TextureIO.newTextureData(isVenus, false, "png");
        this.txtrVenus = TextureIO.newTexture(tdVenus);
        this.isTerra = getClass().getClassLoader().getResourceAsStream("br/unifor/textures/earth.png");
        this.tdTerra = TextureIO.newTextureData(isTerra, false, "png");
        this.txtrTerra = TextureIO.newTexture(tdTerra);
        this.isMarte = getClass().getClassLoader().getResourceAsStream("br/unifor/textures/mars.png");
        this.tdMarte = TextureIO.newTextureData(isMarte, false, "png");
        this.txtrMarte = TextureIO.newTexture(tdMarte);
        this.rgba = new float[]{1f, 1f, 1f};
        
        this.pMercurio = GUI.cind.getPosicaoMercurio();
        this.pVenus = GUI.cind.getPosicaoVenus();
        this.pTerra = GUI.cind.getPosicaoTerra();
        this.pMarte = GUI.cind.getPosicaoMarte();
        this.saltoMercurioX = this.saltoMercurioZ = 0.0f;
        this.saltoVenusX = this.saltoVenusZ = 0.0f;
        this.saltoTerraX = this.saltoTerraZ = 0.0f;
        this.saltoMarteX = this.saltoMarteZ = 0.0f;
        
        this.showMercurio = this.showVenus = this.showTerra = this.showMarte = true;
        
        rotacaoSol = 0.0f;
    }
    
    public void bigBang(GL gl) {
        
    }
    
    public void criarUniverso(GL gl) {
        //Layer espacial 0
        txtrSL0.enable();
        txtrSL0.bind();
        gl.glBegin(GL.GL_POLYGON);
            gl.glTexCoord2f(0.0f, 0.0f);
            gl.glVertex3f(-24.0f, -3.0f, 24.0f);
            gl.glTexCoord2f(1.0f, 0.0f);
            gl.glVertex3f(-24.0f, -3.0f, -88.0f);
            gl.glTexCoord2f(1.0f, 1.0f);
            gl.glVertex3f(88.0f, -3.0f, -88.0f);
            gl.glTexCoord2f(0.0f, 1.0f);
            gl.glVertex3f(88.0f, -3.0f, 24.0f);
        gl.glEnd();
        txtrSL0.disable();

        //Layer espacial 1.1
        txtrSL1.enable();
        txtrSL1.bind();
        gl.glBegin(GL.GL_POLYGON);
            gl.glTexCoord2f(0.0f, 0.0f);
            gl.glVertex3f(-16.0f, -2.0f, 16.0f);
            gl.glTexCoord2f(1.0f, 0.0f);
            gl.glVertex3f(-16.0f, -2.0f, -80.0f);
            gl.glTexCoord2f(1.0f, 1.0f);
            gl.glVertex3f(80.0f, -2.0f, -80.0f);
            gl.glTexCoord2f(0.0f, 1.0f);
            gl.glVertex3f(80.0f, -2.0f, 16.0f);
        gl.glEnd();
        //Layer espacial 1.2
        gl.glBegin(GL.GL_POLYGON);
            gl.glTexCoord2f(0.0f, 0.0f);
            gl.glVertex3f(-16.0f, -2.0f, -80.0f);
            gl.glTexCoord2f(1.0f, 0.0f);
            gl.glVertex3f(-16.0f, 94.0f, -80.0f);
            gl.glTexCoord2f(1.0f, 1.0f);
            gl.glVertex3f(80.0f, 94.0f, -80.0f);
            gl.glTexCoord2f(0.0f, 1.0f);
            gl.glVertex3f(80.0f, -2.0f, -80.0f);
        gl.glEnd();
        //Layer espacial 1.3
        gl.glBegin(GL.GL_POLYGON);
            gl.glTexCoord2f(0.0f, 0.0f);
            gl.glVertex3f(80.0f, -2.0f, 16.0f);
            gl.glTexCoord2f(1.0f, 0.0f);
            gl.glVertex3f(80.0f, 94.0f, 16.0f);
            gl.glTexCoord2f(1.0f, 1.0f);
            gl.glVertex3f(80.0f, 94.0f, -80.0f);
            gl.glTexCoord2f(0.0f, 1.0f);
            gl.glVertex3f(80.0f, -2.0f, -80.0f);
        gl.glEnd();
        //Layer espacial 1.4
        gl.glBegin(GL.GL_POLYGON);
            gl.glTexCoord2f(0.0f, 0.0f);
            gl.glVertex3f(-16.0f, -2.0f, 16.0f);
            gl.glTexCoord2f(1.0f, 0.0f);
            gl.glVertex3f(-16.0f, 94.0f, 16.0f);
            gl.glTexCoord2f(1.0f, 1.0f);
            gl.glVertex3f(80.0f, 94.0f, 16.0f);
            gl.glTexCoord2f(0.0f, 1.0f);
            gl.glVertex3f(80.0f, -2.0f, 16.0f);
        gl.glEnd();
        //Layer espacial 1.5
        gl.glBegin(GL.GL_POLYGON);
            gl.glTexCoord2f(0.0f, 0.0f);
            gl.glVertex3f(-16.0f, -2.0f, 16.0f);
            gl.glTexCoord2f(1.0f, 0.0f);
            gl.glVertex3f(-16.0f, -2.0f, -80.0f);
            gl.glTexCoord2f(1.0f, 1.0f);
            gl.glVertex3f(-16.0f, 94.0f, -80.0f);
            gl.glTexCoord2f(0.0f, 1.0f);
            gl.glVertex3f(-16.0f, 94.0f, 16.0f);
        gl.glEnd();
        //Layer espacial 1.6
        gl.glBegin(GL.GL_POLYGON);
            gl.glTexCoord2f(0.0f, 0.0f);
            gl.glVertex3f(-16.0f, 94.0f, 16.0f);
            gl.glTexCoord2f(1.0f, 0.0f);
            gl.glVertex3f(-16.0f, 94.0f, -80.0f);
            gl.glTexCoord2f(1.0f, 1.0f);
            gl.glVertex3f(80.0f, 94.0f, -80.0f);
            gl.glTexCoord2f(0.0f, 1.0f);
            gl.glVertex3f(80.0f, 94.0f, 16.0f);
        gl.glEnd();
        txtrSL1.disable();
        
        //Layer espacial 2
        txtrSL2.enable();
        txtrSL2.bind();
        gl.glBegin(GL.GL_POLYGON);
            gl.glTexCoord2f(0.0f, 0.0f);
            gl.glVertex3f(-8.0f, -1.0f, 8.0f);
            gl.glTexCoord2f(1.0f, 0.0f);
            gl.glVertex3f(-8.0f, -1.0f, -72.0f);
            gl.glTexCoord2f(1.0f, 1.0f);
            gl.glVertex3f(72.0f, -1.0f, -72.0f);
            gl.glTexCoord2f(0.0f, 1.0f);
            gl.glVertex3f(72.0f, -1.0f, 8.0f);
        gl.glEnd();
        txtrSL2.disable();
    }
    
    public void criarSol(GL gl, GLU glu) {
        Integer[] p = GUI.cind.getPosicaoSol();

        gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, rgba, 0);
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, rgba, 0);
        gl.glMaterialf(GL.GL_FRONT, GL.GL_SHININESS, 0.5f);

        txtrSol.enable();
        txtrSol.bind();

        gl.glPushMatrix();
            gl.glTranslatef(p[0] * 2.0f, 1.0f, p[1] * -2.0f);
            gl.glRotatef(rotacaoSol, 0.0f, 1.0f, 0.0f);
            gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
            GLUquadric sol = glu.gluNewQuadric();
            glu.gluQuadricTexture(sol, true);
            glu.gluQuadricDrawStyle(sol, GLU.GLU_FILL);
            glu.gluQuadricNormals(sol, GLU.GLU_FLAT);
            glu.gluQuadricOrientation(sol, GLU.GLU_OUTSIDE);
            glu.gluSphere(sol, 2.0f, 16, 16);
            glu.gluDeleteQuadric(sol);
        gl.glPopMatrix();
        
        txtrSol.disable();
    }
    
    public void criarMercurio(GL gl, GLU glu) {
        if(showMercurio) {
            float x = (pMercurio[1] + saltoMercurioX) * 2.0f + 1.0f;
            float z = (pMercurio[2] + saltoMercurioZ) * -2.0f - 1.0f;

            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, rgba, 0);
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, rgba, 0);
            gl.glMaterialf(GL.GL_FRONT, GL.GL_SHININESS, 0.5f);

            txtrMercurio.enable();
            txtrMercurio.bind();

            gl.glPushMatrix();
                gl.glTranslatef(x, 1.0f, z);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GLUquadric mercurio = glu.gluNewQuadric();
                glu.gluQuadricTexture(mercurio, true);
                glu.gluQuadricDrawStyle(mercurio, GLU.GLU_FILL);
                glu.gluQuadricNormals(mercurio, GLU.GLU_FLAT);
                glu.gluQuadricOrientation(mercurio, GLU.GLU_OUTSIDE);
                glu.gluSphere(mercurio, 1.0f, 16, 16);
                glu.gluDeleteQuadric(mercurio);
            gl.glPopMatrix();

            txtrMercurio.disable();
        }
    }
    
    public void criarVenus(GL gl, GLU glu) {
        if(showVenus) {
            float x = (pVenus[1] + saltoVenusX) * 2.0f + 1.0f;
            float z = (pVenus[2] + saltoVenusZ) * -2.0f - 1.0f;

            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, rgba, 0);
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, rgba, 0);
            gl.glMaterialf(GL.GL_FRONT, GL.GL_SHININESS, 0.5f);

            txtrVenus.enable();
            txtrVenus.bind();

            gl.glPushMatrix();
                gl.glTranslatef(x, 1.0f, z);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GLUquadric venus = glu.gluNewQuadric();
                glu.gluQuadricTexture(venus, true);
                glu.gluQuadricDrawStyle(venus, GLU.GLU_FILL);
                glu.gluQuadricNormals(venus, GLU.GLU_FLAT);
                glu.gluQuadricOrientation(venus, GLU.GLU_OUTSIDE);
                glu.gluSphere(venus, 1.0f, 16, 16);
                glu.gluDeleteQuadric(venus);
            gl.glPopMatrix();

            txtrVenus.disable();
        }
    }
    
    public void criarTerra(GL gl, GLU glu) {
        if(showTerra) {
            float x = (pTerra[1] + saltoTerraX) * 2.0f + 1.0f;
            float z = (pTerra[2] + saltoTerraZ) * -2.0f - 1.0f;

            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, rgba, 0);
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, rgba, 0);
            gl.glMaterialf(GL.GL_FRONT, GL.GL_SHININESS, 0.5f);

            txtrTerra.enable();
            txtrTerra.bind();

            gl.glPushMatrix();
                gl.glTranslatef(x, 1.0f, z);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GLUquadric terra = glu.gluNewQuadric();
                glu.gluQuadricTexture(terra, true);
                glu.gluQuadricDrawStyle(terra, GLU.GLU_FILL);
                glu.gluQuadricNormals(terra, GLU.GLU_FLAT);
                glu.gluQuadricOrientation(terra, GLU.GLU_OUTSIDE);
                glu.gluSphere(terra, 1.0f, 16, 16);
                glu.gluDeleteQuadric(terra);
            gl.glPopMatrix();

        txtrTerra.disable();
        }
    }
    
    public void criarMarte(GL gl, GLU glu) {
        if(showMarte) {
            float x = (pMarte[1] + saltoMarteX) * 2.0f + 1.0f;
            float z = (pMarte[2] + saltoMarteZ) * -2.0f - 1.0f;

            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, rgba, 0);
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, rgba, 0);
            gl.glMaterialf(GL.GL_FRONT, GL.GL_SHININESS, 0.5f);

            txtrMarte.enable();
            txtrMarte.bind();

            gl.glPushMatrix();
                gl.glTranslatef(x, 1.0f, z);
                gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GLUquadric marte = glu.gluNewQuadric();
                glu.gluQuadricTexture(marte, true);
                glu.gluQuadricDrawStyle(marte, GLU.GLU_FILL);
                glu.gluQuadricNormals(marte, GLU.GLU_FLAT);
                glu.gluQuadricOrientation(marte, GLU.GLU_OUTSIDE);
                glu.gluSphere(marte, 1.0f, 16, 16);
                glu.gluDeleteQuadric(marte);
            gl.glPopMatrix();

            txtrMarte.disable();
        }
    }
    
    public void iniciarRotacao() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    while(true) {
                        Thread.sleep(50);
                        Integer[] ppMercurio = GUI.cind.getProximaPosicaoMercurio();
                        saltoMercurioX = (ppMercurio[1] - pMercurio[1]) / 10.0f;
                        saltoMercurioZ = (ppMercurio[2] - pMercurio[2]) / 10.0f;
                        Integer[] ppVenus = GUI.cind.getProximaPosicaoVenus();
                        saltoVenusX = (ppVenus[1] - pVenus[1]) / 10.0f;
                        saltoVenusZ = (ppVenus[2] - pVenus[2]) / 10.0f;
                        Integer[] ppTerra = GUI.cind.getProximaPosicaoTerra();
                        saltoTerraX = (ppTerra[1] - pTerra[1]) / 10.0f;
                        saltoTerraZ = (ppTerra[2] - pTerra[2]) / 10.0f;
                        Integer[] ppMarte = GUI.cind.getProximaPosicaoMarte();
                        saltoMarteX = (ppMarte[1] - pMarte[1]) / 10.0f;
                        saltoMarteZ = (ppMarte[2] - pMarte[2]) / 10.0f;
                        rotacaoSol += 9.0f;
                        Thread.sleep(50);
                        saltoMercurioX += (ppMercurio[1] - pMercurio[1]) / 10.0f;
                        saltoMercurioZ += (ppMercurio[2] - pMercurio[2]) / 10.0f;
                        saltoVenusX += (ppVenus[1] - pVenus[1]) / 10.0f;
                        saltoVenusZ += (ppVenus[2] - pVenus[2]) / 10.0f;
                        saltoTerraX += (ppTerra[1] - pTerra[1]) / 10.0f;
                        saltoTerraZ += (ppTerra[2] - pTerra[2]) / 10.0f;
                        saltoMarteX += (ppMarte[1] - pMarte[1]) / 10.0f;
                        saltoMarteZ += (ppMarte[2] - pMarte[2]) / 10.0f;
                        rotacaoSol += 9.0f;
                        Thread.sleep(50);
                        saltoMercurioX += (ppMercurio[1] - pMercurio[1]) / 10.0f;
                        saltoMercurioZ += (ppMercurio[2] - pMercurio[2]) / 10.0f;
                        saltoVenusX += (ppVenus[1] - pVenus[1]) / 10.0f;
                        saltoVenusZ += (ppVenus[2] - pVenus[2]) / 10.0f;
                        saltoTerraX += (ppTerra[1] - pTerra[1]) / 10.0f;
                        saltoTerraZ += (ppTerra[2] - pTerra[2]) / 10.0f;
                        saltoMarteX += (ppMarte[1] - pMarte[1]) / 10.0f;
                        saltoMarteZ += (ppMarte[2] - pMarte[2]) / 10.0f;
                        rotacaoSol += 9.0f;
                        Thread.sleep(50);
                        saltoMercurioX += (ppMercurio[1] - pMercurio[1]) / 10.0f;
                        saltoMercurioZ += (ppMercurio[2] - pMercurio[2]) / 10.0f;
                        saltoVenusX += (ppVenus[1] - pVenus[1]) / 10.0f;
                        saltoVenusZ += (ppVenus[2] - pVenus[2]) / 10.0f;
                        saltoTerraX += (ppTerra[1] - pTerra[1]) / 10.0f;
                        saltoTerraZ += (ppTerra[2] - pTerra[2]) / 10.0f;
                        saltoMarteX += (ppMarte[1] - pMarte[1]) / 10.0f;
                        saltoMarteZ += (ppMarte[2] - pMarte[2]) / 10.0f;
                        rotacaoSol += 9.0f;
                        Thread.sleep(50);
                        saltoMercurioX += (ppMercurio[1] - pMercurio[1]) / 10.0f;
                        saltoMercurioZ += (ppMercurio[2] - pMercurio[2]) / 10.0f;
                        saltoVenusX += (ppVenus[1] - pVenus[1]) / 10.0f;
                        saltoVenusZ += (ppVenus[2] - pVenus[2]) / 10.0f;
                        saltoTerraX += (ppTerra[1] - pTerra[1]) / 10.0f;
                        saltoTerraZ += (ppTerra[2] - pTerra[2]) / 10.0f;
                        saltoMarteX += (ppMarte[1] - pMarte[1]) / 10.0f;
                        saltoMarteZ += (ppMarte[2] - pMarte[2]) / 10.0f;
                        rotacaoSol += 9.0f;
                        Thread.sleep(50);
                        saltoMercurioX += (ppMercurio[1] - pMercurio[1]) / 10.0f;
                        saltoMercurioZ += (ppMercurio[2] - pMercurio[2]) / 10.0f;
                        saltoVenusX += (ppVenus[1] - pVenus[1]) / 10.0f;
                        saltoVenusZ += (ppVenus[2] - pVenus[2]) / 10.0f;
                        saltoTerraX += (ppTerra[1] - pTerra[1]) / 10.0f;
                        saltoTerraZ += (ppTerra[2] - pTerra[2]) / 10.0f;
                        saltoMarteX += (ppMarte[1] - pMarte[1]) / 10.0f;
                        saltoMarteZ += (ppMarte[2] - pMarte[2]) / 10.0f;
                        rotacaoSol += 9.0f;
                        Thread.sleep(50);
                        saltoMercurioX += (ppMercurio[1] - pMercurio[1]) / 10.0f;
                        saltoMercurioZ += (ppMercurio[2] - pMercurio[2]) / 10.0f;
                        saltoVenusX += (ppVenus[1] - pVenus[1]) / 10.0f;
                        saltoVenusZ += (ppVenus[2] - pVenus[2]) / 10.0f;
                        saltoTerraX += (ppTerra[1] - pTerra[1]) / 10.0f;
                        saltoTerraZ += (ppTerra[2] - pTerra[2]) / 10.0f;
                        saltoMarteX += (ppMarte[1] - pMarte[1]) / 10.0f;
                        saltoMarteZ += (ppMarte[2] - pMarte[2]) / 10.0f;
                        rotacaoSol += 9.0f;
                        Thread.sleep(50);
                        saltoMercurioX += (ppMercurio[1] - pMercurio[1]) / 10.0f;
                        saltoMercurioZ += (ppMercurio[2] - pMercurio[2]) / 10.0f;
                        saltoVenusX += (ppVenus[1] - pVenus[1]) / 10.0f;
                        saltoVenusZ += (ppVenus[2] - pVenus[2]) / 10.0f;
                        saltoTerraX += (ppTerra[1] - pTerra[1]) / 10.0f;
                        saltoTerraZ += (ppTerra[2] - pTerra[2]) / 10.0f;
                        saltoMarteX += (ppMarte[1] - pMarte[1]) / 10.0f;
                        saltoMarteZ += (ppMarte[2] - pMarte[2]) / 10.0f;
                        rotacaoSol += 9.0f;
                        Thread.sleep(50);
                        saltoMercurioX += (ppMercurio[1] - pMercurio[1]) / 10.0f;
                        saltoMercurioZ += (ppMercurio[2] - pMercurio[2]) / 10.0f;
                        saltoVenusX += (ppVenus[1] - pVenus[1]) / 10.0f;
                        saltoVenusZ += (ppVenus[2] - pVenus[2]) / 10.0f;
                        saltoTerraX += (ppTerra[1] - pTerra[1]) / 10.0f;
                        saltoTerraZ += (ppTerra[2] - pTerra[2]) / 10.0f;
                        saltoMarteX += (ppMarte[1] - pMarte[1]) / 10.0f;
                        saltoMarteZ += (ppMarte[2] - pMarte[2]) / 10.0f;
                        rotacaoSol += 9.0f;
                        Thread.sleep(50);
                        saltoMercurioX = saltoMercurioZ = 0.0f;
                        GUI.cind.rotacionarMercurio();
                        pMercurio = GUI.cind.getPosicaoMercurio();
                        saltoVenusX = saltoVenusZ = 0.0f;
                        GUI.cind.rotacionarVenus();
                        pVenus = GUI.cind.getPosicaoVenus();
                        saltoTerraX = saltoTerraZ = 0.0f;
                        GUI.cind.rotacionarTerra();
                        pTerra = GUI.cind.getPosicaoTerra();
                        saltoMarteX = saltoMarteZ = 0.0f;
                        GUI.cind.rotacionarMarte();
                        pMarte = GUI.cind.getPosicaoMarte();
                        rotacaoSol += 9.0f;
                        
                        if(rotacaoSol >= 360.0f)
                            rotacaoSol = 0.0f;
                    } 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    
    public void hajaLuz(GL gl) {
        float[] lightPos = {28, 1.0f, -28, 1};
        float[] lightColorSpecular = {0.8f, 0.8f, 0.8f, 1.0f};
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, lightPos, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_SPECULAR, lightColorSpecular, 0);
        
        lightPos = new float[]{28, 1.0f, -36, 1};
        gl.glLightfv(GL.GL_LIGHT1, GL.GL_POSITION, lightPos, 0);
        gl.glLightfv(GL.GL_LIGHT1, GL.GL_SPECULAR, lightColorSpecular, 0);
        
        lightPos = new float[]{36, 1.0f, -36, 1};
        gl.glLightfv(GL.GL_LIGHT2, GL.GL_POSITION, lightPos, 0);
        gl.glLightfv(GL.GL_LIGHT2, GL.GL_SPECULAR, lightColorSpecular, 0);
        
        lightPos = new float[]{36, 1.0f, -28, 1};
        gl.glLightfv(GL.GL_LIGHT3, GL.GL_POSITION, lightPos, 0);
        gl.glLightfv(GL.GL_LIGHT3, GL.GL_SPECULAR, lightColorSpecular, 0);
        
        lightPos = new float[]{32, 5.0f, -32, 1};
        gl.glLightfv(GL.GL_LIGHT4, GL.GL_POSITION, lightPos, 0);
        gl.glLightfv(GL.GL_LIGHT4, GL.GL_SPECULAR, lightColorSpecular, 0);
        
        lightPos = new float[]{32, -3.0f, -32, 1};
        gl.glLightfv(GL.GL_LIGHT5, GL.GL_POSITION, lightPos, 0);
        gl.glLightfv(GL.GL_LIGHT5, GL.GL_SPECULAR, lightColorSpecular, 0);

        gl.glEnable(GL.GL_LIGHTING);
        gl.glEnable(GL.GL_LIGHT0);
        gl.glEnable(GL.GL_LIGHT1);
        gl.glEnable(GL.GL_LIGHT2);
        gl.glEnable(GL.GL_LIGHT3);
        gl.glEnable(GL.GL_LIGHT4);
        gl.glEnable(GL.GL_LIGHT5);
    }
}
