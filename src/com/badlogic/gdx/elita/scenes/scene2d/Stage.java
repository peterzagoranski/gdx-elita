package com.badlogic.gdx.elita.scenes.scene2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.elita.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class Stage extends com.badlogic.gdx.scenes.scene2d.Stage {

    public Stage(final SpriteBatch spriteBatch, final float pWidth, final float pHeight) {
        super(new ExtendViewport(pWidth, pHeight), spriteBatch);
    }

    public Stage(final SpriteBatch spriteBatch, final float pWidth, final float pHeight, final float pMaxWidth, final float pMaxHeight) {
        super(new ExtendViewport(pWidth, pHeight, pMaxWidth, pMaxHeight), spriteBatch);
    }

    public Stage(final Game game) {
        this(game.getBatch(), game.getViewportWidth(), game.getViewportHeight());
    }

    @Override
    public void dispose() {
        Gdx.app.log(Game.LOG, "Disposing stage: " + getName());

        super.dispose();
    }

    protected String getName()
    {
        return ((Object) this).getClass().getSimpleName();
    }
}
