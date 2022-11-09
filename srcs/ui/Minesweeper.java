package ui;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Minesweeper {
	private JButton [][]ButtonMap;
	private JLabel bombCounter;
	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JButton markBomButton = new JButton("Mark as Bomb");
	private JButton backMenuButton = new JButton("Menu");
	private int [][]BombMap;
	private int mapWidth, mapHeight, bombCount;
	private int width , height;
	private int markflag;
	private int status = 1;
	private Color color[] = {
		new Color(240, 240, 240),
		new Color(40, 200, 240),
		new Color(0, 150, 255),
		new Color(180, 0, 255),
		new Color(255, 198, 0),
		new Color(255, 114, 0),
		new Color(255, 60, 0),
		new Color(255, 60, 0),
		new Color(255, 60, 0),
		new Color(255, 60, 0)
	};

	private void init_element()
	{
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Minesweeper");
		frame.setLocationRelativeTo(null);

		panel.setLayout(null);
		frame.add(panel);

		bombCounter	= new JLabel("Bomb Remaining: " + String.valueOf(bombCount), SwingConstants.CENTER);
		bombCounter.setBounds(0, 2, 180, 48);
		bombCounter.setFont(new Font("Kanit Medium", Font.PLAIN, 18));
		panel.add(bombCounter);

		backMenuButton.setBounds(width - 280, 2, 90, 48);
		backMenuButton.setFont(new Font("Kanit Medium", Font.PLAIN, 18));
		backMenuButton.setBackground(new Color(0xff2e5f));
		backMenuButton.addActionListener(backToMenu);;
		panel.add(backMenuButton);

		markBomButton.setBounds(width - 190, 2, 166, 48);
		markBomButton.setFont(new Font("Kanit Medium", Font.PLAIN, 18));
		markBomButton.setBackground(new Color(255, 198, 0));
		markBomButton.addActionListener(setflag);
		panel.add(markBomButton);

		ButtonMap = new JButton[mapWidth][mapHeight];
		for (int y = 0; y < mapHeight; y++)
		{
			for (int x = 0; x < mapWidth; x++)
			{
				ButtonMap[x][y] = new JButton();
				ButtonMap[x][y].setFont(new Font("Kanit SemiBold", Font.PLAIN, 14));
				ButtonMap[x][y].setBounds(8 + (x * 44), 52 + (y * 44), 42, 42);
				ButtonMap[x][y].setBackground(new Color(127, 127, 127));
				ButtonMap[x][y].addActionListener(update);
				panel.add(ButtonMap[x][y]);
			}
		}

		frame.setVisible(true);
	}

	private void updateMap(int x, int y)
	{
		if (0 <= x && x <= mapWidth - 1 && 0 <= y && y <= mapHeight - 1)
		{
			if (status == 0)
				return ;
			if (markflag == 1 && ButtonMap[x][y].getText() == "")
			{
				bombCount -= 1;
				ButtonMap[x][y].setFont(new Font("Kanit SemiBold", Font.PLAIN, 10));
				ButtonMap[x][y].setText("X");
				ButtonMap[x][y].setBackground(new Color(255, 0, 108));
				ButtonMap[x][y].setForeground(new Color(255, 255, 255));
			}
			else if (markflag == 1 && ButtonMap[x][y].getText() == "X")
			{
				bombCount += 1;
				ButtonMap[x][y].setFont(new Font("Kanit SemiBold", Font.PLAIN, 14));
				ButtonMap[x][y].setText("");
				ButtonMap[x][y].setBackground(new Color(127, 127, 127));
			}
			bombCounter.setText("Bomb Remaining: " + String.valueOf(bombCount));
			if (BombMap[x][y] != 9 && ButtonMap[x][y].getText() != "X" && markflag == 0)
			{
				if (BombMap[x][y] == 0)
					ButtonMap[x][y].setText(" ");
				else
					ButtonMap[x][y].setText(String.valueOf(BombMap[x][y]));
				ButtonMap[x][y].setFont(new Font("Kanit SemiBold", Font.PLAIN, 14));
				ButtonMap[x][y].setBackground(color[BombMap[x][y]]);
				ButtonMap[x][y].setForeground(new Color(0, 0, 0));
				if (BombMap[x][y] == 0)
				{
					if (0 <= x + 1 && x + 1 <= mapWidth - 1 && 0 <= y + 1 && y + 1 <= mapHeight - 1 &&
					ButtonMap[x + 1][y + 1].getText() == "")
						updateMap(x + 1, y + 1);
					if (0 <= x + 1 && x + 1 <= mapWidth - 1 && 0 <= y && y <= mapHeight - 1 &&
					ButtonMap[x + 1][y].getText() == "")
						updateMap(x + 1, y);
					if (0 <= x + 1 && x + 1 <= mapWidth - 1 && 0 <= y - 1 && y - 1 <= mapHeight - 1 &&
					ButtonMap[x + 1][y - 1].getText() == "")
						updateMap(x + 1, y - 1);
					if (0 <= x && x <= mapWidth - 1 && 0 <= y + 1 && y + 1 <= mapHeight - 1 &&
					ButtonMap[x][y + 1].getText() == "")
						updateMap(x, y + 1);
					if (0 <= x && x <= mapWidth - 1 && 0 <= y - 1 && y - 1 <= mapHeight - 1 &&
					ButtonMap[x][y - 1].getText() == "")
						updateMap(x, y - 1);
					if (0 <= x - 1 && x - 1 <= mapWidth - 1 && 0 <= y + 1 && y + 1 <= mapHeight - 1 &&
					ButtonMap[x - 1][y + 1].getText() == "")
						updateMap(x - 1, y + 1);
					if (0 <= x - 1 && x - 1 <= mapWidth - 1 && 0 <= y && y <= mapHeight - 1 &&
					ButtonMap[x - 1][y].getText() == "")
						updateMap(x - 1, y);
					if (0 <= x - 1 && x - 1 <= mapWidth - 1 && 0 <= y - 1 && y - 1 <= mapHeight - 1 &&
					ButtonMap[x - 1][y - 1].getText() == "")
						updateMap(x - 1, y - 1);
				}
			}
			if (ButtonMap[x][y].getText() == "" && BombMap[x][y] == 9 && markflag == 0)
			{
				JOptionPane.showMessageDialog(frame, "You lose!");
				status = 0;
				showAnswer();
			}
			else
				checkAnswer();
		}
	}

	private void updateMapFromButton(ActionEvent event)
	{
		for (int y = 0; y < mapHeight; y++)
		{
			for (int x = 0; x < mapWidth; x++)
			{
				if (ButtonMap[x][y] == event.getSource())
				{
					// JOptionPane.showMessageDialog(null, "hi from x:"
					// + String.valueOf(x) + " y:" + String.valueOf(y));
					updateMap(x, y);
					return ;
				}
			}
		}
		JOptionPane.showMessageDialog(frame, "Error! please Restart game.");
	}

	private ActionListener update = new ActionListener()
	{
		public void actionPerformed (ActionEvent event)
		{
			updateMapFromButton(event);
		}
	};

	private ActionListener backToMenu = new ActionListener()
	{
		public void actionPerformed (ActionEvent event)
		{
			new StartupMenu();
			if (frame.isVisible() == true)
				frame.setVisible(false);
		}
	};

	private ActionListener setflag = new ActionListener()
	{
		public void actionPerformed (ActionEvent event)
		{
			if (markflag == 0)
			{
				((JButton) event.getSource()).setBackground(new Color(172, 134, 0));
				markflag = 1;
			}
			else
			{
				((JButton) event.getSource()).setBackground(new Color(255, 198, 0));
				markflag = 0;
			}
		}
	};

	private int countNearBomb(int xStart, int yStart)
	{
		int count = 0;
		int i = xStart - 1;
		int j = yStart - 1;
		if (BombMap[xStart][yStart] != 0)
			return BombMap[xStart][yStart];
		for (int y = 0; y < 3; y++)
		{
			for (int x = 0; x < 3; x++)
			{
				if (0 <= x + i && x + i <= mapWidth - 1 && 0 <= y + j && y + j <= mapHeight - 1 &&
				BombMap[x + i][y + j] == 9)
					count++;
			}
		}
		return count;
	}

	private void genMap()
	{
		BombMap = new int[mapWidth][mapHeight];
		for (int i = 0; i < bombCount; i++)
		{
			Random rand = new Random();
			int x = rand.nextInt(mapWidth - 1), y = rand.nextInt(mapHeight - 1);
			if (BombMap[x][y] == 0)
				BombMap[x][y] = 9;
			else
				i--;
		}
		for (int y = 0; y < mapHeight; y++)
		{
			for (int x = 0; x < mapWidth; x++)
			{
				BombMap[x][y] = countNearBomb(x, y);
			}
		}
	}

	private void showAnswer()
	{
		for (int y = 0; y < mapHeight; y++)
		{
			for (int x = 0; x < mapWidth; x++)
			{
				if (BombMap[x][y] == 9)
				{
					ButtonMap[x][y].setText("X");
					ButtonMap[x][y].setFont(new Font("Kanit SemiBold", Font.PLAIN, 10));
					ButtonMap[x][y].setForeground(new Color(0xFFffff));
					ButtonMap[x][y].setBackground(new Color(0xFF5555));
				}
				else if (BombMap[x][y] == 0)
				{
					ButtonMap[x][y].setText(" ");
					ButtonMap[x][y].setBackground(color[BombMap[x][y]]);
				}
				else
				{
					ButtonMap[x][y].setFont(new Font("Kanit SemiBold", Font.PLAIN, 14));
					ButtonMap[x][y].setText(String.valueOf(BombMap[x][y]));
					ButtonMap[x][y].setBackground(color[BombMap[x][y]]);
				}
			}
		}
	}

	private boolean checkAnswer()
	{
		for (int y = 0; y < mapHeight; y++)
		{
			for (int x = 0; x < mapWidth; x++)
			{
				if (BombMap[x][y] == 9 && !ButtonMap[x][y].getText().equals("X"))
					return false;
				else if (BombMap[x][y] == 0 && !ButtonMap[x][y].getText().equals(" "))
					return false;
				else if (BombMap[x][y] != 0 && BombMap[x][y] != 9 && !String.valueOf(BombMap[x][y]).equals(ButtonMap[x][y].getText()))
					return false;
			}
		}
		JOptionPane.showMessageDialog(frame, "You Win!!!");
		return true;
	}

	public Minesweeper(int width, int height, int bombCount)
	{
		this.bombCount = bombCount;
		this.mapWidth = width;
		this.mapHeight = height;
		this.width = (mapWidth * 44) + 30;
		this.height = (mapHeight * 44) + 98;
		genMap();
		init_element();
	}
}
