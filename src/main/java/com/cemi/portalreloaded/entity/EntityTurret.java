package com.cemi.portalreloaded.entity;

import java.util.Collections;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHandSide;
import net.minecraft.world.World;

public class EntityTurret extends EntityLivingBase {

	public boolean shouldOpen = false;
	public boolean isOpen = false;
	public float openingSpeed = 0.1f;
	int shootingTimer = 0;
	
	public EntityTurret(World worldIn) {
		super(worldIn);
	}

	@Override
	public void onUpdate() {
		List<EntityPlayer> players = world.getEntitiesWithinAABB(EntityPlayer.class, getEntityBoundingBox().grow(15.d));
		for (EntityPlayer player : players) {
			if(player.getPositionVector().subtract(getPositionVector()).normalize().dotProduct(getForward()) <= .5d) {
				shouldOpen = false;
			}
			if(player.getPositionVector().subtract(getPositionVector()).normalize().dotProduct(getForward()) > .5d) {
				shouldOpen = true;
				if(isOpen) {
					shootingTimer = (shootingTimer + 1) % 10;
					if(shootingTimer == 0) {
						System.out.println("TEST");
						System.out.println(player.attackEntityFrom(DamageSource.GENERIC, 3.f));
						//player.knockBack(this, 2.f, 0.d, 0.d);
					}
				}
			}
		}
		if(players.size() == 0) {
			shouldOpen = false;
		}
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
