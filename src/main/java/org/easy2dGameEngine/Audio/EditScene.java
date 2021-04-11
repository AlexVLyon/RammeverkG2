package org.easy2dGameEngine.Audio;

public class EditScene extends GameEditor {



    public Entity entity1,entity2,entity3;

    public EditScene(){



    }

@Override
public void init(){

    entity1 = new Entity("assets/images/MoreDmg.png",111,111);
    entity2 = new Entity("assets/images/MoreDmg.png",211,111);


}

//depth-buffering

    @Override
    public void Update() {

        AddGameObjectsToScreen(entity1);
        AddGameObjectsToScreen(entity2);

       AddTextToScreen("a b c d e f g h i j k l m n o p q r ", 200, 200, 1f, 0xFF00AB0);







    }
}
