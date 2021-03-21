package org.easy2dGameEngine;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Entity {


    // Location

    String name;

    public Transform transform;



    public List<Component> components;

    public Entity(String name) {
        this.name = name;
        this.components  = new ArrayList<>();
        this.transform = new Transform();
    }

public Entity(String name, Transform transform){
    this.name = name;
    this.components = new ArrayList<>();
    this.transform = transform;
}

    public <T extends Component> T GetComponent(Class<T> componentClass){
        for(Component c : components){
            if(componentClass.isAssignableFrom(c.getClass())){
                try {
                    return componentClass.cast(c);
                }catch (ClassCastException e){
                    e.printStackTrace();
                    assert false : "Error: Casting component";
                }
            }
        }
        return null;
    }

    public <T extends Component> void RemoveComponent(Class<T> componentClass){
        for(int i = 0; i < components.size(); i++){
            if(componentClass.isAssignableFrom(componentClass.getClass())){
                components.remove(i);
                return;
            }
        }
    }

    public void AddComponent(Component c){
        System.out.println("adding component");
        components.add(c);
        c.entity =this;
    }

    public void Update(float dt){
        for(int i = 0; i < components.size(); i++){
            components.get(i).Update(dt);
        }
    }

    public void Start(){
        for(int i = 0; i < components.size(); i++){
            components.get(i).Start();
        }
    }





}
