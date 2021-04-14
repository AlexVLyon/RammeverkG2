package org.easy2dGameEngine.Entity;

import Components.SpriteRenderer;
import org.easy2dGameEngine.Component;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.stb.STBImage.stbi_set_flip_vertically_on_load;

public class Entity {
    // Location
    public List<Component> components;
    boolean InstaniateCpu = false;


    String TexturefilePath;
    Texture tex;
    public Transform Gettransform;

public Entity(String TexturefilePath, int x, int y){
    this.TexturefilePath = TexturefilePath;
    this.Gettransform = new Transform(x,y);
    this.components = new ArrayList<>();
    AddAComponent(new SpriteRenderer(tex = new Texture(TexturefilePath)));

    if(!InstaniateCpu)
    AddAComponent(new SpriteRenderer(tex = new Texture(TexturefilePath)));
    InstaniateCpu = true;
}


public Entity(){
    AddAComponent(new SpriteRenderer(tex = new Texture("")));
}

public void MoveEntityX(float x){
    this.Gettransform.position.x += x;

}

    public void MoveEntityY(float y){
        this.Gettransform.position.y += y;
    }

    public <T extends Component> T GetAComponent(Class<T> componentClass){
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

    public <T extends Component> void RemoveAComponent(Class<T> componentClass){
        for(int i = 0; i < components.size(); i++){
            Component c = components.get(i);
            if(componentClass.isAssignableFrom(c.getClass())){
                components.remove(i);
                return;
            }
        }
    }

    public void AddAComponent(Component c){
        components.add(c);
        c.entity = this;
    }

    public void Start(){
        for(int i = 0; i < components.size(); i++){
            components.get(i).Start();
        }
    }

public void flip(){
    stbi_set_flip_vertically_on_load(true);

}


    public boolean Collision(Entity entityCollision){
        if(entityCollision.Gettransform.position.x+20 >= this.Gettransform.position.x  && entityCollision.Gettransform.position.x <= this.Gettransform.position.x+ 20 &&
                entityCollision.Gettransform.position.y+20 >= this.Gettransform.position.y &&  entityCollision.Gettransform.position.y <= this.Gettransform.position.y+ 20){
            System.out.println("collision happening");
            return true;
        }
        return false;
    }


}
