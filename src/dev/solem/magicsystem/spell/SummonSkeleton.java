package dev.solem.magicsystem.spell;

import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import dev.solem.magicsystem.MagicSystem;
import dev.solem.magicsystem.particleanim.Summon;

public class SummonSkeleton extends Spell{
	public SummonSkeleton(){
		this.setSchool(School.CONJURATION);
		this.setSpellType(SpellType.TARGET);
		this.setName("Summon Skeleton");
		this.setDescription("Summons a Skeleton wherever the caster is pointing.");
		this.setManaCost(50);
		this.setCraftingComponent(Material.SKELETON_SPAWN_EGG);
		this.setParticleAnimation(new Summon());
	}
	public void cast(Player player) {
		Location targetLocation = player.getTargetBlock((Set<Material>) null, 20).getLocation().add(0, 1, 0);
		this.getParticleAnimation().playAnimation(targetLocation);
		LivingEntity skeleton = (LivingEntity) player.getWorld().spawnEntity(targetLocation, EntityType.SKELETON);
		skeleton.setCustomName(player.getName() + "'s Skeleton");
		MagicSystem.getInstance().addMinion(player, skeleton);
	}
}
