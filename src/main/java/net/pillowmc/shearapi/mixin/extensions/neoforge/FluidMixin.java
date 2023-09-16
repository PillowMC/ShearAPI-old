package net.pillowmc.shearapi.mixin.extensions.neoforge;

import net.minecraftforge.common.ForgeHooks;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.extensions.IForgeFluid;
import net.minecraftforge.fluids.FluidType;

@Mixin(Fluid.class)
public class FluidMixin implements IForgeFluid {
	private FluidType forgeFluidType;

	@Override
	public FluidType getFluidType() {
		// TODO: Generate for Quilt mod
		if (forgeFluidType == null) forgeFluidType = ForgeHooks.getVanillaFluidType((Fluid)(Object)this);
		return forgeFluidType;
	}

}
