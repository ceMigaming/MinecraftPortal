package com.cemi.portalreloaded.entity;

import java.util.Collections;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraft.world.World;

public class EntityTurret extends EntityLivingBase {

	public boolean shouldOpen = false;
	public boolean isOpen = false;
	public float openingSpeed = 0.1f;
	
	public EntityTurret(World worldIn) {
		super(worldIn);
	}

	@Override
	public void onUpdate() {
		
		super.onUpdate();
	}
	
	@Override
	public Iterable<ItemStack> getArmorInventoryList() {
		return Collections.EMPTY_LIST;
	}

	@Override
	public ItemStack getItemStackFromSlot(EntityEquipmentSlot slotIn) {
		return ItemStack.EMPTY;
	}
	
	@Override
	public void setItemStackToSlot(EntityEquipmentSlot slotIn, ItemStack stack) {
		
	}

	@Override
	public EnumHandSide getPrimaryHand() {
		return EnumHandSide.RIGHT;
	}
}
