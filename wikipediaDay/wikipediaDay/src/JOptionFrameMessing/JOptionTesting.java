package JOptionFrameMessing;


import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import sun.audio.*;

public class JOptionTesting {

	private JFrame myFrame;
	private AudioStream audio;
	private InputStream music;
	private Question nextStore;
	//TODO

	public JOptionTesting() {
		boolean fail = false;
		int totalScore = 9;
		int questionN = 1;
		boolean superScore = false;
		audio = null;
		music = null;
		
		//TODO
		nextStore = new Question(); 

		try {
			music = new FileInputStream(new File("C:\\Users\\Jack\\eclipse-workspace\\wikipediaDay\\src\\JOptionFrameMessing\\GmeShow.wav"));
			AudioStream audios = new AudioStream(music);
			AudioPlayer.player.start(audios);
			audio = audios;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: MUSIC FILE NOT FOUND");
		}

		while (!fail) {
			// Question Object
			String a = nextStore.getAnswerA();
			String b = nextStore.getAnswerB();
			String c = nextStore.getAnswerC();
			String d = nextStore.getAnswerD();
			String questionText = nextStore.getQuestion();

			int answer = -1;
			String[] opt = { a, b, c, d };

			for (int i = 0; i < opt.length; i++) {
				if (opt[i].equals(nextStore.getCorrectAnswer())) {
					answer = i;
					break;
				}
			}

			ImageIcon icon = new ImageIcon(JOptionTesting.class.getResource("Squirrel-X_1_1.png"));
			
			ImageIcon icon2 = new ImageIcon(JOptionTesting.class.getResource("frowny.png"));
			
			ImageIcon icon3 = new ImageIcon(JOptionTesting.class.getResource("PogChamp_20.png"));

			int optionClicked = JOptionPane.showOptionDialog(myFrame, questionText, "WikiTrivia v.1",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, icon, opt, null);
			
			
			if (optionClicked == answer) {
				JOptionPane.showMessageDialog(myFrame, "Correct!", "You did it!", JOptionPane.INFORMATION_MESSAGE, icon3);
				//TODO
				nextStore = new Question();
				if (totalScore % 130 > 0 || superScore) {
					totalScore = (int) (totalScore + Math.pow(questionN, 3));
					superScore = true;
				} else {
					totalScore = (int) (totalScore + Math.pow(questionN, 2));
				}

				questionN++;
			} else {
				if (totalScore == 9) {
					totalScore = 0;
				}

				JOptionPane.showMessageDialog(myFrame, "Wrong! \n Your Score is: " + totalScore + "\n The correct answer was: " + nextStore.getCorrectAnswer(), "Game Over!",
						JOptionPane.ERROR_MESSAGE, icon2);
				fail = true;
				AudioPlayer.player.stop(audio);
			}

		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new JOptionTesting();
	}

}
