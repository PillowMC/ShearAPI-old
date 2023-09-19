package net.pillowmc.shearapi.extensions.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;

public interface IShearScreen {
	private Screen self() {
		return (Screen) this;
	}

	default Minecraft getMinecraft() {
		return self().getClient();
	}
}
