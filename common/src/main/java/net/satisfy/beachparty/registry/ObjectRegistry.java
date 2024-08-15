package net.satisfy.beachparty.registry;


import dev.architectury.registry.fuel.FuelRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.satisfy.beachparty.Beachparty;
import net.satisfy.beachparty.block.*;
import net.satisfy.beachparty.block.furnitureblocks.*;
import net.satisfy.beachparty.entity.BeachpartyBoat;
import net.satisfy.beachparty.item.*;
import net.satisfy.beachparty.item.armor.BeachHatItem;
import net.satisfy.beachparty.item.armor.BeachpartyArmorItem;
import net.satisfy.beachparty.item.armor.DyeableBeachpartyArmorItem;
import net.satisfy.beachparty.util.BeachpartyIdentifier;
import net.satisfy.beachparty.util.BeachpartyUtil;

import java.util.function.Consumer;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class ObjectRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Beachparty.MOD_ID, Registries.ITEM);
    public static final Registrar<Item> ITEM_REGISTRAR = ITEMS.getRegistrar();
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Beachparty.MOD_ID, Registries.BLOCK);
    public static final Registrar<Block> BLOCK_REGISTRAR = BLOCKS.getRegistrar();

    public static final RegistrySupplier<Block> PALM_LEAVES = registerWithItem("palm_leaves", () -> new PalmLeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));
    public static final Supplier<SaplingBlock> PALM_SAPLING = registerWithItem("palm_sapling", PalmSaplingBlock::new);
    public static final RegistrySupplier<Block> SAND_PILE = registerWithoutItem("sand_pile", () -> new SandPileBlock(14406560, BlockBehaviour.Properties.copy(Blocks.SAND).mapColor(MapColor.SAND)));
    public static final RegistrySupplier<Block> STRIPPED_PALM_LOG = registerLog("stripped_palm_log");
    public static final RegistrySupplier<Block> PALM_LOG = registerLog("palm_log");
    public static final RegistrySupplier<Block> STRIPPED_PALM_WOOD = registerLog("stripped_palm_wood");
    public static final RegistrySupplier<Block> PALM_WOOD = registerLog("palm_wood");
    public static final RegistrySupplier<Block> PALM_BEAM = registerLog("palm_beam");
    public static final RegistrySupplier<Block> PALM_PLANKS = registerWithItem("palm_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BAMBOO_PLANKS)));
    public static final RegistrySupplier<Block> PALM_FLOORBOARD = registerWithItem("palm_floorboard", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BAMBOO_PLANKS)));
    public static final RegistrySupplier<Block> PALM_STAIRS = registerWithItem("palm_stairs", () -> new StairBlock(PALM_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(PALM_PLANKS.get())));
    public static final RegistrySupplier<Block> PALM_SLAB = registerWithItem("palm_slab", () -> new SlabBlock(getSlabSettings()));
    public static final RegistrySupplier<Block> PALM_FENCE = registerWithItem("palm_fence", () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)));
    public static final RegistrySupplier<Block> PALM_FENCE_GATE = registerWithItem("palm_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE), WoodType.BAMBOO));
    public static final RegistrySupplier<Block> PALM_BUTTON = registerWithItem("palm_button", () -> woodenButton(FeatureFlags.VANILLA));
    public static final RegistrySupplier<Block> PALM_PRESSURE_PLATE = registerWithItem("palm_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE), BlockSetType.BAMBOO));
    public static final RegistrySupplier<Block> PALM_DOOR = registerWithItem("palm_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR), BlockSetType.BAMBOO));
    public static final RegistrySupplier<Block> PALM_TRAPDOOR = registerWithItem("palm_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR), BlockSetType.BAMBOO));
    public static final RegistrySupplier<Block> DRIED_WHEAT_BLOCK = registerWithItem("dried_wheat_block", () -> new HayBlock(BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK)));
    public static final RegistrySupplier<Block> DRIED_WHEAT_STAIRS = registerWithItem("dried_wheat_stairs", () -> new StairBlock(PALM_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(PALM_PLANKS.get()).sound(SoundType.GRASS)));
    public static final RegistrySupplier<Block> DRIED_WHEAT_SLAB = registerWithItem("dried_wheat_slab", () -> new SlabBlock(getSlabSettings().sound(SoundType.GRASS)));
    public static final RegistrySupplier<Block> LOUNGE_CHAIR = registerWithItem("lounge_chair", () -> new LoungeChairBlock(BlockBehaviour.Properties.copy(Blocks.BAMBOO_PLANKS)));
    public static final RegistrySupplier<Block> CHAIR = registerWithItem("chair", () -> new ChairBlock(BlockBehaviour.Properties.copy(Blocks.BAMBOO_PLANKS).pushReaction(PushReaction.IGNORE)));
    public static final RegistrySupplier<Block> TABLE = registerWithItem("table", () -> new TableBlock(BlockBehaviour.Properties.copy(Blocks.BAMBOO_PLANKS)));
    public static final RegistrySupplier<Block> BEACH_CHAIR = registerWithItem("beach_chair", () -> new BeachChairBlock(BlockBehaviour.Properties.copy(Blocks.BAMBOO_PLANKS)));
    public static final RegistrySupplier<Block> DECK_CHAIR = registerWithItem("deck_chair", () -> new DeckChairBlock(BlockBehaviour.Properties.copy(Blocks.BAMBOO_PLANKS)));
    public static final RegistrySupplier<Block> TIKI_CHAIR = registerWithItem("tiki_chair", () -> new TikiChairBlock(BlockBehaviour.Properties.copy(Blocks.BAMBOO_PLANKS)));
    //public static final RegistrySupplier<Block> TIKI_BAR = registerWithItem("tiki_bar", () -> new TikiBarBlock(BlockBehaviour.Properties.copy(Blocks.BAMBOO_PLANKS)));
    public static final RegistrySupplier<Block> CABINET = registerWithItem("cabinet", () -> new CabinetBlock(BlockBehaviour.Properties.copy(Blocks.BAMBOO_PLANKS), () -> SoundEvents.BAMBOO_WOOD_TRAPDOOR_OPEN, () -> SoundEvents.BAMBOO_WOOD_TRAPDOOR_CLOSE));
    //public static final RegistrySupplier<Block> MINI_FRIDGE = registerWithItem("mini_fridge", () -> new MiniFridgeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.COPPER)));
    public static final RegistrySupplier<Block> RADIO = registerWithItem("radio", () -> new RadioBlock(BlockBehaviour.Properties.copy(Blocks.BAMBOO_PLANKS)));
    public static final RegistrySupplier<Item> OVERGROWN_DISC = registerItem("overgrown_disc", () -> new RecordItem(1, SoundEventRegistry.RADIO_BEACHPARTY.get(), getSettings().stacksTo(1), 196));
    public static final RegistrySupplier<Block> MESSAGE_IN_A_BOTTLE = registerWithoutItem("message_in_a_bottle", () -> new MessageInABottleBlock(BlockBehaviour.Properties.copy(Blocks.GLASS), Block.box(4.0f, 0.0f, 4.0f, 12.0f, 6.0f, 12.0f)));
    public static final RegistrySupplier<Item> MESSAGE_IN_A_BOTTLE_ITEM = registerItem("message_in_a_bottle", () -> new MessageInABottleItem(ObjectRegistry.MESSAGE_IN_A_BOTTLE.get(), getSettings()));
    public static final RegistrySupplier<Block> SEASHELL_BLOCK = registerWithoutItem("seashell_block", () -> new SeashellBlock(BlockBehaviour.Properties.copy(Blocks.DECORATED_POT).instabreak().noParticlesOnBreak()));
    public static final RegistrySupplier<Item> SEASHELL = registerItem("seashell", () -> new SeashellItem(SEASHELL_BLOCK.get(), getSettings()));
    public static final RegistrySupplier<Block> SAND_BUCKET_BLOCK = registerWithoutItem("sand_bucket_block", () -> new SandBucketBlock(BlockBehaviour.Properties.copy(Blocks.DECORATED_POT)));
    public static final RegistrySupplier<Block> EMPTY_SAND_BUCKET_BLOCK = registerWithoutItem("empty_sand_bucket_block", () -> new SandBucketBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_WART)));
    public static final RegistrySupplier<Item> SAND_BUCKET = registerItem("sand_bucket", () -> new SandBucketItem(SAND_BUCKET_BLOCK.get(), getSettings().stacksTo(1)));
    public static final RegistrySupplier<Item> EMPTY_SAND_BUCKET = registerItem("empty_sand_bucket", () -> new SandBucketItem(EMPTY_SAND_BUCKET_BLOCK.get(), getSettings()));
    public static final RegistrySupplier<Block> COCONUT_BLOCK = registerWithoutItem("coconut_block", () -> new CoconutBlock(BlockBehaviour.Properties.copy(Blocks.BAMBOO)));
    public static final RegistrySupplier<Item> COCONUT = registerItem("coconut", () -> new CoconutItem(COCONUT_BLOCK.get(), getSettings()));
    public static final RegistrySupplier<Item> COCONUT_OPEN = registerItem("coconut_open", () -> new Item(getSettings().food(Foods.CARROT)));
    public static final RegistrySupplier<Block> COCONUT_COCKTAIL = registerCocktail("coconut_cocktail", () -> new CocktailBlock(getCocktailSettings()), MobEffects.DAMAGE_BOOST);
    public static final RegistrySupplier<Block> SWEETBERRIES_COCKTAIL = registerCocktail("sweetberries_cocktail", () -> new CocktailBlock(getCocktailSettings()), MobEffects.ABSORPTION);
    public static final RegistrySupplier<Block> COCOA_COCKTAIL = registerCocktail("cocoa_cocktail", () -> new CocktailBlock(getCocktailSettings()), MobEffects.REGENERATION);
    public static final RegistrySupplier<Block> PUMPKIN_COCKTAIL = registerCocktail("pumpkin_cocktail", () -> new CocktailBlock(getCocktailSettings()), MobEffects.FIRE_RESISTANCE);
    public static final RegistrySupplier<Block> HONEY_COCKTAIL = registerCocktail("honey_cocktail", () -> new CocktailBlock(getCocktailSettings()), MobEffects.DIG_SPEED);
    public static final RegistrySupplier<Block> MELON_COCKTAIL = registerCocktail("melon_cocktail", () -> new CocktailBlock(getCocktailSettings()), MobEffects.LUCK);
    //TODO: Add a 3D Model, then register as Cocktail & Cocktailblock
    //public static final RegistrySupplier<Item> SWEETBERRY_MILKSHAKE = registerItem("sweetberry_milkshake", () -> new DrinkItem(getSettings().food(Foods.CARROT), 32));
    //public static final RegistrySupplier<Item> COCONUT_MILKSHAKE = registerItem("coconut_milkshake", () -> new DrinkItem(getSettings().food(Foods.CARROT), 32));
    //public static final RegistrySupplier<Item> CHOCOLATE_MILKSHAKE = registerItem("chocolate_milkshake", () -> new DrinkItem(getSettings().food(Foods.CARROT), 32));
    //public static final RegistrySupplier<Item> REFRESHING_DRINK = registerItem("refreshing_drink", () -> new DrinkItem(getSettings().food(Foods.CARROT), 32));
    //TODO: Idea for making it useful? Actually theres no real reason why to craft & eat Icedream
    public static final RegistrySupplier<Item> SWEETBERRY_ICECREAM = registerItem("sweetberry_icecream", () -> new Item(getSettings().food(Foods.SWEET_BERRIES)));
    public static final RegistrySupplier<Item> COCONUT_ICECREAM = registerItem("coconut_icecream", () -> new Item(getSettings().food(Foods.SWEET_BERRIES)));
    public static final RegistrySupplier<Item> CHOCOLATE_ICECREAM = registerItem("chocolate_icecream", () -> new Item(getSettings().food(Foods.SWEET_BERRIES)));

    public static final RegistrySupplier<Item> ICECREAM_COCONUT = registerItem("icecream_coconut", () -> new Item(getSettings().food(Foods.CARROT)));
    public static final RegistrySupplier<Item> ICECREAM_MELON = registerItem("icecream_melon", () -> new Item(getSettings().food(Foods.CARROT)));
    public static final RegistrySupplier<Item> ICECREAM_CACTUS = registerItem("icecream_cactus", () -> new Item(getSettings().food(Foods.CARROT)));
    public static final RegistrySupplier<Item> ICECREAM_SWEETBERRIES = registerItem("icecream_sweetberries", () -> new Item(getSettings().food(Foods.CARROT)));
    public static final RegistrySupplier<Item> ICECREAM_CHOCOLATE = registerItem("icecream_chocolate", () -> new Item(getSettings().food(Foods.CARROT)));
    public static final RegistrySupplier<Item> RAW_MUSSEL_MEAT = registerItem("raw_mussel_meat", () -> new Item(getSettings().food(Foods.BEEF)));
    public static final RegistrySupplier<Item> COOKED_MUSSEL_MEAT = registerItem("cooked_mussel_meat", () -> new Item(getSettings().food(Foods.COOKED_BEEF)));
    public static final RegistrySupplier<Block> BEACH_TOWEL = registerWithItem("beach_towel", () -> new BeachTowelBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).sound(SoundType.WOOL)));
    public static final RegistrySupplier<Item> BEACH_HAT = registerItem("beach_hat", () -> new BeachHatItem(ArmorMaterialRegistry.BEACH_HAT, ArmorItem.Type.HELMET, getSettings().rarity(Rarity.EPIC), new BeachpartyIdentifier("textures/models/armor/beach_hat.png")));
    public static final RegistrySupplier<Item> SUNGLASSES = registerItem("sunglasses", () -> new ArmorItem(ArmorMaterialRegistry.SUNGLASSES, ArmorItem.Type.HELMET, getSettings()));
    public static final RegistrySupplier<Item> TRUNKS = registerItem("trunks", () -> new DyeableBeachpartyArmorItem(ArmorMaterialRegistry.TRUNKS, ArmorItem.Type.LEGGINGS, 16715535, getSettings().rarity(Rarity.COMMON)));
    public static final RegistrySupplier<Item> BIKINI = registerItem("bikini", () -> new DyeableBeachpartyArmorItem(ArmorMaterialRegistry.BIKINI, ArmorItem.Type.LEGGINGS, 987135, getSettings().rarity(Rarity.COMMON)));
    public static final RegistrySupplier<Item> CROCS = registerItem("crocs", () -> new DyeableBeachpartyArmorItem(ArmorMaterialRegistry.CROCS, ArmorItem.Type.BOOTS, 1048335, getSettings().rarity(Rarity.UNCOMMON)));
    public static final RegistrySupplier<Item> SWIM_WINGS = registerItem("swim_wings", () -> new ArmorItem(ArmorMaterialRegistry.SWIM_WINGS, ArmorItem.Type.CHESTPLATE, getSettings()));
    public static final RegistrySupplier<Item> RUBBER_RING_BLUE = registerItem("rubber_ring_blue", () -> new BeachpartyArmorItem(ArmorMaterialRegistry.RING, ArmorItem.Type.CHESTPLATE, getSettings().rarity(Rarity.COMMON), new BeachpartyIdentifier("textures/entity/rubber_ring_blue.png")));
    public static final RegistrySupplier<Item> RUBBER_RING_PINK = registerItem("rubber_ring_pink", () -> new BeachpartyArmorItem(ArmorMaterialRegistry.RING, ArmorItem.Type.CHESTPLATE, getSettings().rarity(Rarity.COMMON), new BeachpartyIdentifier("textures/entity/rubber_ring_pink.png")));
    public static final RegistrySupplier<Item> RUBBER_RING_STRIPPED = registerItem("rubber_ring_stripped", () -> new BeachpartyArmorItem(ArmorMaterialRegistry.RING, ArmorItem.Type.CHESTPLATE, getSettings().rarity(Rarity.COMMON), new BeachpartyIdentifier("textures/entity/rubber_ring_stripped.png")));
    public static final RegistrySupplier<Item> RUBBER_RING_PELICAN = registerItem("rubber_ring_pelican", () -> new BeachpartyArmorItem(ArmorMaterialRegistry.RING, ArmorItem.Type.CHESTPLATE, getSettings().rarity(Rarity.RARE), new BeachpartyIdentifier("textures/entity/rubber_ring_pelican.png")));
    public static final RegistrySupplier<Item> RUBBER_RING_AXOLOTL = registerItem("rubber_ring_axolotl", () -> new BeachpartyArmorItem(ArmorMaterialRegistry.RING, ArmorItem.Type.CHESTPLATE, getSettings().rarity(Rarity.RARE), new BeachpartyIdentifier("textures/entity/rubber_ring_axolotl.png")));
    public static final RegistrySupplier<Item> POOL_NOODLE = registerItem("pool_noodle", () -> new PoolNoodleItem(Tiers.WOOD, 1, -1.4F, getSettings()));
    public static final RegistrySupplier<Block> BEACH_PARASOL = registerWithItem("beach_parasol", () -> new ParasolBlock(BlockBehaviour.Properties.copy(Blocks.BAMBOO_PLANKS)));
    public static final RegistrySupplier<Block> PALM_TORCH = registerWithoutItem("palm_torch", () -> new TorchBlock(BlockBehaviour.Properties.copy(Blocks.TORCH).noCollission().instabreak().lightLevel((state) -> 14).sound(SoundType.WOOD), ParticleTypes.FLAME));
    public static final RegistrySupplier<Block> PALM_WALL_TORCH = registerWithoutItem("palm_wall_torch", () -> new WallTorchBlock(BlockBehaviour.Properties.copy(Blocks.TORCH).noCollission().instabreak().lightLevel((state) -> 14).sound(SoundType.WOOD).dropsLike(PALM_TORCH.get()), ParticleTypes.FLAME));
    public static final RegistrySupplier<Item> PALM_TORCH_ITEM = registerItem("palm_torch_item", () -> new StandingAndWallBlockItem(ObjectRegistry.PALM_TORCH.get(), ObjectRegistry.PALM_WALL_TORCH.get(), getSettings(), Direction.DOWN));
    public static final RegistrySupplier<Block> PALM_TALL_TORCH = registerWithItem("palm_tall_torch", () -> new TallTorchBlock(BlockBehaviour.Properties.copy(Blocks.TORCH).noCollission().instabreak().lightLevel((state) -> 14).sound(SoundType.WOOD), ParticleTypes.FLAME));
    public static final RegistrySupplier<Block> SANDCASTLE = registerWithoutItem("sandcastle", () -> new SandCastleBlock(BlockBehaviour.Properties.copy(Blocks.SAND)));
    public static final RegistrySupplier<Block> COCONUT_HANGING = registerWithoutItem("coconut_hanging", () -> new HangingCoconutBlock(BlockBehaviour.Properties.copy(Blocks.BAMBOO)));
    public static final RegistrySupplier<Block> SANDWAVES = registerWithItem("sandwaves", () -> new SandBlock(14406560, BlockBehaviour.Properties.copy(Blocks.SAND).mapColor(MapColor.SAND).strength(0.5F).sound(SoundType.SAND)));
    public static final RegistrySupplier<Block> SAND_SEASTARS = registerWithoutItem("sand_seastars", () -> new SandBlock(14406560, BlockBehaviour.Properties.copy(Blocks.SAND).mapColor(MapColor.SAND).strength(0.5F).sound(SoundType.SAND)));
    public static final Supplier<Item> PALM_BOAT = registerItem("palm_boat", () -> new BeachpartyBoatItem(false, BeachpartyBoat.Type.PALM, getSettings().stacksTo(1)));
    public static final Supplier<Item> PALM_CHEST_BOAT = registerItem("palm_chest_boat", () -> new BeachpartyBoatItem(true, BeachpartyBoat.Type.PALM, getSettings().stacksTo(1)));
    public static final Supplier<Item> FLOATY_BOAT = registerItem("floaty_boat", () -> new BeachpartyBoatItem(false, BeachpartyBoat.Type.FLOATY, getSettings().stacksTo(1)));

    private static RegistrySupplier<Block> registerLog(String path) {
        return registerWithItem(path, () -> new RotatedPillarBlock(getLogBlockSettings()));
    }

    private static BlockBehaviour.Properties getLogBlockSettings() {
        return BlockBehaviour.Properties.copy(Blocks.BAMBOO_PLANKS).strength(2.0F).sound(SoundType.WOOD);
    }

    private static BlockBehaviour.Properties getSlabSettings() {
        return getLogBlockSettings().explosionResistance(3.0F);
    }


    private static Item.Properties getSettings(Consumer<Item.Properties> consumer) {
        Item.Properties settings = new Item.Properties();
        consumer.accept(settings);
        return settings;
    }


    private static FoodProperties cocktailFoodComponent(MobEffect effect) {
        FoodProperties.Builder component = new FoodProperties.Builder().nutrition(2).saturationMod(1);
        if (effect != null) component.effect(new MobEffectInstance(effect, 45 * 20), 1.0f);
        return component.build();
    }

    private static BlockBehaviour.Properties getCocktailSettings() {
        return BlockBehaviour.Properties.copy(Blocks.GLASS).noOcclusion().instabreak();
    }


    static Item.Properties getSettings() {
        return getSettings(settings -> {
        });
    }


    public static void init() {
        ITEMS.register();
        BLOCKS.register();
    }

    public static void commonInit() {
        FuelRegistry.register(300, PALM_FENCE.get(), PALM_FENCE_GATE.get(), PALM_PLANKS.get(), PALM_LOG.get(), PALM_WOOD.get(), STRIPPED_PALM_LOG.get(), STRIPPED_PALM_WOOD.get());

    }

    private static ButtonBlock woodenButton(FeatureFlag... featureFlags) {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.of().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY);
        if (featureFlags.length > 0) {
            properties = properties.requiredFeatures(featureFlags);
        }

        return new ButtonBlock(properties, BlockSetType.BAMBOO, 30, true);
    }

    private static <T extends Block> RegistrySupplier<T> registerCocktail(String name, Supplier<T> block, MobEffect effect) {
        RegistrySupplier<T> toReturn = registerWithoutItem(name, block);
        registerItem(name, () -> new DrinkBlockItem(toReturn.get(), getSettings(settings -> settings.food(cocktailFoodComponent(effect)))));
        return toReturn;
    }


    public static <T extends Block> RegistrySupplier<T> registerWithItem(String name, Supplier<T> block) {
        return BeachpartyUtil.registerWithItem(BLOCKS, BLOCK_REGISTRAR, ITEMS, ITEM_REGISTRAR, new BeachpartyIdentifier(name), block);
    }

    public static <T extends Block> RegistrySupplier<T> registerWithoutItem(String path, Supplier<T> block) {
        return BeachpartyUtil.registerWithoutItem(BLOCKS, BLOCK_REGISTRAR, new BeachpartyIdentifier(path), block);
    }

    public static <T extends Item> RegistrySupplier<T> registerItem(String path, Supplier<T> itemSupplier) {
        return BeachpartyUtil.registerItem(ITEMS, ITEM_REGISTRAR, new BeachpartyIdentifier(path), itemSupplier);
    }
}

