package dev.solem.magicsystem.particleanim;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public class LightningRay extends ParticleAnimation {
	
	// this code didn't work as intended by created another really cool effect
	/*
	public void playAnimation(Location location, Vector direction) {
		Location sublocation = location.clone();
		Vector subdirection = direction.clone().normalize();
		int samples = 10;
		for(int i=0; i<20; i++) {
			double k = Math.random();
			Vector rdir = new Vector(k,k,k).normalize();
			Vector rdirinv = subdirection.subtract(rdir);
			for(int j=0; j< samples/2;j++) {
				sublocation = sublocation.add(rdir.multiply(1.0/samples));
				location.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, sublocation, 1, 0, 0, 0, 1);
				sublocation = sublocation.add(direction.normalize().multiply(0.5/samples));
			}
			for(int j=0; j< samples/2;j++) {
				sublocation = sublocation.add(rdirinv.multiply(1.0/samples));
				location.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, sublocation, 1, 0, 0, 0, 1);
				sublocation = sublocation.add(direction.normalize().multiply(0.5/samples));
			}
			sublocation = sublocation.add(direction);
		}
	}
	*/
	// the idea here is to make a lightning effect by splitting the ray
	// into chunks and making them point in random directions
	// then connecting them back together to form a randomzied zig-zag pattern
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
			delta.add(dir.clone().normalize().multiply(((double)len)/n));
		}
	}
	public void playAnimation(Location location) {
		this.playAnimation(location, location.getDirection());
	}
}
