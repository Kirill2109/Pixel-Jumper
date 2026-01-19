package screen.com;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import pixeljumper.com.Main;
import ui.com.CustomButton;
import utils.com.AssetManager;
import utils.com.Constants;
import utils.com.GameManager;

import static utils.com.Constants.WORLD_HEIGHT;

public class MenuScreen extends ScreenAdapter {
    private final Main game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Stage stage;

    public MenuScreen(Main game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, Constants.WORLD_WIDTH, WORLD_HEIGHT);
        this.batch = new SpriteBatch();
        this.stage = new Stage(new ScreenViewport());
        createUI();
    }

    private void createUI() {
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        CustomButton title = new CustomButton("PIXEL JUMPER", "title");
        title.getLabel().setAlignment(Align.center);
        table.add(title).padBottom(50).colspan(2).row();
        CustomButton playButton = new CustomButton("ИГРАТЬ", "normal");
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
            }
        });
        table.add(playButton).width(200).height(60).padBottom(20).colspan(2).row();
        CustomButton settingsButton = new CustomButton("НАСТРОЙКИ", "normal");
        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new SettingsScreen(game));
            }
        });
        table.add(settingsButton).width(200).height(60).padBottom(20).colspan(2).row();
        CustomButton scoresButton = new CustomButton("РЕКОРД: " + GameManager.getInstance().getHighScore(), "normal");
        scoresButton.setDisabled(true);
        table.add(scoresButton).width(200).height(60).padBottom(20).colspan(2).row();
        CustomButton exitButton = new CustomButton("ВЫХОД", "normal");
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        table.add(exitButton).width(200).height(60).colspan(2);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(AssetManager.getInstance().getBackgroundTexture(),
            0, 0, Constants.WORLD_WIDTH, WORLD_HEIGHT);
        batch.end();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
    }
}
