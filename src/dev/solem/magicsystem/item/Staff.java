package dev.solem.magicsystem.item;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import dev.solem.magicsystem.spell.Spell;

public class Staff {
	private Spell spell;
	public Staff(Spell spell) {
		this.spell = spell;
	}
	public ItemStack getItemStack() {
		ItemStack itemStack = new ItemStack(Material.DIAMOND_HOE);
		ItemMeta isMeta = itemStack.getItemMeta();
		List<String> lore = Arrays.asList(
				"",
				"§7"+this.spell.getDescription(), // description
				"§7School: "+this.spell.getSchoolAsString(),
				"§9"+this.spell.getManaCost()+" mana" // mana cost
				);
		
		isMeta.setDisplayName("§eStaff of " + this.spell.getName());
		isMeta.setLore(lore);
		isMeta.setEnchantmentGlintOverride(true);
		isMeta.setMaxStackSize(1);
		// required to make durability and tool breakage work
		if(isMeta instanceof Damageable) {
			Damageable dmgIsMeta = (Damageable)(isMeta);
			dmgIsMeta.setMaxDamage(1561);
			itemStack.setItemMeta(dmgIsMeta);
			return itemStack;
		}
		itemStack.setItemMeta(isMeta);
		return itemStack;
	}
	public Spell getSpell() {
		return this.spell;
	}
}
