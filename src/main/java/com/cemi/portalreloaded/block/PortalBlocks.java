package com.cemi.portalreloaded.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class PortalBlocks {

	public static BlockBallLauncher ballLauncher = new BlockBallLauncher();
	public static BlockBallTarget ballTarget = new BlockBallTarget();
	public static GlassBlock glassBlock = new GlassBlock("glass_block");
	public static GlassBlock seemedGlassBlock = new GlassBlock("glass_block_seemed");
	public static PaneBlock glassPane = new PaneBlock("glass_pane");
	public static PaneBlock seemedGlassPane = new PaneBlock("glass_pane_seemed");
	public static BlockFizzlerField fizzlerField = new BlockFizzlerField();
	
	
	// CONCRETE TILES
	public static PortalBlock smallConcreteTile = new PortalBlock(Material.ROCK, "small_concrete_tile");
	public static PortalBlock mediumConcreteTile = new PortalBlock(Material.ROCK, "medium_concrete_tile");
	public static PortalBlock upperConcreteCollumnTile = new PortalBlock(Material.ROCK, "upper_concrete_collumn_tile");
	public static PortalBlock lowerConcreteCollumnTile = new PortalBlock(Material.ROCK, "lower_concrete_collumn_tile");
	public static BlockCollumn concreteCollumnTile = new BlockCollumn(Material.ROCK, "concrete_collumn_tile",
			lowerConcreteCollumnTile, upperConcreteCollumnTile);
	public static PortalBlock upperLeftConcreteBigTile = new PortalBlock(Material.ROCK, "upper_left_concrete_big_tile");
	public static PortalBlock upperRightConcreteBigTile = new PortalBlock(Material.ROCK,
			"upper_right_concrete_big_tile");
	public static PortalBlock lowerLeftConcreteBigTile = new PortalBlock(Material.ROCK, "lower_left_concrete_big_tile");
	public static PortalBlock lowerRightConcreteBigTile = new PortalBlock(Material.ROCK,
			"lower_right_concrete_big_tile");
	public static BlockBigTile concreteBigTile = new BlockBigTile(Material.ROCK, "concrete_big_tile",
			upperLeftConcreteBigTile, upperRightConcreteBigTile, lowerLeftConcreteBigTile, lowerRightConcreteBigTile);

	// METAL TILES
	public static PortalBlock smallMetalTile = new PortalBlock(Material.ROCK, "small_metal_tile");
	public static PortalBlock mediumMetalTile = new PortalBlock(Material.ROCK, "medium_metal_tile");
	public static PortalBlock upperMetalCollumnTile = new PortalBlock(Material.ROCK, "upper_metal_collumn_tile");
	public static PortalBlock lowerMetalCollumnTile = new PortalBlock(Material.ROCK, "lower_metal_collumn_tile");
	public static BlockCollumn metalCollumnTile = new BlockCollumn(Material.ROCK, "metal_collumn_tile",
			lowerMetalCollumnTile, upperMetalCollumnTile);

	public static PortalBlock upperLeftMetalBigTile = new PortalBlock(Material.ROCK, "upper_left_metal_big_tile");
	public static PortalBlock upperRightMetalBigTile = new PortalBlock(Material.ROCK, "upper_right_metal_big_tile");
	public static PortalBlock lowerLeftMetalBigTile = new PortalBlock(Material.ROCK, "lower_left_metal_big_tile");
	public static PortalBlock lowerRightMetalBigTile = new PortalBlock(Material.ROCK, "lower_right_metal_big_tile");
	public static BlockBigTile metalBigTile = new BlockBigTile(Material.ROCK, "metal_big_tile", upperLeftMetalBigTile,
			upperRightMetalBigTile, lowerLeftMetalBigTile, lowerRightMetalBigTile);

	static PortalBlock[] blocks = { ballLauncher, ballTarget,
			// CONCRETE TILES
			smallConcreteTile, mediumConcreteTile, upperConcreteCollumnTile, lowerConcreteCollumnTile,
			concreteCollumnTile, upperLeftConcreteBigTile, upperRightConcreteBigTile, lowerLeftConcreteBigTile,
			lowerRightConcreteBigTile, concreteBigTile,
			// METAL TILES
			smallMetalTile, mediumMetalTile, upperMetalCollumnTile, lowerMetalCollumnTile, metalCollumnTile,
			upperLeftMetalBigTile, upperRightMetalBigTile, lowerLeftMetalBigTile, lowerRightMetalBigTile,
			metalBigTile,
			// GLASS
			glassBlock, seemedGlassBlock,
			glassPane, seemedGlassPane,
			// Fizzler
			fizzlerField
			};

	public static void register(IForgeRegistry<Block> registry) {
		registry.registerAll(blocks);
		GameRegistry.registerTileEntity(ballLauncher.getTileEntityClass(), ballLauncher.getRegistryName().toString());
		GameRegistry.registerTileEntity(ballTarget.getTileEntityClass(), ballTarget.getRegistryName().toString());
	}

	public static void registerItemBlocks(IForgeRegistry<Item> registry) {
		for (PortalBlock block : blocks) {
			registry.register(block.createItemBlock());
		}
	}

	public static void registerModels() {
		for (PortalBlock block : blocks) {
			block.registerItemModel(Item.getItemFromBlock(block));
		}
	}

}
