package dev.solem.magicsystem.particleanim;

import java.util.Collection;
import java.util.HashMap;

public class ParticleAnimationCatalog {
	private HashMap<String, ParticleAnimation> particleAnimationHashMap = new HashMap<String, ParticleAnimation>();
	public ParticleAnimationCatalog() {
		particleAnimationHashMap.put("summon", new Summon());
		particleAnimationHashMap.put("flamethrower", new Flamethrower());
		particleAnimationHashMap.put("lightningray", new LightningRay());
		particleAnimationHashMap.put("ward", new Ward());
		particleAnimationHashMap.put("flesh", new Flesh());
	}
	public ParticleAnimation getParticleAnimation(String name) {
		if (!particleAnimationHashMap.containsKey(name)) {
			return null;
		}
		return particleAnimationHashMap.get(name);
	}
	public Collection<ParticleAnimation> getParticleAnimationCollection(){
		return particleAnimationHashMap.values();
	}
}
