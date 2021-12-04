package entity.base;

import java.awt.Point;

public abstract class Entity {

	protected String name;
	protected String imageUrl;
	protected EntityStatus status;

	protected int maxLP;
	protected int currentLP;
	protected int defaultATK;
	protected int currentATK;

	protected Point currentPos;
	protected Point initialPos;
	protected Direction direction;

	protected int vx;
	protected int vy;

	public Entity() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getMaxLP() {
		return maxLP;
	}

	public void setMaxLP(int maxLP) {
		this.maxLP = maxLP;
	}

	public int getCurrentLP() {
		return currentLP;
	}

	public void setCurrentLP(int currentLP) {
		int val = currentLP;
		if (val > getMaxLP())
			val = getMaxLP();
		else if (val < 0)
			val = 0;
		this.currentLP = val;
	}

	public int getDefaultATK() {
		return defaultATK;
	}

	public void setDefaultATK(int defaultATK) {
		this.defaultATK = defaultATK;
	}

	public int getCurrentATK() {
		return currentATK;
	}

	public void setCurrentATK(int currentATK) {
		int val = currentATK;
		if (val < getCurrentATK())
			val = getDefaultATK();
		this.currentATK = val;
	}

	public int getVx() {
		return vx;
	}

	public void setVx(int vx) {
		this.vx = vx;
	}

	public int getVy() {
		return vy;
	}

	public void setVy(int vy) {
		this.vy = vy;
	}

	public EntityStatus getStatus() {
		return status;
	}

	public void setStatus(EntityStatus status) {
		this.status = status;
	}

	public Point getCurrentPos() {
		return currentPos;
	}

	public void setCurrentPos(Point currentPos) {
		this.currentPos = currentPos;
	}

	public void setCurrentPos(int x, int y) {
		this.currentPos = new Point(x, y);
	}

	public Point getInitialPos() {
		return initialPos;
	}

	public void setInitialPos(Point initialPos) {
		this.initialPos = initialPos;
	}

	public void setInitialPos(int x, int y) {
		this.initialPos = new Point(x, y);
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
}
