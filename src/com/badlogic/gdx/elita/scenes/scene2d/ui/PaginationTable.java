package com.badlogic.gdx.elita.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;

public class PaginationTable extends Table {
    private final TextureRegion mDefaultTextureRegion;
    private final TextureRegion mSelectedTextureRegion;

    public PaginationTable(final TextureRegion pDefaultTextureRegion, final TextureRegion pSelectedTextureRegion, final Skin pSkin) {
        super(pSkin);
        this.mDefaultTextureRegion = pDefaultTextureRegion;
        this.mSelectedTextureRegion = pSelectedTextureRegion;
    }

    public void setPage(final int pIndex) {
        final Array<Cell> cells = this.getCells();
        for(int i=0; i < cells.size; i++) {
            Cell cell = cells.get(i);
            cell.setActor(new Image(pIndex == i ? this.mSelectedTextureRegion : this.mDefaultTextureRegion));
        }
    }
}
