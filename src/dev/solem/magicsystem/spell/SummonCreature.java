package dev.solem.magicsystem.spell;

import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import dev.solem.magicsystem.MagicSystem;
import dev.solem.magicsystem.particleanim.Summon;


/*
 * This class acts as an abstraction for summoning type spells, so that I don't need to make an individual class for 
 * all summoning spells. Given that, you know, most of these spells work pretty much the same but summons a different
 * entity. 
 * */

public class SummonCreature extends Spell{
	private EntityType creature;
	public SummonCreature(){
		this.setSchool(School.CONJURATION);
		this.setSpellType(SpellType.TARGET);
		this.setName("Summon Mob");
		this.setDescription("Summons a %s wherever the caster is pointing. Summoned creatures will protect their caster.");
		this.setManaCost(50);
		this.setCraftingComponent(Material.SKELETON_SPAWN_EGG);
		this.setParticleAnimation(new Summon());
	}
	public SummonCreature(EntityType creature, int manaCost, Material craftingComponent) {
		this();
		this.setManaCost(manaCost);
		this.setDescription(String.format(getDescription(), creature.name()));
		this.setCreature(creature);
		this.setName("Summon " +creature.name().substring(0, 1)+ creature.name().substring(1).toLowerCase());
		this.setCraftingComponent(craftingComponent);
	}
	public void cast(Player player) {
		player.playSound(player.getLocation(), Sound.ENTITY_BLAZE_HURT, 20, 0.3f);
		Location targetLocation = player.getTargetBlock((Set<Material>) null, 20).getLocation().add(0, 1, 0);
		this.getParticleAnimation().playAnimation(targetLocation);
		LivingEntity creatureInstance = (LivingEntity) player.getWorld().spawnEntity(targetLocation, creature);
		creatureInstance.setCustomName(player.getName() + "'s " + creature.name());
		
		MagicSystem.getInstance().addMinion(player, creatureInstance);
	}
	public void setCreature(EntityType creature) {
		this.creature = creature;
	}
	public EntityType getCreature() {
		return this.creature;
	}
}