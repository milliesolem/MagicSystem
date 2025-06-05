package dev.solem.magicsystem.particleanim;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public class Ward extends ParticleAnimation {

	public void playAnimation(Location location, Vector vector) {
		int steps = 16;
		Vector subvector = vector.clone().normalize();
		Vector rotationVector = subvector.clone().add(new Vector(0,1,0));
		for(int i=0; i<steps; i++) {
			rotationVector.rotateAroundAxis(subvector, Math.TAU/((double)steps));
			Location sublocation = location.clone().add(rotationVector).add(vector);
			location.getWorld().spawnParticle(
					Particle.ENCHANTED_HIT,
					sublocation
					, 1, 0, 0, 0, 0);
			location.getWorld().spawnParticle(
					Particle.ELECTRIC_SPARK,
					sublocation
					, 1, 0.05, 0.05, 0.05, 0);
		}
	}
	public void playAnimation(Location location) {
		this.playAnimation(location, location.getDirection());
	}
}
