package com.badlogic.gdx.elita.services;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Disposable;

public interface IMusicService extends Disposable {
    void play(Music music);
    void stop();
    void setVolume(float volume);
    void setEnabled(boolean enabled);
}
