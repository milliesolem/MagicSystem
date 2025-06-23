package dev.solem.magicsystem.spell;

import java.util.Collection;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

public class SpellCatalog {
	private HashMap<String, Spell> spellHashMap = new HashMap<String, Spell>();
	
	// note to self:
	// to make the spell catalog command-friendly,
	// DO NOT USE SPACES
	public SpellCatalog() {
		
		// Destruction
		spellHashMap.put("Fireball", new SpellFireball());
		spellHashMap.put("Flames",  new Flames());
		spellHashMap.put("Firebolt",  new Firebolt());
		spellHashMap.put("TripleFirebolt",  new TripleFirebolt());
		spellHashMap.put("LightningBolt", new LightningBolt());
		spellHashMap.put("Thunderbolt", new Thunderbolt());
		spellHashMap.put("FlameCloak", new FlameCloak());
		
		// Restoration
		spellHashMap.put("LesserWard", new SpellWard("Lesser Ward", 5, Material.IRON_INGOT, 1));
		spellHashMap.put("SteadfastWard", new SpellWard("Steadfast Ward", 10, Material.SHIELD, 2));
		spellHashMap.put("GreaterWard", new SpellWard("Greater Ward", 15, Material.IRON_BLOCK, 3));
		spellHashMap.put("HealingCircle", new HealingCircle());
		
		// Alteration
		spellHashMap.put("Oakflesh", new Oakflesh());
		spellHashMap.put("Teleport", new Teleport());
		spellHashMap.put("IcarianFlight", new IcarianFlight());
		spellHashMap.put("Levitation", new Levitation());
		spellHashMap.put("Madness", new Madness());
		spellHashMap.put("Polymorph", new Polymorph());
		
		// Conjuration
		spellHashMap.put("SpectralArrow",  new SpectralArrow());
		
		// Conjuration: Summonings
		spellHashMap.put("SummonBlaze", new SummonCreature(EntityType.BLAZE, 100, Material.BLAZE_SPAWN_EGG));
		spellHashMap.put("SummonSkeleton", new SummonCreature(EntityType.SKELETON, 50, Material.SKELETON_SPAWN_EGG));
		spellHashMap.put("SummonZombie", new SummonCreature(EntityType.ZOMBIE, 25, Material.ZOMBIE_SPAWN_EGG));
		spellHashMap.put("SummonSpider", new SummonCreature(EntityType.SPIDER, 50, Material.SPIDER_SPAWN_EGG));
		spellHashMap.put("SummonCreeper", new SummonCreature(EntityType.CREEPER, 50, Material.CREEPER_SPAWN_EGG));
	}
	public Spell getSpell(String name) {
		if (!spellHashMap.containsKey(name)) {
			return null;
		}
		return spellHashMap.get(name);
	}
	public Collection<Spell> getSpellCollection(){
		return spellHashMap.values();
	}
	
}
