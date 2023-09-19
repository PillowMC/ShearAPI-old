package net.pillowmc.shearapi.server;

import net.minecraftforge.server.loading.ServerModLoader;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.server.DedicatedServerModInitializer;

public class ShearAPIServer implements DedicatedServerModInitializer {
	@Override
	public void onInitializeServer(ModContainer mod) {
		ServerModLoader.load();
	}
}
