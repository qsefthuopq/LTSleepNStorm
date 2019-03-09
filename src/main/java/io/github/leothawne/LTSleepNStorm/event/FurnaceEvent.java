/*
 * Copyright (C) 2019 Murilo Amaral Nappi
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.leothawne.LTSleepNStorm.event;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryType.SlotType;

import io.github.leothawne.LTSleepNStorm.item.BottleOfCoffeeItem;
import io.github.leothawne.LTSleepNStorm.item.WaterWithCoffeeItem;

public class FurnaceEvent implements Listener {
	@EventHandler(priority = EventPriority.HIGHEST)
	public static final void onFurnaceSmelt(FurnaceSmeltEvent event) {
		if(event.getResult().equals(BottleOfCoffeeItem.getItemStack())) {
			if(event.getSource().equals(WaterWithCoffeeItem.getItemStack()) == false) {
				event.setCancelled(true);
			}
		}
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public static final void onFurnaceClick(InventoryClickEvent event) {
		if(event.getInventory().getType().equals(InventoryType.FURNACE)) {
			if(event.getSlotType().equals(SlotType.CRAFTING)) {
				if(event.getAction().equals(InventoryAction.PLACE_ALL) || event.getAction().equals(InventoryAction.PLACE_ONE) || event.getAction().equals(InventoryAction.PLACE_SOME)) {
					if(event.getCursor().getType().equals(Material.POTION)) {
						if(event.getCurrentItem().equals(WaterWithCoffeeItem.getItemStack()) == false) {
							event.setCancelled(true);
							event.getWhoClicked().sendMessage("no");
						}
					}
				}
			}
		}
	}
}