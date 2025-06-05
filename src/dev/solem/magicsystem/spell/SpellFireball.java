package dev.solem.magicsystem.spell;

import org.bukkit.entity.Player;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Fireball;

public class SpellFireball extends Spell {
	
	public SpellFireball() {
		this.setSchool(School.DESTRUCTION);
		this.setName("Fireball");
		this.setDescription("Shoots a fireball that explodes on impact");
		this.setManaCost(50);
		this.setCraftingComponent(Material.FIRE_CHARGE);
	}
	public void cast(Player player) {
		player.playSound(player.getLocation(), Sound.ITEM_FIRECHARGE_USE, 20, 1);
		for(int i=0;i<5;i++) {
			player.getLocation().getWorld().spawnParticle(Particle.LAVA, player.getLocation(), 10, 0.5, 0.5, 0.5);
		}
		Fireball fireball = player.getWorld().spawn(player.getEyeLocation(), Fireball.class);
		fireball.setShooter(player);
	}
	
}
