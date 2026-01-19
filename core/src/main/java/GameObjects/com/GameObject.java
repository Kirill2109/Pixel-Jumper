package GameObjects.com;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {
    protected Vector2 position;
    protected Rectangle bounds;
    protected float width;
    protected float height;

    public GameObject(float x, float y, float width, float height) {
        this.position = new Vector2(x, y);
        this.width = width;
        this.height = height;
        this.bounds = new Rectangle(x, y, width, height);
    }

    public abstract void update(float delta);
    public abstract void render(SpriteBatch batch);

    public Vector2 getPosition() { return position; }
    public void setPosition(float x, float y) {
        position.set(x, y);
        bounds.setPosition(x, y);
    }
    public Rectangle getBounds() { return bounds; }
    public float getWidth() { return width; }
    public float getHeight() { return height; }
}
