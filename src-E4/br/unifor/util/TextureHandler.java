package br.unifor.util;
import java.io.File;

import javax.media.opengl.GL;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;

public class TextureHandler {
	private String texFnm;
	private Texture texture;
	
	public TextureHandler(String fnm)
	{
		loadTexture(fnm);
	}
	
	private void loadTexture(String fnm)
    {
      try {
        texFnm = fnm;
        texture = TextureIO.newTexture( new File(texFnm), false);
        texture.setTexParameteri(GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
        texture.setTexParameteri(GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
      }
      catch(Exception e)
      { System.out.println("Error loading texture " + texFnm); e.printStackTrace(); }
    }  // end of loadTexture()


    public void setTexture(Texture t)
    {  texture = t;  }

    public Texture getTexture()
    {  return texture;  }
	
}