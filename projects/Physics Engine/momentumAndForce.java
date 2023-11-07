import java.awt.Point;

public class momentumAndForce {
	
	public momentumAndForce() {
		
	}
	
	public static void xFrictionChange(Thing one, Thing two) {
		double vXOne = one.getxVelocity();
		double vXTwo = two.getxVelocity();
		double m1 = one.getMass();
		double m2 = two.getMass();
		System.out.println("true");
		
		if(vXOne-vXTwo!=0) {
			double downForce = one.getForce(2);
			
			if(downForce>0 && one.getY()<two.getY()) {
				
				double oneXVelocity = one.getxVelocity();
				one.setxVelocity(oneXVelocity + (downForce/one.getMass() * Integer.signum((int) oneXVelocity) * -1));
			}
		}
		
	}
	
	public static void momentumChange(Thing one, Thing two) {
		double vYOne = one.getyVelocity();
		double vYTwo = two.getyVelocity();
		double m1 = one.getMass();
		double m2 = two.getMass();
		
	}
	
	

	public static void yMomentumChange(Thing one, Thing two) { // checks for and calculates momentum changes in the y
																// direction

		double vYOne = one.getyVelocity();
		double vYTwo = two.getyVelocity();
		double m1 = one.getMass();
		double m2 = two.getMass();
		// System.out.println(one.getyVelocity() +" " + two.getyVelocity());
		if (one.isElastic() && two.isElastic()) {
			one.setyVelocity( (elasticFinalVelocity(vYOne, vYTwo, m1, m2)));
			two.setyVelocity( (elasticFinalVelocity(vYTwo, vYOne, m2, m1)));
			return;
		}
		else if(one.isMiddle()&&two.isMiddle()) {
			one.setyVelocity(Math.round (finalVelocity(vYOne, vYTwo, m1, m2)));
			two.setyVelocity(Math.round (finalVelocity(vYTwo, vYOne, m2, m1)));
			return;
		}
		double velocity = Math.round(inelasticFinalVelocity(vYOne, vYTwo, m1, m2));
		one.setyVelocity(velocity);
		two.setyVelocity(velocity);
		// System.out.println(one.getyVelocity() +" " + two.getyVelocity());

	}

	public static void xMomentumChange(Thing one, Thing two) { // checks for and calculates momentum change in the x
																// direction

		double vXOne = one.getxVelocity();

		double vXTwo = two.getxVelocity();
		double m1 = one.getMass();
		double m2 = two.getMass();

		if (one.isElastic() && two.isElastic()) {
			one.setxVelocity((int)(elasticFinalVelocity(vXOne, vXTwo, m1, m2)));
			two.setxVelocity(Math.round(elasticFinalVelocity(vXTwo, vXOne, m2, m1)));
			return;
		}
		else if(one.isMiddle()&&two.isMiddle()) {
			one.setxVelocity(Math.round(finalVelocity(vXOne, vXTwo, m1, m2)));
			two.setxVelocity(Math.round(finalVelocity(vXTwo, vXOne, m2, m1)));
			return;
		}

		double velocity = (Math.round(inelasticFinalVelocity(vXOne, vXTwo, m1, m2)));
		one.setxVelocity(velocity);
		two.setxVelocity(velocity);

	}

	private static double finalVelocity(double v1, double v2, double m1, double m2) { // calculates final velocity
	
		double energy = (int) (Math.random()*10+1);
		double vF = 1*((((m1 - m2) / (m1 + m2)) * v1) + (((2 * m2) / (m1 + m2)) * v2));
		int sign = (int) (Math.abs(vF) / vF) *-1;
		double eL = vF + Math.sqrt(5*2/m1)*sign;
		if(eL< 0 && vF>=0) {
			return 0;
		}
		else if(eL>0 && vF<=0) {
			return 0;
		}
		return eL;

	}
	private static double elasticFinalVelocity(double v1, double v2, double m1, double m2){
		
		return 1*((((m1 - m2) / (m1 + m2)) * v1) + (((2 * m2) / (m1 + m2)) * v2));
	}

	private static double inelasticFinalVelocity(double v1, double v2, double m1, double m2) {
		return (m1 * v1 + m2 * v2) / (m1 + m2);
	}

}