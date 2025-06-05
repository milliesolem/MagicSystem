package dev.solem.magicsystem;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.solem.magicsystem.spell.Spell;
import dev.solem.magicsystem.spell.SpellCatalog;

public class CommandCast implements CommandExecutor {
	
	private SpellCatalog spellCatalog = new SpellCatalog();
	
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	if (!(sender instanceof Player) || args.length!=1) {
    		return false;
    	}
    	Player player = (Player) sender;
    	Spell spell = this.spellCatalog.getSpell(args[0]);
    	if (spell == null) {
    		player.sendMessage("§cSpell '" + args[0] + "' does not exist");
    		return false;
    	}
    	spell.cast(player);
        return true;
    }
}
