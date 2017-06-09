package simulator;

import javax.swing.*;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;

import java.awt.*;

public class Main {
	public static void main(String[] args) {
		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);

		Canvas canvas = new Canvas(800, 800, capabilities);
		// the window frame
		JFrame frame = new JFrame("Solar system");
		frame.getContentPane().add(canvas, BorderLayout.CENTER);

		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setResizable(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		canvas.requestFocus();
	}
}
