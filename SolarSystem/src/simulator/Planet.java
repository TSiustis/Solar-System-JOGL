package simulator;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.texture.Texture;

import javax.swing.*;

public class Planet {
	private GL2 gl;
	private GLU glu;
	private Texture planetTexture;
	private float angle;
	private float distance;

	private float rotationAngle = 0;
	private float speed = 0;
	private float radius;

	public Planet(GL2 gl, GLU glu, Texture planetTexture, float speed, float distance, float radius) {
		this.gl = gl;
		this.glu = glu;
		this.planetTexture = planetTexture;

		this.speed = speed;
		this.distance = distance;
		this.radius = radius;
	}

	public void display() {
		gl.glPushMatrix();
		angle = (angle + speed) % 360f;
		final float x = (float) Math.sin(Math.toRadians(angle)) * distance;
		final float y = (float) Math.cos(Math.toRadians(angle)) * distance;
		final float z = 0;

		gl.glTranslatef(x, y, z);

		draw();

		gl.glPopMatrix();

	}

	private void draw() {

		// Set material properties.
		float[] rgba = { 1f, 1f, 1f };
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT, rgba, 0);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, rgba, 0);
		gl.glMaterialf(GL2.GL_FRONT, GL2.GL_SHININESS, 0.6f);

		// Apply texture.
		planetTexture.enable(gl);
		planetTexture.bind(gl);

		rotationAngle = (rotationAngle + 0.1f) % 360f;

		gl.glPushMatrix();
		gl.glRotatef(rotationAngle, 0.2f, 0.1f, 0);

		GLUquadric planet = glu.gluNewQuadric();
		glu.gluQuadricTexture(planet, true);
		glu.gluQuadricDrawStyle(planet, GLU.GLU_FILL);
		glu.gluQuadricNormals(planet, GLU.GLU_FLAT);
		glu.gluQuadricOrientation(planet, GLU.GLU_OUTSIDE);
		final int slices = 16;
		final int stacks = 16;
		glu.gluSphere(planet, radius, slices, stacks);
		glu.gluDeleteQuadric(planet);

		gl.glPopMatrix();

	}

}