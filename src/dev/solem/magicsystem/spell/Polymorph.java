package dev.solem.magicsystem.spell;

import java.util.Collection;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import dev.solem.magicsystem.particleanim.PolymorphAnim;

// inspired by the Hearthstone/Warcraft spell of the same name
public class Polymorph extends Spell{
	public Polymorph() {
		this.setSchool(School.ALTERATION);
		this.setSpellType(SpellType.TARGET);
		this.setName("Polymorph");
		this.setDescription("Turns the target into a sheep.");
		this.setManaCost(50);
		this.setCraftingComponent(Material.WHITE_WOOL);
		this.setParticleAnimation(new PolymorphAnim());
	}
	public void cast(Player player) {
		player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 20, 10);
		Location location = player.getEyeLocation();
		this.getParticleAnimation().playAnimation(location, location.getDirection());
		Location targetLocation = player.getTargetBlock((Set<Material>) null, 20).getLocation();
		Collection<Entity> entities = location.getWorld().getNearbyEntities(targetLocation, 5, 5, 5);
		for(Entity entity:entities) {
			if(player.getEntityId() == entity.getEntityId()) {
				continue;
			}
			if(entity.getType() == EntityType.PLAYER) {
				continue; //polymorphing players TBA
			}
			if(entity instanceof LivingEntity) {
				LivingEntity livingEntity = (LivingEntity) entity;
				LivingEntity sheep = (LivingEntity) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.SHEEP);
				if(livingEntity.getCustomName() != null) {
					sheep.setCustomName(livingEntity.getCustomName());
					sheep.setCustomNameVisible(livingEntity.isCustomNameVisible());
				}
				targetLocation.getWorld().spawnParticle(Particle.DRAGON_BREATH, targetLocation, 15, 1, 1, 1, 5);
				livingEntity.remove(); // into the shadow realm >:)
				break; // only one target may be polymorphed with this spell
			}
		}
	}
}
