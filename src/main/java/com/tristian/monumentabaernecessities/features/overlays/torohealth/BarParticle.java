package com.tristian.monumentabaernecessities.features.overlays.torohealth;


import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

import java.util.Random;

// CREDIT: torohealth
public class BarParticle {
    public int damage;

    public double x;
    public double y;
    public double z;
    public double xPrev;
    public double yPrev;
    public double zPrev;

    public int age;

    public double ax = 0.00;
    public double ay = -0.01;
    public double az = 0.00;

    public double vx;
    public double vy;
    public double vz;

    private static final Random RAND = new Random();

    public BarParticle(Entity entity, int damage) {
        MinecraftClient client = MinecraftClient.getInstance();
        Vec3d entityLocation = entity.getPos().add(0, entity.getHeight() / 2, 0);
        Vec3d cameraLocation = client.gameRenderer.getCamera().getPos();
        double offsetBy = entity.getWidth() * (RAND.nextGaussian() * 0.5);
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