package com.rinko1231.homingxporb.Mixin;

import com.rinko1231.homingxporb.Config.HomingXpOrbConfig;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ExperienceOrb.class)
public class XpOrbMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        Entity entity = (Entity) (Object) this;
        Level level = entity.level();

        double maxDistance1 = HomingXpOrbConfig.maxHomingDistance.get();

        if (!level.isClientSide) {  // 确保在服务端运行
            Player nearestPlayer = level.getNearestPlayer(entity, maxDistance1);  // 查找范围内最近的玩家
            if (nearestPlayer != null && !nearestPlayer.isSpectator()) {
                entity.setNoGravity(true);
                double distance = entity.distanceToSqr(nearestPlayer);
                double maxDistance = maxDistance1 * maxDistance1; // 最大距离的平方

                if (distance <= maxDistance) {
                    if(HomingXpOrbConfig.accelerationMode.get()) {
                        Vec3 vec3 = new Vec3(nearestPlayer.position().x - entity.getX(), nearestPlayer.position().y - entity.getY(), nearestPlayer.position().z - entity.getZ());
                        double d0 = vec3.lengthSqr();
                        double d1 = 1.0D - Math.sqrt(d0) / maxDistance1;
                        entity.setDeltaMovement(entity.getDeltaMovement().add(vec3.normalize().scale(d1 * d1 * 0.1D * HomingXpOrbConfig.accelerationMultiplier.get())));
                        } else {
                            Vec3 direction = nearestPlayer.position().subtract(entity.position()); // 计算从实体指向玩家的向量
                            Vec3 normalizedDirection = direction.normalize();
                            entity.setDeltaMovement(normalizedDirection.scale(HomingXpOrbConfig.constantSpeed.get()));
                        }
                }
            } else entity.setNoGravity(false);
        }
    }
}