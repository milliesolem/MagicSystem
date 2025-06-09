package dev.solem.magicsystem.spell;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import dev.solem.magicsystem.particleanim.Flesh;

public class IcarianFlight extends Spell {
	public IcarianFlight() {
		this.setSchool(School.ALTERATION);
		this.setSpellType(SpellType.SELF);
		this.setName("Icarian Flight");
		this.setDescription("Launches the caster into the air.");
		this.setManaCost(100);
		this.setCraftingComponent(Material.FEATHER);
		this.setParticleAnimation(new Flesh());
	}
	
	public void cast(Player player) {
		Location location = player.getEyeLocation();
		this.getParticleAnimation().playAnimation(location, location.getDirection());
		player.playSound(player.getLocation(), Sound.ENTITY_WIND_CHARGE_WIND_BURST, 20, 10);

		player.setVelocity(player.getVelocity().multiply(300).add(new Vector(0,200,0)));
	}
}
