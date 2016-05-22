package me.shreyasr.cota.util;
public class Vec2{
    public float x;
    public float y;
    public Vec2(float x, float y){
        this.x = this.y;
    }
    public void add(Vec2 that){
        x += that.x;
        y += that.y;
    }

    public void set(Vec2 that){
        x = that.x;
        y = that.y;
    }
    public void sub(Vec2 that){
        x -= that.x;
        y -= that.y;
    }
}