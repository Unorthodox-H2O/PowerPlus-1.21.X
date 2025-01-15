package net.unorthodox.powerplus.util;

import net.minecraft.core.Direction;
import net.minecraft.world.phys.AABB;

import java.util.Objects;

public class AreaAffectingData {
    public double xRadius = 0, yRadius = 0, zRadius = 0;
    public int xOffset = 0, yOffset = 1, zOffset = 0;
    public boolean renderArea = false;
    public AABB area;

    public AreaAffectingData() {

    }

    public AreaAffectingData(Direction facing) {
        xOffset = facing.getNormal().getX();
        yOffset = facing.getNormal().getY();
        zOffset = facing.getNormal().getZ();
    }

    public AreaAffectingData(double xRadius, double yRadius, double zRadius, int xOffset, int yOffset, int zOffset) {
        this.xRadius = xRadius;
        this.yRadius = yRadius;
        this.zRadius = zRadius;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.zOffset = zOffset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AreaAffectingData that = (AreaAffectingData) o;
        return xRadius == that.xRadius &&
                yRadius == that.yRadius &&
                zRadius == that.zRadius &&
                xOffset == that.xOffset &&
                yOffset == that.yOffset &&
                zOffset == that.zOffset &&
                renderArea == that.renderArea;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xRadius, yRadius, zRadius, xOffset, yOffset, zOffset, renderArea);
    }
}