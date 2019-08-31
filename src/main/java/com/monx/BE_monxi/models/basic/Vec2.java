package com.monx.BE_monxi.models.basic;

public class Vec2<T> {
	public T x;
	public T y;

	public Vec2() {
	}

	public Vec2(T x, T y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int hashCode() {
		int result = 3;
		result = result * 13 + x.hashCode();
		result = result * 13 + y.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		Vec2 o = (Vec2) obj;
		if (this.x.getClass() != o.x.getClass()) {
			return false;
		}
		Vec2<T> ot = (Vec2<T>) obj;
		return ot.x == x && ot.y == y;
	}

	@Override
	public String toString() {
		return "Vec2: { x: " + x.toString() + ", y:" + y.toString() + "}";
	}
}
