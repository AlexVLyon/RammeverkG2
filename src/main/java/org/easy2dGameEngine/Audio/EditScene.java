package org.easy2dGameEngine.Audio;

import org.lwjgl.glfw.GLFW;

import java.awt.event.KeyListener;

import static org.lwjgl.glfw.GLFW.*;

public class EditScene extends GameEditor {



    public Entity entity1,entity2, entity3;

    public EditScene(){



    }

@Override
public void init(){

    entity1 = new Entity("assets/images/MoreDmg.png",111,111);
    entity2 = new Entity("assets/images/MoreDmg.png",211,111);
    entity3 = new Entity("assets/images/MoreDmg.png",311,111);

}

//depth-buffering

    @Override
    public void Update() {

        AddGameObjectsToScreen(entity1);


        if(KeyEventListener.ButtonPressed(GLFW_KEY_D))
            entity1.MoveEntityX(10);
         if(KeyEventListener.ButtonPressed(GLFW_KEY_A))
            entity1.MoveEntityX(-10);
         if(KeyEventListener.ButtonPressed(GLFW_KEY_W))
            entity1.MoveEntityY(10);
          if(KeyEventListener.ButtonPressed(GLFW_KEY_S))
            entity1.MoveEntityY(-10);



        AddTextToScreen("a b c d e f g h i j k l m n o p q r ", 200, 200, 1f, 0xFF00AB0);

        AddTextToScreen("a b c d e f g h i j k l m n o p q r ", 200, 500, 1f, 0xFF00AB0);





    }
}
