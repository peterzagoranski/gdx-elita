package com.badlogic.gdx.elita.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.elita.Game;

/**
 * A service that manages the sound effects.
 */
public class SoundService implements ISoundService {

    /**
     * The volume to be set on the sound.
     */
    private float volume = 1f;

    /**
     * Whether the sound is enabled.
     */
    private boolean enabled = true;

    /**
     * Creates the sound manager.
     */
    public SoundService()
    {
    }

    /**
     * Plays the specified sound.
     */
    public void play(Sound sound)
    {
        play(sound, false);
    }

    /**
     * Plays the specified sound.
     */
    public void play(Sound sound, boolean loop)
    {
        // check if the sound is enabled
        if( ! enabled ) return;


        Gdx.app.log(Game.LOG, "Playing sound: " +  sound);

        if(loop) {
            sound.loop(volume);
        } else {
            sound.play(volume);
        }
    }

    /**
     * Sets the sound volume which must be inside the range [0,1].
     */
    public void setVolume(float volume )
    {
        Gdx.app.log(Game.LOG, "Adjusting sound volume to: " + volume );

        // check and set the new volume
        if( volume < 0 || volume > 1f ) {
            throw new IllegalArgumentException( "The volume must be inside the range: [0,1]" );
        }
        this.volume = volume;
    }

    /**
     * Enables or disabled the sound.
     */
    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    /**
     * Releases all resources of this object.
     */
    @Override
    public void dispose() {
        Gdx.app.log(Game.LOG, "Disposing sound service");
    }
}
