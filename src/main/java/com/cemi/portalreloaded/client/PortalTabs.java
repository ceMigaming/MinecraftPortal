package com.cemi.portalreloaded.client;

import com.cemi.portalreloaded.block.PortalBlocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class PortalTabs extends CreativeTabs {
	
	public PortalTabs(String label) {
		super(label);
	}

	public static final CreativeTabs PORTAL_BUILDING_BLOCKS = new CreativeTabs("portalBuildingBlocks")
    {
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem()
        {
            return new ItemStack(Item.getItemFromBlock(PortalBlocks.smallConcreteTile));
        }
    };
    
    public static final CreativeTabs PORTAL_REDSTONE = new CreativeTabs("portalBuildingBlocks")
    {
    	@SideOnly(Side.CLIENT)
    	public ItemStack getTabIconItem()
    	{
    		return new ItemStack(Item.getItemFromBlock(PortalBlocks.ballLauncher));
    	}
    };

	@Override
	public abstract ItemStack getTabIconItem();
    
}
