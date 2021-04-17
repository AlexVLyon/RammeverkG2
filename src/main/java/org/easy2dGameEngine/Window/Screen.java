package org.easy2dGameEngine.Window;

import org.easy2dGameEngine.GameEditor;
import org.easy2dGameEngine.KeyEventListener;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11C.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Screen {
public long window;
public float blue,red,green,alpha;

//set method class
public GameEditor s;

public void ConnectClassToScreen(GameEditor screenConntector){
    s = screenConntector;

}
    public void run()  {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");
s.init();
s.start();

        loop();



        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public Screen(){

    }


    public void CreateWindow(int width, int height, String title) {

        // Setup an error callback. The default implementation
        // will print the error message in System.err.

        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if ( !glfwInit() ) {
            System.exit(1);
            throw new IllegalStateException("Unable to initialize GLFW");

        }
        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

        // Create the window
        window = glfwCreateWindow(width, height, title, NULL, NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
        });




        // Get the thread stack and push a new frame
        try ( MemoryStack stack = stackPush() ) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);
glfwSetKeyCallback(window, KeyEventListener::GetScreenKeyEvents);
            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        } // the stack frame is popped automatically

        glfwMakeContextCurrent(window);



        // Enable v-sync
        glfwSwapInterval(1);
        // Make the window visible
        glfwShowWindow(window);
        // Make the OpenGL context current

        GL.createCapabilities();
    }



    private void loop()  {
glEnable(GL_DEPTH_TEST);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA);

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        // Set the clear color

        glDisable(GL_DEPTH_TEST);
        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( !glfwWindowShouldClose(window) ) {
            glClearColor(red, green, blue, alpha);

            glClear(GL_COLOR_BUFFER_BIT); // clear the framebuffer
/*
            editScene.Update();
            editScene.Render();

 */
s.Update();
s.Render();






            glfwSwapBuffers(window); // swap the color buffers


            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();

        }


    }


public void ChangeScreenColor(float blue, float red, float green, float alpha){
        this.blue = blue;
        this.red = red;
        this.green = green;
        this.alpha = alpha;
}


}
