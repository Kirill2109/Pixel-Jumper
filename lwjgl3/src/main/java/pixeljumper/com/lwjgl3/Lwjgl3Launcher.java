package pixeljumper.com.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import pixeljumper.com.Main;

public class Lwjgl3Launcher {
    public static void main(String[] args) {
        StarterHelper.printGameInfo();
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Pixel Jumper");
        config.setWindowedMode(800, 480);
        config.setResizable(false);
        config.setForegroundFPS(60);
        config.setIdleFPS(30);
        config.setWindowIcon(
            "libgdx128.png",
            "libgdx64.png",
            "libgdx32.png",
            "libgdx16.png"
        );
        StarterHelper.checkResources();
        try {
            new Lwjgl3Application(new Main(), config);
        } catch (Exception e) {
            System.err.println("Ошибка запуска игры:");
            e.printStackTrace();
            System.out.println("\nВозможные решения:");
            System.out.println("1. Убедитесь что Java 8+ установлена");
            System.out.println("2. Проверьте наличие библиотек LibGDX");
            System.out.println("3. Попробуйте очистить и пересобрать проект");
        }
    }
}
