package com.phasico.infinistack.helper.logic;

import com.phasico.infinistack.helper.Configurables;
import net.minecraft.util.EnumChatFormatting;

public class ItemCountDisplay {

    public static String formatStackSize(int count) {
        if (count > Configurables.maxStackSize) return EnumChatFormatting.RED + "Inf";
        if (count < 1000) return Integer.toString(count);
        if (count < 10_000) return EnumChatFormatting.YELLOW + formatFloorDecimal(count / 1000.0, 1) + "k";
        if (count < 1_000_000) return EnumChatFormatting.YELLOW + Integer.toString(count / 1000) + "k";
        if (count < 10_000_000) return EnumChatFormatting.AQUA + formatFloorDecimal(count / 1_000_000.0, 1) + "m";
        if (count < 1_000_000_000) return EnumChatFormatting.AQUA + Integer.toString(count / 1_000_000) + "m";                      // Aqua
        return EnumChatFormatting.LIGHT_PURPLE + formatFloorDecimal(count / 1_000_000_000.0, 1) + "b";
    }

    private static String formatFloorDecimal(double value, int decimalPlaces) {
        double scale = Math.pow(10, decimalPlaces);
        double floored = Math.floor(value * scale) / scale;
        return String.format("%." + decimalPlaces + "f", floored);
    }
}
