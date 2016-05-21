package me.shreyasr.cota.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import me.shreyasr.cota.component.StateDataComponent;

public class CollisionSystem extends IteratingSystem{

    public CollisionSystem(int priority) {
        super(Family.all(StateDataComponent.class).get(), priority);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }
}
