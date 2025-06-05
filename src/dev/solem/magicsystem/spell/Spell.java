package dev.solem.magicsystem.spell;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import dev.solem.magicsystem.particleanim.ParticleAnimation;

public class Spell {
	
	private School school;
	private SpellType spellType;
	private String name;
	private String description;
	private int manaCost;
	private Material craftingComponent;
	private ParticleAnimation particleAnimation;
	
	public Spell() {
		
	}
	public void cast(Player player) {
		
	}
	public void cast(PlayerInteractEvent event) {
		this.cast(event.getPlayer());
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public School getSchool() {
		return this.school;
	}
	public String getSchoolAsString() {
		switch(this.school) {
		case School.ALTERATION:
			return "Alteration";
		case School.DESTRUCTION:
			return "Destruction";
		case School.RESTORATION:
			return "Restoration";
		case School.CONJURATION:
			return "Conjuration";
		default:
			break;
		}
		return "Unknown";
	}
	
	public void setSchool(School school) {
		this.school = school;
	}
	public SpellType getSpellType() {
		return this.spellType;
	}
	public void setSpellType(SpellType spellType) {
		this.spellType = spellType;
	}
	public int getManaCost() {
		return this.manaCost;
	}
	public void setManaCost(int cost) {
		this.manaCost = cost;
	}
	public Material getCraftingComponent() {
		return this.craftingComponent;
	}
	public void setCraftingComponent(Material component) {
		this.craftingComponent = component;
	}
	public void setParticleAnimation(ParticleAnimation particleAnimation) {
		this.particleAnimation = particleAnimation;
	}
	public ParticleAnimation getParticleAnimation() {
		return this.particleAnimation;
	}
}
