package utils.com;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class GameManager {
    private static GameManager instance;
    private Preferences prefs;
    private boolean soundEnabled;
    private boolean musicEnabled;
    private int highScore;

    private GameManager() {
        prefs = Gdx.app.getPreferences("PixelJumperPrefs");
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public void loadSettings() {
        soundEnabled = prefs.getBoolean("soundEnabled", true);
        musicEnabled = prefs.getBoolean("musicEnabled", true);
        highScore = prefs.getInteger("highScore", 0);
    }

    public void saveSettings() {
        prefs.putBoolean("soundEnabled", soundEnabled);
        prefs.putBoolean("musicEnabled", musicEnabled);
        prefs.flush();
        if (musicEnabled) {
            SoundGenerator.getInstance().startMusic();
        } else {
            SoundGenerator.getInstance().stopMusic();
        }
    }

    public void saveHighScore(int score) {
        if (score > highScore) {
            highScore = score;
            prefs.putInteger("highScore", highScore);
            prefs.flush();
        }
    }

    public boolean isSoundEnabled() { return soundEnabled; }
    public boolean isMusicEnabled() { return musicEnabled; }
    public int getHighScore() { return highScore; }

    public void setSoundEnabled(boolean enabled) {
        this.soundEnabled = enabled;
        saveSettings();
    }

    public void setMusicEnabled(boolean enabled) {
        this.musicEnabled = enabled;
        saveSettings();
    }
}
