package dev.solem.magicsystem.spell;

import java.util.Collection;
import java.util.HashMap;

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
		spellHashMap.put("LightningBolt", new LightningBolt());
		spellHashMap.put("Thunderbolt", new Thunderbolt());
		
		// Restoration
		spellHashMap.put("LesserWard", new LesserWard());
		
		// Alteration
		spellHashMap.put("Oakflesh", new Oakflesh());
		spellHashMap.put("Teleport", new Teleport());
		spellHashMap.put("IcarianFlight", new IcarianFlight());
		spellHashMap.put("Levitation", new Levitation());
		spellHashMap.put("Madness", new Madness());
		spellHashMap.put("Polymorph", new Polymorph());
		
		// Conjuration
		spellHashMap.put("SummonBlaze", new SummonBlaze());
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
