package me.lofro.jesusScreamer.client.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import me.lofro.jesusScreamer.global.utils.ModConstants;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class JesusScreen extends Screen {

    public final Identifier jesusImageLocation = new Identifier(ModConstants.MOD_ID, "images/jesus");

    protected JesusScreen() {
        super(Text.of("Jesus frame screen"));
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);

        super.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    public void renderBackground(MatrixStack matrices) {
        this.renderBackground(matrices, 0);
    }

    @Override
    public void renderBackground(MatrixStack matrices, int vOffset) {
        this.renderBackgroundTexture(vOffset);
    }

    @Override
    public void renderBackgroundTexture(int vOffset) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.setShaderTexture(0, jesusImageLocation);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
        bufferBuilder.vertex(0.0, this.height, 0.0).texture(0.0F, (float)this.height / 32.0F + (float)vOffset).color(64, 64, 64, 255).next();
        bufferBuilder.vertex(this.width, this.height, 0.0).texture((float)this.width / 32.0F, (float)this.height / 32.0F + (float)vOffset).color(64, 64, 64, 255).next();
        bufferBuilder.vertex(this.width, 0.0, 0.0).texture((float)this.width / 32.0F, (float)vOffset).color(64, 64, 64, 255).next();
        bufferBuilder.vertex(0.0, 0.0, 0.0).texture(0.0F, (float)vOffset).color(64, 64, 64, 255).next();
        tessellator.draw();
    }

}
