package dev.solem.magicsystem.particleanim;

import java.time.Instant;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import dev.solem.magicsystem.MagicSystem;

public class FlameCloakAnim extends ParticleAnimation {
	public FlameCloakAnim() {
		this.setAnimType(ParticleAnimationType.ENTITY);
	}
	public void playAnimation(Location location, Vector vector) {
		playAnimation(location);
	}
	public void playAnimation(Entity entity) {
		final double playtime = 4.0; // seconds to run the animation
		final int interval = 2; // ticks between animation frames
		
		long now = Instant.now().getEpochSecond();
		MagicSystem plugin = MagicSystem.getInstance();
		new BukkitRunnable() {
			private double frame = 0;
			public void run() {
				double delta =  Instant.now().getEpochSecond();
				Location location = entity.getLocation();
				frame += ((double)interval)/20d;
				double biframe = (frame + playtime/2) % playtime;
				Location sublocation = location.clone().add(
						1.5*Math.cos(frame*Math.TAU/playtime),
						1,
						1.5*Math.sin(frame*Math.TAU/playtime)
				);
				location.getWorld().spawnParticle(Particle.FLAME, sublocation, 5, 0.1, 0.2, 0.1, 0);
				sublocation = location.clone().add(
						1.5*Math.cos(biframe*Math.TAU/playtime),
						1,
						1.5*Math.sin(biframe*Math.TAU/playtime)
				);
				location.getWorld().spawnParticle(Particle.FLAME, sublocation, 5, 0.1, 0.2, 0.1, 0);
				if(delta > now + playtime) {
					cancel();
				}
			}
		}.runTaskTimer(plugin, 0, interval);
	}
}
