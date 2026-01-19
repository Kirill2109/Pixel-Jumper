package GameObjects.com;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import utils.com.AssetManager;

public class Platform extends GameObject {
    public Platform(float x, float y) {
        super(x, y, 200, 40);
    }

    @Override
    public void update(float delta) {}

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(AssetManager.getInstance().getPlatformTexture(),
            position.x, position.y, width, height);
    }
}
