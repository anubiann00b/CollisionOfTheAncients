package me.shreyasr.cota.util;


public class Rectangle {

    public Vec2 offset;
    public Vec2 size;

    public Rectangle(float x, float y, float width, float height) {
        offset = new Vec2(x, y);
        size = new Vec2(width, height);
    }

    private float left(Vec2 pos) {
        return offset.x + pos.x;
    }

    private float right(Vec2 pos) {
        return offset.x + size.x + pos.x;
    }

    private float up(Vec2 pos) {
        return offset.y + size.y + pos.y;
    }

    private float down(Vec2 pos) {
        return offset.y + pos.y;
    }

    public boolean isIn(Rectangle rect, Vec2 m, Vec2 o) {
        return ((this.left(m) < rect.left(o) && rect.left(o) < this.right(m))
                    || (this.left(m) < rect.right(o) && rect.right(o) < this.right(m)))
                && ((this.down(m) < rect.down(o) && rect.down(o) < this.up(m))
                    || (this.down(m) < rect.up(o) && rect.up(o) < this.up(m)));
    }

    public boolean isIn(Vec2 pos, Vec2 point) {
        return point.x > left(pos)
                && point.x < right(pos)
                && point.y > down(pos)
                && point.y < up(pos);
    }
}
