package com.badlogic.gdx.elita;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Assets {

    private Assets() {
    }

    public static void dispose() {
        manager.clear();
    }

    public static <T> T get (AssetDescriptor<T> descriptor) {

        if (!manager.isLoaded(descriptor.fileName, descriptor.type)) {

            manager.load(descriptor);

            manager.finishLoadingAsset(descriptor.fileName);

            if (descriptor.type.isInstance(BitmapFont.class)) {
                final BitmapFont font = (BitmapFont)manager.get(descriptor);
                font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            }
        }

        return manager.get(descriptor);
    }

    private static final AssetManager manager = new AssetManager();
}
