package com.spedia.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @version 1.0
 */
public class ImageCreator extends HttpServlet {
	private static final long serialVersionUID = -1761346889117186607L;

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// set mime type as jpg image
		response.setContentType("image/jpeg");
		String text=request.getParameter("text");
		ServletOutputStream out = response.getOutputStream();
        System.out.println("creating image for "+text);
		BufferedImage image = new BufferedImage(350, 30, BufferedImage.TYPE_BYTE_INDEXED);

		Graphics2D graphics = image.createGraphics();
		
		// Set back ground of the generated image to white
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, 350, 30);

		// set gradient font of text to be converted to image
		GradientPaint gradientPaint = new GradientPaint(10, 5, Color.BLUE, 20, 10, Color.LIGHT_GRAY, true);
		graphics.setPaint(gradientPaint);
		Font font = new Font("Abril Fatface", Font.BOLD, 12);
		graphics.setFont(font);

		// write 'Hello World!' string in the image
		graphics.drawString(text, 5, 22);

		// release resources used by graphics context
		graphics.dispose();

		// encode the image as a JPEG data stream and write the same to servlet output stream	
		ImageIO.write(image, "jpg",out);

		// close the stream
		out.close();
	}
}
