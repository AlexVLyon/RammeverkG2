package org.Easy2dGameEngine.Audio;

import org.joml.Vector3f;

import static org.lwjgl.openal.AL10.*;

/**
 * Class for the SoundSource, or the "sound player"
 * This is the class that plays a SoundBuffer
 */
public class SoundSource {

    private final int sourceId;

    /**
     * constrcutor for the SoundSource
     * @param loop If true, will loop
     * @param relative If true, will play with sound relative to the listener.
     */
    public SoundSource(boolean loop, boolean relative) {
        this.sourceId = alGenSources();
        if (loop) {
            alSourcei(sourceId, AL_LOOPING, AL_TRUE);
        }
        if (relative) {
            alSourcei(sourceId, AL_SOURCE_RELATIVE, AL_TRUE);
        }
    }

    /**
     * Set what buffer to use. (Here is where you set the desired sound)
     * @param bufferId
     * Tip: Use the getBufferId() method on a buffer you created.
     */
    public void setBuffer(int bufferId) {
        stop();
        alSourcei(sourceId, AL_BUFFER, bufferId);
    }

    /**
     * Method to set the position of the SoundSource.
     * @param position
     */
    public void setPosition(Vector3f position) {
        alSource3f(sourceId, AL_POSITION, position.x, position.y, position.z);
    }

    /**
     * Set the speed of the SoundSource
     * @param speed
     */
    public void setSpeed(Vector3f speed) {
        alSource3f(sourceId, AL_VELOCITY, speed.x, speed.y, speed.z);
    }

    /**
     * Set the gain of the SoundSource
     * @param gain
     */
    public void setGain(float gain) {
        alSourcef(sourceId, AL_GAIN, gain);
    }

    /**
     * Method to set the property of the SoundSource
     * @param param
     * @param value
     */
    public void setProperty(int param, float value) {
        alSourcef(sourceId, param, value);
    }

    /**
     * method to play the SoundSource
     */
    public void play() {
        alSourcePlay(sourceId);
    }

    /**
     * Method that returns a boolean value if SoundSource is currently playing
     * @return
     */
    public boolean isPlaying() {
        return alGetSourcei(sourceId, AL_SOURCE_STATE) == AL_PLAYING;
    }

    /**
     * Method to pause the SoundSource
     */
    public void pause() {
        alSourcePause(sourceId);
    }

    /**
     * Method to stop the SoundSource
     */
    public void stop() {
        alSourceStop(sourceId);
    }

    /**
     * Method that stops and deletes the SoundSource
     */
    public void cleanup() {
        stop();
        alDeleteSources(sourceId);
    }
}
