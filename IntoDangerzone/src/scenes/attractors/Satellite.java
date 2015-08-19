package scenes.attractors;

import math.Vector2D;

public class Satellite {
	private float width;
	private float height;
	
	Vector2D location;
	float speed;
	float theta;
	float mass;
	int age;

	Satellite(float x, float y, float width, float height) {
		this.width = width;
		this.height = height;
		
		this.location = new Vector2D(x, y);
	    speed = (1);
	    theta = (1);
	    mass = 100;
	}

	Vector2D getLocation() {
		return this.location;
	}

	public void update() {
		float x = location.getX();
		float y = location.getY();
	    if (x >= width || x < 0) {
	        float velX = (float) (-1 * speed * Math.cos(theta));
	        float velY = (float) (speed * Math.sin(theta));
	        theta = (float) Math.atan2(velY, velX);
	      }
	      x += speed * Math.cos(theta);

	      if (y >= height || y < 0) {
	        float velX = (float) (speed * Math.cos(theta));
	        float velY = (float) (-1 * speed * Math.sin(theta));
	        theta = (float) Math.atan2(velY, velX);
	      }
	      y += speed * Math.sin(theta);

	      this.location = new Vector2D(x, y);
	}

	public void applyForce(Vector2D f) {
	    float velX = (float) (speed * Math.cos(theta));
	    float velY = (float) (speed * Math.sin(theta));
	    float accX = (float) (f.getLength() * Math.cos(f.getHeading()));
	    float accY = (float) (f.getLength() * Math.sin(f.getHeading()));
	    velX += accX/mass;
	    velY += accY/mass;
	    speed = (float) Math.sqrt(velX*velX + velY*velY);
	    theta = (float) Math.atan2(velY, velX);
	}
}
