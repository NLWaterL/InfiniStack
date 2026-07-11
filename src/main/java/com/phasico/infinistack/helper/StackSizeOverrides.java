package com.phasico.infinistack.helper;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StackSizeOverrides {

    private static class Entry {
        int wildcardSize = -1;
        Map<Integer, Integer> byMeta;
    }

    private static final Entry NONE = new Entry();

    /** Parsed config entries grouped by item key ("modid:unlocalizedName" or registry name). */
    private static final Map<String, Entry> byKey = new HashMap<String, Entry>();
    /** Lazy per-Item resolution cache; items resolve once the registry knows them. */
    private static final ConcurrentHashMap<Item, Entry> cache = new ConcurrentHashMap<Item, Entry>();
    private static boolean active = false;

    public static void load(String[] lines) {
        byKey.clear();
        cache.clear();
        for (String line : lines) {
            if (line == null) continue;
            line = line.trim();
            if (line.isEmpty() || line.startsWith("#")) continue;

            String[] parts = line.split("\\s+");
            if (parts.length != 2) {
                LogHelper.warn("Bad stack size override, expected '<item> <size>': " + line);
                continue;
            }
            int size;
            try {
                size = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                LogHelper.warn("Bad stack size override, size is not a number: " + line);
                continue;
            }
            if (size < 1) {
                LogHelper.warn("Bad stack size override, size must be >= 1: " + line);
                continue;
            }

            String key = parts[0];
            int meta = -1; // -1 = wildcard
            int lastColon = key.lastIndexOf(':');
            if (lastColon >= 0) {
                String metaPart = key.substring(lastColon + 1);
                if (metaPart.equals("*")) {
                    key = key.substring(0, lastColon);
                } else {
                    try {
                        meta = Integer.parseInt(metaPart);
                        key = key.substring(0, lastColon);
                    } catch (NumberFormatException ignored) {
                        // last segment is part of the item name, no metadata given -> wildcard
                    }
                }
            }

            Entry entry = byKey.get(key);
            if (entry == null) {
                entry = new Entry();
                byKey.put(key, entry);
            }
            if (meta < 0) {
                entry.wildcardSize = size;
            } else {
                if (entry.byMeta == null) {
                    entry.byMeta = new HashMap<Integer, Integer>();
                }
                entry.byMeta.put(meta, size);
            }
        }
        active = !byKey.isEmpty();
    }

    /** @return the configured size for this item+metadata, or -1 if none. */
    public static int get(Item item, int meta) {
        if (!active || item == null) return -1;
        Entry entry = resolve(item);
        if (entry.byMeta != null) {
            Integer size = entry.byMeta.get(meta);
            if (size != null) return size;
        }
        return entry.wildcardSize;
    }

    /** Metadata-blind lookup for callers without stack context; only wildcard entries match. */
    public static int get(Item item) {
        if (!active || item == null) return -1;
        return resolve(item).wildcardSize;
    }

    private static Entry resolve(Item item) {
        Entry entry = cache.get(item);
        if (entry != null) return entry;

        GameRegistry.UniqueIdentifier id = GameRegistry.findUniqueIdentifierFor(item);
        if (id == null) {
            // not registered yet - don't cache, retry on a later query
            return NONE;
        }
        entry = byKey.get(id.modId + ":" + item.getUnlocalizedName());
        if (entry == null) {
            entry = byKey.get(id.modId + ":" + id.name);
        }
        if (entry == null) {
            entry = NONE;
        }
        cache.put(item, entry);
        return entry;
    }
}
