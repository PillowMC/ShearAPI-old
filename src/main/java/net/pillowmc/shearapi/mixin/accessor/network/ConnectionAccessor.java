package net.pillowmc.shearapi.mixin.accessor.network;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import io.netty.channel.Channel;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.PacketFlow;
import net.pillowmc.shearapi.extensions.network.IShearConnection;

@Mixin(Connection.class)
public interface ConnectionAccessor extends IShearConnection {
	@Accessor("channel")
	Channel channel();

	@Accessor("receiving")
	PacketFlow getDirection();
}
