package dev.solem.magicsystem.particleanim;

import org.bukkit.Location;
import org.bukkit.Particle;

public class Summon extends ParticleAnimation {
	public void playAnimation(Location location) {
		int steps = 64;
		for(int i=0; i<steps; i++) {
			Location sublocation = location.clone().add(
					Math.cos(6*i*Math.PI/steps),
					((double)2*i)/steps,
					Math.sin(6*i*Math.PI/steps)
			);
			location.getWorld().spawnParticle(
					Particle.PORTAL,
					sublocation
					, 1, 0, 0, 0, 0);
		}
	}
}
