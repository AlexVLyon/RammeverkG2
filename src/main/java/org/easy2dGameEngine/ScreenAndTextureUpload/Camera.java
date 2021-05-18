package org.easy2dGameEngine.ScreenAndTextureUpload;


import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

/**
 * a class to handle camera functionality
 */
public class Camera {

    private Matrix4f projection, viewMatrix;
    private Vector2f Camerapos;

    protected Camera(Vector2f posisjon) {
        this.Camerapos = posisjon;
        this.projection = new Matrix4f();
        this.viewMatrix = new Matrix4f();
        adjustProjection();
    }

    /**
     * This method will follow an entity around the screen
     *
     * @param entity takes in an entity object, which the camera will follow around
     * @param x defines the x-axis distance between the camera and the entity
     * @param y defines the y-axis distance between the camera and the entity
     */
    public void followEntity(Entity entity, int x, int y) {
        this.Camerapos.x = entity.Gettransform.position.x + x;
        this.Camerapos.y = entity.Gettransform.position.y + y;
    }

    protected void adjustProjection() {
        projection.identity();
        projection.ortho(0.0f, 32.0f * 40.0f, 0.0f, 32.0f * 21.0f, 0.0f, 100.0f);
    }

    protected Matrix4f getViewMatrix() {
        Vector3f cameraFront = new Vector3f(0.0f, 0.0f, -1.0f);
        Vector3f cameraUp = new Vector3f(0.0f, 1.0f, 0.0f);
        this.viewMatrix.identity();
        //where camera is looking at
        viewMatrix = viewMatrix.lookAt(new Vector3f(Camerapos.x, Camerapos.y, 20.0f),
                cameraFront.add(Camerapos.x, Camerapos.y, 0.0f),
                cameraUp);
        return this.viewMatrix;
    }

    protected Matrix4f getProjectionMatrix() {
        return this.projection;
    }

    /**
     * This method will move the camera on the x- and y-axis
     *
     * @param x moves the camera in the x-axis based on the parameter
     * @param y moves the camera in the y-axis based on the parameter
     */
    public void MoveCamera(int x, int y) {
        this.Camerapos.x += x;
        this.Camerapos.x += y;
    }
}
