package GameObjects.com;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import utils.com.AssetManager;
import utils.com.Constants;
import utils.com.SoundGenerator;

public class Player extends GameObject {
    private Vector2 velocity;
    private boolean isJumping;
    private int score;
    private int health;

    public Player(float x, float y) {
        super(x, y, 50, 50);
        this.velocity = new Vector2(0, 0);
        this.isJumping = false;
        this.score = 0;
        this.health = 100;
    }

    @Override
    public void update(float delta) {
        position.y += velocity.y * delta;
        position.x += velocity.x * delta;
        if (position.y > 0) {
            velocity.y -= Constants.GRAVITY * delta;
        } else {
            position.y = 0;
            velocity.y = 0;
            isJumping = false;
        }
        bounds.setPosition(position);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(AssetManager.getInstance().getPlayerTexture(),
            position.x, position.y, width, height);
    }

    public void jump() {
        if (!isJumping) {
            velocity.y = Constants.JUMP_FORCE;
            isJumping = true;
            SoundGenerator.getInstance().playJumpSound();
        }
    }

    public void moveLeft() {
        velocity.x = -Constants.PLAYER_SPEED;
    }

    public void moveRight() {
        velocity.x = Constants.PLAYER_SPEED;
    }

    public void stopMoving() {
        velocity.x = 0;
    }

    public void addScore(int points) {
        this.score += points;
        SoundGenerator.getInstance().playCoinSound();
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        SoundGenerator.getInstance().playHitSound();
    }

    public int getScore() { return score; }
    public int getHealth() { return health; }
    public Vector2 getVelocity() { return velocity; }
    public boolean isJumping() { return isJumping; }
}
