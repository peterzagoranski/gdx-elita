package com.badlogic.gdx.elita.scenes.scene2d.ui;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class BackgroundTable extends Table {
    public BackgroundTable(final Drawable background) {
        super();
        this.setFillParent(true);
        this.setBackground(background);

        setTouchable(Touchable.enabled);
    }
}
