package dev.solem.magicsystem.particleanim;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public class PolymorphAnim extends ParticleAnimation{
	public void playAnimation(Location location, Vector direction) {
		Location sublocation = location.clone();
		Vector subdirection = direction.clone().normalize().multiply(2.0);
		int samples = 64;
		drawParticleLine(sublocation, subdirection, 20, samples);
	}
	public void drawParticleLine(Location a, Vector dir, double len, int n) {
		Location delta = a.clone();
		for(int i=0; i<n; i++) {
			Particle.DustOptions dustColor = new Particle.DustOptions(rainbowGradient((float)i/(float)n), 1);
			a.getWorld().spawnParticle(Particle.DUST, delta, 4, 0.1, 0.1, 0.1, 0.1, dustColor);
			delta.add(dir.clone().normalize().multiply(((double)len)/n));
		}
	}
	
	// 200 IQ rainbow gradient generator using lagrange interpolation
	public Color rainbowGradient(float index) {
		int red = (int) Math.clamp(1858*index*index - 2369*index + 749, 0, 255);
		int green = (int) Math.clamp(index * (4620*index*index - 7332*index + 2841), 0, 255);
		int blue = (int) Math.clamp(index*255, 0, 255);
		return Color.fromRGB(red,green,blue);
	}
	public void playAnimation(Location location) {
		this.playAnimation(location, location.getDirection());
	}
}
