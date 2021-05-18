package org.easy2dGameEngine.ScreenAndTextureUpload;


import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.stb.STBImage.stbi_set_flip_vertically_on_load;

public abstract class Entity {
    // Location
    protected List<Component> components;
    boolean InstaniateCpu = false;


    String TexturefilePath;
    Texture tex;
    protected Transform Gettransform;

    public Entity(String TexturefilePath, int x, int y) {

        this.TexturefilePath = TexturefilePath;
        this.Gettransform = new Transform(x, y);
        this.components = new ArrayList<>();
        AddAComponent(new SpriteRenderer(tex = new Texture(TexturefilePath)));

        if (!InstaniateCpu) {
            AddAComponent(new SpriteRenderer(tex = new Texture(TexturefilePath)));
            InstaniateCpu = true;
        }
    }

    public void AdjustScaling(int Width, int Height) {
        this.Gettransform.scale = new Vector2f(Width, Height);
    }


    public Entity() {
        AddAComponent(new SpriteRenderer(tex = new Texture("")));
    }

    public void MoveEntityX(float x) {
        this.Gettransform.position.x += x;

    }

    public void MoveEntityY(float y) {
        this.Gettransform.position.y += y;
    }

    public void setXpos(int x){
        this.Gettransform.position.x = x;
    }

    public void setYpos(int y){
        this.Gettransform.position.y = y;
    }

    public int getXPos(){
        return (int)this.Gettransform.position.x;
    }

    public int getYPos(){
        return (int)this.Gettransform.position.y;
    }

    protected <T extends Component> T GetAComponent(Class<T> componentClass) {
        for (Component c : components) {
            if (componentClass.isAssignableFrom(c.getClass())) {
                try {
                    return componentClass.cast(c);
                } catch (ClassCastException e) {
                    e.printStackTrace();
                    assert false : "Error: Casting component";
                }
            }
        }
        return null;
    }

    protected <T extends Component> void RemoveAComponent(Class<T> componentClass) {
        for (int i = 0; i < components.size(); i++) {
            Component c = components.get(i);
            if (componentClass.isAssignableFrom(c.getClass())) {
                components.remove(i);
                return;
            }
        }
    }

    protected void AddAComponent(Component c) {
        components.add(c);
        c.entity = this;
    }



    public void flip() {
        stbi_set_flip_vertically_on_load(true);

    }


    public boolean Collision(Entity entityCollision) {
        if (entityCollision.Gettransform.position.x + 20 >= this.Gettransform.position.x && entityCollision.Gettransform.position.x <= this.Gettransform.position.x + 20 &&
                entityCollision.Gettransform.position.y + 20 >= this.Gettransform.position.y && entityCollision.Gettransform.position.y <= this.Gettransform.position.y + 20) {
            System.out.println("collision happening");
            return true;
        }
        return false;
    }

}
