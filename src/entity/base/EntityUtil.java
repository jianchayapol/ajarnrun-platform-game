package entity.base;

import java.awt.Point;

public class EntityUtil {
	
	public static double getDistance(Point p1, Point p2) {
		return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
	}
	
	public static boolean isContact(Entity e1,Entity e2) {
		return getDistance(e1.getCurrentPos(),e2.getCurrentPos()) < 1;
	}
}
