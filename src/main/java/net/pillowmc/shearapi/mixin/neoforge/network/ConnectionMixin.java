package net.pillowmc.shearapi.mixin.neoforge.network;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.function.Consumer;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import net.minecraftforge.network.NetworkHooks;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import io.netty.channel.Channel;
import net.minecraft.network.Connection;
import net.minecraftforge.network.DualStackUtils;

@Mixin(Connection.class)
public class ConnectionMixin {
	@Shadow
	private Channel channel;

	@Shadow
	@Final
	private static Logger LOGGER;

	private Consumer<Connection> activationHandler;

	@Inject(method = "channelActive", at = @At(value = "INVOKE", target = "Lio/netty/channel/Channel;remoteAddress()Ljava/net/SocketAddress;", shift = At.Shift.AFTER, remap = false))
	public void channelActive(ChannelHandlerContext channelHandlerContext, CallbackInfo ci) {
		if (activationHandler != null) activationHandler.accept((Connection)(Object) this);
	}

	@Inject(method = "setProtocol", at = @At(value = "INVOKE", target = "Lio/netty/util/Attribute;set(Ljava/lang/Object;)V", shift = At.Shift.AFTER, remap = false), cancellable = true)
	public void setProtocol(CallbackInfo ci) {
		this.channel.eventLoop().execute(()-> {
			this.channel.config().setAutoRead(true);
			LOGGER.debug("Enabled auto read");
		});
		ci.cancel();
	}

	@Inject(method = "connect", at = @At("HEAD"), locals = LocalCapture.CAPTURE_FAILHARD)
	private static void connect(InetSocketAddress address, boolean useEpoll, Connection connection, CallbackInfoReturnable<ChannelFuture> cir) {
		DualStackUtils.checkIPv6(address.getAddress());
		((ConnectionMixin)(Object) connection).activationHandler = NetworkHooks::registerClientLoginChannel;
	}

	@Inject(method = "connectToLocalServer", at = @At(value = "NEW", target = "Lio/netty/bootstrap/Bootstrap;<init>()V", shift = At.Shift.BEFORE, remap = false), locals = LocalCapture.CAPTURE_FAILHARD)
	private static void connectToLocalServer(SocketAddress address, CallbackInfoReturnable<Connection> cir, Connection connection) {
		((ConnectionMixin)(Object) connection).activationHandler = NetworkHooks::registerClientLoginChannel;
	}
}
