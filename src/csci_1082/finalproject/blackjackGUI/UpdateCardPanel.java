package csci_1082.finalproject.blackjackGUI;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class UpdateCardPanel implements Runnable {
		
		private JLabel card = null;
		private JLayeredPane seatCardPane = null;
		private int cardDepth = 0;
		
		public UpdateCardPanel (JLabel card, JLayeredPane seatCardPane, int cardDepth) {
			this.card = card;
			this.seatCardPane = seatCardPane;
			this.cardDepth = cardDepth;
			Thread addCardThread = new Thread(this);		
			addCardThread.start();
		}

		@Override
		public void run() {
			System.out.println("I'm in theupdateCardPanel thread");
			seatCardPane.add(card, new Integer(cardDepth));
			try {
				System.out.println("I'm trying to sleep");
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
}