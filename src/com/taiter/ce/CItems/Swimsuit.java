package com.taiter.ce.CItems;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;



public class Swimsuit extends CItem {

	int	DamageBoostLevel;
	int	SpeedBoostLevel;
	public String[] parts = {
			ChatColor.BLUE + "Scuba Mask",
			ChatColor.BLUE + "Upper Swimsuit",
			ChatColor.BLUE + "Lower Swimsuit",
			ChatColor.BLUE + "Flippers"
	};

	public Swimsuit(String originalName, ChatColor color, String lDescription, long lCooldown, Material mat) {
		super(originalName, color, lDescription, lCooldown, mat);
		configEntries.add("DamageBoostLevel: 4");
		configEntries.add("SpeedBoostLevel: 4");
	}// TODO: Use the Helmet as the main item

	@Override
	public boolean effect(Event event, final Player player) {

		if(player.getLocation().getBlock().getType().equals(Material.WATER) || player.getLocation().getBlock().getType().equals(Material.STATIONARY_WATER)) {
			if((player.getEquipment().getHelmet() != null && player.getEquipment().getHelmet().hasItemMeta() && player.getEquipment().getHelmet().getItemMeta().hasDisplayName() && player.getEquipment().getHelmet().getItemMeta().getDisplayName().equals(parts[0])) || (player.getEquipment().getChestplate() != null && player.getEquipment().getChestplate().hasItemMeta() && player.getEquipment().getChestplate().getItemMeta().hasDisplayName() && player.getEquipment().getChestplate().getItemMeta().getDisplayName().equals(parts[1])) || (player.getEquipment().getLeggings() != null && player.getEquipment().getLeggings().hasItemMeta() && player.getEquipment().getLeggings().getItemMeta().hasDisplayName() && player.getEquipment().getLeggings().getItemMeta().getDisplayName().equals(parts[2])) || (player.getEquipment().getBoots() != null && player.getEquipment().getBoots().hasItemMeta() && player.getEquipment().getBoots().getItemMeta().hasDisplayName() && player.getEquipment().getBoots().getItemMeta().getDisplayName().equals(parts[3]))) {

				player.setMetadata("ce." + getOriginalName() + ".lock", new FixedMetadataValue(main, null));

				new BukkitRunnable() {

					@Override
					public void run() {

						if(player.getLocation().getBlock().getType().equals(Material.WATER) || player.getLocation().getBlock().getType().equals(Material.STATIONARY_WATER)) {
							if((player.getEquipment().getHelmet() != null && player.getEquipment().getHelmet().hasItemMeta() && player.getEquipment().getHelmet().getItemMeta().hasDisplayName() && player.getEquipment().getHelmet().getItemMeta().getDisplayName().equals(parts[0])) || (player.getEquipment().getChestplate() != null && player.getEquipment().getChestplate().hasItemMeta() && player.getEquipment().getChestplate().getItemMeta().hasDisplayName() && player.getEquipment().getChestplate().getItemMeta().getDisplayName().equals(parts[1])) || (player.getEquipment().getLeggings() != null && player.getEquipment().getLeggings().hasItemMeta() && player.getEquipment().getLeggings().getItemMeta().hasDisplayName() && player.getEquipment().getLeggings().getItemMeta().getDisplayName().equals(parts[2])) || (player.getEquipment().getBoots() != null && player.getEquipment().getBoots().hasItemMeta() && player.getEquipment().getBoots().getItemMeta().hasDisplayName() && player.getEquipment().getBoots().getItemMeta().getDisplayName().equals(parts[3]))) {
								player.setRemainingAir(player.getMaximumAir());
								player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 120, SpeedBoostLevel), true);
								player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, DamageBoostLevel), true);

							} else {
								player.removeMetadata("ce." + getOriginalName() + ".lock", main);
							}

						} else {
							player.removeMetadata("ce." + getOriginalName() + ".lock", main);
						}
					}
				}.runTaskTimer(main, 0l, 80l);
			}

		}
		return true;
	}

	@Override
	public void initConfigEntries() {
		SpeedBoostLevel  = Integer.parseInt(getConfig().getString("Items.Swimsuit.SpeedBoostLevel"));
		DamageBoostLevel = Integer.parseInt(getConfig().getString("Items.Swimsuit.DamageBoostLevel"));
	}

}