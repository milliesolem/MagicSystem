package dev.solem.magicsystem;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.solem.magicsystem.particleanim.ParticleAnimation;
import dev.solem.magicsystem.particleanim.ParticleAnimationCatalog;

public class CommandPlayParticleAnimation implements CommandExecutor {
	private ParticleAnimationCatalog particleAnimationCatalog = new ParticleAnimationCatalog();
	
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	if (!(sender instanceof Player) || args.length!=1) {
    		return false;
    	}
    	Player player = (Player) sender;
    	ParticleAnimation particleAnimation = this.particleAnimationCatalog.getParticleAnimation(args[0].toLowerCase());
    	if (particleAnimation == null) {
    		player.sendMessage("§cParticle animation '" + args[0] + "' does not exist");
    		return false;
    	}
    	particleAnimation.playAnimation(player.getLocation());
        return true;
    }
}
