package screenRecording;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.screenrecording.CanRecordScreen;
import org.apache.commons.codec.binary.Base64;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

public class MobileScreenRecordingUtil {

    public static void startRecordingScreen(AndroidDriver appiumdriver) {
        ((CanRecordScreen)appiumdriver).startRecordingScreen();
    }

    public static void stopRecordingScreen(AndroidDriver appiumdriver, String filePath) {
        String base64String = ((CanRecordScreen) appiumdriver).stopRecordingScreen();
        byte[] data = Base64.decodeBase64(base64String);
        File videoFile = new File(filePath);
        try (FileOutputStream stream = new FileOutputStream(videoFile)) {
            stream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
