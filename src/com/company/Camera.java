package com.company;


import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Camera {

    int width, height;
private Matrix4f projection, viewMatrix;
public Vector2f Camerapos;

public Camera(Vector2f posisjon){
this.Camerapos = posisjon;
this.projection = new Matrix4f();
this.viewMatrix = new Matrix4f();
adjustProjection();
}
/*
    public Camera(int width, int height){
        this.width = width;
        this.height = height;

    }
*/
    public void adjustProjection(){
    projection.identity();
    projection.ortho(0.0f,32.0f*40.0f,0.0f, 32.0f*21.0f,0.0f,100.0f);
    }

    public Matrix4f getViewMatrix(){
        Vector3f cameraFront = new Vector3f(0.0f,0.0f,-1.0f);
        Vector3f cameraUp = new Vector3f(0.0f,1.0f,0.0f);
        this.viewMatrix.identity();
        //where camera is looking at
        viewMatrix = viewMatrix.lookAt(new Vector3f(Camerapos.x,Camerapos.y,20.0f),
                cameraFront.add(Camerapos.x, Camerapos.y, 0.0f),
                cameraUp);
        return this.viewMatrix;
    }

    public Matrix4f getProjectionMatrix(){
    return this.projection;
    }

    public void translate(int x, int y){

    }

    public void zoom(float zoomVal){

    }

    public void bindTo(int x, int y){


    }
    public void transition(Camera AnewCameraTolook){

    }
}
