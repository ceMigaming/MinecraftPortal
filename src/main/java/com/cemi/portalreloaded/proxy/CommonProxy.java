package com.cemi.portalreloaded.proxy;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;

public class CommonProxy {
	public void registerItemRenderer(Item item, int meta, String id) {
	}

	public <T extends Entity, U extends Render<? super T>> void registerEntityRenderer(Class<T> entityClass,
			Class<U> renderClass) {
	}
}
