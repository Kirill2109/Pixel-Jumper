package screen.com;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import pixeljumper.com.Main;
import GameObjects.com.*;
import utils.com.AssetManager;
import utils.com.Constants;
import utils.com.GameManager;

public class GameScreen extends ScreenAdapter {
    private final Main game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Player player;
    private Array<Platform> platforms;
    private Array<Coin> coins;
    private Array<Enemy> enemies;
    private boolean isPaused;
    private float gameTime;

    public GameScreen(Main game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        this.batch = new SpriteBatch();
        this.isPaused = false;
        this.gameTime = 0;
        initGameObjects();
        utils.com.SoundGenerator.getInstance().startMusic();
    }

    private void initGameObjects() {
        player = new Player(100, 200);
        platforms = new Array<>();
        for (int i = 0; i < 10; i++) {
            platforms.add(new Platform(i * 200, 100));
        }
        coins = new Array<>();
        for (int i = 0; i < 5; i++) {
            coins.add(new Coin(150 + i * 250, 250));
        }
        enemies = new Array<>();
        enemies.add(new Enemy(400, 150));
        enemies.add(new Enemy(600, 150));
    }

    @Override
    public void render(float delta) {
        if (!isPaused) {
            update(delta);
            gameTime += delta;
        }
        Gdx.gl.glClearColor(0.2f, 0.6f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(AssetManager.getInstance().getBackgroundTexture(),
            0, 0, Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        for (Platform platform : platforms) {
            platform.render(batch);
        }
        for (Coin coin : coins) {
            coin.render(batch);
        }
        for (Enemy enemy : enemies) {
            enemy.render(batch);
        }
        player.render(batch);
        batch.end();
        if (player.getHealth() <= 0) {
            GameManager.getInstance().saveHighScore(player.getScore());
            utils.com.SoundGenerator.getInstance().stopMusic();
            game.setScreen(new GameOverScreen(game, player.getScore()));
        }
    }

    private void update(float delta) {
        player.update(delta);
        handleInput();
        checkCollisions();
    }

    private void handleInput() {
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            if (touchPos.x < Constants.WORLD_WIDTH / 3) {
                player.moveLeft();
            }
            else if (touchPos.x > 2 * Constants.WORLD_WIDTH / 3) {
                player.moveRight();
            }
            else {
                player.jump();
            }
        } else {
            player.stopMoving();
        }
        if (Gdx.input.isTouched(0) && Gdx.input.isTouched(1)) {
            isPaused = !isPaused;
        }
    }

    private void checkCollisions() {
        for (Platform platform : platforms) {
            if (player.getBounds().overlaps(platform.getBounds())) {
                if (player.getVelocity().y < 0) {
                    player.setPosition(player.getPosition().x, platform.getPosition().y + platform.getHeight());
                    player.getVelocity().y = 0;
                    player.jump();
                }
            }
        }
        for (Coin coin : coins) {
            if (player.getBounds().overlaps(coin.getBounds())) {
                player.addScore(10);
                coins.removeValue(coin, true);
                break;
            }
        }
        for (Enemy enemy : enemies) {
            if (player.getBounds().overlaps(enemy.getBounds())) {
                player.takeDamage(10);
                enemies.removeValue(enemy, true);
                break;
            }
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
