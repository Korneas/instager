package instager;

import java.util.Comparator;
import processing.core.PApplet;
import processing.core.PImage;

public class Imagen implements Comparable<Imagen>{

	private int x, y, escalaX, escalaY, escalaOx, escalaOy, angulo;
	private String nombre,tipo;
	private PApplet app;
	private PImage img;

	public Imagen(PApplet app, String nombre, String tipo, PImage img) {
		this.app = app;
		this.img = img;
		this.nombre=nombre;
		this.tipo=tipo;
		lugar(500,258);
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
	
	public void escalar(int escalaX, int escalaY) {
		this.escalaX = escalaX;
		this.escalaY = escalaY;
	}

	public void devolver() {
		escalaX = escalaOx;
		escalaY = escalaOy;
	}

	public int getEscalaX() {
		return escalaX;
	}

	public int getEscalaY() {
		return escalaY;
	}

	public int getAngulo() {
		return angulo;
	}

	public void setAngulo(int angulo) {
		this.angulo = angulo;
	}

	public PImage getImg() {
		return img;
	}

	public void setImg(PImage img) {
		this.img = img;
	}
	
	public void pintarBarra(int xB,int yB){
		app.imageMode(PApplet.CENTER);
		app.image(img, xB, yB, (int)(img.width*0.25), (int)(img.height*0.25));
		app.fill(255);
		app.text(nombre, xB-50, yB+80);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public int getWidth(){
		return img.width;
	}

	@Override
	public int compareTo(Imagen o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
