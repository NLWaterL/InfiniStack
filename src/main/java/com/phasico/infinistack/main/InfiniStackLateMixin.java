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

        return mixins;
    }

}