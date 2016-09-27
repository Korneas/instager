package instager;
import processing.core.*;

public class Main extends PApplet {
	
	private Logica app;
	
	static public void main(String[] args) {
		PApplet.main("instager.Main");
	}

	@Override
	public void settings() {
		size(1000, 700);
	}

	@Override
	public void setup() {
		app = new Logica(this);

	}

	@Override
	public void draw() {
		background(0);
		app.ejecutar();
	}
	
	@Override
	public void mouseClicked(){
		app.click();
	}
}
