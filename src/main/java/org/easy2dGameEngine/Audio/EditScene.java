package org.easy2dGameEngine.Audio;

public class EditScene extends GameEditor {



    public Entity entity1,entity2,entity3;

    public EditScene(){



    }
    FontBatch batch = new FontBatch();
@Override
public void init(){

    entity1 = new Entity("assets/images/MoreDmg.png",111,111);
    entity2 = new Entity("assets/images/MoreDmg.png",211,111);

    batch.initBatch();
}

//depth-buffering

    @Override
    public void Update() {

        AddGameObjectsToScreen(entity1);
        AddGameObjectsToScreen(entity2);




        batch.flushBatch();
        batch.addText("Hello wo77rlD! =>", 200, 200, 1f, 0xFF00AB0);







    }
}
