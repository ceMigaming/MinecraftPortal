package com.cemi.portalreloaded.item;

import com.cemi.portalreloaded.PortalReloaded;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class PortalItems {

	//public static PortalItem testItem = new PortalItem("test_item").setCreativeTab(CreativeTabs.MATERIALS);

	public static PortalArmor longFallBoots = new PortalArmor(PortalReloaded.LONGFALL_BOOTS_MATERIAL, EntityEquipmentSlot.FEET, "longfall_boots");
	
	public static void register(IForgeRegistry<Item> registry) {
		registry.registerAll(
				//testItem
				longFallBoots
		);
	}
	
	public static void registerModels() {
		//testItem.registerItemModel();
		longFallBoots.registerItemModel();
	}
}
