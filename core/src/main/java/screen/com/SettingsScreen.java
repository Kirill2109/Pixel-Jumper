package screen.com;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
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
import utils.com.Constants;
import utils.com.GameManager;

public class SettingsScreen extends ScreenAdapter {
    private final Main game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Stage stage;
    private CustomButton soundButton;
    private CustomButton musicButton;

    public SettingsScreen(Main game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        this.batch = new SpriteBatch();
        this.stage = new Stage(new ScreenViewport());
        createUI();
    }

    private void createUI() {
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        CustomButton title = new CustomButton("НАСТРОЙКИ", "title");
        title.getLabel().setAlignment(Align.center);
        table.add(title).padBottom(50).colspan(2).row();
        soundButton = new CustomButton("ЗВУК: ВКЛ", "toggle");
        soundButton.setChecked(GameManager.getInstance().isSoundEnabled());
        updateSoundButtonText();
        soundButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                boolean enabled = !GameManager.getInstance().isSoundEnabled();
                GameManager.getInstance().setSoundEnabled(enabled);
                soundButton.setChecked(enabled);
                updateSoundButtonText();
            }
        });
        table.add(soundButton).width(250).height(50).padBottom(20).colspan(2).row();
        musicButton = new CustomButton("МУЗЫКА: ВКЛ", "toggle");
        musicButton.setChecked(GameManager.getInstance().isMusicEnabled());
        updateMusicButtonText();
        musicButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                boolean enabled = !GameManager.getInstance().isMusicEnabled();
                GameManager.getInstance().setMusicEnabled(enabled);
                musicButton.setChecked(enabled);
                updateMusicButtonText();
            }
        });
        table.add(musicButton).width(250).height(50).padBottom(20).colspan(2).row();
        CustomButton backButton = new CustomButton("НАЗАД", "normal");
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
            }
        });
        table.add(backButton).width(200).height(60).colspan(2);
        Gdx.input.setInputProcessor(stage);
    }

    private void updateSoundButtonText() {
        boolean enabled = GameManager.getInstance().isSoundEnabled();
        soundButton.setText("ЗВУК: " + (enabled ? "ВКЛ" : "ВЫКЛ"));
    }

    private void updateMusicButtonText() {
        boolean enabled = GameManager.getInstance().isMusicEnabled();
        musicButton.setText("МУЗЫКА: " + (enabled));
    }
}
