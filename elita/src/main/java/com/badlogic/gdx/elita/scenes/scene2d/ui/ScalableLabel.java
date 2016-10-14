package com.badlogic.gdx.elita.scenes.scene2d.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class ScalableLabel extends Container {
    private final Label label;
    public ScalableLabel(CharSequence text, Skin skin, String styleName) {
        label = new Label(text, skin, styleName);
        setActor(label);
        setTransform(true);
    }

    public void setText(CharSequence text) {
        label.setText(text);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.setOrigin(this.getPrefWidth() / 2, this.getPrefHeight() / 2);
    }
}
