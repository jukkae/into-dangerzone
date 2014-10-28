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
	
	public Complex(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getReal() { return this.x; }
	public float getImaginary() { return this.y; }
	
	public float x() { return getReal(); }
	public float y() { return getImaginary(); }
	
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
