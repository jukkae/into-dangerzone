package scenes.attractors;

import math.Vector2D;

public class Attractor {

	Vector2D location;
	float g;

	Attractor(Vector2D location) {
		this.location = location;
		this.g = 100;
	}

	Attractor(Vector2D location, float g) {
		this.location = location;
		this.g = g;
	}

	Vector2D getForce(Vector2D target) {
		Vector2D delta = target.subtract(location);
		float distance = delta.getLength();
		float theta = delta.getHeading();
		return Vector2D.fromPolar(-g / distance, theta);
	}
}
