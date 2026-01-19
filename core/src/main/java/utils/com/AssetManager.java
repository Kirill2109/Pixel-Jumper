package utils.com;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

public class AssetManager implements Disposable {
    private static AssetManager instance;
    private Texture playerTexture;
    private Texture platformTexture;
    private Texture coinTexture;
    private Texture enemyTexture;
    private Texture backgroundTexture;

    private AssetManager() {}

    public static AssetManager getInstance() {
        if (instance == null) {
            instance = new AssetManager();
        }
        return instance;
    }

    public void loadAssets() {
        TextureGenerator textureGen = TextureGenerator.getInstance();
        playerTexture = textureGen.createPlayerTexture();
        platformTexture = textureGen.createPlatformTexture();
        coinTexture = textureGen.createCoinTexture();
        enemyTexture = textureGen.createEnemyTexture();
        backgroundTexture = textureGen.createBackgroundTexture(800, 480);
        SoundGenerator.getInstance().loadSounds();
    }

    public Texture getPlayerTexture() { return playerTexture; }
    public Texture getPlatformTexture() { return platformTexture; }
    public Texture getCoinTexture() { return coinTexture; }
    public Texture getEnemyTexture() { return enemyTexture; }
    public Texture getBackgroundTexture() { return backgroundTexture; }

    @Override
    public void dispose() {
        if (playerTexture != null) playerTexture.dispose();
        if (platformTexture != null) platformTexture.dispose();
        if (coinTexture != null) coinTexture.dispose();
        if (enemyTexture != null) enemyTexture.dispose();
        if (backgroundTexture != null) backgroundTexture.dispose();
        SoundGenerator.getInstance().dispose();
    }
}
