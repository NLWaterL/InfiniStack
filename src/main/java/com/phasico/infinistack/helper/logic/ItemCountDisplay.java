package com.phasico.infinistack.helper.logic;

import com.phasico.infinistack.helper.Configurables;
import net.minecraft.util.EnumChatFormatting;

import java.text.NumberFormat;
import java.util.Locale;

public class ItemCountDisplay {

    private static final char[] SUFFIXES = {'k', 'M', 'G', 'T', 'P', 'E'};
    private static final NumberFormat GROUPED_FORMAT = NumberFormat.getNumberInstance(Locale.US);

    //Central entry point used by all display mixins.
    public static String getStackSizeText(long count) {
        if (Configurables.useAlternateDisplay) {
            return formatStackSizeModularUI(count);
        }
        return formatStackSize(count);
    }

    public static String formatStackSize(long count) {
        if (count < 1000) return Long.toString(count);
        if (count < 10_000) return EnumChatFormatting.YELLOW + formatFloorDecimal(count / 1000.0, 1) + "k";
        if (count < 1_000_000) return EnumChatFormatting.YELLOW + Long.toString(count / 1000) + "k";
        if (count < 10_000_000) return EnumChatFormatting.AQUA + formatFloorDecimal(count / 1_000_000.0, 1) + "m";
        if (count < 1_000_000_000) return EnumChatFormatting.AQUA + Long.toString(count / 1_000_000) + "m";                      // Aqua
        return EnumChatFormatting.LIGHT_PURPLE + formatFloorDecimal(count / 1_000_000_000.0, 1) + "b";
    }

    //Same number logic as ModularUI's NumberFormatMUI#formatWithSuffix.
    public static String formatStackSizeModularUI(long count) {
        long number = count;
        if (number < 10_000L) return GROUPED_FORMAT.format(number);
        for (char suffix : SUFFIXES) {
            if (number < 10_000L) return GROUPED_FORMAT.format((number / 100L) / 10.0) + suffix;
            if (number < 1_000_000L) return GROUPED_FORMAT.format(number / 1000L) + suffix;
            number /= 1000L;
        }
        return GROUPED_FORMAT.format(number / 1000L) + SUFFIXES[SUFFIXES.length - 1];
    }

    //Length without colour formatting codes; this is what the scale steps must be picked from.
    public static int getVisibleLength(String text) {
        int length = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == 167) { //167 = the formatting char used by EnumChatFormatting
                i++;
                continue;
            }
            length++;
        }
        return length;
    }

    public static float getScaleForLength(int length) {
        if (length == 4) return 0.6f;
        if (length > 4) return 0.5f;
        return 0.8f;
    }

    //The unicode font is half-width and already small, so auto skips resizing there.
    //unicodeFontInUse must be supplied by the (client side) caller; this class stays
    //loadable on the server.
    public static boolean shouldResizeCountText(boolean unicodeFontInUse) {
        if (Configurables.resizeCountTextOverride != null) {
            return Configurables.resizeCountTextOverride;
        }
        return !unicodeFontInUse; //auto
    }

    //Scale for the vanilla RenderItem path; resizing is its own config option and
    //works with either display format (colour codes are excluded from the length).
    public static float getDisplayScale(String text, boolean unicodeFontInUse) {
        if (!shouldResizeCountText(unicodeFontInUse)) return 1.0f;
        return getScaleForLength(getVisibleLength(text));
    }

    public static String formatFloorDecimal(double value, int decimalPlaces) {
        double scale = Math.pow(10, decimalPlaces);
        double floored = Math.floor(value * scale) / scale;
        return String.format("%." + decimalPlaces + "f", floored);
    }
}
