package dev.solem.magicsystem.particleanim;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public class HealingCircleAnim extends ParticleAnimation{
	public void playAnimation(Location location, Vector vector) {
		playAnimation(location);
	}
	public void playAnimation(Location location) {
		int steps = 64;
		for(int i=0; i<steps; i++) {
			Location sublocation = location.clone().add(
					3*Math.cos(2*i*Math.PI/steps),
					1,
					3*Math.sin(2*i*Math.PI/steps)
			);
			location.getWorld().spawnParticle(Particle.END_ROD, sublocation, 3, 0.1, 0.1, 0.1, 0);
			location.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, sublocation, 1, 0.0, 0.0, 0.0, 0);
		}
	}
}
