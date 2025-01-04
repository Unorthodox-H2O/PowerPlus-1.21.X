package net.unorthodox.powerplus.screen.machines.crystalinfuser;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.unorthodox.powerplus.PowerPlus;

public class CrystalInfuserScreen extends AbstractContainerScreen<CrystalInfuserMenu> {
    public static final ResourceLocation GUI_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(PowerPlus.MOD_ID, "textures/gui/crystalinfuser/crystalinfuser_gui.png");
    private static final ResourceLocation ARROW_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(PowerPlus.MOD_ID, "textures/gui/crystalinfuser/arrow_progress.png");
    private static final ResourceLocation CRYSTAL_TEXTURE =
            ResourceLocation.parse("textures/block/amethyst_cluster.png");

    public CrystalInfuserScreen(CrystalInfuserMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void init() {
        super.init();

        this.inventoryLabelY = 73;
        this.titleLabelY = 4;
        this.inventoryLabelX = 27;
        this.titleLabelX = 27;

    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(GUI_TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        renderProgressArrow(guiGraphics, x, y);
        renderProgressCrystal(guiGraphics, x, y);
    }

    private void renderProgressArrow (GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()) {
            guiGraphics.blit(ARROW_TEXTURE, x + 73, y + 35, 0, 0, menu.getScaledArrowProgress(),16,24,16);
        }
    }

    private void renderProgressCrystal (GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()){
            guiGraphics.blit(CRYSTAL_TEXTURE, x + 104, y + 13 + 16 - menu.getScaledCrystalProgress(), 0,
                    16 - menu.getScaledCrystalProgress(), 16, menu.getScaledCrystalProgress(), 16, 16);
        }

    }


    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics, mouseX, mouseY, delta);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
