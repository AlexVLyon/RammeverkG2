package org.easy2dGameEngine.Audio;

import static org.lwjgl.glfw.GLFW.*;


public class EditScene extends ScreenEditor {

    public Entity entity1,entity2;
    public EditScene(){

    }

@Override
public void init(){


    entity2 = new Entity("assets/images/MoreDmg.png", 100,100);
    entity2.Gettransform.AdjustScale(50,40);

    entity1 = new Entity("assets/images/testImage.png", 50,100);
      entity1.Gettransform.AdjustScale(50,40);










}




    @Override
    public void Update() {

        AddGameObjectsToScreen(entity1);



if(KeyEventListener.isButtonPressed(GLFW_KEY_D))
    entity1.Gettransform.position.x += 5;

       else if (KeyEventListener.isButtonPressed(GLFW_KEY_A))
        entity1.Gettransform.position.x -= 5;
    if (KeyEventListener.isButtonPressed(GLFW_KEY_W))
        entity1.Gettransform.position.y += 5;

    else if (KeyEventListener.isButtonPressed(GLFW_KEY_S))
        entity1.Gettransform.position.y -= 5;


    AddGameObjectsToScreen(entity2);





        entity2.Collision(entity1);
    }


}
