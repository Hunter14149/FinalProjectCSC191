package mainPackage;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.print.DocFlavor.URL;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.tools.*;

import org.json.simple.parser.JSONParser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop.Action;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.io.FileReader;
import java.util.Iterator;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.google.gson.*;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.Rectangle;

//Error: Could not find or load main class org.xmlpull.v1.dom2_builder.DOM2XmlPullBuilder in module com.FinalCSC191

public class mainWindow {

	private JFrame frame;
	static JTextField textField;
	static JButton runBtn;
	static JTextArea textArea;
	static JLabel titleLabel;
	static JLabel levelBtn;
	static JButton restartBtn;
	static JButton skipBtn;
	static int level = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainWindow window = new mainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainWindow() {
		initialize();
		StartGame();
		PlayTheme();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 800, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		runBtn = new JButton("Begin Your Run");
		runBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Run();
			}
		});
		runBtn.setFont(new Font("American Typewriter", Font.PLAIN, 20));
		runBtn.setForeground(new Color(0, 128, 0));
		runBtn.setBackground(Color.WHITE);
		runBtn.setBounds(285, 531, 226, 43);
		frame.getContentPane().add(runBtn);

		textField = new JTextField();
		textField.setForeground(new Color(0, 128, 0));
		textField.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		textField.setBackground(new Color(255, 255, 255));
		textField.setBounds(6, 482, 788, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout()); // give your JPanel a BorderLayout
		panel.setBounds(6, 52, 788, 413);
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		textArea.setForeground(new Color(0, 128, 0));
		textArea.setBackground(Color.WHITE);
		textArea.setBounds(6, 52, 788, 413);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		JScrollPane scroll = new JScrollPane(textArea);
		panel.add(scroll, BorderLayout.CENTER);
		frame.getContentPane().add(panel);

		titleLabel = new JLabel("Hacker Run");
		titleLabel.setFont(new Font("American Typewriter", Font.PLAIN, 20));
		titleLabel.setForeground(new Color(50, 205, 50));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(162, 7, 462, 29);
		frame.getContentPane().add(titleLabel);

		levelBtn = new JLabel("Level 0");
		levelBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		levelBtn.setHorizontalAlignment(SwingConstants.CENTER);
		levelBtn.setForeground(new Color(255, 255, 255));
		levelBtn.setBounds(691, 5, 89, 35);
		frame.getContentPane().add(levelBtn);

		restartBtn = new JButton("Restart");
		restartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StartGame();
			}
		});
		restartBtn.setFont(new Font("American Typewriter", Font.PLAIN, 20));
		restartBtn.setForeground(new Color(0, 128, 0));
		restartBtn.setBounds(22, 614, 226, 43);
		frame.getContentPane().add(restartBtn);

		skipBtn = new JButton("Skip Level");
		skipBtn.setForeground(new Color(0, 128, 0));
		skipBtn.setFont(new Font("American Typewriter", Font.PLAIN, 20));
		skipBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MoveToNextLevel();
			}
		});
		skipBtn.setBounds(554, 619, 226, 39);
		frame.getContentPane().add(skipBtn);
	}

	public static void PlayTheme() {
		if (true) {
			File file = new File(
					"dee_yan_key_01_driving_home.wav");
			Clip clip = null;
			try {
				clip = AudioSystem.getClip();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			AudioInputStream Audio = null;
			try {
				Audio = AudioSystem.getAudioInputStream(file);
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				clip.open(Audio);
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void StartGame() {
		level = 0;
		textArea.setText("");
		textField.setText("");
		levelBtn.setText("Level " + level);
		runBtn.setText("Begin Your Run");
		titleLabel.setText("Welcome to Hacker Run!");
		ReadQuestionsFile();

	}

	public static void MoveToNextLevel() {
		// just in case we skip level off the bat set text
		runBtn.setText("Run");
		titleLabel.setText("Hacker Run!");
		if (level == 10) {
			FinishedGame();
			JOptionPane.showMessageDialog(null, "You've Reach the Maximun Level", "Ok",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			level += 1;
			textField.setText("");
			levelBtn.setText("Level " + level);
			ReadQuestionsFile();
		}

	}

	public static void FinishedGame() {
		new gifTimer();
	}

	public static void ShowFinishedGIF1() {

		Icon icon = new ImageIcon("Finished.gif");
		JLabel label = new JLabel(icon);
		JFrame f = new JFrame("Animation");
		f.getContentPane().add(label);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

	public static void ShowFinishedGIF2() {
		Icon icon = new ImageIcon("Finished2.gif");
		JLabel label = new JLabel(icon);
		JFrame f = new JFrame("Animation");
		f.getContentPane().add(label);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

	public static void ShowFinishedGIF3() {
		Icon icon = new ImageIcon("Finished3.gif");
		JLabel label = new JLabel(icon);
		JFrame f = new JFrame("Animation");
		f.getContentPane().add(label);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

	public static boolean ReadAnswerFile() {
		JSONArray a = null;
		try {
			a = (JSONArray) new JSONParser().parse(new FileReader(
					"src/answers/answers.json"));// "c:\\answers.json"
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		for (Object o : a) {
			JSONObject answerList = (JSONObject) o;
			String jsonlevel = (String) answerList.get("level");
			String answer = (String) answerList.get("answer");
			if (AnswerCorrect(answer, jsonlevel)) {
				MoveToNextLevel();
				return true;
			}
		}
		return false;
	}

	public static boolean ReadCmdFile() {
		JSONArray a = null;
		try {
			a = (JSONArray) new JSONParser().parse(new FileReader(
					"src/commands/commands.json"));// "c:\\answers.json"
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		for (Object o : a) {
			JSONObject cmdList = (JSONObject) o;
			String jsonCommand = (String) cmdList.get("command");
			JSONArray consoleText = (JSONArray) cmdList.get("consoleText");
			// If a Command was found
			if (Command(jsonCommand, consoleText)) {
				//textField.setText("");
				return true;
			}
		}
		return false;
	}

	public static void ReadQuestionsFile() {
		JSONArray a = null;
		try {
			a = (JSONArray) new JSONParser().parse(new FileReader(
					"src/questions/questions.json"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		for (Object o : a) {
			JSONObject questionsList = (JSONObject) o;
			String jsonLevel = (String) questionsList.get("level");
			JSONArray question = (JSONArray) questionsList.get("question");
			Question(jsonLevel, question);
		}
	}

	public static void Question(String JSLevel, JSONArray JSConsoleText) {
		if (String.valueOf(level).equals(JSLevel)) {
			for (Object c : JSConsoleText) {
				textArea.setText(textArea.getText() + (c + "\n"));

			}
			textArea.setCaretPosition(textArea.getDocument().getLength());
		}
	}

	public static boolean Command(String JSCmd, JSONArray JSConsoleText) {
		String text = textField.getText();
		if ((text == "get started help") && JSCmd == "help") {
			for (Object c : JSConsoleText) {
				textArea.setText(textArea.getText() + (c + "\n"));
			}
			textArea.setCaretPosition(textArea.getDocument().getLength());
		} else if (text.contains(JSCmd)) {
			for (Object c : JSConsoleText) {
				textArea.setText(textArea.getText() + (c + "\n"));
			}
			textArea.setCaretPosition(textArea.getDocument().getLength());
			if (JSCmd.contains("clr")) {
				textField.setText("");
				textArea.setText("");
				ReadQuestionsFile();
			}
			return true;
		}
		return false;
	}

	public static boolean AnswerCorrect(String asnwer, String JSLevel) {
		String text = textField.getText();
		System.out.println("sddsd");
		System.out.println("asnwer " + asnwer);
		System.out.println("level " + level);
		System.out.println("JSLevel " + JSLevel);
		System.out.println("text " + text);
		if (text.contains(asnwer) && (String.valueOf(level).equals(JSLevel))) {
			System.out.println("kldks");
			return true;
		}
		return false;
	}

	public void Run() {
		String text = textField.getText();
		runBtn.setText("Run");
		titleLabel.setText("Hacker Run!");

		// switch statement with int data type
		switch (level) {
		case 0:
			if (ReadCmdFile()) {
				ReadAnswerFile();
			} else {
				textArea.setText(textArea.getText() + "\n System Error line is not a command> " + text);
				textArea.setText(textArea.getText() + " ");
			}
			break;
		case 1:
			// Determining if we found a command or the correct answer was given
			if ((ReadAnswerFile()) || ReadCmdFile()) {
			} else {
				textArea.setText(textArea.getText() + "\n System Error line is not a command> " + text);
				textArea.setText(textArea.getText() + " ");
			}
			break;
		case 2:
			// Determining if we found a command or the correct answer was given
			if ((ReadAnswerFile()) || ReadCmdFile()) {
			} else {
				textArea.setText(textArea.getText() + "\n System Error line is not a command> " + text);
				textArea.setText(textArea.getText() + " ");
			}
			break;
		case 3:
			// Determining if we found a command or the correct answer was given
						if ((ReadAnswerFile()) || ReadCmdFile()) {
						} else {
							textArea.setText(textArea.getText() + "\n System Error line is not a command> " + text);
							textArea.setText(textArea.getText() + " ");
						}
			break;
		case 4:
			// Determining if we found a command or the correct answer was given
						if ((ReadAnswerFile()) || ReadCmdFile()) {
						} else {
							textArea.setText(textArea.getText() + "\n System Error line is not a command> " + text);
							textArea.setText(textArea.getText() + " ");
						}
			break;
		case 5:
			// Determining if we found a command or the correct answer was given
						if ((ReadAnswerFile()) || ReadCmdFile()) {
						} else {
							textArea.setText(textArea.getText() + "\n System Error line is not a command> " + text);
							textArea.setText(textArea.getText() + " ");
						}
			break;
		case 6:
			// Determining if we found a command or the correct answer was given
			if ((ReadAnswerFile()) || ReadCmdFile()) {
			} else {
				textArea.setText(textArea.getText() + "\n System Error line is not a command> " + text);
				textArea.setText(textArea.getText() + " ");
			}
			break;
		case 7:
			// Determining if we found a command or the correct answer was given
			if ((ReadAnswerFile()) || ReadCmdFile()) {
			} else {
				textArea.setText(textArea.getText() + "\n System Error line is not a command> " + text);
				textArea.setText(textArea.getText() + " ");
			}
			break;
		case 8:
			// Determining if we found a command or the correct answer was given
			if ((ReadAnswerFile()) || ReadCmdFile()) {
			} else {
				textArea.setText(textArea.getText() + "\n System Error line is not a command> " + text);
				textArea.setText(textArea.getText() + " ");
			}
			break;
		case 9:
			// Determining if we found a command or the correct answer was given
			if ((ReadAnswerFile()) || ReadCmdFile()) {
			} else {
				textArea.setText(textArea.getText() + "\n System Error line is not a command> " + text);
				textArea.setText(textArea.getText() + " ");
			}
			break;
		case 10:
			// Determining if we found a command or the correct answer was given
			if ((ReadAnswerFile()) || ReadCmdFile()) {
			} else {
				textArea.setText(textArea.getText() + "\n System Error line is not a command> " + text);
				textArea.setText(textArea.getText() + " ");
			}
			break;
		default:

			break;
		}
	}
}
