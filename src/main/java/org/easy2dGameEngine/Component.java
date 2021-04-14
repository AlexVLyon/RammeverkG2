package org.easy2dGameEngine;

import org.easy2dGameEngine.Entity.Entity;

public abstract class Component {
    public Entity entity = null;

    public abstract void Start();

}
