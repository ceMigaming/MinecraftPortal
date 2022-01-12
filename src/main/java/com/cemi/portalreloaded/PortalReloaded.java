package com.cemi.portalreloaded;

import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

import com.cemi.portalreloaded.block.PortalBlocks;
import com.cemi.portalreloaded.command.PortalCommands;
import com.cemi.portalreloaded.entity.PortalEntities;
import com.cemi.portalreloaded.handlers.PacketHandler;
import com.cemi.portalreloaded.item.PortalItems;
import com.cemi.portalreloaded.proxy.CommonProxy;

import me.ichun.mods.portalgun.common.PortalGun;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;

@Mod(modid = PortalReloaded.MODID, name = PortalReloaded.NAME, version = PortalReloaded.VERSION, dependencies = "required-after:ichunutil; required-after:portalgun;")
public class PortalReloaded {
	public static final String MODID = "portalreloaded";
	public static final String NAME = "Portal Reloaded";
	public static final String VERSION = "0.1";

	private static Logger logger;

	public static final ItemArmor.ArmorMaterial LONGFALL_BOOTS_MATERIAL = EnumHelper.addArmorMaterial("LONGFALLBOOTS",
			MODID + ":longfallboots", 15, new int[] { 0, 0, 0, 0 }, 0, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0F);

	@SidedProxy(serverSide = "com.cemi.portalreloaded.proxy.CommonProxy", clientSide = "com.cemi.portalreloaded.proxy.ClientProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		PacketHandler.register();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		logger.info("PORTAL BLOCK >> {}", PortalGun.blockPortal.getRegistryName());
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

	@EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
		PortalCommands.register(event);
	}

	@Mod.EventBusSubscriber
	public static class RegistrationHandler {

		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			PortalItems.register(event.getRegistry());
			PortalBlocks.registerItemBlocks(event.getRegistry());
		}

		@SubscribeEvent
		public static void registerModels(ModelRegistryEvent event) {
			PortalItems.registerModels();
			PortalBlocks.registerModels();
		}

		@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event) {
			PortalBlocks.register(event.getRegistry());
		}

		@SubscribeEvent
		public static void registerEntity(RegistryEvent.Register<EntityEntry> event) {
			PortalEntities.register(event.getRegistry());
		}

	}
}
