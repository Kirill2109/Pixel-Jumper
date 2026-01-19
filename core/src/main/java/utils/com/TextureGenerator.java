package utils.com;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;

public class TextureGenerator {
    private static TextureGenerator instance;

    private TextureGenerator() {}

    public static TextureGenerator getInstance() {
        if (instance == null) {
            instance = new TextureGenerator();
        }
        return instance;
    }

    public Texture createPlayerTexture() {
        Pixmap pixmap = new Pixmap(50, 50, Pixmap.Format.RGBA8888);
        pixmap.setColor(0, 0, 0, 0);
        pixmap.fill();
        pixmap.setColor(0, 0.4f, 1, 1);
        pixmap.fillRectangle(10, 10, 30, 30);
        pixmap.setColor(Color.WHITE);
        pixmap.fillRectangle(18, 20, 4, 4);
        pixmap.fillRectangle(28, 20, 4, 4);
        pixmap.setColor(Color.BLACK);
        pixmap.fillRectangle(19, 21, 2, 2);
        pixmap.fillRectangle(29, 21, 2, 2);
        pixmap.setColor(Color.RED);
        pixmap.drawCircle(18, 30, 14);
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }

    public Texture createPlatformTexture() {
        Pixmap pixmap = new Pixmap(200, 40, Pixmap.Format.RGBA8888);
        pixmap.setColor(0, 0.7f, 0, 1);
        pixmap.fillRectangle(0, 0, 200, 35);
        pixmap.setColor(0.4f, 0.9f, 0.4f, 1);
        pixmap.fillRectangle(0, 0, 200, 10);
        pixmap.setColor(0, 0.6f, 0, 1);
        for (int i = 0; i < 200; i += 15) {
            pixmap.drawLine(i, 5, i + 7, 0);
        }
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }

    public Texture createCoinTexture() {
        Pixmap pixmap = new Pixmap(30, 30, Pixmap.Format.RGBA8888);
        pixmap.setColor(0, 0, 0, 0);
        pixmap.fill();
        pixmap.setColor(1, 0.84f, 0, 1);
        pixmap.fillCircle(15, 15, 14);
        pixmap.setColor(1, 1, 0.8f, 0.8f);
        pixmap.fillCircle(20, 10, 4);
        pixmap.fillCircle(10, 20, 3);
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }

    public Texture createEnemyTexture() {
        Pixmap pixmap = new Pixmap(40, 40, Pixmap.Format.RGBA8888);
        pixmap.setColor(0, 0, 0, 0);
        pixmap.fill();
        pixmap.setColor(0.9f, 0.1f, 0.1f, 1);
        pixmap.fillCircle(20, 20, 18);
        pixmap.setColor(Color.WHITE);
        pixmap.fillCircle(14, 24, 4);
        pixmap.fillCircle(26, 24, 4);
        pixmap.setColor(Color.BLACK);
        pixmap.fillCircle(14, 24, 2);
        pixmap.fillCircle(26, 24, 2);
        pixmap.setColor(0.5f, 0, 0, 1);
        pixmap.fillRectangle(12, 12, 16, 3);
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }

    public Texture createBackgroundTexture(int width, int height) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        for (int y = 0; y < height; y++) {
            float progress = (float) y / height;
            float r = 0.53f - progress * 0.1f;
            float g = 0.81f - progress * 0.1f;
            float b = 0.92f - progress * 0.1f;
            pixmap.setColor(r, g, b, 1);
            pixmap.drawLine(0, y, width - 1, y);
        }
        pixmap.setColor(1, 1, 0, 0.9f);
        pixmap.fillCircle(width - 80, 70, 40);
        pixmap.setColor(1, 1, 1, 0.8f);
        drawCloud(pixmap, 100, 100, 60);
        drawCloud(pixmap, 300, 150, 80);
        drawCloud(pixmap, 500, 80, 70);
        pixmap.setColor(0, 0.5f, 0, 1);
        pixmap.fillRectangle(0, height - 60, width, 60);
        pixmap.setColor(0, 0.4f, 0, 1);
        for (int x = 0; x < width; x += 3) {
            int grassHeight = 5 + (int)(Math.random() * 15);
            pixmap.drawLine(x, height - 60, x, height - 60 + grassHeight);
        }
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }

    private void drawCloud(Pixmap pixmap, int x, int y, int size) {
        int cloudSize = size / 2;
        pixmap.fillCircle(x, y, cloudSize);
        pixmap.fillCircle(x + cloudSize/2, y - cloudSize/3, cloudSize);
        pixmap.fillCircle(x + cloudSize, y, cloudSize);
    }
}
