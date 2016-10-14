package com.badlogic.gdx.elita.scenes.scene2d;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ParallaxActor extends Actor {
    private final TextureRegion mTextureRegion;

    private final Vector2 mRatio;
    private final Vector2 mPosition;
    private final Vector2 mPadding;
    private final boolean mRepeatY;

    public ParallaxActor(final TextureRegion pTextureRegion, final Vector2 pRatio, final Vector2 pPosition, final Vector2 pPading, final boolean pRepeatY) {
        this.mTextureRegion = pTextureRegion;

        this.mRatio = pRatio;
        this.mPosition = pPosition;
        this.mPadding = pPading;
        this.mRepeatY = pRepeatY;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        final Camera camera = this.getStage().getCamera();

        batch.setProjectionMatrix(camera.projection);

        float currentX = - camera.position.x * mRatio.x % ( mTextureRegion.getRegionWidth() + mPadding.x) ;

        do{
             float currentY = - camera.position.y * mRatio.y % ( mTextureRegion.getRegionHeight() + mPadding.y) ;
            do{
                batch.draw(mTextureRegion,
                        -camera.viewportWidth / 2 + currentX + mPosition.x,
                        -camera.viewportHeight / 2 + currentY + mPosition.y);

                currentY += ( mTextureRegion.getRegionHeight() + mPadding.y );

            } while( mRepeatY && currentY < camera.viewportHeight);

            currentX += ( mTextureRegion.getRegionWidth()+ mPadding.x);

        } while( currentX < camera.viewportWidth);

        batch.setProjectionMatrix(camera.combined);
    }
}
