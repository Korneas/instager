package instager;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

import processing.core.*;

public class Logica {

	//Creacion de elementos necesarios
	private PApplet app;
	private PImage fondo, interfaz, tools, botonAs, botonAn, botonDs, botonDn;
	private Imagen img;
	private boolean full, asc, des = true;
	private ArrayList<Imagen> fotos;
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
		img = fotos.get(pos);
		pos = 0;
		img.lugar(500, 258);
	}

	//Metodo para ejecutar el código
	public void ejecutar() {
		
		//Imagen principal se actualiza constantemente
		img = fotos.get(pos);
		
		//Modo de imagen con punto de alineación en el centro
		app.imageMode(PApplet.CENTER);
		
		//Si no esta en pantalla completa, entonces muestre la interfaz
		if (full == false)
			app.image(fondo, app.width / 2, app.height / 2);

		//IMAGEN PRINCIPAL
		img.rotar();

		//Si no esta en pantalla completa, entonces muestre la interfaz
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

		//Herramientas de edicion
		app.image(tools, app.width / 2, app.height / 2);
	}

	public void click() {

		//Zona sensible de reducción
		if (zonaSensible(0)) {
			full = false;
			img.lugar(500, 258);
			img.devolver();
		}
		
		//Zona sensible fullScreen
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
		
		//Zona sensible Zoom in
		if (zonaSensible(2)) {
			if (img.getEscalaX() < 950) {
				int escalaX = img.getEscalaX();
				int escalaY = img.getEscalaY();
				img.escalar(escalaX + (int) (escalaX * 0.1), escalaY + (int) (escalaY * 0.1));
			}
		}
		
		//Zona sensible Zoom out
		if (zonaSensible(3)) {

			if (img.getEscalaX() > 200) {
				int escalaX = img.getEscalaX();
				int escalaY = img.getEscalaY();
				img.escalar(escalaX - (int) (escalaX * 0.1), escalaY - (int) (escalaY * 0.1));
			}
		}
		
		//Zona sensible girar derecha
		if (zonaSensible(4)) {
			img.setAngulo(img.getAngulo() + 90);
		}
		
		//Zona sensible girar izquierda
		if (zonaSensible(5)) {
			img.setAngulo(img.getAngulo() - 90);
		}

		//Boton para organizar por nombre del archivo dependiendo de si el boton esta ascendente o descendetne
		if (zonaMouse(24, 90, 507, 545)) {
			if (asc == true) {
				Collections.sort(fotos, new ComparadorNombreAsc());
			} else if (des == true) {
				Collections.sort(fotos, new ComparadorNombreDes());
			}
		}

		//Boton para organizar por tipo del archivo dependiendo de si el boton esta ascendente o descendetne
		if (zonaMouse(114, 180, 507, 545)) {
			if (asc == true) {
				Collections.sort(fotos, new ComparadorTipoAsc());
			} else if (des == true) {
				Collections.sort(fotos, new ComparadorTipoDes());
			}
		}

		//Boton para organizar por tamaño horizontal del archivo dependiendo de si el boton esta ascendente o descendetne
		if (zonaMouse(205, 271, 507, 545)) {
			if (asc == true) {
				Collections.sort(fotos, new ComparadorSizeAsc());
			} else if (des == true) {
				Collections.sort(fotos, new ComparadorSizeDes());
			}
		}

		//Boton ascendente
		if (zonaMouse(820, 886, 507, 545)) {
			asc = true;
			des = false;
		}

		//Boton descendente
		if (zonaMouse(911, 977, 507, 545)) {
			des = true;
			asc = false;
		}

		// Zona de cambio de imagenes, izquierda - derecha
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
	
	
	//Metodo que solicita un valor para crear una zona sensible, utilizada para las herramientas
	public boolean zonaSensible(int i) {
		if (app.mouseX > (i * 92) + 225 && app.mouseX < ((i + 1) * 92) + (225) && app.mouseY > 0 && app.mouseY < 52) {
			return true;
		}
		return false;
	}

	//Metodo para crear una zona sensible con el mouse
	public boolean zonaMouse(int x1, int x2, int y1, int y2) {
		if (app.mouseX > x1 && app.mouseX < x2 && app.mouseY > y1 && app.mouseY < y2) {
			return true;
		}
		return false;
	}
	

	//Metodo para crear las imagenes con un for basado en el contenido de la carpeta "imagenes", utilizando el
	//metodo agregarImagen
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

	//Metodo para crear un nuevo Object Imagen con caracteristicas de nombre, tipo e imagen
	public void agregarImagen(File files) {

		String nombre = files.getName();
		String[] arregloTipo = nombre.split(Pattern.quote("."));
		String tipo = arregloTipo[1];
		PImage img = app.loadImage("./data/imagenes/" + nombre);

		Imagen insta = new Imagen(app, nombre, tipo, img);

		fotos.add(insta);
	}
}
