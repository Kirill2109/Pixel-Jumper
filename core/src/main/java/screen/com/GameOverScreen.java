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

public class GameOverScreen extends ScreenAdapter {
    private final Main game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Stage stage;
    private int finalScore;
    private int highScore;

    public GameOverScreen(Main game, int score) {
        this.game = game;
        this.finalScore = score;
        this.highScore = GameManager.getInstance().getHighScore();
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
        CustomButton title = new CustomButton("ИГРА ОКОНЧЕНА", "title");
        title.getLabel().setAlignment(Align.center);
        table.add(title).padBottom(30).colspan(2).row();
        CustomButton scoreText = new CustomButton("ВАШ СЧЕТ: " + finalScore, "info");
        scoreText.getLabel().setAlignment(Align.center);
        table.add(scoreText).padBottom(20).colspan(2).row();
        CustomButton highScoreText = new CustomButton("РЕКОРД: " + highScore, "info");
        highScoreText.getLabel().setAlignment(Align.center);
        table.add(highScoreText).padBottom(40).colspan(2).row();
        if (finalScore == highScore && finalScore > 0) {
            CustomButton newRecord = new CustomButton("НОВЫЙ РЕКОРД!", "highlight");
            newRecord.getLabel().setAlignment(Align.center);
            table.add(newRecord).padBottom(30).colspan(2).row();
        }
        CustomButton restartButton = new CustomButton("ИГРАТЬ СНОВА", "normal");
        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
            }
        });
        table.add(restartButton).width(250).height(60).padBottom(20).colspan(2).row();
        CustomButton menuButton = new CustomButton("В МЕНЮ", "normal");
        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
            }
        });
        table.add(menuButton).width(250).height(60).colspan(2);
        Gdx.input.setInputProcessor(stage);
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
