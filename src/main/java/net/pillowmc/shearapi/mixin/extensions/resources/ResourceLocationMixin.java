package net.pillowmc.shearapi.mixin.extensions.resources;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.resources.ResourceLocation;
import net.pillowmc.shearapi.extensions.resources.IShearResourceLocation;

@Mixin(ResourceLocation.class)
public class ResourceLocationMixin implements IShearResourceLocation {

}
