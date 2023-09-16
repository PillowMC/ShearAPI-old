package net.pillowmc.shearapi.mixin.extensions.crash;

import net.minecraftforge.logging.CrashReportExtender;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import net.minecraft.CrashReport;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(CrashReport.class)
public class CrashReportMixin {
	@Inject(method = "getFriendlyReport", at = @At(value = "INVOKE", target = "Ljava/lang/StringBuilder;append(Ljava/lang/String;)Ljava/lang/StringBuilder;", ordinal = 0, shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD)
	public void asString(CallbackInfoReturnable<String> cir, StringBuilder stringbuilder) {
		CrashReportExtender.addCrashReportHeader(stringbuilder, (CrashReport)(Object)this);
	}
}
