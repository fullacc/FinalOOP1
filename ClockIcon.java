import java.awt.*;
import java.awt.geom.*;
import java.util.GregorianCalendar;

import javax.swing.*;

public class ClockIcon implements Icon{

	public double radius,diameter,x,y;

	public ClockIcon(int x, int y, int diameter){
		radius = diameter/2;
		this.x = x;
		this.y = y;
		this.diameter = diameter;
	}
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		draw(g2);
	}

	public int getIconWidth() {
		return (int)diameter;
	}

	public int getIconHeight() {
		return (int)diameter;
	}
	
	public void draw(Graphics2D g2)
	
	{
		GregorianCalendar now = new GregorianCalendar();
		int second = now.get(GregorianCalendar.SECOND);
		int minute = now.get(GregorianCalendar.MINUTE);
		int hour = now.get(GregorianCalendar.HOUR);
	      
		hour = hour % 12;
		hour = hour * 5;
		hour = hour + (int) (5 * minute / 60.0);  
		
		Ellipse2D.Double circle = new Ellipse2D.Double(x,y,diameter,diameter);
		g2.setColor(Color.green);
		g2.draw(circle);
		g2.fill(circle);

		Point2D.Double hour_start = new Point2D.Double(radius, radius);
		Point2D.Double hour_end = new Point2D.Double(getX(hour,1,3), getY(hour,1,3));
		Shape hour_hand = new Line2D.Double(hour_start,hour_end);			
				
		Point2D.Double minute_start = new Point2D.Double(radius, radius);
		Point2D.Double minute_end = new Point2D.Double(getX(minute,2,3), getY(minute,2,3));
		Shape minute_hand = new Line2D.Double(minute_start, minute_end);
		
		Point2D.Double second_start = new Point2D.Double(radius, radius);
		Point2D.Double second_end = new Point2D.Double(getX(second,19,20), getY(second,19,20));
		Shape second_hand = new Line2D.Double(second_start, second_end);

		g2.setColor(Color.yellow);
		g2.draw(hour_hand);	
		g2.setColor(Color.red);
		g2.draw(minute_hand);
		g2.setColor(Color.black);
		g2.draw(second_hand);
	}
	
	
	public double getX(int time, int numerator, int denominator){
		double hand_len = radius* numerator/denominator;
		double radians = Math.PI * 6 * time / 180;
		double x = radius + hand_len * Math.sin(radians);
		return x;
	}
	
	public double getY(int time, int numerator,int denominator){
		double hand_len = radius* numerator/denominator;
		double radians = Math.PI * 6 * time / 180;
		double y= radius - hand_len * Math.cos(radians);
		return y;
	}
	


}
