package net.pillowmc.shearapi.mixin.extensions.neoforge;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.advancements.Advancement;
import net.minecraftforge.common.extensions.IForgeAdvancementBuilder;

@Mixin(Advancement.Builder.class)
public class AdvancementBuilderMixin implements IForgeAdvancementBuilder {

}
