package main.gui;

import java.io.File;
import java.util.HashMap;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class GameSound {

  public static GameSound instance;

  public static final String BACKGROUND = "res/data/sound/backgroundSound.wav";
  public static final String BOMBPLACED = "res/data/sound/bombPlaced.wav";
  public static final String BOMBEXPLOSED = "res/data/sound/bomdExplosed.wav";
  public static final String CLICK = "res/data/sound/click.wav";
  public static final String DEATH = "res/data/sound/death.wav";
  public static final String GETITEM = "res/data/sound/getItem.wav";
  public static final String LOSE = "res/data/sound/lose.mid";
  public static final String WALK = "res/data/sound/walk.wav";
  public static final String WIN = "res/data/sound/win.wav";

  Clip clip;
  AudioInputStream audioInputStream;

  private final HashMap<String, Clip> audioMap;

  public GameSound() {
    audioMap = new HashMap<>();
    loadAllAudio();
  }

  public static GameSound getInstance() {
    if (instance == null) {
      instance = new GameSound();
    }
    return instance;
  }

  public void loadAllAudio() {
    putAudio(BACKGROUND);
    putAudio(BOMBPLACED);
    putAudio(BOMBEXPLOSED);
    putAudio(CLICK);
    putAudio(DEATH);
    putAudio(GETITEM);
    putAudio(LOSE);
    putAudio(WALK);
    putAudio(WIN);
  }

  public void stop() {
    getAudio(BOMBPLACED).stop();
    getAudio(BOMBEXPLOSED).stop();
    getAudio(CLICK).stop();
    getAudio(DEATH).stop();
    getAudio(GETITEM).stop();
    getAudio(LOSE).stop();
    getAudio(WALK).stop();
    getAudio(WIN).stop();
  }

  public void putAudio(String name) {
    try {
      audioInputStream =
          AudioSystem.getAudioInputStream(new File(name).getAbsoluteFile());
      clip = AudioSystem.getClip();
      clip.open(audioInputStream);
      audioMap.put(name, clip);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Clip getAudio(String name) {
    return audioMap.get(name);
  }

  public void play(String name) {
    if (name.equals(WALK) && audioMap.get(name).isRunning()) return;
    try {
      audioMap.get(name).stop();
      if (name.equals(BACKGROUND)) {
        audioMap.get(name).loop(Clip.LOOP_CONTINUOUSLY);
      }
      audioMap.get(name).setMicrosecondPosition(0);
      audioMap.get(name).start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
