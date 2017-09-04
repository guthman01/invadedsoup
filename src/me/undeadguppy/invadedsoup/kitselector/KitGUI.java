package me.undeadguppy.invadedsoup.kitselector;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.undeadguppy.invadedsoup.kit.Kit;
import me.undeadguppy.invadedsoup.kit.KitManager;
import net.md_5.bungee.api.ChatColor;

public class KitGUI {

	private String title;
	private Inventory inv;
	private ArrayList<String> purchased;
	private ArrayList<String> selling;

	public KitGUI(String title, int size) {
		this.purchased = new ArrayList<String>();
		this.purchased.add(ChatColor.GREEN + "Owned");
		this.selling = new ArrayList<String>();
		this.selling.add(ChatColor.RED + "Purchase: /kitshop!");
		this.title = title;
		this.inv = Bukkit.getServer().createInventory(null, size, title);
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setupIcons(Player p) {
		this.inv.clear();
		for (Kit k : KitManager.getInstance().getRegisteredKits()) {
			ItemStack icon = k.getIcon();
			ItemMeta meta = icon.getItemMeta();
			meta.setDisplayName(k.getName());
			if (p.hasPermission(k.getPermission())) {
				meta.setLore(purchased);
			} else {
				meta.setLore(selling);
			}
			icon.setItemMeta(meta);
			inv.addItem(icon);
			p.openInventory(inv);
		}
	}

	public Inventory getInventory() {
		return inv;
	}

}
