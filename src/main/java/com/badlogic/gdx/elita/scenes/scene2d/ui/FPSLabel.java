package com.badlogic.gdx.elita.scenes.scene2d.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class FPSLabel extends Label {
    public FPSLabel() {
        super("", new LabelStyle(new BitmapFont(), Color.WHITE));
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        this.setText("FPS: " + Gdx.graphics.getFramesPerSecond());
    }
}
