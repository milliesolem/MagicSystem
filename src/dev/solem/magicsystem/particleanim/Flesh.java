package dev.solem.magicsystem.particleanim;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public class Flesh extends ParticleAnimation{
	public void playAnimation(Location location, Vector vector) {
		location.getWorld().spawnParticle(
				Particle.ENCHANTED_HIT,
				location
				, 20, 0.2, 0.2, 0.2, 1);
		location.getWorld().spawnParticle(
				Particle.GLOW,
				location
				, 20, 0.2, 0.2, 0.2, 1);
	}
	public void playAnimation(Location location) {
		this.playAnimation(location, location.getDirection());
	}
}
