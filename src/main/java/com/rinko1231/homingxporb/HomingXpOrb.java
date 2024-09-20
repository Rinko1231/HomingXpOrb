package com.rinko1231.homingxporb;


import com.rinko1231.homingxporb.Config.HomingXpOrbConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(HomingXpOrb.MODID)
public class HomingXpOrb
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "homingxporb";

    public HomingXpOrb()
    {
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        HomingXpOrbConfig.setup();

    }

}
