package com.badlogic.gdx.elita.services;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Disposable;

public interface ISoundService extends Disposable {
    void play(Sound sound);
    void play(Sound sound, boolean loop);
    void setVolume(float volume);
    void setEnabled(boolean enabled);
}
