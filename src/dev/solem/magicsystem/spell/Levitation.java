package dev.solem.magicsystem.spell;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import dev.solem.magicsystem.particleanim.Levitate;

public class Levitation extends Spell {
	public Levitation() {
		this.setSchool(School.ALTERATION);
		this.setSpellType(SpellType.CONCENTRATION);
		this.setName("Levitation");
		this.setDescription("Allows the caster to levitate.");
		this.setManaCost(10);
		this.setCraftingComponent(Material.ENDER_EYE);
		this.setParticleAnimation(new Levitate());
	}
	
	public void cast(Player player) {
		Location location = player.getEyeLocation();
		this.getParticleAnimation().playAnimation(location, location.getDirection());
		player.playSound(player.getLocation(), Sound.ENTITY_BREEZE_WIND_BURST, 20, 10);
		
		// give slow falling
		player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 20 * 2, 1));
		Vector newPlayerVelocity = player.getVelocity().multiply(3).add(new Vector(0,0.5,0));
		// prevent the player from going insanely fast
		double speedLimit = 1;
		if(newPlayerVelocity.length() > speedLimit) {
			newPlayerVelocity = newPlayerVelocity.normalize().multiply(speedLimit);
		}
		player.setVelocity(newPlayerVelocity);
	}
}
