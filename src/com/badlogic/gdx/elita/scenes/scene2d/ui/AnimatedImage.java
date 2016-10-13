package com.badlogic.gdx.elita.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class AnimatedImage extends Image {

    public AnimatedImage(Animation animation) {
        super(animation.getKeyFrame(0));
        this.animation = animation;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(this.animate) {
            stateTime += delta;
        }

        ((TextureRegionDrawable) getDrawable()).setRegion(animation.getKeyFrame(animate ? stateTime : 0, true));
    }

    @SuppressWarnings("unused")
    public void setAnimation(final Animation animation) {
        this.animation = animation;
    }

    @SuppressWarnings("unused")
    public Animation getAnimation() { return this.animation; }

    public void setAnimate(boolean animate) {
        //if (animate && !this.animate) stateTime = 0;

        this.animate = animate;
    }

    private Animation animation = null;
    private float stateTime = 0;
    private boolean animate = true;
}
