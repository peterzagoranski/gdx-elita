package com.badlogic.gdx.elita;

import com.badlogic.gdx.*;
import com.badlogic.gdx.elita.services.IMusicService;
import com.badlogic.gdx.elita.services.ISettingsService;
import com.badlogic.gdx.elita.services.ISoundService;
import com.badlogic.gdx.elita.services.MusicService;
import com.badlogic.gdx.elita.services.SettingsService;
import com.badlogic.gdx.elita.services.SoundService;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

@SuppressWarnings("unused")
public abstract class Game extends com.badlogic.gdx.Game {
    public Game(final int width, final int height, final boolean debug) {
        this.width = width;
        this.height = height;
        this.debug = debug;
    }

    public Game(final int width, final int height) {
        this(width, height, false);
    }

    public Game(final boolean debug) {
        this(480, 854, debug);
    }

    public Game() {
        this(false);
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

    @Override
    public void create() {
        this.log("Creating game on " + Gdx.app.getType() );

        this.batch = new SpriteBatch();

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
        this.log("Resizing game to: " + width + " x " + height );
    }

    @Override
    public void render() {
        glClear();

        super.render();

        // output the current FPS
        if(debug) fpsLogger.log();
    }

    @Override
    public void pause() {
        super.pause();
        this.log("Pausing game" );
    }

    @Override
    public void resume() {
        super.resume();
        this.log("Resuming game" );
    }

    @Override
    public void setScreen(com.badlogic.gdx.Screen screen) {
        super.setScreen( screen );
        this.log("Setting screen: " + screen.getClass().getSimpleName());
    }

    @Override
    public void dispose() {
        super.dispose();
        this.log("Disposing game");

        // dispose some services
        getMusicService().dispose();
        getSoundService().dispose();

        if (null != this.batch) this.batch.dispose();
    }

    public SpriteBatch getBatch() {
        return this.batch;
    }

    public int getViewportWidth() {
        return this.width;
    }

    public int getViewportHeight() {
        return this.height;
    }

    public void log(final String message) {
        if (debug) Gdx.app.log(this.getClass().getSimpleName(), message);
    }

    protected void glClear() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    // A LibGdx helper class that logs the current FPS each second
    private FPSLogger fpsLogger;

    private SpriteBatch batch;

    // services
    private ISettingsService settingsService;
    private IMusicService musicService;
    private ISoundService soundService;

    private final int width;
    private final int height;
    private final boolean debug;
}
