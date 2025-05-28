package com.phasico.infinistack.main;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import com.phasico.infinistack.helper.Logger;

import java.util.Map;

@IFMLLoadingPlugin.MCVersion("1.7.10")
@IFMLLoadingPlugin.TransformerExclusions({"com.phasico.infinistack"})
public class InfiniStackIFMLLoadingPlugin implements IFMLLoadingPlugin {

    static {
        MixinBootstrap.init();
        Logger.info("FML Plugin Loaded!");
    }

    public String getSetupClass() { return InfiniStackIFMLHook.class.getName(); }

    public String[] getASMTransformerClass() { return new String[0]; }
    public String getModContainerClass() { return null; }
    public void injectData(Map<String, Object> data) {}
    public String getAccessTransformerClass() { return null; }
}