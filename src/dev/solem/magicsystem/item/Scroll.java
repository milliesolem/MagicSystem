package dev.solem.magicsystem.item;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import dev.solem.magicsystem.spell.Spell;

public class Scroll implements Listener {
	private Spell spell;
	public Scroll(Spell spell) {
		this.spell = spell;
	}
	public ItemStack getItemStack() {
		ItemStack itemStack = new ItemStack(Material.PAPER);
		ItemMeta isMeta = itemStack.getItemMeta();
		List<String> lore = Arrays.asList(
				"",
				"§7"+this.spell.getDescription(), // description
				"§7School: "+this.spell.getSchoolAsString(),
				"§9"+this.spell.getManaCost()+" mana" // mana cost
				);
		
		isMeta.setDisplayName("§eScroll: " + this.spell.getName());
		isMeta.setLore(lore);
		isMeta.setEnchantmentGlintOverride(true);
		isMeta.setMaxStackSize(16);
		itemStack.setItemMeta(isMeta);
		return itemStack;
	}
	public Spell getSpell() {
		return this.spell;
	}
}
