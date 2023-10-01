package com.iflytek.util;

	import java.awt.Graphics2D;
	import java.awt.RenderingHints;
	import java.awt.geom.AffineTransform;
	import java.awt.image.BufferedImage;
	import java.awt.image.ColorModel;
	import java.awt.image.WritableRaster;
	import java.io.File;

	import javax.imageio.ImageIO;

	public class Resize {
	 BufferedImage bufImage;
	 int width;
	 int height;
	 
	 public Resize() {
	  // TODO Auto-generated constructor stub
	 }
	 
	 public Resize(String srcPath,int width,int height) {
	  this.width = width;
	  this.height = height;
	  try{
	   this.bufImage = ImageIO.read(new File(srcPath));
	  }catch(Exception e){
	   e.printStackTrace();
	  }
	 }
	 
	 public static BufferedImage rize(BufferedImage srcBufImage,int width,int height){
	  
	  BufferedImage bufTarget = null;
	  
	  double sx = (double) width / srcBufImage.getWidth();
	  double sy = (double) height / srcBufImage.getHeight();
	  
	  int type = srcBufImage.getType();
	  if(type == BufferedImage.TYPE_CUSTOM){
	   ColorModel cm = srcBufImage.getColorModel();
	   WritableRaster raster = cm.createCompatibleWritableRaster(width,
	     height);
	   boolean alphaPremultiplied = cm.isAlphaPremultiplied();
	   bufTarget = new BufferedImage(cm, raster, alphaPremultiplied, null);
	  }else
	   bufTarget = new BufferedImage(width, height, type);
	  
	  Graphics2D g = bufTarget.createGraphics();
	  g.setRenderingHint(RenderingHints.KEY_RENDERING,
	    RenderingHints.VALUE_RENDER_QUALITY);
	  g.drawRenderedImage(srcBufImage, AffineTransform.getScaleInstance(sx, sy));
	  g.dispose();
	  return bufTarget;
	 }
	 
}
