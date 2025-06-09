package dev.solem.magicsystem.particleanim;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public class LightningRayPowerful extends ParticleAnimation {
	public void playAnimation(Location location, Vector direction) {
		Location sublocation = location.clone();
		Vector subdirection = direction.clone().normalize().multiply(2.0);
		int samples = 20;
		for(int i=0; i<20; i++) {
			double k1 = Math.random();
			double k2 = Math.random();
			double k3 = Math.random();
			Vector rdir = new Vector(k1,k2,k3).normalize().multiply(2.0);
			Vector rdirinv = subdirection.clone().subtract(rdir);
			drawParticleLine(sublocation, rdir, 1, samples/2);
			drawParticleLine(sublocation.clone().add(rdir), rdirinv, rdirinv.length(), samples/2);
			sublocation.add(direction.clone().multiply(2.0));
		}
		
	}
	public void drawParticleLine(Location a, Vector dir, double len, int n) {
		Location delta = a.clone();
		for(int i=0; i<n; i++) {
			a.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, delta, 1, 0, 0, 0, 0);
			a.getWorld().spawnParticle(Particle.INSTANT_EFFECT, delta, 1, 0, 0, 0, 0);
			delta.add(dir.clone().normalize().multiply(((double)len)/n));
		}
	}
	public void playAnimation(Location location) {
		this.playAnimation(location, location.getDirection());
	}
}
