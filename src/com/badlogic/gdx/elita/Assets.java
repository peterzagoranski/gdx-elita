package com.badlogic.gdx.elita;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AssetLoader;
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

            if (BitmapFont.class == descriptor.type) {
                final BitmapFont font = (BitmapFont)manager.get(descriptor);
                font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            }
        }

        return manager.get(descriptor);
    }
    
    public static <T, P extends AssetLoaderParameters<T>> void setLoader(Class<T> type, AssetLoader<T, P> loader) {
        manager.setLoader(type, loader);
    }

    private static final AssetManager manager = new AssetManager();
}
