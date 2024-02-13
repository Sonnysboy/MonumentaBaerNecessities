package com.tristian.monumentabaernecessities.features.overlays.torohealth;


import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

import java.util.Random;

// CREDIT: torohealth
public class BarParticle {
    public int damage;

    public double x = 0;
    public double y = 0;
    public double z = 0;
    public double xPrev = 0;
    public double yPrev = 0;
    public double zPrev = 0;

    public int age = 0;

    public double ax = 0.00;
    public double ay = -0.01;
    public double az = 0.00;

    public double vx = 0;
    public double vy = 0;
    public double vz = 0;

    private static Random RAND = new Random();

    public BarParticle(Entity entity, int damage) {
        MinecraftClient client = MinecraftClient.getInstance();
        Vec3d entityLocation = entity.getPos().add(0, entity.getHeight() / 2, 0);
        Vec3d cameraLocation = client.gameRenderer.getCamera().getPos();
        double offsetBy = entity.getWidth();
        Vec3d offset = cameraLocation.subtract(entityLocation).normalize().multiply(offsetBy);
        Vec3d pos = entityLocation.add(offset);

        age = 0;
        this.damage = damage;

        vx = RAND.nextGaussian() * 0.004;
        vy = 0.10 + (RAND.nextGaussian() * 0.005);
        vz = RAND.nextGaussian() * 0.004;

        x = pos.x;
        y = pos.y;
        z = pos.z;

        xPrev = x;
        yPrev = y;
        zPrev = z;
    }

    public void tick() {
        xPrev = x;
        yPrev = y;
        zPrev = z;
        age++;
        x += vx;
        y += vy;
        z += vz;
        vx += ax;
        vy += ay;
        vz += az;
    }

}