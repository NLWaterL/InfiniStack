package com.phasico.infinistack.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;
import com.phasico.infinistack.helper.Logger;

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

        //If you're not using a proper IDE reading this class is a pain.

        if (loadedMods.contains("hbm")) {
            List<String> hbmMixins = Arrays.asList(
                    "AccessorAnvilCraftPacket",
                    "MixinAnvilCraftPacket",
                    "MixinContainerAnvil",
                    "MixinContainerBook",
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
                    "MixinTileEntityNeutron",
                    "MixinContainerExtremeCrafting"
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

        if (loadedMods.contains("Forestry")){
            List<String> forestryMixins = Arrays.asList(
                    "MixinDataInputStreamForestry",
                    "MixinDataOutputStreamForestry",
                    "MixinInventoryAdapter",
                    "MixinInventoryAdapterTile",
                    "MixinInventoryPlain",
                    "MixinInventoryUtil",
                    "MixinItemInventory",
                    "MixinItemStackUtil",
                    "MixinTileEnginePeat",
                    "MixinTileMoistener",
                    "MixinTileTrader",
                    "MixinBlockBase"
                    );
            for (String mixinClass : forestryMixins) {
                mixins.add("forestry." + mixinClass);
            }
        }

        if (loadedMods.contains("avaritiaddons")){
            List<String> avaritiaddonsMixins = Arrays.asList(
                    "MixinTileEntityCompressedChest",
                    "MixinTileEntityExtremeAutoCrafter"
            );
            for (String mixinClass : avaritiaddonsMixins) {
                mixins.add("avaritiaddons." + mixinClass);
            }
        }

        if (loadedMods.contains("adventurebackpack")){

            boolean isGTNH = false;
            try {
                //GTNH fork have this class, but original version doesn't.
                Class.forName("com.darkona.adventurebackpack.block.TileAdventure");
                isGTNH = true;
            } catch (ClassNotFoundException ignored) {}

            if (isGTNH){
                Logger.warn("Detected GTNH Fork of adventure backpack! Using the GTNH patch is necessary!");
                } else {
                List<String> adventureBackpackMixins = Arrays.asList(
                        "MixinFontUtils",
                        "MixinInventoryBackpack",
                        "MixinInventoryCopterPack",
                        "MixinInventorySteamJetpack",
                        "MixinTileAdventureBackpack",
                        "MixinContainerBackpack"
                );
                for (String mixinClass : adventureBackpackMixins) {
                    mixins.add("adventurebackpack." + mixinClass);
                }
            }
        }

        if (loadedMods.contains("manametalmod")){
            List<String> manametalMixins = Arrays.asList(
                    "MixinBlockAncientThuliumChests",
                    "MixinBlockCastingAnvil",
                    "MixinBlockCastingFurnace",
                    "MixinBlockCastingGrindstone",
                    "MixinBlockCastingWelding",
                    "MixinBlockTileEntityAdvancedBrewing",
                    "MixinBlockTileEntityAetherEnergyGenerator",
                    "MixinBlockTileEntityAirMobSpawn",
                    "MixinBlockTileEntityAlloyFurnace",
                    "MixinBlockTileEntityBedrockCrusher",
                    "MixinBlockTileEntityBedrockOre",
                    "MixinBlockTileEntityCastingTable",
                    "MixinBlockTileEntityCheeseMaker",
                    "MixinBlockTileEntityChest",
                    "MixinBlockTileEntityCrusher",
                    "MixinBlockTileEntityCrusherIE",
                    "MixinBlockTileEntityCrystalPillars",
                    "MixinBlockTileEntityDarkEnchanting",
                    "MixinBlockTileEntityDarkFurnace",
                    "MixinBlockTileEntityDarkItemMake",
                    "MixinBlockTileEntityDarkPerfusion",
                    "MixinBlockTileEntityDarkSteelFurnace",
                    "MixinBlockTileEntityDarkTattoo",
                    "MixinBlockTileEntityEffectBaublesMaker",
                    "MixinBlockTileEntityEXPExtractor",
                    "MixinBlockTileEntityIceBox",
                    "MixinBlockTileEntityIronPlateMaker",
                    "MixinBlockTileEntityIronWroughtContainer",
                    "MixinBlockTileEntityItemRepair",
                    "MixinBlockTileEntityLogisticsCore",
                    "MixinBlockTileEntityManaEnergyGenerator",
                    "MixinBlockTileEntityManaFuelMake",
                    "MixinBlockTileEntityManaFurnace",
                    "MixinBlockTileEntityManaIngot",
                    "MixinBlockTileEntityManaMake",
                    "MixinBlockTileEntityManaMetalInjection",
                    "MixinBlockTileEntityManaSF",
                    "MixinBlockTileEntityManaSFurnace",
                    "MixinBlockTileEntityMetalChest2",
                    "MixinBlockTileEntityMetalCrusher",
                    "MixinBlockTileEntityMetalFurnace",
                    "MixinBlockTileEntityMetalFurnaceIE",
                    "MixinBlockTileEntityMetalSeparator",
                    "MixinBlockTileEntityOceanEnergyGenerator",
                    "MixinBlockTileEntityOreMine",
                    "MixinBlockTileEntityOrePurification",
                    "MixinBlockTileEntityPotionMake",
                    "MixinBlockTileEntityRuneSteelBox",
                    "MixinBlockTileEntitySafeChest",
                    "MixinBlockTileEntitySieve",
                    "MixinBlockTimeFurnace",
                    "MixinBlockWoodCabinet",
                    "MixinContainerWorkbenchClone",
                    "MixinEntityBoatM3",
                    "MixinEntityBossHydraItem",
                    "MixinEntityBossHydraItemCore",
                    "MixinEntityItemHolyDevice",
                    "MixinNbtBattleCard",
                    "MixinNbtBaubles",
                    "MixinNbtMoney",
                    "MixinNbtWarehouse",
                    "MixinRFMakeAetherEnergy",
                    "MixinRFMakeDark",
                    "MixinRFMakeMana",
                    "MixinSlotPotion",
                    "MixinTileEntityAdvancedBrewing",
                    "MixinTileEntityAetherEnergyGenerator",
                    "MixinTileEntityAirMobSpawn",
                    "MixinTileEntityAlloyFurnace",
                    "MixinTileEntityArmorstrengthen",
                    "MixinTileEntityArmorTable",
                    "MixinTileentityAuction",
                    "MixinTileEntityBase",
                    "MixinTileEntityBedrockCrusher",
                    "MixinTileEntityBedrockMaker",
                    "MixinTileEntityBedrockOre",
                    "MixinTileEntityBeeBreeding",
                    "MixinTileEntityBeecultivate",
                    "MixinTileEntityBeehive",
                    "MixinTileEntityBuild",
                    "MixinTileEntityCastingOther",
                    "MixinTileEntityCastingTable",
                    "MixinTileEntityCheeseMaker",
                    "MixinTileEntityChestBox",
                    "MixinTileEntityClothesTailor",
                    "MixinTileEntityCookingTable",
                    "MixinTileEntityCrusherMetal",
                    "MixinTileEntityCuttingBoard",
                    "MixinTileEntityDarkEnergyGenerator",
                    "MixinTileEntityDarkSteelBlast",
                    "MixinTileEntityDarkSteelFurnace",
                    "MixinTileEntityDiamondCompressor",
                    "MixinTileEntityEffectBaublesMaker",
                    "MixinTileEntityEquipmentDismantle",
                    "MixinTileEntityEXPExtractor",
                    "MixinTileEntityFishbox",
                    "MixinTileEntityFoodRootBox",
                    "MixinTileEntityFurnaceMetal",
                    "MixinTileEntityFurnaceSpecial",
                    "MixinTileEntityGemCraft",
                    "MixinTileEntityGemIdentification",
                    "MixinTileEntityGilded",
                    "MixinTileEntityHotPot",
                    "MixinTileEntityIceBox",
                    "MixinTileEntityIngot",
                    "MixinTileEntityIronCrusher",
                    "MixinTileEntityIronPlateMaker",
                    "MixinTileEntityIronWroughtCrusher",
                    "MixinTileEntityIronWroughtFurnace",
                    "MixinTileEntityIronWroughtSteelF",
                    "MixinTileEntityItemRepair",
                    "MixinTileEntityItemUseTable",
                    "MixinTileEntityJabbaBox",
                    "MixinTileEntityLogisticsBox",
                    "MixinTileEntityLogisticsCore",
                    "MixinTileEntityMagicUpdate",
                    "MixinTileEntityManaCraftTable",
                    "MixinTileEntityManaEnchanting",
                    "MixinTileEntityManaEnergyGenerator",
                    "MixinTileEntityManaFuelMake",
                    "MixinTileEntityManaFurnace",
                    "MixinTileEntityManaGravityWell",
                    "MixinTileEntityManaMake1",
                    "MixinTileEntityManaMetalInjection",
                    "MixinTileEntityManaOreDictionary",
                    "MixinTileEntityManaPawnshop",
                    "MixinTileEntityManaSF",
                    "MixinTileEntityManaSFurnace",
                    "MixinTileEntityManaSteelF",
                    "MixinTileEntityMetalChest",
                    "MixinTileEntityMetalCraftTable",
                    "MixinTileEntityMetalFurnace",
                    "MixinTileEntityMetalReduction",
                    "MixinTileEntityMetalSeparator",
                    "MixinTileEntityMintingMachine",
                    "MixinTileEntityOceanEnergyGenerator",
                    "MixinTileEntityOreMine",
                    "MixinTileEntityOrePurification",
                    "MixinTileEntityPackage",
                    "MixinTileEntityPlayerStore",
                    "MixinTileEntityPot",
                    "MixinTileEntityPotionMake",
                    "MixinTileEntityPowerCrystalBase",
                    "MixinTileEntityPrayerAltar",
                    "MixinTileEntityQualityTable",
                    "MixinTileEntityResourceTrade",
                    "MixinTileEntityRFMakeAetherEnergy",
                    "MixinTileEntityRFMakeDark",
                    "MixinTileEntityRFMakeMana",
                    "MixinTileEntityRuneSteelBox",
                    "MixinTileEntitySacrificialCeremony",
                    "MixinTileEntitySieve",
                    "MixinTileEntitySoulEnergyGenerator",
                    "MixinTileEntitySpinningWheel",
                    "MixinTileEntityStorageCoreM3",
                    "MixinTileEntitySwordGem",
                    "MixinTileEntityTimeFurnace",
                    "MixinTileEntityTotem",
                    "MixinTileEntityWandMaker",
                    "MixinTileEntityWeaponIdentification",
                    "MixinTileEntityWeaponScroll",
                    "MixinTileEntityWeaponStar",
                    "MixinTileEntityWeaponTable",
                    "MixinTileEntityWoodCabinet"
            );
            for (String mixinClass : manametalMixins) {
                mixins.add("manametal." + mixinClass);
            }
        }

        if (loadedMods.contains("Muya")){
            List<String> muyaMixins = Arrays.asList(
                    "MixinTemporaryWarehouseInventory",
                    "MixinTileEntityRedstoneChunkLoader"
            );
            for (String mixinClass : muyaMixins) {
                mixins.add("muya." + mixinClass);
            }
        }

        if (loadedMods.contains("BuildCraft|Core")){
            List<String> buildCraftMixins = Arrays.asList(
                    "MixinEntityRobot",
                    "MixinInventoryConcatenator",
                    "MixinNetworkUtils",
                    "MixinSimpleInventory",
                    "MixinTileAutoWorkbench",
                    "MixinTileBuilder",
                    "MixinTileEngineWithInventory",
                    "MixinTilePackager"
            );
            for (String mixinClass : buildCraftMixins) {
                mixins.add("buildcraft." + mixinClass);
            }
        }

        return mixins;
    }

}