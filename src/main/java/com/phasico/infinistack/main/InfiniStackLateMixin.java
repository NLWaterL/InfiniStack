package com.phasico.infinistack.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;

// Define late mixins (mixins targetting non-coremod mod classes) in this class.
// These mixins get loaded after mod classes are put on the classpath, allowing you to mix into them.
@LateMixin
public class InfiniStackLateMixin implements ILateMixinLoader {

    @Override
    public String getMixinConfig() {
        return "mixins.infinistack.late.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedMods) {
        List<String> mixins = new ArrayList<>();

        if (loadedMods.contains("hbm")) {
            List<String> hbmMixins = Arrays.asList(
                    "MixinBufferUtil",
                    "MixinEntityDeliverDrone",
                    "MixinEntityMinecartContainerBase",
                    "MixinEntityRailCarCargo",
                    "MixinInventoryAmmoBag",
                    "MixinInventoryCasingBag",
                    "MixinItemInventory",
                    "MixinTileEntityCompactLauncher",
                    "MixinTileEntityCrateBase",
                    "MixinTileEntityDiFurnaceRTG",
                    "MixinTileEntityForceField",
                    "MixinTileEntityFoundryCastingBase",
                    "MixinTileEntityInventoryBase",
                    "MixinTileEntityLaunchTable",
                    "MixinTileEntityMachineArcFurnace",
                    "MixinTileEntityMachineBase",
                    "MixinTileEntityMachineKeyForge",
                    "MixinTileEntityMachineMissileAssembly",
                    "MixinTileEntityMachineRTG",
                    "MixinTileEntityMachineSatDock",
                    "MixinTileEntityMachineSatLinker",
                    "MixinTileEntityMachineShredder",
                    "MixinTileEntityMachineSiren",
                    "MixinTileEntityMachineTurbine",
                    "MixinTileEntityNukeBoy",
                    "MixinTileEntityNukeCustom",
                    "MixinTileEntityNukeFleija",
                    "MixinTileEntityNukeN2",
                    "MixinTileEntityNukePrototype",
                    "MixinTileEntityNukeSolinium",
                    "MixinTileEntityProxyInventory",
                    "MixinTileEntityRBMKSlottedBase",
                    "MixinTileEntityRequestNetworkContainer",
                    "MixinTileEntityRtgFurnace",
                    "MixinBlockCraneBase",
                    "MixinBlockCustomMachine",
                    "MixinBlockDecoContainer",
                    "MixinBlockDummyable",
                    "MixinBlockFluidBarrel",
                    "MixinBlockMachineBase",
                    "MixinBlockMassStorage",
                    "MixinBlockStorageCrate",
                    "MixinBombMulti",
                    "MixinCompactLauncher",
                    "MixinCranePartitioner",
                    "MixinDroneCrate",
                    "MixinDroneDock",
                    "MixinItemStackUtil",
                    "MixinItemToolBox",
                    "MixinLaunchTable",
                    "MixinMachineArcFurnace",
                    "MixinMachineAutocrafter",
                    "MixinMachineBattery",
                    "MixinMachineDiFurnace",
                    "MixinMachineDiFurnaceRTG",
                    "MixinMachineElectricFurnace",
                    "MixinMachineEPress",
                    "MixinMachineForceField",
                    "MixinMachineFunnel",
                    "MixinMachineICFPress",
                    "MixinMachineKeyForge",
                    "MixinMachineMissileAssembly",
                    "MixinMachinePress",
                    "MixinMachineReactorControl",
                    "MixinMachineRTG",
                    "MixinMachineRtgFurnace",
                    "MixinMachineSatDock",
                    "MixinMachineSatLinker",
                    "MixinMachineSchrabidiumTransmutator",
                    "MixinMachineShredder",
                    "MixinMachineSiren",
                    "MixinMachineTurbine",
                    "MixinNukeBoy",
                    "MixinNukeCustom",
                    "MixinNukeFleija",
                    "MixinNukeGadget",
                    "MixinNukeMan",
                    "MixinNukeMike",
                    "MixinNukeN2",
                    "MixinNukePrototype",
                    "MixinNukeSolinium",
                    "MixinNukeTsar",
                    "MixinPistonInserter",
                    "MixinSoyuzCapsule",
                    "MixinSoyuzLauncher",
                    "MixinTurretSentry",
                    "MixinWasteDrum"
            );
            for (String mixinClass : hbmMixins) {
                mixins.add("hbm." + mixinClass);
            }
        }

        if (loadedMods.contains("Avaritia")) {
            List<String> avaritiaMixins = Arrays.asList(
                    "MixinBlockAutoDireCrafting",
                    "MixinBlockCompressor",
                    "MixinBlockDireCrafting",
                    "MixinBlockNeutronCollector",
                    "MixinTileEntityAutoDireCrafting",
                    "MixinTileEntityCompressor",
                    "MixinTileEntityDireCrafting",
                    "MixinTileEntityNeutron"
            );
            for (String mixinClass : avaritiaMixins) {
                mixins.add("avaritia." + mixinClass);
            }
        }

        if (loadedMods.contains("ProjectE")){
            List<String> projecteMixins = Arrays.asList(
                    "MixinAlchBagInventory",
                    "MixinAlchChestTile",
                    "MixinCollectorMK1Tile",
                    "MixinCondenserTile",
                    "MixinDMPedestalTile",
                    "MixinEternalDensityInventory",
                    "MixinMercurialEyeInventory",
                    "MixinRelayMK1Tile",
                    "MixinRMFurnaceTile",
                    "MixinTransmutationInventory",
                    "MixinWorldHelper"
            );
            for (String mixinClass : projecteMixins) {
                mixins.add("projecte." + mixinClass);
            }
        }

        if (loadedMods.contains("IronChest")){
            List<String> ironChestMixins = Arrays.asList(
                    "MixinBlockIronChest",
                    "MixinTileEntityIronChest"
                    );
            for (String mixinClass : ironChestMixins) {
                mixins.add("ironchest." + mixinClass);
            }
        }

        if (loadedMods.contains("compactstorage")){
            List<String> compactStorageMixins = Arrays.asList(
                    "MixinTileEntityChest",
                    "MixinTileEntityChestBuilder",
                    "MixinInventoryBackpack"
                    );
            for (String mixinClass : compactStorageMixins) {
                mixins.add("compactstorage." + mixinClass);
            }
        }


        return mixins;
    }

}