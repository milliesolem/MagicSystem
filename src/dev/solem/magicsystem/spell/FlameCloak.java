package dev.solem.magicsystem.spell;

import java.time.Instant;
import java.util.Collection;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import dev.solem.magicsystem.MagicSystem;
import dev.solem.magicsystem.particleanim.FlameCloakAnim;
import dev.solem.magicsystem.particleanim.ParticleAnimation;

public class FlameCloak extends Spell{
	public FlameCloak() {
		this.setSchool(School.DESTRUCTION);
		this.setSpellType(SpellType.SELF);
		this.setName("Flame Cloak");
		this.setDescription("For 60 seconds, enemies in melee range take catch fire. Targets on fire take extra damage.");
		this.setManaCost(25);
		this.setCraftingComponent(Material.MAGMA_CREAM);
		this.setParticleAnimation(new FlameCloakAnim());
	}
	
	public void cast(Player player) {
		long now = Instant.now().getEpochSecond();
		MagicSystem plugin = MagicSystem.getInstance();
		final ParticleAnimation anim = this.getParticleAnimation();
		new BukkitRunnable() {
			ParticleAnimation a = anim;

			public void run() {
				Location location = player.getLocation();
				long delta = Instant.now().getEpochSecond();
				if(delta % 4 == 0) {
					a.playAnimation((Entity) player);
				}
				Collection<Entity> entities =  location.getWorld().getNearbyEntities(location, 4, 4, 4);
				for(Entity entity : entities) {
					if(entity.getEntityId() == player.getEntityId()) {
						continue;
					}
					if(entity instanceof Damageable && entity instanceof LivingEntity) {
						LivingEntity lentity = (LivingEntity) entity;
						
						double distance = location.distance(entity.getLocation());
						// not using Attribute.MAX_HEALTH because I think this convention is stupid
						if(distance <= 3.0) {
							lentity.setFireTicks(20 * 20);
						}
					}
				}
				if(delta > now + 60 || player.isDead()) {
					cancel();
				}
			}
		}.runTaskTimer(plugin, 0, 10);
	}
}
