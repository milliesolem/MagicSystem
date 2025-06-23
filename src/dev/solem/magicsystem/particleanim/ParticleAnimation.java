package dev.solem.magicsystem.particleanim;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class ParticleAnimation {
	
	private ParticleAnimationType type;
	public ParticleAnimation() {
		this.setAnimType(ParticleAnimationType.DIRECTION);
	}
	public void playAnimation(Location location) {
		
	}
	public void playAnimation(Location location, Vector vector) {
		this.playAnimation(location);
	}
	public void playAnimation(Entity entity) {
		
	}
	public BukkitRunnable getBukkitRunnable(Location location, Vector vector) {
		return new BukkitRunnable() {
			public void run() {
				playAnimation(location, vector);
			}
		};
	}
	public ParticleAnimationType getAnimType() {
		return this.type;
	}
	public void setAnimType(ParticleAnimationType type) {
		this.type = type;
	}
}
