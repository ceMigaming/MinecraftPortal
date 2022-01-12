package com.cemi.portalreloaded.client.render.entity;

import org.lwjgl.opengl.GL11;

import com.cemi.portalreloaded.PortalReloaded;
import com.cemi.portalreloaded.client.render.entity.model.StorageCubeModel;
import com.cemi.portalreloaded.entity.EntityStorageCube;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderEntityCube extends RenderLivingBase<EntityStorageCube> {

	private static final ResourceLocation texture = new ResourceLocation(PortalReloaded.MODID,
			"textures/entity/storage_cube.png");
	private static ModelBase model = new StorageCubeModel();

	public RenderEntityCube(RenderManager renderManagerIn) {
		super(renderManagerIn, model, 1.0f);
	}

	@Override
	protected boolean canRenderName(EntityStorageCube entity) {
		return false;
	}
	
	@Override
	public void doRender(EntityStorageCube entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityStorageCube entity) {
		return texture;
	}
}
