package dev.solem.magicsystem.spell;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;

public class SpectralArrow extends Spell {
	public SpectralArrow() {
		this.setSchool(School.CONJURATION);
		this.setSpellType(SpellType.TARGET);
		this.setName("Spectral Arrow");
		this.setDescription("Shoots an arrow.");
		this.setManaCost(25);
		this.setCraftingComponent(Material.ARROW);
	}
	public void cast(Player player) {
		player.playSound(player.getLocation(), Sound.ENTITY_ARROW_SHOOT, 20, 1);
		Arrow arrow = player.getWorld().spawn(player.getEyeLocation(), Arrow.class);
		arrow.setVelocity(player.getEyeLocation().getDirection().normalize().multiply(2.0));
		arrow.setShooter(player);
	}
}
