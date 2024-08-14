package net.satisfy.beachparty.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.satisfy.beachparty.Beachparty;

@SuppressWarnings("unused")
public class TabRegistry {
    public static final DeferredRegister<CreativeModeTab> BEACHPARTY_TABS = DeferredRegister.create(Beachparty.MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static final RegistrySupplier<CreativeModeTab> BEACHPARTY_TAB = BEACHPARTY_TABS.register("beachparty", () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
            .icon(() -> new ItemStack(ObjectRegistry.COCONUT_COCKTAIL.get()))
            .title(Component.translatable("creativetab.beachparty.tab"))
            .displayItems((parameters, out) -> {
                out.accept(ObjectRegistry.PALM_LOG.get());
                out.accept(ObjectRegistry.PALM_WOOD.get());
                out.accept(ObjectRegistry.STRIPPED_PALM_LOG.get());
                out.accept(ObjectRegistry.STRIPPED_PALM_WOOD.get());
                out.accept(ObjectRegistry.PALM_BEAM.get());
                out.accept(ObjectRegistry.PALM_PLANKS.get());
                out.accept(ObjectRegistry.PALM_STAIRS.get());
                out.accept(ObjectRegistry.PALM_SLAB.get());
                out.accept(ObjectRegistry.PALM_PRESSURE_PLATE.get());
                out.accept(ObjectRegistry.PALM_DOOR.get());
                out.accept(ObjectRegistry.PALM_TRAPDOOR.get());
                out.accept(ObjectRegistry.PALM_FENCE.get());
                out.accept(ObjectRegistry.PALM_FENCE_GATE.get());
                out.accept(ObjectRegistry.PALM_BUTTON.get());
                out.accept(ObjectRegistry.CABINET.get());
                //out.accept(ObjectRegistry.TIKI_BAR.get());
                out.accept(ObjectRegistry.LOUNGE_CHAIR.get());
                out.accept(ObjectRegistry.CHAIR.get());
                out.accept(ObjectRegistry.TABLE.get());
                out.accept(ObjectRegistry.BEACH_CHAIR.get());
                out.accept(ObjectRegistry.DECK_CHAIR.get());
                out.accept(ObjectRegistry.TIKI_CHAIR.get());
                out.accept(ObjectRegistry.PALM_FLOORBOARD.get());
                out.accept(ObjectRegistry.DRIED_WHEAT_BLOCK.get());
                out.accept(ObjectRegistry.DRIED_WHEAT_STAIRS.get());
                out.accept(ObjectRegistry.DRIED_WHEAT_SLAB.get());
                out.accept(ObjectRegistry.SANDWAVES.get());
                out.accept(ObjectRegistry.PALM_LEAVES.get());
                out.accept(ObjectRegistry.PALM_SAPLING.get());
                out.accept(ObjectRegistry.PALM_TORCH.get());
                out.accept(ObjectRegistry.PALM_TALL_TORCH.get());
                //out.accept(ObjectRegistry.MINI_FRIDGE.get());
                out.accept(ObjectRegistry.RADIO.get());
                out.accept(ObjectRegistry.OVERGROWN_DISC.get());
                out.accept(ObjectRegistry.MESSAGE_IN_A_BOTTLE.get());
                out.accept(ObjectRegistry.SEASHELL.get());
                out.accept(ObjectRegistry.SAND_BUCKET.get());
                out.accept(ObjectRegistry.EMPTY_SAND_BUCKET.get());
                out.accept(ObjectRegistry.COCONUT.get());
                out.accept(ObjectRegistry.COCONUT_OPEN.get());
                out.accept(ObjectRegistry.COCONUT_COCKTAIL.get());
                out.accept(ObjectRegistry.SWEETBERRIES_COCKTAIL.get());
                out.accept(ObjectRegistry.COCOA_COCKTAIL.get());
                out.accept(ObjectRegistry.PUMPKIN_COCKTAIL.get());
                out.accept(ObjectRegistry.MELON_COCKTAIL.get());
                out.accept(ObjectRegistry.HONEY_COCKTAIL.get());
                out.accept(ObjectRegistry.SWEETBERRY_MILKSHAKE.get());
                out.accept(ObjectRegistry.COCONUT_MILKSHAKE.get());
                out.accept(ObjectRegistry.CHOCOLATE_MILKSHAKE.get());
                out.accept(ObjectRegistry.REFRESHING_DRINK.get());
                out.accept(ObjectRegistry.SWEETBERRY_ICECREAM.get());
                out.accept(ObjectRegistry.COCONUT_ICECREAM.get());
                out.accept(ObjectRegistry.CHOCOLATE_ICECREAM.get());
                out.accept(ObjectRegistry.ICECREAM_COCONUT.get());
                out.accept(ObjectRegistry.ICECREAM_CACTUS.get());
                out.accept(ObjectRegistry.ICECREAM_CHOCOLATE.get());
                out.accept(ObjectRegistry.ICECREAM_SWEETBERRIES.get());
                out.accept(ObjectRegistry.ICECREAM_MELON.get());
                out.accept(ObjectRegistry.RAW_MUSSEL_MEAT.get());
                out.accept(ObjectRegistry.COOKED_MUSSEL_MEAT.get());
                out.accept(ObjectRegistry.BEACH_TOWEL.get());
                out.accept(ObjectRegistry.BEACH_HAT.get());
                out.accept(ObjectRegistry.SUNGLASSES.get());
                out.accept(ObjectRegistry.TRUNKS.get());
                out.accept(ObjectRegistry.BIKINI.get());
                out.accept(ObjectRegistry.CROCS.get());
                out.accept(ObjectRegistry.SWIM_WINGS.get());
                //out.accept(ObjectRegistry.RUBBER_RING_BLUE.get());
                //out.accept(ObjectRegistry.RUBBER_RING_PINK.get());
                //out.accept(ObjectRegistry.RUBBER_RING_STRIPPED.get());
                //out.accept(ObjectRegistry.RUBBER_RING_AXOLOTL.get());
                //out.accept(ObjectRegistry.RUBBER_RING_PELICAN.get());
                out.accept(ObjectRegistry.POOL_NOODLE.get());
                out.accept(ObjectRegistry.BEACH_PARASOL.get());
                out.accept(ObjectRegistry.FLOATY_BOAT.get());
                out.accept(ObjectRegistry.PALM_BOAT.get());
                out.accept(ObjectRegistry.PALM_CHEST_BOAT.get());

            })
            .build());

    public static void init() {
        BEACHPARTY_TABS.register();
    }
}
