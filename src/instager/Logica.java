package instager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.regex.Pattern;

import processing.core.*;

public class Logica {

	private PApplet app;
	private PImage fondo, interfaz, tools, botonAs, botonAn, botonDs, botonDn;
	private Imagen img;
	private boolean full, asc, des = true;
	private ArrayList<Imagen> fotos;
	private ListIterator<Imagen> iter;
	private int pos;

	public Logica(PApplet app) {
		this.app = app;
		fotos = new ArrayList<Imagen>();
		fondo = app.loadImage("Fondo.png");
		interfaz = app.loadImage("InterfazInstager.png");
		tools = app.loadImage("tools.png");
		botonAs = app.loadImage("botonElegidoA.png");
		botonAn = app.loadImage("botonIlegidoA.png");
		botonDn = app.loadImage("botonIlegidoD.png");
		botonDs = app.loadImage("botonElegidoD.png");
		crearImagenes();
		iter = fotos.listIterator();
		img = fotos.get(pos);
		pos = 0;
		img.lugar(500, 258);
	}

	public void ejecutar() {
		img = fotos.get(pos);
		app.imageMode(PApplet.CENTER);
		if (full == false)
			app.image(fondo, app.width / 2, app.height / 2);

		img.rotar();

		if (full == false) {
			app.image(interfaz, app.width / 2, app.height / 2);
			if (asc == true) {
				app.image(botonAs, 853, 526);
			} else {
				app.image(botonAn, 853, 526);
			}
			
			if(des==true){
				app.image(botonDs, 944, 526);
			} else {
				app.image(botonDn, 944, 526);
			}

			if (pos - 2 > -1) {
				fotos.get(pos - 2).pintarBarra(84, 615);
			}

			if (pos - 1 > -1) {
				fotos.get(pos - 1).pintarBarra(275, 615);
			}

			fotos.get(pos).pintarBarra(500, 615);

			if (pos + 1 < fotos.size()) {
				fotos.get(pos + 1).pintarBarra(725, 615);
			}

			if (pos + 2 < fotos.size()) {
				fotos.get(pos + 2).pintarBarra(916, 615);
			}
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
			if (pos - 1 > -1) {
				pos--;
				img.lugar(500, 258);
			}
		} else if (zonaMouse(853, 934, 228, 300)) {
			if (pos + 1 < fotos.size()) {
				pos++;
				img.lugar(500, 258);
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
