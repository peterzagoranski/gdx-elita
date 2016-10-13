package com.badlogic.gdx.elita.scenes.scene2d;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class GradientActor extends Actor {

    private final Color from;
    private final Color to;
    private final ShapeRenderer shapeRenderer;

    public GradientActor(final Color from, final Color to) {
        this.from = from;
        this.to = to;
        this.shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        final Camera camera = this.getStage().getCamera();
        shapeRenderer.setProjectionMatrix(camera.combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(this.getX(), this.getY(), this.getWidth(), getHeight(), to, to, from, from);
        shapeRenderer.end();
    }
}
