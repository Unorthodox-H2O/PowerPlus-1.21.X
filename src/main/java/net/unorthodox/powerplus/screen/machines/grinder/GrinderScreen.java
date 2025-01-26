package net.unorthodox.powerplus.screen.machines.grinder;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.unorthodox.powerplus.PowerPlus;

public class GrinderScreen extends AbstractContainerScreen<GrinderMenu> {
    public static final ResourceLocation GUI_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(PowerPlus.MOD_ID, "textures/gui/grinder/grinder_gui.png");
    private static final ResourceLocation GEAR_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(PowerPlus.MOD_ID, "textures/gui/grinder/gear_progress.png");

    public GrinderScreen(GrinderMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void init() {
        super.init();

        this.inventoryLabelY = 73;
        this.titleLabelY = 4;
        this.inventoryLabelX = 114;
        this.titleLabelX = 8;

    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(GUI_TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        renderProgressGear(guiGraphics, x, y);
    }

    private void renderProgressGear (GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()) {
            guiGraphics.blit(GEAR_TEXTURE, x + 71, y + 35, 0, 0, menu.getScaledGearProgress(),16,16,16);
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics, mouseX, mouseY, delta);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
