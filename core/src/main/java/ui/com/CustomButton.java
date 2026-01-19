package ui.com;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;

public class CustomButton extends Button {
    private Label label;
    private String style;
    private boolean isChecked;

    public CustomButton(String text, String style) {
        this.style = style;
        this.isChecked = false;
        Label.LabelStyle labelStyle = new Label.LabelStyle();

        label = new Label(text, labelStyle);
        label.setAlignment(Align.center);
        Skin skin = new Skin();
        ButtonStyle buttonStyle = new ButtonStyle();
        if (style.equals("normal") || style.equals("toggle")) {
            buttonStyle.up = new SimpleDrawable(new Color(0.2f, 0.4f, 0.8f, 0.8f));
            buttonStyle.down = new SimpleDrawable(new Color(0.1f, 0.3f, 0.7f, 0.9f));
            buttonStyle.over = new SimpleDrawable(new Color(0.3f, 0.5f, 0.9f, 0.8f));
            buttonStyle.disabled = new SimpleDrawable(new Color(0.5f, 0.5f, 0.5f, 0.5f));
        }
        else if (style.equals("title") || style.equals("info") || style.equals("highlight")) {
            buttonStyle.up = new EmptyDrawable();
            buttonStyle.down = new EmptyDrawable();
            buttonStyle.over = new EmptyDrawable();
            buttonStyle.disabled = new EmptyDrawable();
        }
        setStyle(buttonStyle);
        add(label).expand().fill();
        setTransform(true);
    }

    public void setText(String text) {
        label.setText(text);
    }

    public Label getLabel() {
        return label;
    }

    public void setChecked(boolean checked) {
        this.isChecked = checked;
        if (isChecked && style.equals("toggle")) {
            label.setColor(Color.GREEN);
        } else if (style.equals("toggle")) {
            label.setColor(Color.RED);
        }
    }

    public boolean isChecked() {
        return isChecked;
    }

    private static class SimpleDrawable implements Drawable, ui.com.SimpleDrawable {
        private Color color;
        public SimpleDrawable(Color color) {
            this.color = color;
        }
        public void draw(SpriteBatch batch, float x, float y, float width, float height) {
            ShapeRenderer shapeRenderer = new ShapeRenderer();
            batch.end();
            shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(color);
            shapeRenderer.rect(x, y, width, height);
            shapeRenderer.end();
            batch.begin();
        }

        @Override
        public void draw(Batch batch, float v, float v1, float v2, float v3) {

        }

        @Override public float getLeftWidth() { return 0; }
        @Override public void setLeftWidth(float leftWidth) { }
        @Override public float getRightWidth() { return 0; }
        @Override public void setRightWidth(float rightWidth) { }
        @Override public float getTopHeight() { return 0; }
        @Override public void setTopHeight(float topHeight) { }
        @Override public float getBottomHeight() { return 0; }
        @Override public void setBottomHeight(float bottomHeight) { }
        @Override public float getMinWidth() { return 0; }
        @Override public void setMinWidth(float minWidth) { }
        @Override public float getMinHeight() { return 0; }
        @Override public void setMinHeight(float minHeight) { }
    }

    private static class EmptyDrawable implements Drawable {
        public void draw(SpriteBatch batch, float x, float y, float width, float height) {
        }

        @Override
        public void draw(Batch batch, float v, float v1, float v2, float v3) {

        }

        @Override public float getLeftWidth() { return 0; }
        @Override public void setLeftWidth(float leftWidth) { }
        @Override public float getRightWidth() { return 0; }
        @Override public void setRightWidth(float rightWidth) { }
        @Override public float getTopHeight() { return 0; }
        @Override public void setTopHeight(float topHeight) { }
        @Override public float getBottomHeight() { return 0; }
        @Override public void setBottomHeight(float bottomHeight) { }
        @Override public float getMinWidth() { return 0; }
        @Override public void setMinWidth(float minWidth) { }
        @Override public float getMinHeight() { return 0; }
        @Override public void setMinHeight(float minHeight) { }
    }
}
