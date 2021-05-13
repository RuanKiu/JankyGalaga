import javax.swing.*;
import java.io.*;
import sun.audio.*;
import java.io.File;


public class musicex
{
	public static void main(String...args)
	{
		JFrame j = new JFrame();
		playAudio("lsong.au");
		j.setSize(1324, 870);
	}
	
	public static void playAudio(String filename)
	{
		InputStream in = null;
		AudioStream as = null;
		try
		{
			//create audio data source
			in = new FileInputStream(filename);
		}
		catch(FileNotFoundException fnfe){System.out.println("The audio file was not found");}
		try
		{
		//create audio stream from file stream
		as = new AudioStream(in);
		}
		catch(IOException ie){System.out.println("Audio stream could not be created");}

		AudioPlayer.player.start(as);

	}
	
}