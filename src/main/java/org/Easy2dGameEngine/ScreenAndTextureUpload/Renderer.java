package org.Easy2dGameEngine.ScreenAndTextureUpload;

import java.util.ArrayList;
import java.util.List;

class Renderer {

    private final int MAX_BATCH_SIZE = 1000;
    private List<RenderBatch> batchList;

    public Renderer() {
        this.batchList = new ArrayList<>();
    }


    public void add(Entity en) {

        SpriteRenderer spriteRenderer = en.GetAComponent(SpriteRenderer.class);

        add(spriteRenderer);


    }

    public void RemoveEntity(Entity en) {
        SpriteRenderer spriteRenderer = en.GetAComponent(SpriteRenderer.class);

        remove(spriteRenderer);
    }

    public void add(SpriteRenderer en) {

        boolean added = false;
        for (RenderBatch batch : batchList) {

            if (batch.hasRoom()) {

                batch.addSprite(en);
                added = true;

                break;
            }
        }
        if (!added) {
            RenderBatch newBatch = new RenderBatch(MAX_BATCH_SIZE);

            newBatch.start();
            batchList.add(newBatch);
            newBatch.addSprite(en);
        }
    }

    public void remove(SpriteRenderer en) {
        for (RenderBatch batch : batchList) {
            batch.removeSprite(en);

        }
    }

    public void Remove(Entity en) {
        SpriteRenderer spriteRenderer = en.GetAComponent(SpriteRenderer.class);

        add(spriteRenderer);
    }

    public void render() {
        for (RenderBatch batch : batchList) {
            batch.render();
        }
    }
}
