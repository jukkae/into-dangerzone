package math;

public class Complex {
	
	private final float x;
	private final float y;
	
	public static Complex add(Complex c1, Complex c2) {
		return new Complex(c1.x + c2.x, c1.y + c2.y);
	}
	
	public static Complex subtract(Complex c1, Complex c2) {
		return new Complex(c1.x - c2.x, c1.y - c2.y);
	}
	
	public static Complex multiply(Complex c1, Complex c2) {
		return new Complex(
				c1.x * c2.x - c1.y * c2.y,
				c1.x * c2.y + c1.y * c2.x
		);
	}
	
	public static Complex multiply(float scalar, Complex c) {
		return new Complex(scalar * c.x, scalar * c.y);
	}
	
	public static Complex fromPolar(float r, float phase) {
		return new Complex( r * (float) Math.cos(phase), r * (float) Math.sin(phase));
	}
	
	public Complex(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getReal() { return this.x; }
	public float getImaginary() { return this.y; }
	
	public float x() { return getReal(); }
	public float y() { return getImaginary(); }
	
	public float getArgument() { return (float) Math.atan2(y, x); }
	public float getPhase() { return getArgument(); }
	
	public float magnitude() { return (float) Math.sqrt(squaredModule()); }
	
	public float squaredModule() {
		return x*x + y*y;
	}
	
	public Complex squared() {
		return multiply(this, this);
	}
	
	public boolean equals(Complex other) {
		return (this.x == other.x) && (this.y == other.y); 
	}

}
