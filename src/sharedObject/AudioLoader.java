package sharedObject;

import javafx.scene.media.AudioClip;

public class AudioLoader {

	public static AudioClip Mouse_Click = new AudioClip(ClassLoader.getSystemResource("audio/mouse_click2.wav").toString());
	public static AudioClip Mouse_Click1 = new AudioClip(ClassLoader.getSystemResource("audio/mouse_click.mp3").toString());
	public static AudioClip Winning_Coin_Sound = new AudioClip(ClassLoader.getSystemResource("audio/winning-coin.wav").toString());
	public static AudioClip Jumping_Sound = new AudioClip(ClassLoader.getSystemResource("audio/jumping.wav").toString());
	public static AudioClip Pick_Up_Item_Sound = new AudioClip(ClassLoader.getSystemResource("audio/picking-up-item.wav").toString());
//	public static AudioClip Footstep_Sound = new AudioClip(ClassLoader.getSystemResource("audio/cutting.mp3").toString());
//	public static AudioClip Hurting_Sound = new AudioClip(ClassLoader.getSystemResource("audio/cutting.mp3").toString());
//	public static AudioClip Hitting_Sound = new AudioClip(ClassLoader.getSystemResource("audio/cutting.mp3").toString());
//	public static AudioClip Jumping_Sound = new AudioClip(ClassLoader.getSystemResource("audio/frying.mp3").toString());
//	public static AudioClip Congrats_Sound = new AudioClip(ClassLoader.getSystemResource("audio/congratulation.mp3").toString());
//	
	public static AudioClip Entrance_Theme_Song = new AudioClip(ClassLoader.getSystemResource("audio/entrance_music.mp3").toString());
//	public static AudioClip Game_Screen = new AudioClip(ClassLoader.getSystemResource("audio/GameScreen.mp3").toString());
//	public static AudioClip End_Screen = new AudioClip(ClassLoader.getSystemResource("audio/EndScreen.mp3").toString());
	
	public static void setMute(boolean isMute) {
		if(isMute) {
			AudioLoader.setVolume(0);
		}
		else {
			AudioLoader.setVolume(0.5);
		}
	}
	
	public static void setVolume(double d) {
		Mouse_Click.setVolume(d);
		Entrance_Theme_Song.setVolume(d);
	}
}
