package dev.solem.magicsystem.spell;

import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import dev.solem.magicsystem.particleanim.Summon;

public class Teleport extends Spell{
	public Teleport() {
		this.setSchool(School.ALTERATION);
		this.setName("Teleport");
		this.setDescription("Teleports user to wherever they are looking");
		this.setManaCost(100);
		this.setCraftingComponent(Material.ENDER_PEARL);
		this.setParticleAnimation(new Summon());
	}
	public void cast(Player player) {
		
		Location location = player.getTargetBlock((Set<Material>) null, 100).getLocation();
		Vector direction = player.getEyeLocation().getDirection();
		// prevent teleporting into walls
		while(!location.getBlock().isEmpty()) {
			location.add(0, 2, 0);
		}
		player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 20, 1);
		//TODO: threaded particle animation
		this.getParticleAnimation().playAnimation(player.getLocation());
		this.getParticleAnimation().playAnimation(location);
		player.teleport(location);
		player.getEyeLocation().setDirection(direction);
		player.playSound(location, Sound.ENTITY_ENDERMAN_TELEPORT, 20, 1);
	}
}
