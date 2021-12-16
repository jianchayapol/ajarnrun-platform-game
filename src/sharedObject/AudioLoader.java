package sharedObject;

import java.lang.reflect.Field;

import javafx.scene.media.AudioClip;

/**
 * This AudioLoader class is the utility for loading AudioClip by using
 * ClassLoader.getSystemResource() with the file path then store in a public
 * static field.
 * 
 * @author jianchayapol
 *
 */
public class AudioLoader {
	
	/**
	 * AudioClip for Mouse_Click sound
	 */
	public static AudioClip Mouse_Click = new AudioClip(
			ClassLoader.getSystemResource("audio/mouse_click2.wav").toString());
	
	/**
	 * AudioClip for Mouse_Click1 sound
	 */
	public static AudioClip Mouse_Click1 = new AudioClip(
			ClassLoader.getSystemResource("audio/mouse_click.mp3").toString());
	
	/**
	 * AudioClip for Winning_Coin_Sound sound
	 */
	public static AudioClip Winning_Coin_Sound = new AudioClip(
			ClassLoader.getSystemResource("audio/winning-coin.wav").toString());
	
	/**
	 * AudioClip for Jumping_Sound sound
	 */
	public static AudioClip Jumping_Sound = new AudioClip(
			ClassLoader.getSystemResource("audio/jumping.wav").toString());
	
	/**
	 * AudioClip for Pick_Up_Item_Sound sound
	 */
	public static AudioClip Pick_Up_Item_Sound = new AudioClip(
			ClassLoader.getSystemResource("audio/picking-up-item.wav").toString());
	
	/**
	 * AudioClip for Entrance_Theme_Song sound
	 */
	public static AudioClip Entrance_Theme_Song = new AudioClip(
			ClassLoader.getSystemResource("audio/entrance_music.mp3").toString());
	
	/**
	 * AudioClip for Game_Theme_Song sound
	 */
	public static AudioClip Game_Theme_Song = new AudioClip(ClassLoader.getSystemResource("audio/game.mp3").toString());
}
