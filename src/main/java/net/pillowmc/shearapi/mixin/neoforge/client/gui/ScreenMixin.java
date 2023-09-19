package net.pillowmc.shearapi.mixin.neoforge.client.gui;

import java.util.List;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.common.MinecraftForge;

@Mixin(Screen.class)
public abstract class ScreenMixin {
	@Shadow
	protected Minecraft minecraft;

	@Shadow
	@Final
	private List<GuiEventListener> children;

	@Shadow
	@Final
	private List<NarratableEntry> narratables;

	@Shadow
	@Final
	public List<Renderable> renderables;

	@Shadow
	protected abstract void removeWidget(GuiEventListener listener);

	@Shadow
	protected abstract void init();

	@Inject(method = "onClose", at = @At("HEAD"), cancellable = true)
	public void onClose(CallbackInfo ci) {
		minecraft.popGuiLayer();
		ci.cancel();
	}

	@Redirect(method = "init(Lnet/minecraft/client/Minecraft;II)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/Screen;init()V"))
	public void preInit1(Screen self) {
		if (!MinecraftForge.EVENT_BUS.post(new ScreenEvent.Init.Pre(self, children, this::addEventWidget, this::removeWidget))) {
			init();
		}
	}

	@Redirect(method = "init(Lnet/minecraft/client/Minecraft;II)V", at = @At("TAIL"))
	public void postInit1(Screen self) {
		MinecraftForge.EVENT_BUS.post(new ScreenEvent.Init.Post(self, children, this::addEventWidget, this::removeWidget));
	}

	@Redirect(method = "rebuildWidgets()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/Screen;init()V"))
	public void preInit2(Screen self) {
		if (!MinecraftForge.EVENT_BUS.post(new ScreenEvent.Init.Pre(self, children, this::addEventWidget, this::removeWidget))) {
			init();
		}
	}

	@Redirect(method = "rebuildWidgets()V", at = @At("TAIL"))
	public void postInit2(Screen self) {
		MinecraftForge.EVENT_BUS.post(new ScreenEvent.Init.Post(self, children, this::addEventWidget, this::removeWidget));
	}

	@Inject(method = "renderBackground(Lnet/minecraft/client/gui/GuiGraphics;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;fillGradient(IIIIII)V"), locals = LocalCapture.CAPTURE_FAILHARD)
	public void postBackground1(GuiGraphics graphics, CallbackInfo ci) {
		MinecraftForge.EVENT_BUS.post(new ScreenEvent.BackgroundRendered((Screen)(Object) this, graphics));
	}

	@Inject(method = "renderDirtBackground(Lnet/minecraft/client/gui/GuiGraphics;)V", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILHARD)
	public void postBackground2(GuiGraphics graphics, CallbackInfo ci, int i) {
		MinecraftForge.EVENT_BUS.post(new ScreenEvent.BackgroundRendered((Screen)(Object) this, graphics));
	}

	private void addEventWidget(GuiEventListener b) {
		if (b instanceof Renderable r)
			this.renderables.add(r);
		if (b instanceof NarratableEntry ne)
			this.narratables.add(ne);
		children.add(b);
    }
}
