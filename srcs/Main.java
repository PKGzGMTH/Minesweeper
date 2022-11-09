import ui.*;
import java.awt.*;
import java.io.File;

public class Main {
	public static void main(String []args)
	{
		try
		{
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(
				Font.createFont(Font.TRUETYPE_FONT, new File("fonts\\Kanit-Medium.ttf"))
			);
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(
				Font.createFont(Font.TRUETYPE_FONT, new File("fonts\\Kanit-SemiBold.ttf"))
			);
		}
		catch (Exception e)
		{
			System.out.println("Font not found!!");
		}
		new StartupMenu();
	}
}
