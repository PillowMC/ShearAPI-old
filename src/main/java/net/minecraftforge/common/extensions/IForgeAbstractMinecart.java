/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package net.minecraftforge.common.extensions;

import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.tags.BlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.BaseRailBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public interface IForgeAbstractMinecart
{
    public static float DEFAULT_MAX_SPEED_AIR_LATERAL = 0.4f;
    public static float DEFAULT_MAX_SPEED_AIR_VERTICAL = -1.0f;
    public static double DEFAULT_AIR_DRAG = 0.95f;

    private AbstractMinecart self() {
        return (AbstractMinecart)this;
    }

    /**
     * Internal, returns the current spot to look for the attached rail.
     */
    default BlockPos getCurrentRailPosition()
    {
        int x = Mth.floor(self().getX());
        int y = Mth.floor(self().getY());
        int z = Mth.floor(self().getZ());
        BlockPos pos = new BlockPos(x, y, z);
        if (self().level().getBlockState(pos.below()).is(BlockTags.RAILS)) pos = pos.below();
        return pos;
    }

    default double getMaxSpeedWithRail() {
		if (!canUseRail()) return self().getMaxSpeed();
		BlockPos pos = this.getCurrentRailPosition();
		BlockState state = self().level().getBlockState(pos);
		if (!state.is(BlockTags.RAILS)) return self().getMaxSpeed();
		float railMaxSpeed = ((BaseRailBlock)state.getBlock()).getRailMaxSpeed(state, self().level(), pos, (AbstractMinecart) this);
		return Math.min(railMaxSpeed, getCurrentCartSpeedCapOnRail());
	}

    /**
     * Moved to allow overrides.
     * This code handles minecart movement and speed capping when on a rail.
     */
    default void moveMinecartOnRail(BlockPos pos) {
		AbstractMinecart mc = self();
		double d24 = mc.isVehicle() ? 0.75D : 1.0D;
		double d25 = getMaxSpeedWithRail();
		Vec3 vec3d1 = mc.getDeltaMovement();
		mc.move(MoverType.SELF, new Vec3(Mth.clamp(d24 * vec3d1.x, -d25, d25), 0.0D, Mth.clamp(d24 * vec3d1.z, -d25, d25)));
	}

    /**
     * Returns true if this cart can currently use rails.
     * This function is mainly used to gracefully detach a minecart from a rail.
     * @return True if the minecart can use rails.
     */
    boolean canUseRail();

    /**
     * Set whether the minecart can use rails.
     * This function is mainly used to gracefully detach a minecart from a rail.
     * @param use Whether the minecart can currently use rails.
     */
    void setCanUseRail(boolean use);

    /**
     * Return false if this cart should not call onMinecartPass() and should ignore Powered Rails.
     * @return True if this cart should call onMinecartPass().
     */
    default boolean shouldDoRailFunctions() {
        return true;
    }

    /**
     * Returns true if this cart is self propelled.
     * @return True if powered.
     */
    default boolean isPoweredCart() {
        return self().getMinecartType() == AbstractMinecart.Type.FURNACE;
    }

    /**
     * Returns true if this cart can be ridden by an Entity.
     * @return True if this cart can be ridden.
     */
    default boolean canBeRidden() {
        return self().getMinecartType() == AbstractMinecart.Type.RIDEABLE;
    }

    /**
     * Returns the carts max speed when traveling on rails. Carts going faster
     * than 1.1 cause issues with chunk loading. Carts cant traverse slopes or
     * corners at greater than 0.5 - 0.6. This value is compared with the rails
     * max speed and the carts current speed cap to determine the carts current
     * max speed. A normal rail's max speed is 0.4.
     *
     * @return Carts max speed.
     */
    default float getMaxCartSpeedOnRail() {
        return 1.2f;
    }

    /**
     * Returns the current speed cap for the cart when traveling on rails. This
     * functions differs from getMaxCartSpeedOnRail() in that it controls
     * current movement and cannot be overridden. The value however can never be
     * higher than getMaxCartSpeedOnRail().
     */
    float getCurrentCartSpeedCapOnRail();
    void setCurrentCartSpeedCapOnRail(float value);
    float getMaxSpeedAirLateral();
    void setMaxSpeedAirLateral(float value);
    float getMaxSpeedAirVertical();
    void setMaxSpeedAirVertical(float value);
    double getDragAir();
    void setDragAir(double value);

    default double getSlopeAdjustment() {
        return 0.0078125D;
    }

    /**
     * Called from Detector Rails to retrieve a redstone power level for comparators.
     */
    default int getComparatorLevel() {
        return -1;
    }
}
