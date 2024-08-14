package net.satisfy.beachparty.client;

import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.satisfy.beachparty.client.model.BeachHatModel;
import net.satisfy.beachparty.networking.BeachpartyMessages;
import net.satisfy.beachparty.registry.ArmorRegistry;
import net.satisfy.beachparty.registry.EntityTypeRegistry;
import net.satisfy.beachparty.registry.ObjectRegistry;
import net.satisfy.beachparty.util.BeachpartyUtil;

@Environment(EnvType.CLIENT)
public class BeachPartyClient {


    public static void initClient() {
        RenderTypeRegistry.register(RenderType.cutout(), ObjectRegistry.TABLE.get(), ObjectRegistry.CHAIR.get(),
                ObjectRegistry.TIKI_CHAIR.get(), ObjectRegistry.PALM_TRAPDOOR.get(), ObjectRegistry.PALM_DOOR.get(),
                ObjectRegistry.PALM_TORCH.get(), ObjectRegistry.PALM_WALL_TORCH.get(), ObjectRegistry.PALM_TALL_TORCH.get(),
                ObjectRegistry.MELON_COCKTAIL.get(), ObjectRegistry.COCONUT_COCKTAIL.get(), ObjectRegistry.HONEY_COCKTAIL.get(),
                ObjectRegistry.SWEETBERRIES_COCKTAIL.get(), ObjectRegistry.PUMPKIN_COCKTAIL.get(), ObjectRegistry.COCOA_COCKTAIL.get(),
                ObjectRegistry.SANDCASTLE.get(), ObjectRegistry.MESSAGE_IN_A_BOTTLE.get(), ObjectRegistry.BEACH_TOWEL.get(),
                ObjectRegistry.DECK_CHAIR.get(), ObjectRegistry.PALM_SAPLING.get(), ObjectRegistry.SEASHELL_BLOCK.get()
        );

        //MenuRegistry.registerScreenFactory(ScreenHandlerTypesRegistry.TIKI_BAR_GUI_HANDLER.get(), TikiBarGui::new);
        //MenuRegistry.registerScreenFactory(ScreenHandlerTypesRegistry.MINI_FRIDGE_GUI_HANDLER.get(), MiniFridgeGui::new);


        BeachpartyMessages.registerS2CPackets();

        initColorItems();
    }

    public static void preInitClient() {
        registerEntityEntityRenderers();
        registerEntityModelLayers();
    }

    private static void initColorItems() {
        BeachpartyUtil.registerColorArmor(ObjectRegistry.TRUNKS.get(), 16715535);
        BeachpartyUtil.registerColorArmor(ObjectRegistry.BIKINI.get(), 987135);
        BeachpartyUtil.registerColorArmor(ObjectRegistry.CROCS.get(), 1048335);
        BeachpartyUtil.registerColorArmor(ObjectRegistry.POOL_NOODLE.get(), 1017855);
    }

    public static void registerEntityEntityRenderers() {
        EntityRendererRegistry.register(EntityTypeRegistry.COCONUT, ThrownItemRenderer::new);
    }

    public static void registerEntityModelLayers() {
        EntityModelLayerRegistry.register(BeachHatModel.LAYER_LOCATION, BeachHatModel::createBodyLayer);
        ArmorRegistry.registerCustomArmorLayers();
    }
}