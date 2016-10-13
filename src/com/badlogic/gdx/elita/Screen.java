package com.badlogic.gdx.elita;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;

public abstract class Screen<TGame extends Game> extends InputAdapter implements com.badlogic.gdx.Screen {

    private final TGame mGame;
    private final ArrayList<Stage> mStages;

    private final InputMultiplexer mInputMultiplexer;

    public Screen(final TGame game) {
        this.mGame = game;
        this.mStages = new ArrayList<Stage>();

        this.mInputMultiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(this.mInputMultiplexer);
        Gdx.input.setCatchBackKey(true);

        this.mInputMultiplexer.addProcessor(this);
    }

    @Override
    public void show() {
        Gdx.app.log(TGame.LOG, "Showing screen: " + getName());
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log(TGame.LOG, "Resizing screen: " + getName() + " to: " + width + " x " + height );
        for(Stage stage : this.mStages) {
            stage.getViewport().update(width, height, true);
        }
    }

    @Override
    public void render(float delta) {
        // update and draw the actors
        for(Stage stage : this.mStages) {
            stage.act(delta);
            stage.draw();
        }
    }

    @Override
    public void hide() {
        Gdx.app.log(TGame.LOG, "Hiding screen: " + getName() );

        dispose();
    }

    @Override
    public void pause() {
        Gdx.app.log(TGame.LOG, "Pausing screen: " + getName() );
    }

    @Override
    public void resume() {
        Gdx.app.log(TGame.LOG, "Resuming screen: " + getName() );
    }

    @Override
    public void dispose() {
        Gdx.app.log(TGame.LOG, "Disposing screen: " + getName());

        for(Stage stage : this.mStages) {
            stage.dispose();
        }
    }

    public TGame getGame() { return this.mGame; }

    protected InputMultiplexer getInputMultiplexer() { return this.mInputMultiplexer; }

    protected void addStage(final Stage stage) {
        Gdx.app.log(TGame.LOG, "Added stage: " + stage.getClass().getSimpleName());
        this.mStages.add(stage);
    }

    protected void addStage(final Stage stage, final int index) {
        Gdx.app.log(TGame.LOG, "Added stage: " + stage.getClass().getSimpleName() + " at index: " + index);
        this.mStages.add(index, stage);
    }

    protected void addStageAndProcessor(final Stage stage) {
        Gdx.app.log(TGame.LOG, "Added stage and processor: " + stage.getClass().getSimpleName());

        this.addStage(stage);
        this.getInputMultiplexer().addProcessor(stage);
    }

    protected void addStageAndProcessor(final Stage stage, final int index) {
        Gdx.app.log(TGame.LOG, "Added stage: " + stage.getClass().getSimpleName() + " and processor at index: " + index);

        this.addStage(stage);
        this.getInputMultiplexer().addProcessor(index, stage);
    }

    protected String getName() {
        return ((Object) this).getClass().getSimpleName();
    }

    @Override
    public boolean keyDown(int keyCode) {
        if (keyCode == Input.Keys.BACK || keyCode == Input.Keys.ESCAPE) {
            onBackKeyPressed();
        }
        return super.keyDown(keyCode);
    }

    protected abstract void onBackKeyPressed();
}
