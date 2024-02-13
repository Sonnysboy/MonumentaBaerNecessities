package com.tristian.monumentabaernecessities.mixin;

import com.tristian.monumentabaernecessities.MonumentaBaerNecessities;
import com.tristian.monumentabaernecessities.features.overlays.torohealth.ParticleRenderer;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.MinecraftServer;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
	@Shadow @Final private BufferBuilderStorage bufferBuilders;

	@Inject(method = "renderEntity", at = @At(value = "RETURN"))
	private void renderEntity(Entity entity, double x, double y, double z, float g,
							  MatrixStack matrix, VertexConsumerProvider v, CallbackInfo info) {
		if (entity instanceof LivingEntity) {
			if (MonumentaBaerNecessities.options.cosmicStyleDamageIndicators) ParticleRenderer.HealthBarRenderer.prepareRenderInWorld((LivingEntity) entity);
		}
	}

	@Inject(method = "render", at = @At(value = "RETURN"))
	private void render(MatrixStack matrices, float tickDelta, long limitTime,
						boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer,
						LightmapTextureManager lightmapTextureManager, Matrix4f matrix, CallbackInfo info) {
		if (MonumentaBaerNecessities.options.cosmicStyleDamageIndicators) {
			ParticleRenderer.HealthBarRenderer.renderInWorld(matrices, this.bufferBuilders.getEntityVertexConsumers(), camera);
			ParticleRenderer.renderParticles(matrices, this.bufferBuilders.getEntityVertexConsumers(), camera);
		}
	}

}