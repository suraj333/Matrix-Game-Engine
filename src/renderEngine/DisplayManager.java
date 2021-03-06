package renderEngine;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.PixelFormat;

public class DisplayManager {
	private static final int WIDTH =1280;
	private static final int HEIGHT =720;
	private static final int FPS_CAP = 120;
	private static long lastFrameTime ;
	private static float delta;
	
	
	public static void createDisplay(){
		
		ContextAttribs attribs = new ContextAttribs(3,3).withForwardCompatible(true).withProfileCore(true);
		
		
		
		try {
			
			Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT) );
			//Display.setFullscreen(true);
			Display.create(new PixelFormat().withSamples(4),attribs);
			//Display.setTitle("Java Game");
			GL11.glEnable(GL13.GL_MULTISAMPLE);
			
			
		
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		GL11.glViewport(0, 0, WIDTH, HEIGHT);
		lastFrameTime = getTime();
		
		
	}
	
	public static void updateDisplay(){
		Display.sync(FPS_CAP);
		Display.update();
		
		long currentFrameTime = getTime();
		delta = (currentFrameTime - lastFrameTime)/1000f;
		lastFrameTime = currentFrameTime;
		
	}
	
	
	
	public static float getFrameTimeSeconds() {
		return delta;
	}
	
	public static void closeDisplay(){
		Display.destroy();
	}

	public static long getTime() {
	    return System.nanoTime() / 1000000; //millis
	}
	/*
	public static long getTime() {
		return (Sys.getTime()*1000)/Sys.getTimerResolution();
	}
*/
	

}
