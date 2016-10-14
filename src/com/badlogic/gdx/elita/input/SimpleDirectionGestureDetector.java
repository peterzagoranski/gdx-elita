package com.badlogic.gdx.elita.input;

import com.badlogic.gdx.input.GestureDetector;

public class SimpleDirectionGestureDetector extends GestureDetector {
    public interface DirectionListener {

        void onSwipeLeft();

        void onSwipeRight();

        void onSwipeUp();

        void onSwipeDown();
    }

    public SimpleDirectionGestureDetector(DirectionListener directionListener) {
        super(new DirectionGestureListener(directionListener));
    }

    private static class DirectionGestureListener extends GestureAdapter{
        DirectionGestureListener(DirectionListener directionListener){
            this.directionListener = directionListener;
        }

        @Override
        public boolean fling(float velocityX, float velocityY, int button) {
            if(Math.abs(velocityX)>Math.abs(velocityY)){
                if(velocityX>0){
                    directionListener.onSwipeRight();
                }else{
                    directionListener.onSwipeLeft();
                }
            }else{
                if(velocityY>0){
                    directionListener.onSwipeDown();
                }else{
                    directionListener.onSwipeUp();
                }
            }
            return super.fling(velocityX, velocityY, button);
        }

        private DirectionListener directionListener;
    }
}
