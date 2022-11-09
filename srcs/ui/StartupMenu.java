package ui;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartupMenu {
	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JLabel menuName = new JLabel("Minesweeper", SwingConstants.CENTER);
	private JButton SelectButton[] = new JButton[3];

	void init_element()
	{
		frame.setSize(416, 180);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Minesweeper");
		frame.setLocationRelativeTo(null);

		panel.setLayout(null);

		menuName.setBounds(0, 16, 400, 50);
		menuName.setFont(new Font("Kanit SemiBold", Font.PLAIN, 42));
		panel.add(menuName);

		for (int i = 0; i < 3; i++)
		{
			if (i == 0)
			{
				SelectButton[i] = new JButton("Easy");
				SelectButton[i].setBackground(new Color(44, 203, 143));
			}
			else if (i == 1)
			{
				SelectButton[i] = new JButton("Medium");
				SelectButton[i].setBackground(new Color(251, 185, 65));
			}
			else if (i == 2)
			{
				SelectButton[i] = new JButton("Hard");
				SelectButton[i].setBackground(new Color(251, 96, 66));
			}
			SelectButton[i].setBounds(30 + (i * 120), 80, 100, 30);
			SelectButton[i].addActionListener(Select);
			SelectButton[i].setFont(new Font("Kanit Medium", Font.PLAIN, 16));
			panel.add(SelectButton[i]);
		}

		frame.add(panel);
		frame.setVisible(true);
	}

	private ActionListener Select = new ActionListener()
	{
		public void actionPerformed (ActionEvent event)
		{
			if (event.getSource() == SelectButton[0])
				new Minesweeper(10, 12, 12);
			else if (event.getSource() == SelectButton[1])
				new Minesweeper(16, 20, 24);
			else if (event.getSource() == SelectButton[2])
				new Minesweeper(24, 20, 52);
			if (frame.isVisible() == true)
				frame.setVisible(false);
		}
	};

	public StartupMenu()
	{
		init_element();
	}
}
