package dev.solem.magicsystem.spell;

import java.util.Collection;
import java.util.HashMap;

public class SpellCatalog {
	private HashMap<String, Spell> spellHashMap = new HashMap<String, Spell>();
	
	// note to self:
	// to make the spell catalog command-friendly,
	// DNOT
	public SpellCatalog() {
		spellHashMap.put("Fireball", new SpellFireball());
		spellHashMap.put("Teleport", new Teleport());
		spellHashMap.put("Flames",  new Flames());
		spellHashMap.put("Firebolt",  new Firebolt());
		spellHashMap.put("LightningBolt", new LightningBolt());
		spellHashMap.put("LesserWard", new LesserWard());
		spellHashMap.put("Oakflesh", new Oakflesh());
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
