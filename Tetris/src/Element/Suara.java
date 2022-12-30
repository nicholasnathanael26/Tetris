/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Element;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

/**
 *
 * @author cynthia
 */
public class Suara {
    public void SuaraTema() {
        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM;

        ContinuousAudioDataStream loop = null;

        try {
            InputStream suara = new FileInputStream("D:\\ITHB\\Semester 3\\Prak PBO\\TUBES\\Lounge Game2.wav");
            BGM = new AudioStream(suara);
            AudioPlayer.player.start(BGM);
        } catch (FileNotFoundException e) {
            System.out.print(e.toString());
        } catch (IOException error) {
            System.out.print(error.toString());
        }
        MGP.start(loop);
    }

    public void suaraBlock() {
        AudioPlayer MGP = AudioPlayer.player;
        AudioStream SFX;

        ContinuousAudioDataStream loop = null;

        try {
            InputStream suara = new FileInputStream("D:\\ITHB\\Semester 3\\Prak PBO\\TUBES\\button_push.wav");
            SFX = new AudioStream(suara);
            AudioPlayer.player.start(SFX);
        } catch (FileNotFoundException e) {
            System.out.print(e.toString());
        } catch (IOException error) {
            System.out.print(error.toString());
        }
        MGP.start(loop);
    }
}
