package com.badlogic.gdx.elita.scenes.scene2d;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class TiledMapActor extends Actor {
    public TiledMapActor(final TiledMap map, final int x, final int y) {
        this.map = map;
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        if (null != renderer) {
            batch.end();

            batch.setColor(1,1,1,1);

            renderer.setView((OrthographicCamera) getStage().getCamera());
            renderer.render();

            ((OrthographicCamera) getStage().getCamera()).position.set(this.x, this.y, 0);

            batch.begin();
        }
    }

    @Override
    protected void setStage(Stage stage) {
        super.setStage(stage);

        if (null != stage) {
            this.renderer = new OrthogonalTiledMapRenderer(this.map, stage.getBatch());
        }
    }

    private final TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private final int x;
    private final int y;
}
