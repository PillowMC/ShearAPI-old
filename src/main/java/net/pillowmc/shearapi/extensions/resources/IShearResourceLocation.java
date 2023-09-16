package net.pillowmc.shearapi.extensions.resources;

import net.minecraft.resources.ResourceLocation;

public interface IShearResourceLocation {
	private ResourceLocation self() {
		return (ResourceLocation) this;
	}
	default int compareNamespaced(ResourceLocation other) {
		int ret = self().getNamespace().compareTo(other.getNamespace());
		return ret != 0 ? ret : self().getPath().compareTo(other.getPath());
	}
}
