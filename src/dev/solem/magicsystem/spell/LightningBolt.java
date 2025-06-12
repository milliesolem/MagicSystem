package dev.solem.magicsystem.spell;

import java.util.Collection;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import dev.solem.magicsystem.particleanim.LightningRay;


public class LightningBolt extends Spell {
	public LightningBolt() {
		this.setSchool(School.DESTRUCTION);
		this.setSpellType(SpellType.TARGET);
		this.setName("Lightning Bolt");
		this.setDescription("Shoots a bolt og electricity that zaps target.");
		this.setManaCost(25);
		this.setCraftingComponent(Material.COPPER_INGOT);
		this.setParticleAnimation(new LightningRay());
	}
	public void cast(Player player) {
		player.playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 20, 3);
		Location location = player.getEyeLocation();
		this.getParticleAnimation().playAnimation(location, location.getDirection());
		Location targetLocation = player.getTargetBlock((Set<Material>) null, 20).getLocation();
		Collection<Entity> entities = location.getWorld().getNearbyEntities(targetLocation, 5, 5, 5);
		for(Entity entity:entities) {
			if(player.getEntityId() == entity.getEntityId()) {
				continue;
			}
			if(entity instanceof LivingEntity) {
				((LivingEntity) entity).damage(8);
			}
			entity.setVelocity(location.getDirection().normalize().multiply(3.0));
		}
	}
}
