package com.phasico.infinistack.main;

import cpw.mods.fml.relauncher.IFMLCallHook;
import org.spongepowered.asm.mixin.Mixins;

import java.util.Map;


public class InfiniStackIFMLHook implements IFMLCallHook {

        @Override
        public void injectData(Map<String, Object> data) {
        }

        @Override
        public Void call() {
            Mixins.addConfiguration("mixins.infinistack.json");
            return null;
        }

}
