package com.framework.sensor;

public class Position {

	private float X;
	private float Y;
	
	public Position(){
		X=0;
		Y=0;
	}
	public Position(float X ,float Y){
		this.X=X;
		this.Y=Y;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(X);
		result = prime * result + Float.floatToIntBits(Y);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (Float.floatToIntBits(X) != Float.floatToIntBits(other.X))
			return false;
		if (Float.floatToIntBits(Y) != Float.floatToIntBits(other.Y))
			return false;
		return true;
	}
	public float getX() {
		return X;
	}
	public void setX(float x) {
		X = x;
	}
	public float getY() {
		return Y;
	}
	public void setY(float y) {
		Y = y;
	}
}
