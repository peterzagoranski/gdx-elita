package com.badlogic.gdx.elita.services;

/**
 * Created by peterza on 12.11.2014.
 */
public interface ISettingsService {
    boolean isSoundEnabled();
    void setSoundEnabled(boolean soundEffectsEnabled);

    boolean isMusicEnabled();
    void setMusicEnabled(boolean musicEnabled);

    float getVolume();
    void setVolume(float volume);

    String getLanguage();
    void setLanguage(String language);

    void reset();
}
