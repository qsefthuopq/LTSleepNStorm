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
package io.github.leothawne.LTSleepNStorm.item;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.leothawne.LTSleepNStorm.LTSleepNStorm;

public class WaterWithCoffeeItem {
	public static final ItemStack getItemStack() {
		ItemStack item = new ItemStack(getMaterial());
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(getName());
		meta.setLore(getLore());
		item.setItemMeta(meta);
		item.setAmount(1);
		return item;
	}
	public static final Material getMaterial() {
		return Material.POTION;
	}
	public static final String getRecipeId() {
		return "water_coffee";
	}
	public static final Recipe getRecipe(LTSleepNStorm plugin) {
		ShapelessRecipe recipe = new ShapelessRecipe(new NamespacedKey(plugin, getRecipeId()), getItemStack());
		recipe.addIngredient(1, Material.POTION);
		recipe.addIngredient(3, Material.COCOA_BEANS);
		return recipe;
	}
	public static final String getName() {
		return "Water with Coffee";
	}
	public static final List<String> getLore(){
		return Arrays.asList("Uh, this looks like disgusting!", "Maybe you forgot to do something.");
	}
}