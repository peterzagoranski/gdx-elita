package com.badlogic.gdx.elita.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Handles the game settings.
 */
public class SettingsService implements ISettingsService
{
    private Preferences preferences;

    // constants
    private static final String PREF_VOLUME = "volume";
    private static final String PREF_MUSIC_ENABLED = "music.enabled";
    private static final String PREF_SOUND_ENABLED = "sound.enabled";
    private static final String PREF_LANGUAGE = "language";
    private static final String PREFS_NAME = "preferences";

    public SettingsService() {
    }

    protected Preferences getPrefs() {
        if (null == this.preferences) {
            this.preferences = Gdx.app.getPreferences(PREFS_NAME);
        }
       return this.preferences;
    }

    @Override
    public boolean isSoundEnabled() {
        return getPrefs().getBoolean( PREF_SOUND_ENABLED, true );
    }

    @Override
    public void setSoundEnabled(boolean soundEffectsEnabled) {
        getPrefs().putBoolean( PREF_SOUND_ENABLED, soundEffectsEnabled );
        getPrefs().flush();
    }

    @Override
    public boolean isMusicEnabled() {
        return getPrefs().getBoolean( PREF_MUSIC_ENABLED, true );
    }

    @Override
    public void setMusicEnabled(boolean musicEnabled) {
        getPrefs().putBoolean( PREF_MUSIC_ENABLED, musicEnabled );
        getPrefs().flush();
    }

    @Override
    public float getVolume() {
        return getPrefs().getFloat( PREF_VOLUME, 0.5f );
    }

    @Override
    public void setVolume(float volume) {
        getPrefs().putFloat( PREF_VOLUME, volume );
        getPrefs().flush();
    }

    @Override
    public String getLanguage() {
        return getPrefs().getString( PREF_LANGUAGE, "en" );
    }

    @Override
    public void setLanguage(String language) {
        getPrefs().putString( PREF_LANGUAGE, language );
        getPrefs().flush();
    }

    @Override
    public void reset() {
        getPrefs().clear();
        getPrefs().flush();
    }
}
