import java.awt.Color;
import javax.swing.JFrame;
public class Driver {

	public static void main(String[] args) {
		int w,h;
		w = 1920;
		h = 1080;
		int scaleFactor = 16;
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(w, h);
		ColorPanel panel = new ColorPanel(new Color(32,42,68), w, h, 1);
		Color borderColor = new Color(32,42,68);
		panel.addThing(new Thing(0,h-h/8, w,h,borderColor, 10000000000000000000000.0, true));
		panel.addThing(new Thing(0, 0, w/24, h-h/8, borderColor, 100000000000000000000000.0, true));
		panel.addThing(new Thing(w/24, 0, w*23/24, w/24, borderColor, 100000000000000000000.0, true));
		panel.addThing(new Thing(w*23/24, w/24, w/8, w-23/24*2, borderColor, 100000000000000000.0, true));
		Thing red = new Thing(w/2,h/2,w/scaleFactor*2,w/scaleFactor*2, Color.red, 10, false);
		//red.setxVelocity(100);
		//red.setyVelocity(100);
	 //   panel.addThing(red);
	    
	    Thing pink = new Thing(w/2,h/3,w/scaleFactor/5,w/scaleFactor/5, Color.pink, 1, false);
	   // panel.addThing(pink);
	
	    
	    
	    Thing green = new Thing(w/2,h/6,w/scaleFactor,w/scaleFactor, Color.green, 1, false);
	
		//panel.addThing(green);
	//	panel.addThing(new Thing(w/2,h/2,w/scaleFactor*2,w/scaleFactor*2, Color.red, 1000, false));
		for(int i = 0 ; i < panel.getThings().size();i++) {
			System.out.println(panel.getThings().get(i).getColor());
		}
	
		frame.getContentPane().add(panel);
		frame.setVisible(true);
		
		while (true) {
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			frame.repaint();
		}

		
	}


}