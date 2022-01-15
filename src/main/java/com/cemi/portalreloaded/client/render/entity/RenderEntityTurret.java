package com.cemi.portalreloaded.client.render.entity;

import com.cemi.portalreloaded.PortalReloaded;
import com.cemi.portalreloaded.client.render.entity.model.TurretModel;
import com.cemi.portalreloaded.entity.EntityTurret;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderEntityTurret extends RenderLivingBase<EntityTurret> {

	private static final ResourceLocation texture = new ResourceLocation(PortalReloaded.MODID,
			"textures/entity/turret.png");
	private static ModelBase model = new TurretModel();

	public RenderEntityTurret(RenderManager renderManager) {
		super(renderManager, model, 1.0f);
	}

	@Override
	protected boolean canRenderName(EntityTurret entity) {
		return false;
	}

	float t, m;
	
	@Override
	public void doRender(EntityTurret entity, double x, double y, double z, float entityYaw, float partialTicks) {
		float speed = 0.075f;
		t += m * partialTicks * speed;
		if(t <= -1) {
			entity.shouldOpen = true;
			m = 1;
		}
		if(t >= 2) {
			entity.shouldOpen = false;
			m = -1;
		}
		System.out.println(entity.shouldOpen);
		
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityTurret entity) {
		return texture;
	}

}
