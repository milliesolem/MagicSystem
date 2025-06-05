package dev.solem.magicsystem.item;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import dev.solem.magicsystem.spell.Spell;

public class SpellTome {
	
	private Spell spell;
	public SpellTome(Spell spell) {
		this.spell = spell;
	}
	public ItemStack getItemStack() {
		ItemStack itemStack = new ItemStack(Material.ENCHANTED_BOOK);
		ItemMeta isMeta = itemStack.getItemMeta();
		List<String> lore = Arrays.asList(
				"",
				"§7"+this.spell.getDescription(), // description
				"§7School: "+this.spell.getSchoolAsString(), // school
				"§9"+this.spell.getManaCost()+" mana" // mana cost
			);
		
		isMeta.setDisplayName("§eSpell Tome: " + this.spell.getName());
		isMeta.setLore(lore);
		isMeta.setEnchantmentGlintOverride(true);
		itemStack.setItemMeta(isMeta);
		return itemStack;
	}
}
