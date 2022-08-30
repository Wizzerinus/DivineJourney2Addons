package org.btpos.dj2addons.mixin.def.tweaks.rftools;

import mcjty.rftools.crafting.NBTMatchingRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import org.btpos.dj2addons.mixin.init.minecraftforge.IngredientNBTAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(NBTMatchingRecipe.class)
public abstract class MNBTMatchingRecipe {
	@Redirect(
			remap = false,
			method = "getIngredients",
			at = @At(
					target = "Lnet/minecraft/item/crafting/Ingredient;fromStacks([Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/crafting/Ingredient;",
					value = "INVOKE"
			)
	)
	private static Ingredient toNBTIngredient(ItemStack[] itemstack) {
		return IngredientNBTAccessor.createIngredientNBT(itemstack[0]);
	}
}
