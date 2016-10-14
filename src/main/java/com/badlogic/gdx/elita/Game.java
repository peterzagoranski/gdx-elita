package com.badlogic.gdx.elita;

import com.badlogic.gdx.*;
import com.badlogic.gdx.elita.services.IGoogleService;
import com.badlogic.gdx.elita.services.IMusicService;
import com.badlogic.gdx.elita.services.ISettingsService;
import com.badlogic.gdx.elita.services.ISoundService;
import com.badlogic.gdx.elita.services.MusicService;
import com.badlogic.gdx.elita.services.SettingsService;
import com.badlogic.gdx.elita.services.SoundService;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Game extends com.badlogic.gdx.Game {
    public static final String LOG = Game.class.getSimpleName();

    public static final int VIEWPORT_WIDTH = 854, VIEWPORT_HEIGHT = 480;

    // Whether we are in development mode
    public static final boolean DEV_MODE = false;

    // A LibGdx helper class that logs the current FPS each second
    private FPSLogger fpsLogger;

    private final IGoogleService googleServices;
    private SpriteBatch mBatch;

    // services
    private ISettingsService settingsService;
    private IMusicService musicService;
    private ISoundService soundService;

    public Game(final IGoogleService googleServices) {
        this.googleServices = googleServices;
    }

    public ISettingsService getSettingsService() {
        if(null == settingsService) {
            settingsService = new SettingsService();
        }
        return settingsService;
    }

    public IMusicService getMusicService() {
        if(null == musicService) {
            musicService = new MusicService();
        }
        return musicService;
    }

    public ISoundService getSoundService() {
        if(null == soundService) {
            soundService = new SoundService();
        }
        return soundService;
    }

    public IGoogleService getGoogleServices() { return googleServices; }

    @Override
    public void create() {
        Gdx.app.log(LOG, "Creating game on " + Gdx.app.getType() );

        this.mBatch = new SpriteBatch();

        // create the music manager
        getMusicService().setVolume(getSettingsService().getVolume());
        getMusicService().setEnabled(getSettingsService().isMusicEnabled());

        // create the sound manager
        getSoundService().setVolume( getSettingsService().getVolume() );
        getSoundService().setEnabled( getSettingsService().isSoundEnabled() );

        // create the helper objects
        fpsLogger = new FPSLogger();
    }

    @Override
    public void resize(int width, int height )
    {
        super.resize( width, height );
        Gdx.app.log(LOG, "Resizing game to: " + width + " x " + height );
    }

    @Override
    public void render() {
        glClear();

        super.render();

        // output the current FPS
        if(DEV_MODE) fpsLogger.log();
    }

    @Override
    public void pause() {
        super.pause();
        Gdx.app.log(LOG, "Pausing game" );
    }

    @Override
    public void resume() {
        super.resume();
        Gdx.app.log(LOG, "Resuming game" );
    }

    @Override
    public void setScreen(com.badlogic.gdx.Screen screen) {
        super.setScreen( screen );
        Gdx.app.log(LOG, "Setting screen: " + screen.getClass().getSimpleName());
    }

    @Override
    public void dispose() {
        super.dispose();
        Gdx.app.log(LOG, "Disposing game");

        // dispose some services
        getMusicService().dispose();
        getSoundService().dispose();

        if (null != this.mBatch) this.mBatch.dispose();
    }

    public SpriteBatch getBatch() {
        return this.mBatch;
    }

    public int getViewportWidth() {
        return VIEWPORT_WIDTH;
    }

    public int getViewportHeight() {
        return VIEWPORT_HEIGHT;
    }

    protected void glClear() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
    }
}
