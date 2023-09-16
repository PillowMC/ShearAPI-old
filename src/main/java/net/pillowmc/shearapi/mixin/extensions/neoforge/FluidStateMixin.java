package net.pillowmc.shearapi.mixin.extensions.neoforge;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.common.extensions.IForgeFluidState;

@Mixin(FluidState.class)
public class FluidStateMixin implements IForgeFluidState {

}
