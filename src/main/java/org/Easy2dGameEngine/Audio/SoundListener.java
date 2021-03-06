package org.Easy2dGameEngine.Audio;

import org.joml.Vector3f;

import static org.lwjgl.openal.AL10.*;

/**
 * SoundListener class
 * This is the listener for all the sounds that is playing.
 */
public class SoundListener {

    /**
     * Constructor for SoundListener with no params
     */
    public SoundListener() {
        this(new Vector3f(0, 0, 0));
    }

    /**
     * Constructor for SoundListener with position
     * @param position
     */
    public SoundListener(Vector3f position) {
        alListener3f(AL_POSITION, position.x, position.y, position.z);
        alListener3f(AL_VELOCITY, 0, 0, 0);

    }

    /**
     * Method to set the speed of the listener
     * @param speed
     */
    public void setSpeed(Vector3f speed) {
        alListener3f(AL_VELOCITY, speed.x, speed.y, speed.z);
    }

    /**
     * Method to set the position of the listener.
     * @param position vector3f
     */
    public void setPosition(Vector3f position) {
        alListener3f(AL_POSITION, position.x, position.y, position.z);
    }

    /**
     * Method to set the orientation fo the Listener
     * @param at
     * @param up
     */
    public void setOrientation(Vector3f at, Vector3f up) {
        float[] data = new float[6];
        data[0] = at.x;
        data[1] = at.y;
        data[2] = at.z;
        data[3] = up.x;
        data[4] = up.y;
        data[5] = up.z;
        alListenerfv(AL_ORIENTATION, data);
    }
}
