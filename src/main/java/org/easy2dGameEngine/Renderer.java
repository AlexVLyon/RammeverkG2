package org.easy2dGameEngine;

import Components.SpriteRenderer;

import java.util.ArrayList;
import java.util.List;

public class Renderer {
    private final int MAX_BATCH_SIZE = 1000;
    private List<RenderBatch> batchList;

    public Renderer(){
        this.batchList = new ArrayList<>();
    }

    public void add(Entity en){

        SpriteRenderer spriteRenderer = en.GetComponent(SpriteRenderer.class);
        if (spriteRenderer != null)
            add(spriteRenderer);

    }

    public void add(SpriteRenderer en){
        System.out.println("heell");
        boolean added = false;
        for(int i = 0; i < batchList.size(); i++){

            if(batchList.get(i).hasSpace){

                batchList.get(i).addSprite(en);
                added = true;

                break;
            }else
                System.out.println("hay");
        }
        if (!added){
            RenderBatch newBatch = new RenderBatch(MAX_BATCH_SIZE);

            newBatch.start();
            batchList.add(newBatch);
            newBatch.addSprite(en);
        }
    }

    public void render(){
        for(RenderBatch batch : batchList){
            batch.render();
        }
    }
}
