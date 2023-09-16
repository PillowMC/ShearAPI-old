package net.pillowmc.shearapi.mixin.neoforge;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagEntry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TagEntry.class)
public class TagEntryMixin {
	@Shadow
	@Final
	private ResourceLocation id;
	@Shadow
	@Final
	private boolean tag;
	@Shadow
	@Final
	private boolean required;
	public ResourceLocation getId() {
		return id;
	}

	public boolean isRequired() {
		return required;
	}

	public boolean isTag() {
		return tag;
	}
}
