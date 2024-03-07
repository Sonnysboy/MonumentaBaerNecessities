package com.tristian.monumentabaernecessities.api.situationals;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;

public class SituationalHelper {

    /** Repurposed from <a href="https://github.com/TeamMonumenta/monumenta-plugins-public/blob/master/plugins/paper/src/main/java/com/playmonumenta/plugins/utils/EntityUtils.java#L361">The monumenta plugin source code</a>
     *
     * @param entity The tntity
     * @return Whether or not the entity is considered "hostile"
     */
    public static boolean isHostileMob(Entity entity) {
        if (entity == null) {
            return false;
        }
        if (entity instanceof Monster || entity instanceof SlimeEntity  || entity instanceof GhastEntity
                || entity instanceof PolarBearEntity
                || entity instanceof PhantomEntity
                || entity instanceof ShulkerEntity
                || entity instanceof PufferfishEntity
                || entity instanceof SkeletonHorseEntity
                || entity instanceof ZombieHorseEntity
                || entity instanceof GiantEntity
                || entity instanceof HoglinEntity
                || entity instanceof PiglinEntity
                || entity instanceof BeeEntity) {
            return true;
        } else if (entity instanceof WolfEntity we) {
            return we.getAngryAt() != null; //|| we.getScoreboardTag(.contains("boss_targetplayer"); we dont have access, so we assume every angry wolf is aggressive
        } else if (entity instanceof RabbitEntity) {
            return ((RabbitEntity) entity).getVariant() == RabbitEntity.RabbitType.EVIL;
        } else if (entity instanceof MobEntity) {
            LivingEntity target = ((MobEntity) entity).getTarget();
            return (target instanceof PlayerEntity); //|| //entity.getScoreboardTags().contains("boss_targetplayer") || entity.getScoreboardTags().contains("boss_hostile") || entity.getScoreboardTags().contains("Hostile");
        }

        return false;
    }

    public static long getNearbyEnemeis(long radius) {
        return MinecraftClient.getInstance().player.getWorld().getEntitiesByClass(Entity.class,
                MinecraftClient.getInstance().player.getBoundingBox().expand(radius),
                SituationalHelper::isHostileMob).size();
    }
}
