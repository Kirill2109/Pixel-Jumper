package ui.com;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class Hud {
    private Stage stage;
    private Table table;
    private Label scoreLabel;
    private Label healthLabel;
    private Label timeLabel;
    private Label coinsLabel;
    private int score;
    private int health;
    private int coins;
    private float time;

    public Hud() {
        stage = new Stage();
        table = new Table();
        table.top();
        table.setFillParent(true);

        scoreLabel = new Label("Счет: 0", new Label.LabelStyle(
        ));
        healthLabel = new Label("Здоровье: 100", new Label.LabelStyle(
        ));
        timeLabel = new Label("Время: 0", new Label.LabelStyle(
        ));
        coinsLabel = new Label("Монеты: 0", new Label.LabelStyle(
        ));

        table.add(scoreLabel).expandX().padTop(10);
        table.add(healthLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.add(coinsLabel).expandX().padTop(10);

        stage.addActor(table);
    }

    public void update(float delta, int score, int health, int coins, float time) {
        this.score = score;
        this.health = health;
        this.coins = coins;
        this.time = time;

        scoreLabel.setText("Счет: " + score);
        healthLabel.setText("Здоровье: " + health);

        if (health < 30) {
            healthLabel.setColor(Color.RED);
        } else if (health < 60) {
            healthLabel.setColor(Color.YELLOW);
        } else {
            healthLabel.setColor(Color.GREEN);
        }

        coinsLabel.setText("Монеты: " + coins);

        int minutes = (int)(time / 60);
        int seconds = (int)(time % 60);
        timeLabel.setText(String.format("Время: %02d:%02d", minutes, seconds));
    }

    public void render(SpriteBatch batch, int score, int health, int gameTime) {
        stage.draw();
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void dispose() {
        stage.dispose();
    }
}
