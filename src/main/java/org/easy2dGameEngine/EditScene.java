package org.easy2dGameEngine;

import Components.SpriteRenderer;
import org.joml.Vector2f;


public class EditScene extends  Scene {

    public EditScene(){

    }
Shader shader;
@Override
public void init(){
camera = new Camera(new Vector2f());


    Texture tex1 = new Texture("assets/images/MoreDmg.png");

    Entity entit = new Entity("object 1", new Transform(new Vector2f(100,100), new Vector2f(256,256)));
    entit.AddComponent(new SpriteRenderer(tex1));
    this.AddGameObjectsToScreen(entit);

    Texture tex2 = new Texture("assets/images/testImage.png");

    Entity entit2 = new Entity("object 2", new Transform(new Vector2f(500,100),new Vector2f(256,256)));
    entit2.AddComponent(new SpriteRenderer(tex1));
    this.AddGameObjectsToScreen(entit2);



    shader = new Shader("Shader/default.glsl");
    shader.compile();

}



    @Override
    public void Update(float dt) {
        this.renderer.render();

        for (Entity eni : this.entityList) {
            eni.Update(dt);

        }
    }


}
