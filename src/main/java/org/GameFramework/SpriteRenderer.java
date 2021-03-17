package org.GameFramework;

public class SpriteRenderer extends Components {
    private  boolean firsttime;
    @Override
    public void Start() {
        System.out.println("starting");
    }

    @Override
    public void Update(float dt) {
        if(!firsttime) {
            System.out.println("I am updating");
firsttime = true;
        }
    }
}
