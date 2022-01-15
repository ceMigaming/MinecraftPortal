package com.cemi.portalreloaded.block;

import com.cemi.portalreloaded.PortalReloaded;
import com.cemi.portalreloaded.client.PortalTabs;

import me.ichun.mods.ichunutil.common.core.util.EntityHelper;
import me.ichun.mods.portalgun.common.PortalGun;
import me.ichun.mods.portalgun.common.entity.EntityPortalProjectile;
import me.ichun.mods.portalgun.common.portal.world.PortalPlacement;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

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

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if (!(entityIn instanceof EntityPortalProjectile) || worldIn.isRemote)
			return;

		EntityPortalProjectile projectile = ((EntityPortalProjectile) entityIn);

		if (projectile.shooter == null)
			return;

		Vec3d posVec = new Vec3d(pos);
		Vec3d entVec = projectile.shooter.getPositionVector();
		RayTraceResult result = EntityHelper.rayTraceBlocks(worldIn,
				posVec.distanceTo(projectile.shooter.getPositionVector()),
				posVec.add(entVec.subtract(posVec).normalize()), entVec, PortalGun.config.canFireThroughLiquid == 0,
				false, false, false);
		if (result != null) {
			if (worldIn.getBlockState(result.getBlockPos()).getMaterial() == Material.GLASS)
				if (projectile.portalInfo != null) {
					projectile.portalHeight = 1;
					PortalPlacement portalPlacement = projectile.portalInfo
							.getPortalPlacement(projectile.shooter.world);
					if (portalPlacement != null) {
						portalPlacement.remove(BlockPos.ORIGIN);
					}
				}
		}
	}
}
