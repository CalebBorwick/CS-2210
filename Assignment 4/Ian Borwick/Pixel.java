public class Pixel {

	private Location _location;
	private int _color;

	public Pixel(Location p, int color) {
		this._location = p;
		this._color = color;
	}

	public Location getLocation() {
		return this._location;
	}

	public int getColor() {
		return this._color;
	}
}