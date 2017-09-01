package com.spedia.controller;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.lang.RandomStringUtils;

import com.spedia.utils.SchoolUtil;
public class ImageCaptchaServlet extends HttpServlet {
	 
	 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public void init(ServletConfig servletConfig) throws ServletException {
 
        super.init(servletConfig);
 
    }
 
 
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
    	    String captcha = SchoolUtil.generateRandomString(5);
    	    httpServletRequest.getSession().setAttribute("captcha", captcha );
    	    BufferedImage bufferedImage = new BufferedImage(350, 30, BufferedImage.TYPE_BYTE_INDEXED);

    		Graphics2D graphics = bufferedImage.createGraphics();
    		
    		// Set back ground of the generated image to white
    		graphics.setColor(Color.WHITE);
    		graphics.fillRect(0, 0, 350, 30);

    		// set gradient font of text to be converted to image
    		GradientPaint gradientPaint = new GradientPaint(10, 5, Color.BLUE, 20, 10, Color.LIGHT_GRAY, true);
    		graphics.setPaint(gradientPaint);
    		Font font = new Font("Abril Fatface", Font.BOLD, 30);
    		graphics.setFont(font);

    		// write 'Hello World!' string in the image
    		graphics.drawString(captcha, 5, 22);

    		// release resources used by graphics context
    		graphics.dispose();
    	    httpServletResponse.setHeader("Cache-Control", "no-store");
  	        httpServletResponse.setHeader("Pragma", "no-cache");
  	        httpServletResponse.setDateHeader("Expires", 0);
    	    httpServletResponse.setContentType("image/png");
    	    try {
				OutputStream os = httpServletResponse.getOutputStream();
				ImageIO.write(bufferedImage, "png", os);
				os.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       
    }
}