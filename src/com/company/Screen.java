package com.company;

import Components.SpriteRenderer;
import org.lwjgl.*;
import org.lwjgl.Version;
import org.lwjgl.glfw.*;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.IntBuffer;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Screen extends Scene{
private long window;
private float blue,red,green,alpha;

Entity testing;
    EditScene e = new EditScene();
    Scene currentsc;
    public void run() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");
e.init();

this.testing = new Entity("test objeckt");
this.testing.AddComponent(new SpriteRenderer());

        loop();

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
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

    private void loop() {

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
float beginTime = Timer.getTimes();
        float endtime = Timer.getTimes();
        float dt = -1.0f;



        // Set the clear color
        glClearColor(red, green, blue, alpha);

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( !glfwWindowShouldClose(window) ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

if(dt >= 0)
    e.Update(dt);

            glfwSwapBuffers(window); // swap the color buffers

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();

            endtime = Timer.getTimes();
            dt = endtime - beginTime;
            beginTime = endtime;
        }


    }

public void WindowColor(float red, float green, float blue, float alpha){
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
}


    @Override
    public void Update(float dt) {

    }
}
