package com.badlogic.gdx.elita.scenes.scene2d.utils;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ActorClickListener extends ClickListener {
    private final Actor mActor;

    public ActorClickListener(final Actor pActor) {
        this.mActor = pActor;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        this.mActor.setScale(0.95f);
        this.mActor.setOrigin(this.mActor.getWidth()/2,this.mActor.getHeight()/2);
        return super.touchDown(event, x, y, pointer, button);
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        super.touchUp(event, x, y, pointer, button);
        this.mActor.setScale(1f);
        this.mActor.setOrigin(this.mActor.getWidth()/2,this.mActor.getHeight()/2);
    }
}
