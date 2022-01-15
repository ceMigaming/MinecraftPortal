package com.cemi.portalreloaded.block;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import com.cemi.portalreloaded.utility.InventoryUtilities;

import me.ichun.mods.ichunutil.common.item.ItemHandler;
import me.ichun.mods.portalgun.common.PortalGun;
import me.ichun.mods.portalgun.common.item.ItemPortalGun;
import me.ichun.mods.portalgun.common.portal.info.PortalInfo;
import me.ichun.mods.portalgun.common.portal.world.PortalPlacement;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockFizzlerField extends PortalBlock {
	public static final PropertyDirection FACING = BlockDirectional.FACING;

	public BlockFizzlerField() {
		super(Material.GLASS, "fizzler_field_block");
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return null;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		AxisAlignedBB bb = new AxisAlignedBB(0, 0, 0, 1, 1, 1);
		EnumFacing enumfacing = (EnumFacing) state.getValue(FACING);
		switch (enumfacing) {
		case DOWN:
		case UP:
			bb = new AxisAlignedBB(0, 7.5d / 16.d, 0, 1, 8.5d / 16.d, 1);
			break;
		case NORTH:
		case SOUTH:
			bb = new AxisAlignedBB(0, 0, 7.5d / 16.d, 1, 1, 8.5d / 16.d);
			break;
		case EAST:
		case WEST:
			bb = new AxisAlignedBB(7.5d / 16.d, 0, 0, 8.5d / 16.d, 1, 1);
			break;

		default:
			break;
		}

		return bb;
	}
	
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		super.onBlockAdded(worldIn, pos, state);
		this.setDefaultDirection(worldIn, pos, state);
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		worldIn.setBlockState(pos,
				state.withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer)), 2);
	}

	private void setDefaultDirection(World worldIn, BlockPos pos, IBlockState state) {
		if (!worldIn.isRemote) {
			EnumFacing enumfacing = (EnumFacing) state.getValue(FACING);
			boolean flag = worldIn.getBlockState(pos.north()).isFullBlock();
			boolean flag1 = worldIn.getBlockState(pos.south()).isFullBlock();

			if (enumfacing == EnumFacing.NORTH && flag && !flag1) {
				enumfacing = EnumFacing.SOUTH;
			} else if (enumfacing == EnumFacing.SOUTH && flag1 && !flag) {
				enumfacing = EnumFacing.NORTH;
			} else {
				boolean flag2 = worldIn.getBlockState(pos.west()).isFullBlock();
				boolean flag3 = worldIn.getBlockState(pos.east()).isFullBlock();

				if (enumfacing == EnumFacing.WEST && flag2 && !flag3) {
					enumfacing = EnumFacing.EAST;
				} else if (enumfacing == EnumFacing.EAST && flag3 && !flag2) {
					enumfacing = EnumFacing.WEST;
				}
			}

			worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		removePortal(worldIn, entityIn);
	}
	
	public void removePortal(World worldIn, Entity entityIn) {
		if (worldIn.isRemote)
			return;
		if (!(entityIn instanceof EntityPlayer)) {
			entityIn.setDead();
			return;
		}
		EntityPlayer player = (EntityPlayer) entityIn;
		int slot = InventoryUtilities.findItem(player.inventory, PortalGun.itemPortalGun);
		if (slot == -1)
			return;
		ItemStack portalGun = player.inventory.getStackInSlot(slot);
		NBTTagCompound NBT = portalGun.getTagCompound();

		HashSet<PortalInfo> infoHS = PortalGun.eventHandlerServer.getWorldSaveData(player.dimension).portalList;
		ArrayList<PortalInfo> infoList = new ArrayList<PortalInfo>();

		for (final PortalInfo info : infoHS) {
			if (info.uuid.equals(NBT.getString("uuid")) && info.channelName.equals(NBT.getString("channelName"))) {
				infoList.add(info);
			}
		}

		for (final PortalInfo info : infoList) {
			PortalPlacement portalPlacement = info.getPortalPlacement(player.world);
			if (portalPlacement != null) {
				portalPlacement.remove(BlockPos.ORIGIN);
			}
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.getFront(meta & 7));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i = i | ((EnumFacing) state.getValue(FACING)).getIndex();

		return i;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}

	public int quantityDropped(Random random) {
		return 0;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

}
