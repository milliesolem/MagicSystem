package dev.solem.magicsystem;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
//import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;

import dev.solem.magicsystem.spell.Spell;
import dev.solem.magicsystem.spell.SpellCatalog;
import dev.solem.magicsystem.spell.SpellType;
import dev.solem.magicsystem.item.Scroll;
import dev.solem.magicsystem.item.SpellTome;
import dev.solem.magicsystem.item.Staff;

public class MagicSystem extends JavaPlugin implements Listener {
	
	private static MagicSystem instance;
	private SpellCatalog spellCatalog = new SpellCatalog();
	private HashMap<Player, Collection<LivingEntity>> conjuredMinions = new HashMap<Player, Collection<LivingEntity>>();
	
	@Override
	public void onLoad() {
		this.loadRecipes();
		
	}
	
	@Override
	public void onEnable() {
		instance = this;
		this.getCommand("cast").setExecutor(new CommandCast()); // command to cast spells
		this.getCommand("playpa").setExecutor(new CommandPlayParticleAnimation()); // debugging command for particle animations
		Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public void onDisable() {
		
		// purge conjured minions
		Set<Player> playersWithConjuredMinions  = conjuredMinions.keySet();
		for(Player player:playersWithConjuredMinions) {
			Collection<LivingEntity> minions = conjuredMinions.get(player);
			for(LivingEntity minion:minions) {
				minion.remove();
			}
		}
	}
	public static MagicSystem getInstance() {
		return instance;
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
			
			ItemStack staff = new Staff(spell).getItemStack();
			ShapedRecipe recipeStaff = new ShapedRecipe(staff);
			recipeStaff.shape("CAC","ABA","CAC");
			recipeStaff.setIngredient('C', spell.getCraftingComponent());
			recipeStaff.setIngredient('B', Material.DIAMOND_HOE);
			recipeStaff.setIngredient('A', Material.AMETHYST_SHARD);
			getServer().addRecipe(recipeStaff);
		}
		
	}
	
	@EventHandler(priority=EventPriority.HIGH)
	private void onRightClick(PlayerInteractEvent event) {
	    Player player = event.getPlayer();
	    ItemStack itemInMH = player.getInventory().getItemInMainHand();
	    ItemMeta itemInHandMeta = itemInMH.getItemMeta();
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
		    		itemInMH.setAmount(itemInMH.getAmount() - 1);
		    		//player.getInventory().removeItem(player.getInventory().getItemInMainHand());
		    	}
		    }
		    else if(itemName.startsWith("§eStaff of")) {
		    	Spell spellToCast = spellCatalog.getSpell(itemName.substring(10).replaceAll("\s", ""));
		    	spellToCast.cast(event);
		    	// creative mode don't consume staff durability
		    	if(player.getGameMode() != GameMode.CREATIVE) {
		    		if (itemInHandMeta instanceof Damageable) {
		    			Damageable metaDmg = ((Damageable) itemInHandMeta);
		    			if(!metaDmg.hasMaxDamage()) {
		    				return;
		    			}
		    			metaDmg.setDamage(metaDmg.getDamage() + spellToCast.getManaCost());
		    			// break staff when durability is used
		    			if(metaDmg.getMaxDamage() <= metaDmg.getDamage()) {
		    				player.getInventory().removeItem(itemInMH);
		    				player.playSound(player.getLocation(), Sound.ITEM_SHIELD_BREAK, 1, 1);
		    			}
		    			itemInMH.setItemMeta(metaDmg);
		    			return;
		    		}
		    	}
		    }
	    }
	}
	
	public void addMinion(Player player, LivingEntity minion) {
		Collection<LivingEntity> minions = this.conjuredMinions.get(player);
		if(minions == null) {
			this.conjuredMinions.put(player, new ArrayList<LivingEntity>());
		}
		this.conjuredMinions.get(player).add(minion);
	}
	
	// Conjured minions
    @EventHandler
    public void onEntityTarget(EntityTargetLivingEntityEvent  event){
        if (event.getTarget() instanceof Player){
        	Player player = (Player)event.getTarget();
        	Entity targetingEntity = event.getEntity();
        	String customName = targetingEntity.getCustomName();
            if (customName != null && customName.startsWith(player.getDisplayName())){
            	event.setTarget(null);
                event.setCancelled(true);
                return;
            }
            else if(conjuredMinions.get(player) != null){
            	for(LivingEntity minion:conjuredMinions.get(player)) {
            		if (minion == null) {
            			conjuredMinions.get(player).remove(null);
            			continue;
            		}
            		Mob minionMob = (Mob) minion;
            		minionMob.setTarget((LivingEntity) targetingEntity);
            	}
            }
        }
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        // wipe minions
        if(conjuredMinions.get(player) != null){
        	Collection<LivingEntity> minions = conjuredMinions.get(player);
			for(LivingEntity minion:minions) {
				minion.remove();
			}
        }
        conjuredMinions.put(player, null);
    }
}
