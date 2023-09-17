package net.pillowmc.shearapi.extensions.network;

import io.netty.channel.Channel;
import net.minecraft.network.protocol.PacketFlow;

public interface IShearConnection {
	Channel channel();

	PacketFlow getDirection();
}
