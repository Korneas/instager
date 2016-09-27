package instager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.regex.Pattern;

import processing.core.*;

public class Logica {

	private PApplet app;
	private PImage fondo, interfaz, tools;
	private Imagen img;
	private boolean full;
	private ArrayList<Imagen> fotos;
	private ListIterator<Imagen> iter;
	private int pos;

	public Logica(PApplet app) {
		this.app = app;
		fotos = new ArrayList<Imagen>();
		fondo = app.loadImage("Fondo.png");
		interfaz = app.loadImage("InterfazInstager.png");
		tools = app.loadImage("tools.png");
		crearImagenes();
		iter = fotos.listIterator();
		img = iter.next();
		pos = iter.nextIndex();
		img.lugar(500, 258);
	}

	public void ejecutar() {
		app.imageMode(PApplet.CENTER);
		if (full == false)
			app.image(fondo, app.width / 2, app.height / 2);
		
		img.rotar();

		if (full == false) {
			app.image(interfaz, app.width / 2, app.height / 2);
		}
		
		if (pos - 3 > 0) {
			fotos.get(pos-3).pintarBarra(84, 615);
		}

		if (pos - 2 > 0 || iter.previousIndex()>0) {
			fotos.get(pos-2).pintarBarra(275, 615);
		}
		
		if (iter.previousIndex()<=fotos.size()) {
			fotos.get(pos-1).pintarBarra(500, 615);
		}

		if (iter.hasNext()) {
			fotos.get(pos).pintarBarra(725, 615);
		}

		if (iter.nextIndex() + 1 < fotos.size()) {
			fotos.get(pos + 1).pintarBarra(916, 615);
		}

		app.image(tools, app.width / 2, app.height / 2);
	}

	public void click() {
		if (zonaSensible(0)) {
			full = false;
			img.lugar(500, 258);
			img.devolver();
		}
		if (zonaSensible(1)) {
			full = true;
			int escalaX = img.getEscalaX();
			int escalaY = img.getEscalaY();
			if (escalaX <= app.width && escalaY < app.height) {
				escalaX += escalaX * 0.65;
				escalaY += escalaY * 0.65;
				img.escalar(escalaX, escalaY);
				img.lugar(500, 350);
			}
		}
		if (zonaSensible(2)) {
			if (img.getEscalaX() < 950) {
				int escalaX = img.getEscalaX();
				int escalaY = img.getEscalaY();
				img.escalar(escalaX + (int) (escalaX * 0.1), escalaY + (int) (escalaY * 0.1));
			}
		}
		if (zonaSensible(3)) {

			if (img.getEscalaX() > 200) {
				int escalaX = img.getEscalaX();
				int escalaY = img.getEscalaY();
				img.escalar(escalaX - (int) (escalaX * 0.1), escalaY - (int) (escalaY * 0.1));
			}
		}
		if (zonaSensible(4)) {
			img.setAngulo(img.getAngulo() + 90);
		}
		if (zonaSensible(5)) {
			img.setAngulo(img.getAngulo() - 90);
		}
		
		// Zona de cambio de imagenes
		if (zonaMouse(68, 148, 228, 300)) {
			if (iter.previousIndex() - 1 > -1) {
				img = fotos.get(iter.previousIndex() - 1);
				img.lugar(500, 258);
				pos--;
				iter.previous();
			}
		} else if (zonaMouse(853, 934, 228, 300)) {
			if (iter.nextIndex() < fotos.size()) {
				img = fotos.get(iter.nextIndex());
				img.lugar(500, 258);
				pos++;
				iter.next();
			}
		}
	}
	
	public boolean zonaSensible(int i) {
		if (app.mouseX > (i * 92) + 225 && app.mouseX < ((i + 1) * 92) + (225) && app.mouseY > 0 && app.mouseY < 52) {
			return true;
		}
		return false;
	}
	
	public boolean zonaMouse(int x1, int x2, int y1, int y2) {
		if (app.mouseX > x1 && app.mouseX < x2 && app.mouseY > y1 && app.mouseY < y2) {
			return true;
		}
		return false;
	}
	
	public void crearImagenes() {

		String directorioImagenes = "./data/imagenes/";
		File dirImg = new File(directorioImagenes);
		
		if (dirImg.exists()) {
			File[] archivosTemp = dirImg.listFiles();
			for (int i = 0; i < archivosTemp.length; i++) {
				agregarImagen(archivosTemp[i]);
			}
		} else {
			System.err.println("No Directory or Files Found on: " + directorioImagenes);
		}

	}

	public void agregarImagen(File files) {

		String nombre = files.getName();
		String[] arregloTipo = nombre.split(Pattern.quote("."));
		String tipo = arregloTipo[1];
		PImage img = app.loadImage("./data/imagenes/" + nombre);

		Imagen insta = new Imagen(app, nombre, tipo, img);

		fotos.add(insta);
	}
}
