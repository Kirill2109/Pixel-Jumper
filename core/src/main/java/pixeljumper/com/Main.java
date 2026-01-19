package pixeljumper.com;

import com.badlogic.gdx.Game;
import screen.com.MenuScreen;
import utils.com.AssetManager;
import utils.com.GameManager;

public class Main extends Game {
    @Override
    public void create() {
        try {
            AssetManager.getInstance().wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        GameManager.getInstance().loadSettings();
        setScreen(new MenuScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetManager.getInstance();
    }
}
