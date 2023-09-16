package net.pillowmc.shearapi.mixin.extensions.client.render;

import net.minecraft.client.renderer.RenderType;
import net.pillowmc.shearapi.extensions.client.render.IShearRenderType;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(RenderType.class)
public class RenderTypeMixin implements IShearRenderType {
	int chunkLayerId = -1;
	@Override
	public int getChunkLayerId() {
		if (-1 == chunkLayerId) return chunkLayerId = RenderType.chunkBufferLayers().indexOf((RenderType)(Object) this);
		return chunkLayerId;
	}
}
