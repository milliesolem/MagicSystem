package dev.solem.magicsystem.spell;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import dev.solem.magicsystem.particleanim.Flesh;

public class Oakflesh extends Spell{
	public Oakflesh() {
		this.setSchool(School.ALTERATION);
		this.setSpellType(SpellType.SELF);
		this.setName("Oakflesh");
		this.setDescription("Gives the caster resistance for 60 seconds.");
		this.setManaCost(25);
		this.setCraftingComponent(Material.OAK_WOOD);
		this.setParticleAnimation(new Flesh());
	}
	
	public void cast(Player player) {
		Location location = player.getEyeLocation();
		this.getParticleAnimation().playAnimation(location, location.getDirection());
		player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_PLACE, 20, 10);
		player.playSound(player.getLocation(), Sound.ENTITY_BLAZE_HURT, 20, 10);
		
		// give resitence
		player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 20 * 60, 1));
	}
}
