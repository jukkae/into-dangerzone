package math;
/**
 * Vector3D is an immutable vector in three-dimensional space.
 *
 */
public class Vector3D {
	private float x, y, z;
	private float length;
	
	public Vector3D(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.length = (float) Math.sqrt(x*x + y*y + z*z);
	}

	/**
	 * Return the x-coordinate of this vector.
	 * @return the x-coordinate of this vector
	 */
	public float getX() {
		return x;
	}

	/**
	 * Return the y-coordinate of this vector.
	 * @return the y-coordinate of this vector
	 */
	public float getY() {
		return y;
	}

	/**
	 * Return the z-coordinate of this vector.
	 * @return the z-coordinate of this vector
	 */
	public float getZ() {
		return z;
	}
	
	/**
	 * Normalize this vector and return the normalized version.
	 * @return the normalized version of this vector.
	 */
	public Vector3D normalize() {
		if(length == 0.0) return this;
		return new Vector3D(x / length, y / length, z / length);
	}
	
	/**
	 * Returns the length of this vector.
	 * @return the length of this vector.
	 */
	public float getLength() {
		return length;
	}
	
	/**
	 * Adds given vector to this vector and returns the resulting vector.
	 * @param other the vector to add to this
	 * @return the resulting vector
	 */
	public Vector3D add(Vector3D other) {
		return new Vector3D(x + other.x, y + other.y, z + other.z);
	}
	
	/**
	 * Subtracts given vector from this vector and returns the resulting vector.
	 * @param other the vector to subtract from this
	 * @return the resulting vector
	 */
	public Vector3D subtract(Vector3D other) {
		return new Vector3D(x - other.x, y - other.y, z - other.z);
	}
	
	/**
	 * Divides this vector by given scalar and returns the resulting vector.
	 * @param scalar the scalar to divide this vector by
	 * @return the resulting vector
	 */
	public Vector3D scalarDivision(float scalar) {
		return new Vector3D(x / scalar, y / scalar, z / scalar);
	}
	
	/**
	 * Multiplies this vector by given scalar and returns the resulting vector.
	 * @param scalar the scalar to multiply this vector by
	 * @return the resulting vector
	 */
	public Vector3D scalarMultiplication(float scalar) {
		return new Vector3D(x * scalar, y * scalar, z * scalar);
	}
	
	/**
	 * Calculates the cross product of this vector and given vector and returns 
	 * the resulting vector.
	 * @param other the right-hand side vector for the cross product
	 * @return the resulting vector
	 */
	public Vector3D crossProduct(Vector3D other) {
		return new Vector3D(
				(y*other.z - z*other.y),
				(z*other.x - x*other.z),
				(x*other.y - y*other.x));
	}
	
	/**
	 * Calculates the dot product of this vector and given vector and returns 
	 * the resulting scalar.
	 * @param other the right-hand side vector for the dot product
	 * @return the resulting scalar
	 */
	public float dotProduct(Vector3D other) {
		return (x * other.x) + (y * other.y) + (z * other.z);
	}
	
	/**
	 * Returns a vector that has the same direction as this, but magnitude set 
	 * to a given scalar.
	 * @param length the length of the resulting vector
	 * @return the resulting vector
	 */
	public Vector3D toLength(float length) {
		return this.normalize().scalarMultiplication(length);
	}
	
	/**
	 * Returns a vector that has the same magnitude as this but points towards 
	 * given position.
	 * @param position the position the resulting vector should point towards
	 * @return the resulting vector
	 */
	public Vector3D directedTowards(Vector3D position) {
		Vector3D directed = position.subtract(this);
		if(directed.length <= 0) return new Vector3D(0,0,0);
		
		return position.subtract(this).toLength(this.getLength());
	}
}