package com.Automation.utilities;

import java.io.File;
import static org.monte.media.AudioFormatKeys.EncodingKey;
import static org.monte.media.AudioFormatKeys.FrameRateKey;
import static org.monte.media.AudioFormatKeys.KeyFrameIntervalKey;
import static org.monte.media.AudioFormatKeys.MediaType;
import static org.monte.media.AudioFormatKeys.MediaTypeKey;
import static org.monte.media.AudioFormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.*;

import org.monte.media.math.Rational;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.monte.media.Format;
import org.monte.media.FormatKeys;
import org.monte.screenrecorder.ScreenRecorder;

import java.awt.*;



/**
 * Created by Mounir Youssef
 * 
 */
public class VideoRecord {
	private static ScreenRecorder screenRecorder;
	 private static final Logger logger = LogManager.getLogger(VideoRecord.class);

	 public static void startRecording(String fileName) throws Exception {
		
		if (ConfigurationProperties.INSTANCE.getVideoRecord() !='t'){
			logger.info("**  Video recorder is turned OFF and no videos will be available **");
			return;
		}
		logger.info("**  Video recorder just started **");
		File file = new File(ConfigurationProperties.INSTANCE.getVideoRecordPath());
						
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;

		Rectangle captureSize = new Rectangle(0, 0, width, height);

		GraphicsConfiguration gc = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();

		screenRecorder = new SpecializedScreenRecorder(
				gc,
				captureSize,
				new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, FormatKeys.MIME_AVI),
				new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey,
						ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
						CompressorNameKey,
						ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24,
						FrameRateKey, Rational.valueOf(15), QualityKey, 1.0f,
						KeyFrameIntervalKey, 15 * 60), new Format(MediaTypeKey,
						MediaType.VIDEO, EncodingKey, "black", FrameRateKey,
						Rational.valueOf(30)), null, file, fileName);
		 Thread.sleep(4000); 
		screenRecorder.start();

	}

	public static void stopRecording() throws Exception {
		if (ConfigurationProperties.INSTANCE.getVideoRecord() !='t'){
			logger.info("**  Video recorder is turned OFF, Stop recording will not fired**");
			return;
		}
		else{
			screenRecorder.stop();
			logger.info("**  Video recorder just Completed **");
		}
	}

}