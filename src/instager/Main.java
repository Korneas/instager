package instager;

import processing.core.*;

public class Main extends PApplet {

	static public void main(String[] args) {
		PApplet.main("instager.Main");
	}

	@Override
	public void settings() {
		// TODO Auto-generated method stub
		size(500, 500);
	}

	@Override
	public void setup() {
		colorMode(HSB, 360, 100, 100);

	}

	@Override
	public void draw() {
		background(0);

	}
}