package instager;

import processing.core.PApplet;
import processing.core.PImage;

public class Imagen {

	private int x, y, escalaX, escalaY, escalaOx, escalaOy, angulo;
	private String nombre,tipo;
	private PApplet app;
	private PImage img;

	public Imagen(PApplet app, String nombre, String tipo, PImage img) {
		this.app = app;
		this.img = img;
		x = 500;
		y = 350;
		angulo = 0;
		escalaX = img.width;
		escalaY = img.height;
		escalaOx = img.width;
		escalaOy = img.height;
	}

	public void pintar() {
		
		app.imageMode(PApplet.CENTER);
		app.image(img, 0, 0, escalaX, escalaY);
	}

	public void rotar() {
		app.pushMatrix();
		app.translate(x, y);
		app.rotate(PApplet.radians(angulo));
		pintar();
		app.popMatrix();
	}

	public void lugar(int x, int y) {
		this.x = x;
		this.y = y;
	}

	

}
