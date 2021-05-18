package org.easy2dGameEngine.ScreenAndTextureUpload;


import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.stb.STBImage.stbi_set_flip_vertically_on_load;

/**
 * An Abstract class which provides methods to create an entity
 */
public abstract class Entity {
    // Location
    protected List<Component> components;
    boolean InstaniateCpu = false;


    String TexturefilePath;
    Texture tex;
    protected Transform Gettransform;

    /**
     * Constructor for class.
     * This constructor takes in three parameters which is required to create a entity
     *
     * @param TexturefilePath A string that takes in a .png filepath that specifies the texture that is going be drawn
     * @param x a int value to set the x-position of the texture
     * @param y a int value to set the y-position of the texture
     */
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

    /**
     * This method re-adjusts the scaling of the texture
     *
     * @param Width defines the width of the texture
     * @param Height defines the height of the texture
     */

    public void AdjustScaling(int Width, int Height) {
        this.Gettransform.scale = new Vector2f(Width, Height);
    }

    /**
     * This method takes in float value to Move the texture in the x-axis
     *
     * @param x sets the speed of the texture
     */
    public void MoveEntityX(float x) {
        this.Gettransform.position.x += x;

    }

    /**
     * This method takes in float value to Move the texture in the y-axis
     * @param y sets the speed of the texture
     */
    public void MoveEntityY(float y) {
        this.Gettransform.position.y += y;
    }

    /**
     * This method  sets the x-position of the texture
     * @param x an int value to set a new x-position of the texture
     */
    public void setXpos(int x){
        this.Gettransform.position.x = x;
    }

    /**
     * This method  sets the y-position of the texture
     * @param y an int value to set a new y-position of the texture
     */
    public void setYpos(int y){
        this.Gettransform.position.y = y;
    }

    /**
     * This method returns the x-position of the texture
     * @return returns the x-position value of the texture
     * @see int
     */
    public int getXPos(){
        return (int)this.Gettransform.position.x;
    }

    /**
     * This method returns the y-position of the texture
     * @return returns the y-position value of the texture
     * @see int
     */
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


    /**
     * This method flips the texture 180-degrees
     */
    public void flip() {
        stbi_set_flip_vertically_on_load(true);

    }

    /**
     *This method will return true or false whether there is any collision
     *
     * @param entityCollision takes in a Entity to check if there is any collision
     * @return returns false or true based on whether there is collision or not
     * @see boolean
     */
    public boolean Collision(Entity entityCollision) {
        if (entityCollision.Gettransform.position.x + 20 >= this.Gettransform.position.x && entityCollision.Gettransform.position.x <= this.Gettransform.position.x + 20 &&
                entityCollision.Gettransform.position.y + 20 >= this.Gettransform.position.y && entityCollision.Gettransform.position.y <= this.Gettransform.position.y + 20) {
            return true;
        }
        return false;
    }

}
