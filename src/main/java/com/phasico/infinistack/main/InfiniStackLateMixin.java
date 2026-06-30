package com.phasico.infinistack.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;

// Define late mixins (mixins targeting non-coremod mod classes) in this class.
// These mixins get loaded after mod classes are put on the classpath, allowing you to mix into them.
@LateMixin
public class InfiniStackLateMixin implements ILateMixinLoader {

    //Helper method used to detect GTNH Version of a mod.
    //Never detect the classes you're going to mixin into.
    private static boolean hasClass(String name) {
        try { Class.forName(name); return true; }
        catch (ClassNotFoundException e) { return false; }
    }

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
                    "MixinBlockCraneBase",
                    "MixinBlockCustomMachine",
                    "MixinBlockDecoContainer",
                    "MixinBlockDummyable",
                    "MixinBlockFluidBarrel",
                    "MixinBlockMachineBase",
                    "MixinBlockMassStorage",
                    "MixinBlockStorageCrate",
                    "MixinBombMulti",
                    "MixinBufferUtil",
                    "MixinCompactLauncher",
                    "MixinContainerAnvil",
                    "MixinContainerBook",
                    "MixinCoreCore",
                    "MixinCranePartitioner",
                    "MixinDroneCrate",
                    "MixinDroneDock",
                    "MixinEntityDeliveryDrone",
                    "MixinEntityMinecartContainerBase",
                    "MixinEntityRailCarCargo",
                    "MixinInventoryAmmoBag",
                    "MixinInventoryCasingBag",
                    "MixinItemInventory",
                    "MixinItemStackUtil",
                    "MixinItemToolBox",
                    "MixinLaunchTable",
                    "MixinMachineAutocrafter",
                    "MixinMachineBattery",
                    "MixinMachineDiFurnace",
                    "MixinMachineDiFurnaceRTG",
                    "MixinMachineElectricFurnace",
                    "MixinMachineForceField",
                    "MixinMachineFunnel",
                    "MixinMachineICFPress",
                    "MixinMachineKeyForge",
                    "MixinMachineMissileAssembly",
                    "MixinMachineReactorControl",
                    "MixinMachineRTG",
                    "MixinMachineRtgFurnace",
                    "MixinMachineSatDock",
                    "MixinMachineSatLinker",
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
                    "MixinTileEntityCompactLauncher",
                    "MixinTileEntityCrateBase",
                    "MixinTileEntityDiFurnaceRTG",
                    "MixinTileEntityForceField",
                    "MixinTileEntityFoundryCastingBase",
                    "MixinTileEntityInventoryBase",
                    "MixinTileEntityLaunchTable",
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
                    "MixinFMPEAlchBagInventory",
                    "MixinMercurialEyeInventory",
                    "MixinRelayMK1Tile",
                    "MixinRMFurnaceTile",
                    "MixinTransmutationContainer",
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
                    "MixinTileTrader"
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
            List<String> adventureBackpackMixins;
            if (hasClass("com.darkona.adventurebackpack.util.ThaumcraftUtils")) { //GTNH Version
                adventureBackpackMixins = Arrays.asList(
                        "AccessorContainerBackpack",
                        "MixinContainerAdventure",
                        "MixinInventoryAdventure",
                        "MixinTileAdventure"
                );
            } else { //Original Version
                adventureBackpackMixins = Arrays.asList(
                        "MixinContainerBackpack",
                        "MixinInventoryBackpack",
                        "MixinInventoryCopterPack",
                        "MixinInventorySteamJetpack",
                        "MixinTileAdventureBackpack"
                );
            }
            for (String mixinClass : adventureBackpackMixins) {
                mixins.add("adventurebackpack." + mixinClass);
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
            mixins.add("muya.MixinTemporaryWarehouseInventory");
            mixins.add("muya.MixinTileEntityRedstoneChunkLoader");
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

        if (loadedMods.contains("StorageDrawers")){
            mixins.add("storagedrawers.MixinDrawerData");
            mixins.add("storagedrawers.MixinTileEntityDrawersComp");
        }

        if (loadedMods.contains("ironfurnaces")){
            List<String> ironFurnaceMixins = Arrays.asList(
                    "MixinCrystalFurnace",
                    "MixinDiamondFurnace",
                    "MixinGoldFurnace",
                    "MixinIronFurnace",
                    "MixinObsidianFurnace",
                    "MixinTileEntityCrystalFurnace",
                    "MixinTileEntityDiamondFurnace",
                    "MixinTileEntityGoldFurnace",
                    "MixinTileEntityIronFurnace",
                    "MixinTileEntityObsidianFurnace"
            );
            for (String mixinClass : ironFurnaceMixins){
                mixins.add("ironfurnace." + mixinClass);
            }
        }

        if (loadedMods.contains("daozcraft")){
            List<String> daoZaMixins = Arrays.asList(
                    "MixinConbag",
                    "MixinConEX",
                    "MixinInv",
                    "MixinInvBase",
                    "MixinInvMultiwand",
                    "MixinSlot_EX",
                    "MixinTEinvbase",
                    "MixinZipCard"
            );
            for (String mixinClass : daoZaMixins){
                mixins.add("daoza." + mixinClass);
            }
        }

        if (loadedMods.contains("gregtech_nh")){
            List<String> gregtechMixins = Arrays.asList(
                    "MixinBaseMetaPipeEntity",
                    "MixinBaseMetaTileEntity",
                    "MixinBTF_Inventory",
                    "MixinCommonMetaTileEntity",
                    "MixinCoverItemMeter",
                    "MixinGTMetaItemBase",
                    "MixinInventoryCircuitProgrammer",
                    "MixinInventoryFishTrap",
                    "MixinInventoryPestKiller",
                    "MixinInventoryProjectMain",
                    "MixinInventoryProjectOutput",
                    "MixinInventoryTradeMain",
                    "MixinInventoryTradeOutput",
                    "MixinInventoryUtils",
                    "MixinMetaBaseItem",
                    "MixinMetaGeneratedItem98",
                    "MixinMetaGeneratedItem99",
                    "MixinServerEventHandler",
                    "MixinSlotGeneric",
                    "MixinSlotIntegratedCircuit",
                    "MixinTeaAcceptorTile",
                    "MixinTileEntityHeatedWaterPump"
            );
            for(String mixinClass : gregtechMixins){
                mixins.add("gregtech." + mixinClass);
            }
        }

        if (loadedMods.contains("modularui2")){
            mixins.add("modularui2.MixinItemStackHandler");
        }

        if (loadedMods.contains("HopperDuctMod")){
            List<String> hopperDuctMixins = Arrays.asList(
                    "MixinBlockGratedHopper",
                    "MixinBlockHopperDuct",
                    "MixinTileEntityGratedHopper",
                    "MixinTileEntityHopperDuct"
            );
            for(String mixinClass : hopperDuctMixins){
                mixins.add("hopperduct." + mixinClass);
            }
        }

        if (loadedMods.contains("chesthopper")) {
            List<String> chestHopperMixins = Arrays.asList(
                    "MixinBlockChestHopperBase",
                    "MixinTileEntityBaseChestHopper"
            );
            for(String mixinClass : chestHopperMixins){
                mixins.add("chesthopper." + mixinClass);
            }
        }

        if (loadedMods.contains("AdvancedSolarPanel")){
            List<String> advsolarpanelMixins = Arrays.asList(
                    "MixinTileEntityMolecularTransformer",
                    "MixinTileEntitySolarPanel"
            );
            for(String mixinClass : advsolarpanelMixins){
                mixins.add("advsolarpanel." + mixinClass);
            }
        }

        if (loadedMods.contains("ae2fc")){
            mixins.add("ae2fc.MixinAeItemStackHandler");
        }

        if (loadedMods.contains("appliedenergistics2")){
            List<String> ae2Mixins = Arrays.asList(
                    "MixinAEBaseInvTile",
                    "MixinAppEngInternalInventory",
                    "MixinAppEngInternalAEInventory",
                    "MixinLayerISidedInventory",
                    "MixinWrapperBCPipe"
            );
            for(String mixinClass : ae2Mixins){
                mixins.add("ae2." + mixinClass);
            }
        }

        if (loadedMods.contains("ae2stuff")){
            List<String> ae2stuffMixins = Arrays.asList(

            );
            for(String mixinClass : ae2stuffMixins){
                mixins.add("ae2stuff." + mixinClass);
            }
        }

        if (loadedMods.contains("AFSU")){
            mixins.add("afsu.MixinAFSUBlock");
        }

        if (loadedMods.contains("alchgrate")){
            mixins.add("alchgrate.MixinTileChestGrate");
        }

        if (loadedMods.contains("GalacticraftAmunRa")){
            List<String> amunraMixins = Arrays.asList(
                    "MixinBlockARChest",
                    "MixinBlockHydroponics",
                    "MixinInventorySchematicShuttle",
                    "MixinTileEntityARChest",
                    "MixinTileEntityGravitation",
                    "MixinTileEntityHydroponics",
                    "MixinTileEntityIsotopeGenerator",
                    "MixinTileEntityMothershipSettings",
                    "MixinTileEntityShuttleDock"
            );
            for(String mixinClass : amunraMixins){
                mixins.add("amunra." + mixinClass);
            }
        }

        if (loadedMods.contains("ArchitectureCraft")){
            mixins.add("architecture.MixinInventoryHelper");
        }

        if (loadedMods.contains("asielib")){
            List<String> asielibMixins = Arrays.asList(
                    "MixinInventoryBase",
                    "MixinTileEntityInventory",
                    "MixinTileMachine"
            );
            for(String mixinClass : asielibMixins){
                mixins.add("asielib." + mixinClass);
            }
        }

        if (loadedMods.contains("Automagy")){
            List<String> automagyMixins = Arrays.asList(
                    "MixinInventorySimple",
                    "MixinModTileEntityWithInventory",
                    "MixinTileEntityMirrorWithEye",
                    "MixinTjUtil"
            );
            for(String mixinClass : automagyMixins){
                mixins.add("automagy." + mixinClass);
            }
        }

        if (loadedMods.contains("bdlib")){
            List<String> bdlibMixins = Arrays.asList(
                    "MixinBaseInventoryClass",
                    "MixinInventoryProxyClass",
                    "MixinItemStackSerialize",
                    "MixinResourceInventoryOutput"
            );
            for(String mixinClass : bdlibMixins){
                mixins.add("bdlib." + mixinClass);
            }
        }

        if (loadedMods.contains("betterquesting")){
            mixins.add("betterquesting.MixinBlockSubmitStation");
        }

        if (loadedMods.contains("BiblioCraft")){
            List<String> bibliocraftMixins = Arrays.asList(
                    "MixinContainerFancyWorkbench",
                    "MixinSlotAtlas",
                    "MixinSlotBlankBook",
                    "MixinSlotBook",
                    "MixinSlotChase",
                    "MixinSlotCookie",
                    "MixinSlotDisc",
                    "MixinSlotFood",
                    "MixinSlotGenericShelf",
                    "MixinSlotInk",
                    "MixinSlotLabel",
                    "MixinSlotPanalerPanels",
                    "MixinSlotPanelerInput",
                    "MixinSlotPotionShelf",
                    "MixinSlotSlottedBook",
                    "MixinSlotTable",
                    "MixinSlotTesterSlot",
                    "MixinSlotWeaponCase",
                    "MixinSlotWeaponRack",
                    "MixinSlotWorkbenchBook",
                    "MixinSlotWritingDeskBooks",
                    "MixinSlotWrittenBook",
                    "MixinTileEntityBookcase",
                    "MixinTileEntityCookieJar",
                    "MixinTileEntityDinnerPlate",
                    "MixinTileEntityDiscRack",
                    "MixinTileEntityFancyWorkbench",
                    "MixinTileEntityFramedChest",
                    "MixinTileEntityFurniturePaneler",
                    "MixinTileEntityGenericShelf",
                    "MixinTileEntityLabel",
                    "MixinTileEntityPaintPress",
                    "MixinTileEntityPotionShelf",
                    "MixinTileEntityPrintPress",
                    "MixinTileEntityTable",
                    "MixinTileEntityTypeMachine",
                    "MixinTileEntityTypewriter",
                    "MixinTileEntityWeaponCase",
                    "MixinTileEntityWeaponRack",
                    "MixinTileEntityWritingDesk"
            );
            for(String mixinClass : bibliocraftMixins){
                mixins.add("bibliocraft." + mixinClass);
            }
        }

        if (loadedMods.contains("BinnieCore")){
            List<String> binniemodsMixins = Arrays.asList(
                    "MixinComponentInventorySlots",
                    "MixinDefaultInventory",
                    "MixinWindowInventory"
            );
            for(String mixinClass : binniemodsMixins){
                mixins.add("binniemods." + mixinClass);
            }
        }

        if (loadedMods.contains("BloodArsenal")){
            mixins.add("bloodarsenal.MixinTilePortableAltar");
        }

        if (loadedMods.contains("AWWayofTime")){
            List<String> bloodmagicMixins = Arrays.asList(
                    "MixinTEAlchemicCalcinator",
                    "MixinTEInventory"
            );
            for(String mixinClass : bloodmagicMixins){
                mixins.add("bloodmagic." + mixinClass);
            }
        }

        if (loadedMods.contains("Botania")){
            List<String> botaniaMixins = Arrays.asList(
                    "MixinBlockAltar",
                    "MixinBlockAvatar",
                    "MixinBlockBrewery",
                    "MixinBlockCorporeaBase",
                    "MixinBlockEnchanter",
                    "MixinBlockHourglass",
                    "MixinBlockOpenCrate",
                    "MixinBlockPrism",
                    "MixinBlockRuneAltar",
                    "MixinBlockSparkChanger",
                    "MixinBlockSpreader",
                    "MixinGenericInventory",
                    "MixinTileSimpleInventory"
            );
            for(String mixinClass : botaniaMixins){
                mixins.add("botania." + mixinClass);
            }
        }

        if (loadedMods.contains("BrandonsCore")){
            mixins.add("brandonscore.MixinGenericInventory");
        }

        if (loadedMods.contains("CarpentersBlocks")){
            mixins.add("carpentersblocks.MixinTECarpentersSafe");
        }

        if (loadedMods.contains("cookingforblockheads")){
            List<String> cfbhMixins = Arrays.asList(
                    "MixinBaseKitchenTileWithInventory",
                    "MixinBlockOven",
                    "MixinBlockToolRack",
                    "MixinInventoryLarge",
                    "MixinInventoryNormal",
                    "MixinInventoryRecipeBook",
                    "MixinInventoryRecipeBookMatrix",
                    "MixinTileOven"
            );
            for(String mixinClass : cfbhMixins){
                mixins.add("cfbh." + mixinClass);
            }
        }

        if (loadedMods.contains("chisel")){
            List<String> chiselMixins = Arrays.asList(
                    "MixinInventoryChiselSelection",
                    "MixinTileEntityAutoChisel",
                    "MixinTileEntityPresent"
            );
            for(String mixinClass : chiselMixins){
                mixins.add("chisel." + mixinClass);
            }
        }

        if (loadedMods.contains("CoFHCore")){
            List<String> cofhcoreMixins = Arrays.asList(
                    "MixinCustomInventoryWrapper",
                    "MixinInventoryContainerItemWrapper",
                    "MixinInventoryCraftingCustom"
            );
            for(String mixinClass : cofhcoreMixins){
                mixins.add("cofhcore." + mixinClass);
            }
        }

        if (loadedMods.contains("computronics")){
            mixins.add("computronics.MixinSlotSecureInput");
        }

        if (loadedMods.contains("DraconicEvolution")){
            List<String> draconicMixins = Arrays.asList(
                    "MixinContainerDraconiumChest",
                    "MixinGenericInventory",
                    "MixinInventoryCraftingChest",
                    "MixinInventoryCraftingChestResult",
                    "MixinSlotBook",
                    "MixinSlotOutput",
                    "MixinTileContainerTemplate",
                    "MixinTileDissEnchanter",
                    "MixinTileDraconiumChest",
                    "MixinTileGenerator",
                    "MixinTileGrinder",
                    "MixinTileReactorCore",
                    "MixinTileUpgradeModifier",
                    "MixinTileWeatherController"
            );
            for(String mixinClass : draconicMixins){
                mixins.add("draconic." + mixinClass);
            }
        }

        if (loadedMods.contains("DummyCore")){
            mixins.add("dummycore.MixinMiscUtils");
        }

        if (loadedMods.contains("EMT")){
            List<String> emtMixins = Arrays.asList(
                    "MixinTileEntityEtherealMacerator",
                    "MixinTileEntityIndustrialWandRecharge"
            );
            for(String mixinClass : emtMixins){
                mixins.add("emt." + mixinClass);
            }
        }

        if (loadedMods.contains("endercore")){
            mixins.add("endercore.MixinArrayInventory");
        }

        if (loadedMods.contains("EnderIO")){
            List<String> enderioMixins = Arrays.asList(
                    "MixinAbstractMachineEntity",
                    "MixinCompositeInventory",
                    "MixinTileEnchanter",
                    "MixinTileHyperCube",
                    "MixinTileVacuumChest"
            );
            for(String mixinClass : enderioMixins){
                mixins.add("enderio." + mixinClass);
            }
        }

        if (loadedMods.contains("EnderStorage")){
            List<String> enderstorageMixins = Arrays.asList(
                    "MixinEnderItemStorage",
                    "MixinTileEnderChest"
            );
            for(String mixinClass : enderstorageMixins){
                mixins.add("enderstorage." + mixinClass);
            }
        }

        if (loadedMods.contains("enhancedlootbags")){
            mixins.add("enhancedlootbags.MixinFakeLootBagInventory");
        }

        if (loadedMods.contains("etfuturum")){
            List<String> etfuturumMixins = Arrays.asList(
                    "MixinBlockBarrel",
                    "MixinBlockBlastFurnace",
                    "MixinBlockSmoker",
                    "MixinEntityNewBoatWithChest",
                    "MixinTileEntityBarrel",
                    "MixinTileEntityBlastFurnace",
                    "MixinTileEntityShulkerBox",
                    "MixinTileEntitySmoker"
            );
            for(String mixinClass : etfuturumMixins){
                mixins.add("etfuturum." + mixinClass);
            }
        }

        if (loadedMods.contains("ExtraUtilities")){
            List<String> extrautilitiesMixins = Arrays.asList(
                    "MixinBlockEnderConstructor",
                    "MixinBlockFullChest",
                    "MixinBlockGenerator",
                    "MixinBlockMiniChest",
                    "MixinBlockTransferNode",
                    "MixinBlockTransferPipe",
                    "MixinBlockTrashCan",
                    "MixinInventoryKraft",
                    "MixinTileEnderCollector",
                    "MixinTileEnderConstructor",
                    "MixinTileEntityTransferNodeInventory",
                    "MixinTileEntityTrashCan",
                    "MixinXUHelper"
            );
            for(String mixinClass : extrautilitiesMixins){
                mixins.add("extrautilities." + mixinClass);
            }
        }

        if (loadedMods.contains("FloodLights")){
            mixins.add("floodlights.MixinTileEntityMetaFloodlight");
        }

        if (loadedMods.contains("gadomancy")){
            List<String> gadomancyMixins = Arrays.asList(
                    "MixinTileArcaneDropper",
                    "MixinTileArcaneHand",
                    "MixinTileArcanePackager"
            );
            for(String mixinClass : gadomancyMixins){
                mixins.add("gadomancy." + mixinClass);
            }
        }

        if (loadedMods.contains("GalacticraftCore")){
            List<String> galacticraftMixins = Arrays.asList(
                    "MixinBlockAdvancedTile",
                    "MixinBlockParaChest",
                    "MixinBlockRefinery",
                    "MixinBlockT1TreasureChest",
                    "MixinBlockTier2TreasureChest",
                    "MixinBlockTier3TreasureChest",
                    "MixinBlockTileGC",
                    "MixinEntityAstroMiner",
                    "MixinEntityAutoRocket",
                    "MixinEntityBuggy",
                    "MixinInventoryBuggyBench",
                    "MixinInventoryEntity",
                    "MixinInventoryExtended",
                    "MixinInventoryRocketBench",
                    "MixinInventorySchematic",
                    "MixinInventorySchematicAstroMiner",
                    "MixinInventorySchematicCargoRocket",
                    "MixinInventorySchematicTier2Rocket",
                    "MixinInventorySchematicTier3Rocket",
                    "MixinInventorySlimeling",
                    "MixinPersistantInventoryCrafting",
                    "MixinTileBaseElectricBlockWithInventory",
                    "MixinTileEntityCoalGenerator",
                    "MixinTileEntityDish",
                    "MixinTileEntityElectricIngotCompressor",
                    "MixinTileEntityIngotCompressor",
                    "MixinTileEntityMinerBase",
                    "MixinTileEntityOxygenCollector",
                    "MixinTileEntityOxygenDistributor",
                    "MixinTileEntityOxygenSealer",
                    "MixinTileEntityOxygenStorageModule",
                    "MixinTileEntityParaChest",
                    "MixinTileEntityShortRangeTelepad",
                    "MixinTileEntitySolar",
                    "MixinTileEntityTreasureChest",
                    "MixinTileEntityTreasureChestAsteroids",
                    "MixinTileEntityTreasureChestMars"
            );
            for(String mixinClass : galacticraftMixins){
                mixins.add("galacticraft." + mixinClass);
            }
        }

        if (loadedMods.contains("GalaxySpace")){
            List<String> galaxyspaceMixins = Arrays.asList(
                    "MixinTileEntityAssemblyMachine",
                    "MixinTileEntityFuelGenerator",
                    "MixinTileEntityGeothermalGenerator",
                    "MixinTileEntityOxStorageModule",
                    "MixinTileEntitySolarPanel",
                    "MixinTileEntitySolarWind"
            );
            for(String mixinClass : galaxyspaceMixins){
                mixins.add("galaxyspace." + mixinClass);
            }
        }

        if (loadedMods.contains("dreamcraft")){
            mixins.add("gtnhcoremod.MixinTileEntityBabyChest");
        }

        if (loadedMods.contains("harvestcraft")){
            List<String> harvestcraftMixins = Arrays.asList(
                    "MixinBlockPamAnimalTrap",
                    "MixinBlockPamApiary",
                    "MixinBlockPamChurn",
                    "MixinBlockPamFishTrap",
                    "MixinBlockPamOven",
                    "MixinBlockPamQuern",
                    "MixinSlotPamAnimalTrap",
                    "MixinSlotPamFishTrap",
                    "MixinTileEntityChurn",
                    "MixinTileEntityMarket",
                    "MixinTileEntityOven",
                    "MixinTileEntityPamAnimalTrap",
                    "MixinTileEntityPamApiary",
                    "MixinTileEntityPamFishTrap",
                    "MixinTileEntityPamGrinder",
                    "MixinTileEntityPamPresser",
                    "MixinTileEntityQuern"
            );
            for(String mixinClass : harvestcraftMixins){
                mixins.add("harvestcraft." + mixinClass);
            }
        }

        if (loadedMods.contains("HardcoreEnderExpansion")){
            List<String> heeMixins = Arrays.asList(
                    "MixinSlotBrewingStandIngredient",
                    "MixinSlotReadOnly",
                    "MixinTileEntityAbstractInventory"
            );
            for(String mixinClass : heeMixins){
                mixins.add("hee." + mixinClass);
            }
        }

        if (loadedMods.contains("holoinventory")){
            mixins.add("holoinventory.MixinFakeInventory");
        }

        if (loadedMods.contains("IC2")){
            List<String> ic2Mixins = Arrays.asList(
                    "MixinHandHeldInventory",
                    "MixinDataEncoder"
            );
            for(String mixinClass : ic2Mixins){
                mixins.add("ic2." + mixinClass);
            }
        }

        if (loadedMods.contains("IC2NuclearControl")){
            List<String> ic2nuclearMixins = Arrays.asList(
                    "MixinTileEntityAverageCounter",
                    "MixinTileEntityEnergyCounter",
                    "MixinTileEntityInfoPanel",
                    "MixinTileEntityRangeTrigger",
                    "MixinTileEntityRemoteThermo"
            );
            for(String mixinClass : ic2nuclearMixins){
                mixins.add("ic2nuclear." + mixinClass);
            }
        }

        if (loadedMods.contains("LogisticsPipes")){
            mixins.add("logisticspipes.MixinCardManagmentInventory");
        }

        if (loadedMods.contains("MagicBees")){
            mixins.add("magicbees.MixinTileEntityEffectJar");
        }

        if (loadedMods.contains("malisisdoors")){
            mixins.add("malisisdoors.MixinTileEntitySidedInventory");
        }

        if (loadedMods.contains("Mantle")){
            List<String> mantleMixins = Arrays.asList(
                    "MixinAdaptiveInventoryLogic",
                    "MixinExpandableInventoryLogic",
                    "MixinInventoryBlock",
                    "MixinInventoryLogic"
            );
            for(String mixinClass : mantleMixins){
                mixins.add("mantle." + mixinClass);
            }
        }

        if (loadedMods.contains("Natura")){
            List<String> naturaMixins = Arrays.asList(
                    "MixinNetherrackFurnaceBlock",
                    "MixinNetherrackFurnaceLogic",
                    "MixinWorkbenchContainer"
            );
            for(String mixinClass : naturaMixins){
                mixins.add("natura." + mixinClass);
            }
        }

        if (loadedMods.contains("OpenComputers")){
            List<String> opencomputersMixins = Arrays.asList(
                    "MixinDrone",
                    "MixinRobot"
            );
            for(String mixinClass : opencomputersMixins){
                mixins.add("opencomputers." + mixinClass);
            }
        }

        if (loadedMods.contains("OpenMods")){
            mixins.add("openmodslibs.MixinGenericInventory");
        }

        if (loadedMods.contains("openprinter")){
            List<String> openprinterMixins = Arrays.asList(
                    "MixinBlockFileCabinet",
                    "MixinBlockPrinter",
                    "MixinBlockShredder",
                    "MixinFileCabinetTE",
                    "MixinFolderInventory",
                    "MixinPrinterTE",
                    "MixinShredderTE"
            );
            for(String mixinClass : openprinterMixins){
                mixins.add("openprinter." + mixinClass);
            }
        }

        if (loadedMods.contains("opensecurity")){
            List<String> opensecurityMixins = Arrays.asList(
                    "MixinBlockCardWriter",
                    "MixinBlockEnergyTurret",
                    "MixinTileEntityCardWriter"
            );
            for(String mixinClass : opensecurityMixins){
                mixins.add("opensecurity." + mixinClass);
            }
        }

        if (loadedMods.contains("openmodularturrets")){
            List<String> openturretsMixins = Arrays.asList(
                    "MixinExpanderInvTierFiveTileEntity",
                    "MixinTileEntityContainer"
            );
            for(String mixinClass : openturretsMixins){
                mixins.add("openturrets." + mixinClass);
            }
        }

        if (loadedMods.contains("ProjectBlue")){
            mixins.add("projectblue.MixinBaseContainerBlock");
        }

        if (loadedMods.contains("Railcraft")){
            List<String> railcraftMixins = Arrays.asList(
                    "AccessorContainerCartWork",
                    "MixinDataTools",
                    "MixinInventoryConcatenator",
                    "MixinInvTools",
                    "MixinRailcraftContainer",
                    "MixinSlotEnergy",
                    "MixinSlotFeed",
                    "MixinSlotFuel",
                    "MixinSlotLinked",
                    "MixinSlotStackFilter",
                    "MixinSlotUpgrade",
                    "MixinStandaloneInventory",
                    "MixinTileBoilerFirebox",
                    "MixinTileDispenserCart",
                    "MixinTileDispenserTrain",
                    "MixinTileEngineSteamHobby",
                    "MixinTileMultiBlockInventory",
                    "MixinTileRollingMachine"
            );
            for(String mixinClass : railcraftMixins){
                mixins.add("railcraft." + mixinClass);
            }
        }

        if (loadedMods.contains("RandomThings")){
            mixins.add("randomthings.MixinBlockAdvancedItemCollector");
            mixins.add("randomthings.MixinSlotVoid");
        }

        if (loadedMods.contains("RIO")){
            List<String> remoteioMixins = Arrays.asList(
                    "MixinInventoryArray",
                    "MixinInventoryHelper",
                    "MixinInventoryTileCrafting"
            );
            for(String mixinClass : remoteioMixins){
                mixins.add("remoteio." + mixinClass);
            }
        }

        if (loadedMods.contains("StevesFactoryManager")){
            List<String> sfmMixins = Arrays.asList(
                    "MixinTileEntityBreaker",
                    "MixinTileEntityCreative",
                    "MixinTileEntityIntake"
            );
            for(String mixinClass : sfmMixins){
                mixins.add("sfm." + mixinClass);
            }
        }

        if (loadedMods.contains("SGCraft")){
            mixins.add("sgcraft.MixinInventoryHelper");
        }

        if (loadedMods.contains("StevesCarts")){
            List<String> stevescartsMixins = Arrays.asList(
                    "MixinBlockCargoManager",
                    "MixinBlockCartAssembler",
                    "MixinBlockLiquidManager",
                    "MixinBlockUpgrade",
                    "MixinMinecartModular",
                    "MixinSlotAssemblerFuel",
                    "MixinTileEntityCartAssembler",
                    "MixinTileEntityDistributor",
                    "MixinTileEntityManager",
                    "MixinTileEntityUpgrade"
            );
            for(String mixinClass : stevescartsMixins){
                mixins.add("stevescarts." + mixinClass);
            }
        }

        if (loadedMods.contains("TConstruct")){
            List<String> tconstructMixins = Arrays.asList(
                    "MixinArmorExtended",
                    "MixinCraftingStationContainer",
                    "MixinEquipBlock",
                    "MixinInventoryCraftingStation",
                    "MixinInventoryCraftingStationResult",
                    "MixinKnapsackInventory",
                    "MixinSmelteryLogic",
                    "MixinTileEntityLandmine"
            );
            for(String mixinClass : tconstructMixins){
                mixins.add("tconstruct." + mixinClass);
            }
        }

        if (loadedMods.contains("Thaumcraft")){
            List<String> thaumcraftMixins = Arrays.asList(
                    "MixinBlockChestHungry",
                    "MixinInventoryFake",
                    "MixinInventoryHandMirror",
                    "MixinInventoryPech",
                    "MixinTileAlchemyFurnace",
                    "MixinTileArcaneBore",
                    "MixinTileChestHungry",
                    "MixinTileDeconstructionTable",
                    "MixinTileGrate",
                    "MixinTileMagicBox",
                    "MixinTileMagicWorkbench",
                    "MixinTileMirror",
                    "MixinTileResearchTable",
                    "MixinTileSpa",
                    "MixinTileThaumatorium",
                    "MixinTileThaumatoriumTop",
                    "MixinTileThaumcraftInventory"
            );
            for(String mixinClass : thaumcraftMixins){
                mixins.add("thaumcraft." + mixinClass);
            }
        }

        if (loadedMods.contains("thaumicenergistics")){
            mixins.add("thaumicenergistics.MixinPartArcaneCraftingTerminal");
        }

        if (loadedMods.contains("ThaumicExploration")){
            List<String> thaumicexplorationMixins = Arrays.asList(
                    "MixinBlockBoundChest",
                    "MixinBlockThinkTank",
                    "MixinSortingInventory",
                    "MixinTempInventory",
                    "MixinTileEntityBoundChest",
                    "MixinTileEntityThinkTank",
                    "MixinTXEventHandler"
            );
            for(String mixinClass : thaumicexplorationMixins){
                mixins.add("thaumicexploration." + mixinClass);
            }
        }

        if (loadedMods.contains("ThaumicHorizons")){
            List<String> thaumichorizonsMixins = Arrays.asList(
                    "MixinInventoryFingers",
                    "MixinTileBloodInfuser",
                    "MixinTileInspiratron",
                    "MixinTileSoulExtractor",
                    "MixinTileVat"
            );
            for(String mixinClass : thaumichorizonsMixins){
                mixins.add("thaumichorizons." + mixinClass);
            }
        }

        if (loadedMods.contains("ThaumicTinkerer")){
            List<String> thaumictinkererMixins = Arrays.asList(
                    "MixinBlockAnimationTablet",
                    "MixinBlockAspectAnalyzer",
                    "MixinBlockEnchanter",
                    "MixinBlockFunnel",
                    "MixinBlockMagnet",
                    "MixinBlockRepairer",
                    "MixinBlockWarpGate",
                    "MixinFakeInventory",
                    "MixinInventoryIchorPouch",
                    "MixinTileAnimationTablet",
                    "MixinTileAspectAnalyzer",
                    "MixinTileRPlacer"
            );
            for(String mixinClass : thaumictinkererMixins){
                mixins.add("thaumictinkerer." + mixinClass);
            }
        }

        if (loadedMods.contains("TwilightForest")){
            List<String> twilightforestMixins = Arrays.asList(
                    "MixinBlockTFCinderFurnace",
                    "MixinInventoryTFGoblinInput",
                    "MixinInventoryTFGoblinUncrafting",
                    "MixinTileEntityTFCinderFurnace"
            );
            for(String mixinClass : twilightforestMixins){
                mixins.add("twilightforest." + mixinClass);
            }
        }

        if (loadedMods.contains("ae2wct")){
            List<String> wirelesscraftingMixins = Arrays.asList(
                    "MixinWCTInventoryCrafting",
                    "MixinWCTInventoryCraftResult",
                    "MixinWCTInventoryTrash"
            );
            for(String mixinClass : wirelesscraftingMixins){
                mixins.add("wirelesscrafting." + mixinClass);
            }
        }

        if (loadedMods.contains("witchery")){
            List<String> witcheryMixins = Arrays.asList(
                    "MixinBlockBrazier",
                    "MixinBlockDistillery",
                    "MixinBlockGrassper",
                    "MixinBlockLeechChest",
                    "MixinBlockPoppetShelf",
                    "MixinBlockSpinningWheel",
                    "MixinBlockWitchesOven",
                    "MixinSlotClayJar",
                    "MixinTileEntityBrazier",
                    "MixinTileEntityDistillery",
                    "MixinTileEntityKettle",
                    "MixinTileEntityLeechChest",
                    "MixinTileEntityPoppetShelf",
                    "MixinTileEntitySilverVat",
                    "MixinTileEntitySpinningWheel",
                    "MixinTileEntityWitchesOven"
            );
            for(String mixinClass : witcheryMixins){
                mixins.add("witchery." + mixinClass);
            }
        }

        if (loadedMods.contains("WitchingGadgets")){
            List<String> witchinggadgetsMixins = Arrays.asList(
                    "MixinBlockWGWoodenDevice",
                    "MixinInventoryBag",
                    "MixinInventoryCloak",
                    "MixinInventoryPrimordialGlove",
                    "MixinInventoryPrimordialRing",
                    "MixinTileEntityCuttingTable",
                    "MixinTileEntityLabelLibrary",
                    "MixinTileEntitySpinningWheel"
            );
            for(String mixinClass : witchinggadgetsMixins){
                mixins.add("witchinggadgets." + mixinClass);
            }
        }

        if (loadedMods.contains("JABBA")) {
            mixins.add("jabba.MixinBarrelPacketHandler");
        }

        if (loadedMods.contains("serverutilities")) {
            mixins.add("serverutilities.MixinItemStackSerializer");
        }

        return mixins;
    }

}