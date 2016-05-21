package me.shreyasr.cota.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import me.shreyasr.cota.component.StateDataComponent;
import me.shreyasr.cota.util.EntityIdComparator;

public class CollisionSystem extends SortedIteratingSystem {

    public CollisionSystem(int priority) {
        super(Family.all(StateDataComponent.class).get(), new EntityIdComparator() ,priority);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }
}
