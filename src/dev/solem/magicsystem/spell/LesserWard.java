package dev.solem.magicsystem.spell;

import java.util.Collection;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import dev.solem.magicsystem.particleanim.Ward;

public class LesserWard extends Spell {
	public LesserWard() {
		this.setSchool(School.RESTORATION);
		this.setSpellType(SpellType.CONCENTRATION);
		this.setName("Lesser Ward");
		this.setDescription("Forms an arcane shield in front of the caster that deflects incoming projectiles.");
		this.setManaCost(5);
		this.setCraftingComponent(Material.SHIELD);
		this.setParticleAnimation(new Ward());
	}
	
	public void cast(Player player) {
		Location location = player.getEyeLocation();
		this.getParticleAnimation().playAnimation(location, location.getDirection());
		player.playSound(player.getLocation(), Sound.ITEM_FIRECHARGE_USE, 20, 0.1f);
		
		// give resitence
		player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 40, 1));
		
		// deflect entites
		Location targetLocation = player.getTargetBlock((Set<Material>) null, 2).getLocation();
		Collection<Entity> entities = location.getWorld().getNearbyEntities(targetLocation, 3, 3, 3);
		for(Entity entity:entities) {
			if(player.getEntityId() == entity.getEntityId()) {
				continue;
			}
			if(entity.isOnGround()) {
				continue;
			}
			entity.setVelocity(entity.getVelocity().multiply(-1.5));
		}
	}
}
