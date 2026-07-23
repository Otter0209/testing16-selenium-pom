package utils;

import org.monte.media.Format;
import org.monte.media.FormatKeys;
import org.monte.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class VideoRecorderUtil {
    private static ScreenRecorder recorder;

    public static void start(String testFolderPath) {
//        record chỉ dùng được ở laptop, CI không dùng được
//        -Djava.awt.headless=true
        if (GraphicsEnvironment.isHeadless()) {
            return;
        }

        try {
//            tạo folder /video ở trong folder test case
            File videoFolder = new File(testFolderPath, "video");
            Files.createDirectories(Paths.get(videoFolder.getPath()));

//            setup video => .avi
//            B1: setup môi trường device: desktop, mobile
            GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment()
                    .getDefaultScreenDevice()
                    .getDefaultConfiguration();

//            định dạng video
            Format fileFormat = new Format(
                    MediaTypeKey, FormatKeys.MediaType.FILE,
                    MimeTypeKey, "video/avi"
            );

            Format screenFormat = new Format(
                    MediaTypeKey, FormatKeys.MediaType.VIDEO,
                    EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                    CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                    DepthKey, 24,
                    FrameRateKey, 15,
                    QualityKey, 1.0f,
                    KeyFrameIntervalKey, 15 * 60
            );

            recorder = new ScreenRecorder(
                    gc, // lấy màn hình nào sẽ được quay
                    gc.getBounds(), // lấy vùng quay -> toàn khung màn hình
                    fileFormat, // định dạng video
                    screenFormat,
                    null,
                    null,
                    videoFolder
            );
            recorder.start();
        } catch (IOException | AWTException e) {
            System.out.println("Error starting video recording: " + e.getMessage());
        }

    }
    public static void stop(boolean testFailed){
        if(recorder==null){
            return;
        }
        try{
            recorder.stop();
        }catch (IOException e){
            System.out.println("Error"+e.getMessage());
        }finally {
            recorder = null;
        }
    }

}