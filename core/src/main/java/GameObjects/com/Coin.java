package GameObjects.com;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import utils.com.AssetManager;

public class Coin extends GameObject {
    public Coin(float x, float y) {
        super(x, y, 30, 30);
    }

    @Override
    public void update(float delta) {}

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(AssetManager.getInstance().getCoinTexture(),
            position.x, position.y, width, height);
    }
}
