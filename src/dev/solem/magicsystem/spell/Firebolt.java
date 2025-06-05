package dev.solem.magicsystem.spell;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.SmallFireball;
import org.bukkit.entity.Player;

public class Firebolt extends Spell {
	public Firebolt() {
		this.setSchool(School.DESTRUCTION);
		this.setSpellType(SpellType.TARGET);
		this.setName("Firebolt");
		this.setDescription("Shoots a firebolt that set whatever it hits on fire");
		this.setManaCost(25);
		this.setCraftingComponent(Material.BLAZE_POWDER);
	}
	public void cast(Player player) {
		player.playSound(player.getLocation(), Sound.ITEM_FIRECHARGE_USE, 20, 1);
		for(int i=0;i<5;i++) {
			player.getLocation().getWorld().spawnParticle(Particle.FLAME, player.getLocation(), 10, 0.5, 0.5, 0.5,0);
		}
		Fireball fireball = player.getWorld().spawn(player.getEyeLocation(), SmallFireball.class);
		fireball.setShooter(player);
	}
}
