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

public class SpellWard extends Spell {
	
	private int power;
	
	public SpellWard() {
		this.setSchool(School.RESTORATION);
		this.setSpellType(SpellType.CONCENTRATION);
		this.setDescription("Forms an arcane shield in front of the caster that deflects incoming projectiles.");
		this.setParticleAnimation(new Ward());
	}
	
	public SpellWard(String name, int manaCost, Material craftingComponent, int power) {
		this();
		this.power = power;
		this.setName(name);
		this.setManaCost(manaCost);
		this.setCraftingComponent(craftingComponent);
	}
	
	public void cast(Player player) {
		Location location = player.getEyeLocation();
		this.getParticleAnimation().playAnimation(location, location.getDirection());
		player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_RESONATE, 5, -10f);
		
		// give resistence
		player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 40, power));
		
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
			player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 20, 0.1f);
			entity.setVelocity(entity.getVelocity().multiply(-((float)this.power/2.0f)));
		}
	}
}