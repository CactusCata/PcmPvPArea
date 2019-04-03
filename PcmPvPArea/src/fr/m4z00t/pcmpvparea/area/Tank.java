package fr.m4z00t.pcmpvparea.area;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.m4z00t.pcmpvparea.utils.ItemBuilder;

public class Tank extends Area {

	@Override
	public Location getSpawnLocation() {
		return new Location(Bukkit.getWorlds().get(0), 100.0d, 50.0d, 100.0d, 0.0f, 5.0f);
	}

	@Override
	public String getName() {
		return "PvP Tank";
	}

	@Override
	public ItemStack[] getArmor() {
		return new ItemStack[] {
				new ItemBuilder(Material.DIAMOND_BOOTS).setDisplayName("§aPantoufles").setUnbreakable()
						.addEnchantement(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
						.addItemFlags(ItemFlag.HIDE_UNBREAKABLE).build(),
				new ItemBuilder(Material.DIAMOND_LEGGINGS).setDisplayName("§eJambières").setUnbreakable()
						.addEnchantement(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
						.addItemFlags(ItemFlag.HIDE_UNBREAKABLE).build(),
				new ItemBuilder(Material.DIAMOND_CHESTPLATE).setDisplayName("§3Plastron").setUnbreakable()
						.addEnchantement(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
						.addItemFlags(ItemFlag.HIDE_UNBREAKABLE).build(),
				new ItemBuilder(Material.DIAMOND_HELMET).setDisplayName("§cCasque").setUnbreakable()
						.addEnchantement(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
						.addItemFlags(ItemFlag.HIDE_UNBREAKABLE).build() };
	}

	@Override
	public Inventory getItems() {

		Inventory inventory = Bukkit.createInventory(null, 36);

		inventory.setItem(0, new ItemBuilder(Material.BOW).setDisplayName("§5OneShot").setUnbreakable()
				.addEnchantement(Enchantment.ARROW_DAMAGE, 6).addEnchantement(Enchantment.ARROW_FIRE, 1)
				.addEnchantement(Enchantment.ARROW_INFINITE, 1).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).build());

		inventory.setItem(1,
				new ItemBuilder(Material.DIAMOND_SWORD).setDisplayName("§4§lKasai").setUnbreakable()
						.addEnchantement(Enchantment.DAMAGE_ALL, 5).addEnchantement(Enchantment.FIRE_ASPECT, 2)
						.addItemFlags(ItemFlag.HIDE_UNBREAKABLE).build());

		inventory.setItem(2, new ItemBuilder(Material.GOLDEN_APPLE, 5).setDisplayName("§6§lDragon's Power").build());

		inventory.setItem(9, new ItemBuilder(Material.ARROW).setDisplayName("§f§lPick").build());

		ItemStack strPotion = new ItemStack(Material.POTION, 1);
		PotionMeta strPotionM = (PotionMeta) strPotion.getItemMeta();
		strPotionM.setDisplayName("§8§lStrength Potion");
		strPotionM.addCustomEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 180, 1), true);
		strPotion.setItemMeta(strPotionM);
		Potion po = new Potion((byte) 16393);
		po.apply(strPotion);

		inventory.setItem(3, po.toItemStack(1));
		inventory.setItem(30, po.toItemStack(1));

		ItemStack speedPotion = new ItemStack(Material.POTION, 1);
		PotionMeta speedPotionM = (PotionMeta) speedPotion.getItemMeta();
		speedPotionM.setDisplayName("§b§lSpeed Potion");
		speedPotionM.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 67, 2), true);
		speedPotion.setItemMeta(speedPotionM);
		Potion po2 = new Potion((byte) 16418);
		po2.apply(speedPotion);

		inventory.setItem(4, po2.toItemStack(1));
		inventory.setItem(31, po2.toItemStack(1));
		inventory.setItem(22, po2.toItemStack(1));
		inventory.setItem(13, po2.toItemStack(1));
		inventory.setItem(12, po2.toItemStack(1));
		inventory.setItem(21, po2.toItemStack(1));

		ItemStack firePotion = new ItemStack(Material.POTION, 1);
		PotionMeta firePotionM = (PotionMeta) firePotion.getItemMeta();
		firePotionM.setDisplayName("§6§lFire Potion");
		firePotionM.addCustomEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20 * 180, 1), true);
		firePotion.setItemMeta(firePotionM);
		Potion po3 = new Potion((byte) 3);
		po3.apply(firePotion);

		inventory.setItem(8, po3.toItemStack(1));
		inventory.setItem(35, po3.toItemStack(1));

		ItemStack slowPotion = new ItemStack(Material.POTION, 1);
		PotionMeta slowPotionM = (PotionMeta) slowPotion.getItemMeta();
		slowPotionM.setDisplayName("§8§lSlown Potion");
		slowPotionM.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 67, 1), true);
		slowPotion.setItemMeta(slowPotionM);
		Potion po4 = new Potion((byte) 16426);
		po4.apply(slowPotion);

		inventory.setItem(17, po4.toItemStack(1));
		inventory.setItem(26, po4.toItemStack(1));

		ItemStack poisonPotion = new ItemStack(Material.POTION, 1);
		PotionMeta poisonPotionM = (PotionMeta) firePotion.getItemMeta();
		firePotionM.setDisplayName("§2§lPoison Potion");
		firePotionM.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 20 * 16, 1), true);
		firePotion.setItemMeta(poisonPotionM);
		Potion po5 = new Potion((byte) 16420);
		po5.apply(poisonPotion);

		inventory.setItem(16, po5.toItemStack(1));
		inventory.setItem(25, po5.toItemStack(1));

		ItemStack healPotion = new ItemStack(Material.POTION, 1);
		PotionMeta healPotionM = (PotionMeta) healPotion.getItemMeta();
		healPotionM.setDisplayName("§c§lLife Potion");
		healPotionM.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 67, 1), true);
		healPotion.setItemMeta(healPotionM);
		Potion po6 = new Potion((byte) 16421);
		po6.apply(healPotion);

		for (int i = 18; i < 35; i++)
			inventory.addItem(po6.toItemStack(1));

		return inventory;
	}
}
