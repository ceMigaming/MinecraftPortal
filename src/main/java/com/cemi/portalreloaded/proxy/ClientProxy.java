package com.cemi.portalreloaded.proxy;

import com.cemi.portalreloaded.PortalReloaded;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	static {
		OBJLoader.INSTANCE.addDomain(PortalReloaded.MODID);
	}

	@Override
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta,
				new ModelResourceLocation(PortalReloaded.MODID + ":" + id, "inventory"));
	}

	@Override
	public <T extends Entity, U extends Render<? super T>> void registerEntityRenderer(Class<T> entityClass,
			Class<U> renderClass) {
		RenderingRegistry.registerEntityRenderingHandler(entityClass, new IRenderFactory<T>() {

			@Override
			public Render<? super T> createRenderFor(RenderManager manager) {
				try {
					return renderClass.getConstructor(RenderManager.class).newInstance(manager);
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}
		});
	}
	
}
