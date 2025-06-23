package dev.solem.magicsystem.spell;

import java.time.Instant;
import java.util.Collection;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import dev.solem.magicsystem.MagicSystem;
import dev.solem.magicsystem.particleanim.HealingCircleAnim;
import dev.solem.magicsystem.particleanim.ParticleAnimation;

public class HealingCircle extends Spell{
	public HealingCircle() {
		this.setSchool(School.RESTORATION);
		this.setSpellType(SpellType.SELF);
		this.setName("Healing Circle");
		this.setDescription("Forms an arcane circle for 60 seconds healing anyone inside it.");
		this.setManaCost(25);
		this.setCraftingComponent(Material.GLISTERING_MELON_SLICE);
		this.setParticleAnimation(new HealingCircleAnim());
	}
	
	public void cast(Player player) {
		Location location = player.getLocation();
		long now = Instant.now().getEpochSecond();
		MagicSystem plugin = MagicSystem.getInstance();
		final ParticleAnimation anim = this.getParticleAnimation();
		new BukkitRunnable() {
			ParticleAnimation a = anim;
			@SuppressWarnings("deprecation")
			public void run() {
				a.playAnimation(location, location.getDirection());
				Collection<Entity> entities =  location.getWorld().getNearbyEntities(location, 4, 4, 4);
				for(Entity entity : entities) {
					if(entity instanceof Damageable && entity instanceof LivingEntity) {
						LivingEntity lentity = (LivingEntity) entity;
						
						double distance = location.distance(entity.getLocation());
						// not using Attribute.MAX_HEALTH because I think this convention is stupid
						if(distance <= 3.0 && lentity.getHealth()+0.5 < lentity.getMaxHealth()) {
							lentity.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20 * 3, 1));
							lentity.setHealth(lentity.getHealth() + 0.5);
						}
					}
				}
				if(Instant.now().getEpochSecond() > now + 60) {
					cancel();
				}
			}
		}.runTaskTimer(plugin, 0, 10);
	}
}
