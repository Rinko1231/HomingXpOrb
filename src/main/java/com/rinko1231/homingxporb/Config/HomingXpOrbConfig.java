package com.rinko1231.homingxporb.Config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class HomingXpOrbConfig
{
    private static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static ForgeConfigSpec.DoubleValue maxHomingDistance;

    public static ForgeConfigSpec.BooleanValue accelerationMode;

    public static ForgeConfigSpec.DoubleValue accelerationMultiplier;

    public static ForgeConfigSpec.DoubleValue constantSpeed;

    static
    {
        BUILDER.push("Homing Xp Orb Config");

        maxHomingDistance = BUILDER
                .comment("The max distance at which the Xp Orb can detect players.")
                .defineInRange("maxHomingDistance",64.0,0.0,128.0);

        accelerationMode = BUILDER
                .comment("If enabled, Xp orbs will fly towards the player in the vanilla way, which is gaining an acceleration vector constantly towards the player. This makes the Xp Orbs' movement smoother, but when the player is too far away, the xp orb will be unable to adjust the flight direction in time.")
                .comment("If disabled, the velocity vector of Xp orbs will continue pointing toward the player, which might not move smoothly enough though.")
                .define("accelerationMode", true);

        accelerationMultiplier = BUILDER
                .comment("Acceleration Multiplier if acceleration mode is enabled, in case that xp orbs become too fast.")
                .defineInRange("accelerationMultiplier",0.5,0.0,1.0);

        constantSpeed = BUILDER
                .comment("The constant flying speed of xp orbs if acceleration mode is disabled.")
                .defineInRange("constantSpeed",0.5,0.0,8.0);

        SPEC = BUILDER.build();
    }

    public static void setup()
    {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SPEC, "HomingXpOrb.toml");
    }

}