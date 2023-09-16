package net.pillowmc.shearapi.extensions.client.render;

public interface IShearRenderType {
	default int getChunkLayerId() {
		throw new AssertionError("Where are you mixin?");
	}
}
