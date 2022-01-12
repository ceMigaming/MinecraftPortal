package com.cemi.portalreloaded.entity;

import javax.annotation.Nullable;

import com.cemi.portalreloaded.PortalReloaded;
import com.cemi.portalreloaded.client.render.entity.RenderEntityCube;
import com.cemi.portalreloaded.client.render.entity.RenderEntityHEP;
import com.cemi.portalreloaded.client.render.entity.RenderEntityPivotCube;
import com.cemi.portalreloaded.client.render.entity.RenderEntityWheatley;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.registries.IForgeRegistry;

public class PortalEntities {

	static int id = 33;

	static IForgeRegistry<EntityEntry> registry;

	public static void register(IForgeRegistry<EntityEntry> registry) {
		PortalEntities.registry = registry;

		register(EntityHEP.class, "hep", getRenderer("RenderEntityHEP"));
		register(EntityWheatley.class, "wheatley", getRenderer("RenderEntityWheatley"));
		register(EntityStorageCube.class, "storage_cube", getRenderer("RenderEntityCube"));
		register(EntityPivotCube.class, "pivot_cube", getRenderer("RenderEntityPivotCube"));
		register(EntityTurret.class, "turret", getRenderer("RenderEntityTurret"));
		register(EntityCamera.class, "camera", getRenderer("RenderEntityCamera"));

	}

	private static void register(Class entityClass, String name, @Nullable Class renderClass) {
		registry.register(EntityEntryBuilder.create().entity(entityClass).tracker(160, 1, true)
				.id(new ResourceLocation(PortalReloaded.MODID, name), id++).name(name).build());
		if (renderClass != null)
			PortalReloaded.proxy.registerEntityRenderer(entityClass, renderClass);
	}

	private static Class getRenderer(String clss) {
		try {
			return Class.forName("com.cemi.portalreloaded.client.render.entity." + clss);
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

}
