package dev.solem.magicsystem.spell;

import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import dev.solem.magicsystem.MagicSystem;
import dev.solem.magicsystem.particleanim.Summon;

public class SummonBlaze extends Spell{
	public SummonBlaze(){
		this.setSchool(School.CONJURATION);
		this.setSpellType(SpellType.TARGET);
		this.setName("Summon Blaze");
		this.setDescription("Summons a Blaze wherever the caster is pointing.");
		this.setManaCost(100);
		this.setCraftingComponent(Material.BLAZE_SPAWN_EGG);
		this.setParticleAnimation(new Summon());
	}
	public void cast(Player player) {
		Location targetLocation = player.getTargetBlock((Set<Material>) null, 20).getLocation().add(0, 1, 0);
		this.getParticleAnimation().playAnimation(targetLocation);
		LivingEntity blaze = (LivingEntity) player.getWorld().spawnEntity(targetLocation, EntityType.BLAZE);
		blaze.setCustomName(player.getName() + "'s Blaze");
		MagicSystem.getInstance().addMinion(player, blaze);
	}
}
