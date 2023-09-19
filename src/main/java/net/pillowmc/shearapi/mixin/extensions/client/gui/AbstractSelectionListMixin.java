package net.pillowmc.shearapi.mixin.extensions.client.gui;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.gui.components.AbstractSelectionList;
import net.pillowmc.shearapi.extensions.client.gui.IShearAbstractSelectionList;

@Mixin(AbstractSelectionList.class)
public abstract class AbstractSelectionListMixin implements IShearAbstractSelectionList {

	@Accessor
	public abstract int getWidth();

	@Accessor
	public abstract int getHeight();

	@Accessor("y0")
	public abstract int getTop();

	@Accessor("y1")
	public abstract int getBottom();

	@Accessor("x0")
	public abstract int getLeft();

	@Accessor("x1")
	public abstract int getRight();
}
