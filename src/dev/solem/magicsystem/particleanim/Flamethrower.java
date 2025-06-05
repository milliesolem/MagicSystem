package dev.solem.magicsystem.particleanim;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public class Flamethrower extends ParticleAnimation{
	
	public void playAnimation(Location location, Vector vector) {
		Location sublocation = location.clone().add(0, -0.5, 0);
		Vector subvector = vector.clone().normalize();
		for(int i=0; i<5; i++) {
			location.getWorld().spawnParticle(Particle.FLAME, sublocation, 5, 0.05, 0.05, 0.05, 0);
			sublocation = sublocation.add(subvector);
		}
	}
	public void playAnimation(Location location) {
		this.playAnimation(location, location.getDirection());
	}
}
