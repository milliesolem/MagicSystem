package dev.solem.magicsystem.particleanim;

import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class ParticleAnimation {
	
	
	
	public ParticleAnimation() {
		
	}
	public void playAnimation(Location location) {
		
	}
	public void playAnimation(Location location, Vector vector) {
		this.playAnimation(location);
	}
	public BukkitRunnable getBukkitRunnable(Location location, Vector vector) {
		return new BukkitRunnable() {
			public void run() {
				playAnimation(location, vector);
			}
		};
	}
}
