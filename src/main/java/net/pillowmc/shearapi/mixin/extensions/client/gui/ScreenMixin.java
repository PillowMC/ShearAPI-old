package net.pillowmc.shearapi.mixin.extensions.client.gui;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.client.gui.screens.Screen;
import net.pillowmc.shearapi.extensions.client.gui.IShearScreen;

@Mixin(Screen.class)
public class ScreenMixin implements IShearScreen {

}
