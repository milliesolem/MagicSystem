package dev.solem.magicsystem.particleanim;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;

public class Summon extends ParticleAnimation {
	public void playAnimation(Location location) {
		int steps = 64;
		Particle.DustOptions particleColor = new Particle.DustOptions(Color.fromRGB(255, 0, 255), 1);
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
			particleColor = new Particle.DustOptions(Color.fromRGB((int)(255 * ((float)i/steps)), 0, (int)(255 * ((float)i/steps))), 1);
			location.getWorld().spawnParticle(Particle.DUST, sublocation, 1, 0, 0, 0, 0, particleColor);
		}
		location.getWorld().spawnParticle(Particle.DUST, location, 15, 0.1, 2, 0.1, 0, particleColor);
	}
}
