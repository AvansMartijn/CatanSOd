package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameContentPane extends JPanel {

	public GameContentPane() {

		setLayout(new BorderLayout());

		ChatWindow chat = new ChatWindow();
		
		Board board = new Board();
		PlayerHand playerHand = new PlayerHand();
		Overview overView = new Overview();

		add(chat, BorderLayout.LINE_START);
		add(board, BorderLayout.CENTER);
		add(overView, BorderLayout.LINE_END);
		add(playerHand, BorderLayout.PAGE_END);

	}

	private class ChatWindow extends JPanel {

		private ChatWindow() {
		
			setPreferredSize(new Dimension(400,500));
			setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
			
			
			
			
		}

	}

	private class Board extends JPanel {

		private Board() {

			setPreferredSize(new Dimension(1000,800));
			setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

			
		}
	}

	private class PlayerHand extends JPanel {

		private PlayerHand() {
			setPreferredSize(new Dimension(1600,200));
			setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
			
			DiceView diceView = new DiceView();
			
			add(diceView);
			
		}
	}

	private class Overview extends JPanel {

		private Overview() {
			setPreferredSize(new Dimension(400,600));
			setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
			
			
			
		}

	}

	private class DiceView extends JPanel{
		
		private DiceView(){
			
			setPreferredSize(new Dimension(200,200));
			setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		}
		
		
	}
	
}
