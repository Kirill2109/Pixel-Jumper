package pixeljumper.com.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import pixeljumper.com.Main;
import utils.com.SoundGenerator;
import utils.com.TextureGenerator;

class StarterHelper {
    public static void startDesktop() {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Pixel Jumper");
        config.setWindowedMode(800, 480);
        config.setResizable(false);
        config.setForegroundFPS(60);
        config.setIdleFPS(30);
        checkResources();
        new Lwjgl3Application(new Main(), config);
    }

    public static <CharacterName> void checkResources() {
        System.out.println("=== Pixel Jumper Resource Check ===");
        try {
            TextureGenerator.getInstance().createPlayerTexture();
            System.out.println("✓ TextureGenerator готов");
        } catch (Exception e) {
            System.out.println("✗ Ошибка TextureGenerator: " + e.getMessage());
        }
        try {
            SoundGenerator.getInstance().loadSounds();
            System.out.println("✓ SoundGenerator готов");
        } catch (Exception e) {
            System.out.println("✗ Ошибка SoundGenerator: " + e.getMessage());
        }
        try {
            CharacterName FontGenerator = null;
            FontGenerator.getClass();
            System.out.println("✓ FontGenerator готов");
        } catch (Exception e) {
            System.out.println("✗ Ошибка FontGenerator: " + e.getMessage());
        }
        System.out.println("=== Проверка завершена ===");
    }

    public static void printGameInfo() {
        System.out.println("\n=== Pixel Jumper ===");
        System.out.println("Версия: 1.0");
        System.out.println("Тип: Платформер");
        System.out.println("Особенности:");
        System.out.println("  • Все ресурсы генерируются кодом");
        System.out.println("  • Не требует внешних файлов");
        System.out.println("  • Полностью самодостаточный");
        System.out.println("Управление:");
        System.out.println("  • Левая часть экрана - движение влево");
        System.out.println("  • Правая часть экрана - движение вправо");
        System.out.println("  • Центр экрана - прыжок");
        System.out.println("  • Два касания - пауза");
        System.out.println("========================\n");
    }
}
