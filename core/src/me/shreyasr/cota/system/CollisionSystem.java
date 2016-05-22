package me.shreyasr.cota.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import me.shreyasr.cota.component.StateDataComponent;
import me.shreyasr.cota.util.EntityIdComparator;
import me.shreyasr.cota.util.Rectangle;

public class CollisionSystem extends SortedIteratingSystem {

    public CollisionSystem(int priority) {
        super(Family.all(StateDataComponent.class).get(), new EntityIdComparator() ,priority);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        for(Entity e : getEntities()){
            if(entity == e) continue;
            Rectangle otherRect = e.getComponent(StateDataComponent.class).rect();
            Rectangle meRect = entity.getComponent(StateDataComponent.class).rect();
            if(otherRect.isIn(meRect)){
                boolean otherGreaterX = otherRect.pos.x > meRect.pos.x;
                boolean otherGreaterY = otherRect.pos.y > meRect.pos.y;
                float targetX = otherGreaterX ? (otherRect.left() + meRect.right())/2 : (meRect.left() + otherRect.right())/2;
                float targetY = otherGreaterY ? (otherRect.down() + meRect.up())/2 : (meRect.up() + otherRect.down())/2;
                otherRect.pos.x = otherGreaterX ? targetX + otherRect.size.x/2 : targetX - otherRect.size.x/2;
                meRect.pos.x = !otherGreaterX ? targetX + otherRect.size.x/2 : targetX - otherRect.size.x/2;
                otherRect.pos.y = otherGreaterY ? targetY + otherRect.size.y/2 : targetX - otherRect.size.y/2;
                meRect.pos.x = !otherGreaterY ? targetY + otherRect.size.y/2 : targetX - otherRect.size.y/2;
            }
        }

    }
}
