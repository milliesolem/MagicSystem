package dev.solem.magicsystem.spell;

import java.util.Collection;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import dev.solem.magicsystem.particleanim.Levitate;

public class Madness extends Spell{
	
	public Madness() {
		this.setSchool(School.ALTERATION);
		this.setSpellType(SpellType.TARGET);
		this.setName("Madness");
		this.setDescription("Casts a random spell.");
		this.setManaCost(0);
		this.setCraftingComponent(Material.FISHING_ROD);
		this.setParticleAnimation(new Levitate());
	}
	public void cast(Player player) {
		SpellCatalog spellCatalog = new SpellCatalog();
		Collection<Spell> spells = spellCatalog.getSpellCollection();
		int index = (int) (Math.random() * spells.size());
		int i = 0;
		for(Spell spell : spells) {
			i++;
			if(spell.getSpellType() == SpellType.CONCENTRATION) {
				continue;
			}
			if(i >= index) {
				spell.cast(player);
				break;
			}
		}
	}
}
