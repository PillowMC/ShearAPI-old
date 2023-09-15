package net.pillowmc.shearapi.mixin.crash;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.CrashReportCategory;
import net.pillowmc.shearapi.extensions.crash.IShearCrashReportCategory;

@Mixin(CrashReportCategory.class)
public class CrashReportCategoryMixin implements IShearCrashReportCategory {
	@Shadow
	private StackTraceElement[] stackTrace;
	public void applyStackTrace(Throwable t) {
		this.stackTrace = t.getStackTrace();
	}
}
