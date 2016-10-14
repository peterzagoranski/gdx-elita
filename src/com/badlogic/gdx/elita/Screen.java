package com.badlogic.gdx.elita;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;

@SuppressWarnings("unused")
public abstract class Screen<TGame extends Game> extends InputAdapter implements com.badlogic.gdx.Screen {

    private final TGame game;
    private final ArrayList<Stage> stages;

    private final InputMultiplexer inputs;

    public Screen(final TGame game) {
        this.game = game;
        this.stages = new ArrayList<Stage>();

        this.inputs = new InputMultiplexer();
        Gdx.input.setInputProcessor(this.inputs);
        Gdx.input.setCatchBackKey(true);

        this.inputs.addProcessor(this);
    }

    @Override
    public void show() {
        this.game.log("Showing screen: " + getName());
    }

    @Override
    public void resize(int width, int height) {
        this.game.log("Resizing screen: " + getName() + " to: " + width + " x " + height );
        for(Stage stage : this.stages) {
            stage.getViewport().update(width, height, true);
        }
    }

    @Override
    public void render(float delta) {
        // update and draw the actors
        for(Stage stage : this.stages) {
            stage.act(delta);
            stage.draw();
        }
    }

    @Override
    public void hide() {
        this.game.log("Hiding screen: " + getName() );

        dispose();
    }

    @Override
    public void pause() {
        this.game.log("Pausing screen: " + getName() );
    }

    @Override
    public void resume() {
        this.game.log("Resuming screen: " + getName() );
    }

    @Override
    public void dispose() {
        this.game.log("Disposing screen: " + getName());

        for(Stage stage : this.stages) {
            stage.dispose();
        }
    }

    public TGame getGame() { return this.game; }

    protected InputMultiplexer getInputMultiplexer() { return this.inputs; }

    protected void addStage(final Stage stage) {
        this.game.log("Added stage: " + stage.getClass().getSimpleName());
        this.stages.add(stage);
    }

    protected void addStage(final Stage stage, final int index) {
        this.game.log("Added stage: " + stage.getClass().getSimpleName() + " at index: " + index);
        this.stages.add(index, stage);
    }

    protected void addStageAndProcessor(final Stage stage) {
        this.game.log("Added stage and processor: " + stage.getClass().getSimpleName());

        this.addStage(stage);
        this.getInputMultiplexer().addProcessor(stage);
    }

    protected void addStageAndProcessor(final Stage stage, final int index) {
        this.game.log("Added stage: " + stage.getClass().getSimpleName() + " and processor at index: " + index);

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
