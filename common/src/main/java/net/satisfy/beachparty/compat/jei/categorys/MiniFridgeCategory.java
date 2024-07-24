package net.satisfy.beachparty.compat.jei.categorys;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.crafting.Ingredient;
import net.satisfy.beachparty.Beachparty;
import net.satisfy.beachparty.client.gui.MiniFridgeGui;
import net.satisfy.beachparty.compat.jei.BeachpartyJEIPlugin;
import net.satisfy.beachparty.registry.ObjectRegistry;
import net.satisfy.beachparty.recipe.MiniFridgeRecipe;

public class MiniFridgeCategory implements IRecipeCategory<MiniFridgeRecipe> {
    public static final RecipeType<MiniFridgeRecipe> MINI_FRIDGE = RecipeType.create(Beachparty.MOD_ID, "mini_fridge_mixing", MiniFridgeRecipe.class);
    public static final int WIDTH = 124;
    public static final int HEIGHT = 60;
    public static final int WIDTH_OF = 26;
    public static final int HEIGHT_OF = 13;
    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableAnimated arrow;
    private final Component localizedName;

    public MiniFridgeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(MiniFridgeGui.BG, WIDTH_OF, HEIGHT_OF, WIDTH, HEIGHT);
        this.arrow = helper.drawableBuilder(MiniFridgeGui.BG, 177, 26, 22, 10)
                .buildAnimated(50, IDrawableAnimated.StartDirection.LEFT, false);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, ObjectRegistry.MINI_FRIDGE.get().asItem().getDefaultInstance());
        this.localizedName = Component.translatable("rei.beachparty.mini_fridge_category");
    }


    @Override
    public void draw(MiniFridgeRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        arrow.draw(guiGraphics, MiniFridgeGui.ARROW_X - WIDTH_OF, MiniFridgeGui.ARROW_Y - HEIGHT_OF);
    }

    @Override
    public RecipeType<MiniFridgeRecipe> getRecipeType() {
        return MINI_FRIDGE;
    }

    @Override
    public Component getTitle() {
        return this.localizedName;
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, MiniFridgeRecipe recipe, IFocusGroup focuses) {
        NonNullList<Ingredient> ingredients = recipe.getIngredients();
        int s = ingredients.size();

        if(s > 0) BeachpartyJEIPlugin.addSlot(builder, 46 - WIDTH_OF, 27 - HEIGHT_OF, ingredients.get(0));
        if(s > 1) BeachpartyJEIPlugin.addSlot(builder, 59 - WIDTH_OF, 43 - HEIGHT_OF, ingredients.get(1));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 128 - WIDTH_OF,  42 - HEIGHT_OF).addItemStack(recipe.getResultItem(Minecraft.getInstance().level.registryAccess()));
    }
}
