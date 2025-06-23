package dev.solem.magicsystem.particleanim;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public class LightningRay extends ParticleAnimation {
	

	// the idea here is to make a lightning effect by splitting the ray
	// into chunks and making them point in random directions
	// then connecting them back together to form a randomzied zig-zag pattern
	public void playAnimation(Location location, Vector direction) {
		Location sublocation = location.clone();
		Vector subdirection = direction.clone().normalize().multiply(2.0);
		int samples = 20;
		for(int i=0; i<20; i++) {
			double k1 = Math.random()-0.5;
			double k2 = Math.random()-0.5;
			double k3 = Math.random()-0.5;
			Vector rdir = new Vector(k1,k2,k3).normalize().multiply(4.0);
			Vector rdirinv = subdirection.clone().subtract(rdir);
			drawParticleLine(sublocation, rdir, 1, samples/2);
			drawParticleLine(sublocation.clone().add(rdir), rdirinv, rdirinv.length(), samples/2);
			sublocation.add(direction.clone().multiply(2.0));
		}
		
	}
	public void drawParticleLine(Location a, Vector dir, double len, int n) {
		Particle.DustOptions dust = new Particle.DustOptions(
                Color.fromRGB(86, 63, 226), 1);
		Location delta = a.clone();
		for(int i=0; i<n; i++) {
			a.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, delta, 1, 0, 0, 0, 0);
			a.getWorld().spawnParticle(Particle.DUST, delta, 1, 0, 0, 0, 0, dust);
			delta.add(dir.clone().normalize().multiply(((double)len)/n));
		}
	}
	public void playAnimation(Location location) {
		this.playAnimation(location, location.getDirection());
	}
}
