package com.cemi.portalreloaded.block;

import com.cemi.portalreloaded.PortalReloaded;
import com.cemi.portalreloaded.client.PortalTabs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class PortalBlock extends Block {

	protected String name;

	public PortalBlock(Material material, String name) {
		super(material);
	
		this.name = name;
	
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(PortalTabs.PORTAL_BUILDING_BLOCKS);
	}
	
	public void registerItemModel(Item itemBlock) {
		PortalReloaded.proxy.registerItemRenderer(itemBlock, 0, name);
	}
	
	public Item createItemBlock() {
		return new ItemBlock(this).setRegistryName(getRegistryName());
	}
	
	@Override
	public PortalBlock setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
	
}
