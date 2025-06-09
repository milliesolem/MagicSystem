package dev.solem.magicsystem.particleanim;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public class Levitate extends ParticleAnimation{
	public void playAnimation(Location location, Vector vector) {
		location.getWorld().spawnParticle(
				Particle.PORTAL,
				location
				, 20, 0.2, 0, 0.2, 0);
	}
	public void playAnimation(Location location) {
		this.playAnimation(location, location.getDirection());
	}
}
