package dev.solem.magicsystem;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;

import dev.solem.magicsystem.spell.Spell;
import dev.solem.magicsystem.spell.SpellCatalog;
import dev.solem.magicsystem.spell.SpellType;
import dev.solem.magicsystem.item.Scroll;
import dev.solem.magicsystem.item.SpellTome;

public class MagicSystem extends JavaPlugin implements Listener {
	
	private SpellCatalog spellCatalog = new SpellCatalog();
	
	@Override
	public void onLoad() {
		this.loadRecipes();
		
	}
	
	@Override
	public void onEnable() {
		this.getCommand("cast").setExecutor(new CommandCast()); // command to cast spells
		this.getCommand("playpa").setExecutor(new CommandPlayParticleAnimation()); // debugging command for particle animations
		Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	@SuppressWarnings("deprecation")
	private void loadRecipes() {
		
		for(Spell spell:spellCatalog.getSpellCollection()) {
			ItemStack spellTome = new SpellTome(spell).getItemStack();
			ShapedRecipe recipeSpellTome = new ShapedRecipe(spellTome);
			recipeSpellTome.shape("CAC","ABA","CAC");
			recipeSpellTome.setIngredient('C', spell.getCraftingComponent());
			recipeSpellTome.setIngredient('B', Material.BOOK);
			recipeSpellTome.setIngredient('A', Material.AMETHYST_BLOCK);
			getServer().addRecipe(recipeSpellTome);
			
			ItemStack scroll = new Scroll(spell).getItemStack();
			ShapedRecipe recipeScroll = new ShapedRecipe(scroll);
			recipeScroll.shape("CAC","ABA","CAC");
			recipeScroll.setIngredient('C', spell.getCraftingComponent());
			recipeScroll.setIngredient('B', Material.PAPER);
			recipeScroll.setIngredient('A', Material.AMETHYST_SHARD);
			getServer().addRecipe(recipeScroll);
		}
		
	}
	
	@EventHandler(priority=EventPriority.HIGH)
	private void onRightClick(PlayerInteractEvent event) {
	    Player player = event.getPlayer();
	    ItemMeta itemInHandMeta = player.getInventory().getItemInMainHand().getItemMeta();
	    if (itemInHandMeta == null) {
	    	return;
	    }
	    String itemName = itemInHandMeta.getDisplayName();
	    if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
	    	// Scroll use
		    if(itemName.startsWith("§eScroll: ")) {
		    	Spell spellToCast = spellCatalog.getSpell(itemName.substring(10).replaceAll("\s", ""));
		    	spellToCast.cast(event);
		    	if (spellToCast.getSpellType() == SpellType.CONCENTRATION) {
		    		return;
		    	}
		    	// creative mode don't consume scrolls
		    	if(player.getGameMode() != GameMode.CREATIVE) {
		    		player.getInventory().removeItem(player.getInventory().getItemInMainHand());
		    	}
		    }
	    }
	}
	
}
