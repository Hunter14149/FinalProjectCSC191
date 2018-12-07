package mainPackage;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Toolkit;

import mainPackage.gifTimer.Reminder;

public class gifTimer {
	Toolkit toolkit;
	Timer timer;
	
	public gifTimer() {
		toolkit = Toolkit.getDefaultToolkit();
		timer = new Timer();
		timer.schedule(new Reminder(), 0, // initial delay
				1 * 2500); // subsequent rate
		
	}
 
	class Reminder extends TimerTask {
		int loop = 3;
		
		public void run() {
			if (loop > 0) {
				toolkit.beep();
				if (loop == 1) {
					mainWindow.ShowFinishedGIF1();
				} else if (loop == 2) {
					mainWindow.ShowFinishedGIF2();
				} else if (loop == 3) {
					mainWindow.ShowFinishedGIF3();
				}
				loop--;
			} else {
				// Done
				toolkit.beep();
				timer.cancel();
			}
		}
	}
	
}
