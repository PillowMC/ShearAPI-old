/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package net.minecraftforge.forge.snapshots;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.CrashReport;
import net.minecraft.client.Options;

public class ForgeSnapshotsMod
{
    public static final String BRANDING_NAME = "ShearAPI";
    public static final String BRANDING_ID = "shear-api";
    private static final Logger LOGGER = LogUtils.getLogger();
    static boolean seenSnapshotWarning = false;

    public static void processOptions(Options.FieldAccess fieldAccess)
    {
        seenSnapshotWarning = fieldAccess.process("seenSnapshotWarning", seenSnapshotWarning);
    }

    public static void logStartupWarning()
    {
        LOGGER.warn("Shear API is not officially supported by NeoForge. Bugs and instability are expected, and don't report them to NeoForge.");
    }

    public static void addCrashReportHeader(StringBuilder builder, CrashReport crashReport)
    {
        builder.append("---- Please note that Shear API is maintained by PillowMC. Bugs and instability are expected, DONT REPORT BUGS TO NEOFORGE ----\n");
    }
}
