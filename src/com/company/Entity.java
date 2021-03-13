package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Entity {

    protected Rectangle boundingbox;
    // Location

    String name;
    int x;
    int y;
    int height;
    int width;



    public List<Component> components;

    public Entity(String name) {
        this.name = name;
        this.components = new ArrayList<>();
    }


    public Entity(String name, int x, int y, int height, int width) {
        this.boundingbox = new Rectangle(x,y,height,width);
        this.name = name;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.components = new ArrayList<>();
    }


    public boolean collision(Entity other){
        boundingbox.setBounds((int) x, (int) y, (int) width, (int) height);
        return boundingbox.intersects((int) other.getX(),(int) other.getY(), other.getWidth(), other.getHeight() );
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
        this.components.add(c);
        c.entity = this;
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



    public Rectangle getBoundingbox() {
        return boundingbox;
    }

    public void setBoundingbox(Rectangle boundingbox) {
        this.boundingbox = boundingbox;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }


}
