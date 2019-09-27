package com.lhy.consumer;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsumerApplicationTests {

    @Test
    public void contextLoads() {
        //File mFile = new File("I:\\questionbank\\nulladuio\\content5720.mp3");
        File mFile = new File("I:\\questionbank\\aduio\\explain6355.mp3");
        long fileLength = mFile.length();
        System.out.println("fileLength:"+fileLength);
        try {
            MP3File f = (MP3File) AudioFileIO.read(mFile);
            MP3AudioHeader audioHeader = (MP3AudioHeader)f.getAudioHeader();
            // 单位为秒
            System.out.println("TrackLength:"+audioHeader.getTrackLength());
        } catch(Exception e) {
            e.printStackTrace();
        }


    }

}
