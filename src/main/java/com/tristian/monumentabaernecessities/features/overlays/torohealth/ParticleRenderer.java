package com.tristian.monumentabaernecessities.features.overlays.torohealth;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class ParticleRenderer {

    public static class HealthBarRenderer {

        private static final int DARK_GRAY = 0x808080;
        private static final float FULL_SIZE = 40;


        private static final List<LivingEntity> renderedEntities = new ArrayList<>();

        public static void prepareRenderInWorld(LivingEntity entity) {
            MinecraftClient client = MinecraftClient.getInstance();


            if (entity.distanceTo(client.getCameraEntity()) > 256)
                return;
            renderedEntities.add(entity);
        }

        public static void renderInWorld(MatrixStack matrix,
                                         VertexConsumerProvider vertexConsumerProvider, Camera camera) {

            MinecraftClient client = MinecraftClient.getInstance();

            if (camera == null) {
                camera = client.getEntityRenderDispatcher().camera;
            }

            if (camera == null) {
                renderedEntities.clear();
                return;
            }

            if (renderedEntities.isEmpty()) {
                return;
            }

            RenderSystem.setShader(GameRenderer::getPositionColorProgram);
            RenderSystem.enableDepthTest();
            RenderSystem.enableBlend();
            RenderSystem.blendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE,
                    GL11.GL_ZERO);

            for (LivingEntity entity : renderedEntities) {
                float scaleToGui = 0.025f;
                boolean sneaking = entity.isInSneakingPose();
                float height = entity.getHeight() + 0.6F - (sneaking ? 0.25F : 0.0F);

                float tickDelta = client.getTickDelta();
                double x = MathHelper.lerp(tickDelta, entity.prevX, entity.getX());
                double y = MathHelper.lerp(tickDelta, entity.prevY, entity.getY());
                double z = MathHelper.lerp(tickDelta, entity.prevZ, entity.getZ());

                Vec3d camPos = camera.getPos();
                double camX = camPos.x;
                double camY = camPos.y;
                double camZ = camPos.z;

                matrix.push();
                matrix.translate(x - camX, (y + height) - camY, z - camZ);
                matrix.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-camera.getYaw()));
                matrix.multiply(RotationAxis.POSITIVE_X.rotationDegrees(camera.getPitch()));
                matrix.scale(-scaleToGui, -scaleToGui, scaleToGui);

                render(matrix, vertexConsumerProvider, entity, 0, 0, FULL_SIZE, true);

                matrix.pop();
            }

            RenderSystem.disableBlend();

            renderedEntities.clear();
        }

        public static void render(MatrixStack matrix, VertexConsumerProvider vertexConsumerProvider,
                                  LivingEntity entity, double x, double y,
                                  float width, boolean inWorld) {

            BarState state = BarStates.getState(entity);

            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

            if (!inWorld) {
                drawDamageNumber(matrix, vertexConsumerProvider, state.lastDmg, x, y, width);
            }
        }

        public static void drawDamageNumber(MatrixStack matrix,
                                            VertexConsumerProvider vertexConsumerProvider,
                                            int dmg, double x, double y, float width) {
            int i = Math.abs(dmg);
            if (i == 0) {
                return;
            }

            int color = dmg < 0 ? 5635925 : 16733525;
            String s = dmg < 0 ? "+" : "-" + i + " HP";
            MinecraftClient minecraft = MinecraftClient.getInstance();
            int sw = minecraft.textRenderer.getWidth(s);
            minecraft.textRenderer.draw(
                    Text.of( Formatting.BOLD + s), (float) (x + (width / 2) - sw), (float) y + 5, color, false,
                    matrix.peek().getPositionMatrix(), vertexConsumerProvider,
                    TextRenderer.TextLayerType.NORMAL, 0, 255);
        }

    }

    public static void renderParticles(MatrixStack matrix, VertexConsumerProvider vertexConsumerProvider, Camera camera) {
        for (BarParticle p : BarStates.PARTICLES) {
            renderParticle(matrix, vertexConsumerProvider, p, camera);
        }
    }

    private static void renderParticle(MatrixStack matrix, VertexConsumerProvider vertexConsumerProvider, BarParticle particle, Camera camera) {
        double distanceSquared = camera.getPos().squaredDistanceTo(particle.x, particle.y, particle.z);
        if (distanceSquared > 256) {
            return;
        }

        float scaleToGui = 0.025f;

        MinecraftClient client = MinecraftClient.getInstance();
        float tickDelta = client.getTickDelta();

        double x = MathHelper.lerp(tickDelta, particle.xPrev, particle.x);
        double y = MathHelper.lerp(tickDelta, particle.yPrev, particle.y);
        double z = MathHelper.lerp(tickDelta, particle.zPrev, particle.z);

        Vec3d camPos = camera.getPos();
        double camX = camPos.x;
        double camY = camPos.y;
        double camZ = camPos.z;

        matrix.push();
        matrix.translate(x - camX, y - camY, z - camZ);
        matrix.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-camera.getYaw()));
        matrix.multiply(RotationAxis.POSITIVE_X.rotationDegrees(camera.getPitch()));
        matrix.scale(-scaleToGui, -scaleToGui, scaleToGui);

        RenderSystem.setShader(GameRenderer::getPositionColorProgram);
        RenderSystem.enableDepthTest();
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE,
                GL11.GL_ZERO);

        HealthBarRenderer.drawDamageNumber(matrix, vertexConsumerProvider, particle.damage, 0, 0, 10);

        RenderSystem.disableBlend();
        matrix.pop();
    }
}
