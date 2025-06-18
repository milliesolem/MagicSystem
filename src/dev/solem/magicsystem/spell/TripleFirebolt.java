package dev.solem.magicsystem.spell;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.util.Vector;

public class TripleFirebolt extends Spell{
	public TripleFirebolt() {
		this.setSchool(School.DESTRUCTION);
		this.setSpellType(SpellType.TARGET);
		this.setName("Triple Firebolt");
		this.setDescription("Shoots three firebolts that ignite on impact.");
		this.setManaCost(75);
		this.setCraftingComponent(Material.FIREWORK_STAR);
	}
	public void cast(Player player) {
		player.playSound(player.getLocation(), Sound.ITEM_FIRECHARGE_USE, 20, 1);
		for(int i=0;i<5;i++) {
			player.getLocation().getWorld().spawnParticle(Particle.FLAME, player.getLocation(), 10, 0.5, 0.5, 0.5,0);
		}
		for(int i=0; i<3; i++) {
			Fireball fireball = player.getWorld().spawn(player.getEyeLocation(), SmallFireball.class);
			double velocity = fireball.getVelocity().length();
			Vector rvector = new Vector(Math.random()-0.5f, Math.random()-0.5f, Math.random()-0.5f);
			rvector.normalize().multiply(0.2f);
			fireball.setVelocity(fireball.getVelocity().add(rvector).normalize().multiply(velocity));
			fireball.setShooter(player);
		}
		
	}
}
