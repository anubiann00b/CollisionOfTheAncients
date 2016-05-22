package me.shreyasr.cota.util;


public class Rectangle {
    public Vec2 pos;
    public Vec2 size;
    public Rectangle(float x, float y, float width, float height){
        pos = new Vec2(x,y);
        size = new Vec2(width,height);
    }
    public float left(){
        return pos.x-size.x;
    }
    public float right(){
        return pos.x+size.x;
    }
    public float up(){
        return pos.y+size.y;
    }
    public float down(){
        return pos.y-size.y;
    }
    public boolean isIn(Rectangle rect){
        return (
                (this.left() < rect.left() && rect.left() < this.right())
                || (this.left() < rect.right() && rect.right() < this.right()))
                &&((this.down() < rect.down() && rect.down() < this.up())
                || (this.down() < rect.up() && rect.up() < this.up())
        );
    }
    public boolean isIn(Vec2 point){
        return point.x > pos.x - size.x&& point.x< pos.x + size.x && point.y > pos.y - size.y && point.y < pos.y + size.y;
    }
}
