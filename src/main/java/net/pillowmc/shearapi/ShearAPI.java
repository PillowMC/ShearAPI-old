package net.pillowmc.shearapi;

import net.fabricmc.fabric.mixin.item.CustomItemSettingImplAccessor;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemTransform;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.data.recipes.packs.VanillaRecipeProvider;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShearAPI implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("Shear API");

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("ShearAPI Init!");
	}
}
