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
	private String nombre,tipo;
	private PImage prueba;

	public Logica(PApplet app) {
		this.app = app;
		fondo = app.loadImage("Fondo.png");
		interfaz = app.loadImage("InterfazInstager.png");
		tools = app.loadImage("tools.png");
		nombre = "Entrenador1";
		tipo = "png";
		prueba = app.loadImage("data/imagenes/Entrenador1.png");
		img = new Imagen(app,nombre,tipo,prueba);
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

		app.image(tools, app.width / 2, app.height / 2);
	}

	public void click() {
		
	}
}
