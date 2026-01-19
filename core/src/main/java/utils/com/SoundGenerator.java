package utils.com;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;

public class SoundGenerator implements Disposable {
    private static SoundGenerator instance;
    private Sound jumpSound;
    private Sound coinSound;
    private Sound hitSound;
    private Music backgroundMusic;

    private SoundGenerator() {}

    public static SoundGenerator getInstance() {
        if (instance == null) {
            instance = new SoundGenerator();
        }
        return instance;
    }

    public void loadSounds() {
        try {
            jumpSound = Gdx.audio.newSound(Gdx.files.internal("sounds/jump.wav"));
            coinSound = Gdx.audio.newSound(Gdx.files.internal("sounds/coin.wav"));
            hitSound = Gdx.audio.newSound(Gdx.files.internal("sounds/hit.wav"));
            backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
            if (backgroundMusic != null) {
                backgroundMusic.setLooping(true);
                backgroundMusic.setVolume(0.5f);
            }
        } catch (Exception e) {
            jumpSound = new SilentSound();
            coinSound = new SilentSound();
            hitSound = new SilentSound();
        }
    }

    public Sound getJumpSound() {
        return jumpSound;
    }

    public Sound getCoinSound() {
        return coinSound;
    }

    public Sound getHitSound() {
        return hitSound;
    }

    public Music getBackgroundMusic() {
        return backgroundMusic;
    }

    public void playJumpSound() {
        if (jumpSound != null && GameManager.getInstance().isSoundEnabled()) {
            jumpSound.play(0.5f);
        }
    }

    public void playCoinSound() {
        if (coinSound != null && GameManager.getInstance().isSoundEnabled()) {
            coinSound.play(0.7f);
        }
    }

    public void playHitSound() {
        if (hitSound != null && GameManager.getInstance().isSoundEnabled()) {
            hitSound.play(0.6f);
        }
    }

    public void startMusic() {
        if (backgroundMusic != null && GameManager.getInstance().isSoundEnabled()) {
            backgroundMusic.play();
        }
    }

    public void stopMusic() {
        if (backgroundMusic != null) {
            backgroundMusic.stop();
        }
    }

    public void setMusicVolume(float volume) {
        if (backgroundMusic != null) {
            backgroundMusic.setVolume(volume);
        }
    }

    @Override
    public void dispose() {
        if (jumpSound != null) jumpSound.dispose();
        if (coinSound != null) coinSound.dispose();
        if (hitSound != null) hitSound.dispose();
        if (backgroundMusic != null) backgroundMusic.dispose();
    }

    private static class SilentSound implements Sound {
        @Override public long play() { return 0; }
        @Override public long play(float volume) { return 0; }
        @Override public long play(float volume, float pitch, float pan) { return 0; }
        @Override public long loop() { return 0; }
        @Override public long loop(float volume) { return 0; }
        @Override public long loop(float volume, float pitch, float pan) { return 0; }
        @Override public void stop() {}
        @Override public void pause() {}
        @Override public void resume() {}
        @Override public void dispose() {}
        @Override public void stop(long soundId) {}
        @Override public void pause(long soundId) {}
        @Override public void resume(long soundId) {}
        @Override public void setLooping(long soundId, boolean looping) {}
        @Override public void setPitch(long soundId, float pitch) {}
        @Override public void setVolume(long soundId, float volume) {}
        @Override public void setPan(long soundId, float pan, float volume) {}
    }
}
