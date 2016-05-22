package me.shreyasr.cota.util;

public class Vec2 {

    public float x;
    public float y;

    public Vec2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public boolean isZero() {
        return x == 0 && y == 0;
    }

    public Vec2 copy() {
        return new Vec2(x, y);
    }

    public Vec2 add(Vec2 that) {
        x += that.x;
        y += that.y;
        return this;
    }

    public Vec2 set(Vec2 that){
        x = that.x;
        y = that.y;
        return this;
    }

    public Vec2 sub(Vec2 that){
        x -= that.x;
        y -= that.y;
        return this;
    }
}