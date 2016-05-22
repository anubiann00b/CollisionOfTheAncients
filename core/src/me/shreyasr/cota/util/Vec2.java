package me.shreyasr.cota.util;

import com.badlogic.gdx.math.Vector2;

import me.shreyasr.cota.component.InputDataComponent;

public class Vec2 {

    public static Vec2 create(InputDataComponent input) {
        return new Vec2(input.mouseX(), input.mouseY());
    }

    public static Vec2 fromDir(float dir) {
        return new Vec2((float)Math.cos(dir), (float)Math.sin(dir));
    }

    public float x;
    public float y;

    public Vec2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vec2(Vector2 vector) {
        this.x = vector.x;
        this.y = vector.y;
    }

    public boolean isZero() {
        return x == 0 && y == 0;
    }

    public double dir() {
        return Math.atan2(y, x);
    }

    public Vec2 copy() {
        return new Vec2(x, y);
    }

    public Vec2 norm() {
        float len = len();
        x /= len;
        y /= len;
        return this;
    }

    public float len() {
        return (float) Math.sqrt(x*x + y*y);
    }

    public Vec2 scale(float amount) {
        x *= amount;
        y *= amount;
        return this;
    }

    public Vec2 add(Vec2 that) {
        x += that.x;
        y += that.y;
        return this;
    }

    public Vec2 set(Vec2 that) {
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