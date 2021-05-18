package org.easy2dGameEngine.ScreenAndTextureUpload;

import java.util.ArrayList;
import java.util.List;

 class StartAndRender {
    protected static Renderer renderer = new Renderer();
    protected static List<Entity> entityList = new ArrayList<>();


     protected static void Render() {
         renderer.render();
     }

     protected static void start() {
         for (Entity en : StartAndRender.entityList) {
             renderer.add(en);
             renderer.render();
         }
     }

}
