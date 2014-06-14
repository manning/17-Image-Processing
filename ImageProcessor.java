import java.awt.*;
import acm.program.*;
import acm.util.*;
import acm.graphics.*;

public class ImageProcessor extends GraphicsProgram {
	/* Size of the window. */
	public static final int APPLICATION_HEIGHT = 700;
	public static final int APPLICATION_WIDTH  = 750;
	
	/**
	 * Creates and returns a blue/green spectrum showing off all colors that
	 * can be made from pure blue and green.
	 * 
	 * @return An image representing a blue/green spectrum.
	 */
	private GImage makeSpectrum() {
		/* Create an array of pixels that will hold the spectrum. */
		int[][] pixels = new int[256][256];
		
		/* For each pixel, set its green component to its row and its
		 * blue component to its columns.
		 */
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < pixels[row].length; col++) {
				pixels[row][col] = GImage.createRGBPixel(0, row, col);
			}
		}
		
		return new GImage(pixels);
	}
	
	/**
	 * Given a GImage, returns a new GImage with the same colors, but with the green
	 * channel disabled.
	 * 
	 * @param image The image to process.
	 * @return A new image with the same colors, except with all green removed.
	 */
	private GImage removeGreen(GImage image) {
		/* Get the underlying pixel array. */
		int[][] pixels = image.getPixelArray();
		
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < pixels[row].length; col++) {
				/* Read back the red and blue components of this pixel. */
				int red   = GImage.getRed(pixels[row][col]);
				int blue  = GImage.getBlue(pixels[row][col]);
				
				/* Write them back, explicitly setting the green to 0. */
				pixels[row][col] = GImage.createRGBPixel(red, 0, blue);
			}
		}
		
		return new GImage(pixels);
	}
	
	/**
	 * Given an image, cycles the colors in that image by rotating through red,
	 * green, and blue.
	 * 
	 * @param image The source image.
	 * @return That image with the color channels cycled.
	 */
	private GImage psychedelify(GImage image) {
		int[][] pixels = image.getPixelArray();
		
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < pixels[row].length; col++) {
				int red   = GImage.getRed(pixels[row][col]);
				int green = GImage.getGreen(pixels[row][col]);
				int blue  = GImage.getBlue(pixels[row][col]);
				
				/* Write back the color channels, but slightly cycled. */
				pixels[row][col] = GImage.createRGBPixel(green, blue, red);
			}
		}
		
		return new GImage(pixels);
	}
	
	public void run() {
		
		add(makeSpectrum());
		waitForClick();
		add(new GImage("rainbow-dash.png"));
		waitForClick();
		add(removeGreen(new GImage("rainbow-dash.png")));
		waitForClick();	
		add(new GImage("rainbow-dash.png"));
		waitForClick();
		add(psychedelify(new GImage("rainbow-dash.png")));
		waitForClick();
		add(psychedelify(psychedelify(new GImage("rainbow-dash.png"))));
		waitForClick();
		add(psychedelify(psychedelify(psychedelify(new GImage("rainbow-dash.png")))));

	}
}
