package dev.solem.magicsystem.spell;

import java.util.Collection;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import dev.solem.magicsystem.particleanim.LightningRayPowerful;

public class Thunderbolt extends Spell {
	public Thunderbolt() {
		this.setSchool(School.DESTRUCTION);
		this.setSpellType(SpellType.TARGET);
		this.setName("Thunderbolt");
		this.setDescription("Shoots a bolt of lightning that electrocutes the target. Violently.");
		this.setManaCost(50);
		this.setCraftingComponent(Material.COPPER_BLOCK);
		this.setParticleAnimation(new LightningRayPowerful());
	}
	public void cast(Player player) {
		player.playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 20, 1);
		Location location = player.getEyeLocation();
		this.getParticleAnimation().playAnimation(location, location.getDirection());
		Location targetLocation = player.getTargetBlock((Set<Material>) null, 20).getLocation();
		targetLocation.getWorld().spawnParticle(Particle.LARGE_SMOKE, targetLocation, 15, 1, 1, 1, 5);
		
		if(targetLocation.getBlock().getType() == Material.STONE) {
			targetLocation.getBlock().setType(Material.BLACKSTONE);
		}
		if(targetLocation.getBlock().getType().isFlammable()) {
			targetLocation.getBlock().setType(Material.COAL_BLOCK);
		}
		
		Collection<Entity> entities = location.getWorld().getNearbyEntities(targetLocation, 5, 5, 5);
		for(Entity entity:entities) {
			if(player.getEntityId() == entity.getEntityId()) {
				continue;
			}
			try {
				((LivingEntity) entity).damage(16);
			}
			catch(ClassCastException e) {
				
			}
			entity.setVelocity(location.getDirection().normalize().multiply(10.0));
		}
	}
}
