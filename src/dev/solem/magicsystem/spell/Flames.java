package dev.solem.magicsystem.spell;

import java.util.Collection;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import dev.solem.magicsystem.particleanim.Flamethrower;

public class Flames extends Spell {
	public Flames() {
		this.setSchool(School.DESTRUCTION);
		this.setSpellType(SpellType.CONCENTRATION);
		this.setName("Flames");
		this.setDescription("Shoots a blast of fire that ignites target. Targets on fire take extra damage.");
		this.setManaCost(5);
		this.setCraftingComponent(Material.COAL);
		this.setParticleAnimation(new Flamethrower());
	}
	
	// TODO: change this function to use threaded particle animations
	public void cast(Player player) {
		Location location = player.getEyeLocation();
		this.getParticleAnimation().playAnimation(location, location.getDirection());
		player.playSound(player.getLocation(), Sound.ITEM_FIRECHARGE_USE, 20, 2);
		
		Location targetLocation = player.getTargetBlock((Set<Material>) null, 10).getLocation();
		Collection<Entity> entities = location.getWorld().getNearbyEntities(targetLocation, 2, 2, 2);
		for(Entity entity:entities) {
			try {
				if(entity.getEntityId() == player.getEntityId()) {
					continue;
				}
				LivingEntity lentity = ((LivingEntity) entity);
				if(lentity.getFireTicks() > 20) {
					lentity.damage(2);
				}
				lentity.setFireTicks(10 * 20); // how many 20th of seconds the entity is on fire
			}
			catch(ClassCastException e) {
				
			}
		}

	}
	
}

