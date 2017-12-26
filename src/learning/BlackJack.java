/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning;

import java.util.LinkedList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import jdk.nashorn.internal.ir.Labels;

/**
 *
 * @author vincent
 */
public class BlackJack extends javax.swing.JFrame {

	/**
	 * Creates new form BlackJack
	 */
	public BlackJack() {
		initComponents();
		myCardsLabel[0] = My1;
		myCardsLabel[1] = My2;
		myCardsLabel[2] = My3;
		myCardsLabel[3] = My4;
		myCardsLabel[4] = My5;
		opCardsLabel[0] = Op1;
		opCardsLabel[1] = Op2;
		opCardsLabel[2] = Op3;
		opCardsLabel[3] = Op4;
		GenerateCards();
	}

	List<Integer> myCards = new LinkedList<>();
	JLabel[] myCardsLabel = new JLabel[5];
	List<Integer> opCards = new LinkedList<>();
	JLabel[] opCardsLabel = new JLabel[4];

	int MyCards;
	int OpCards;

	private void GenerateCards() {
		myCards.clear();
		opCards.clear();
		DeleteCards();
		myCards.add((int) (Math.random() * 11) + 1);
		myCards.add((int) (Math.random() * 11) + 1);
		opCards.add((int) (Math.random() * 11) + 1);
		opCards.add((int) (Math.random() * 11) + 1);
		DisplayCards();
	}

	private void DisplayCards() {
		int idx = 0;
		for (Integer card : myCards) {
			myCardsLabel[idx++].setText(String.valueOf(card));
		}
		idx = 0;
		boolean firstCard = true;
		for (Integer card : opCards) {
			if (firstCard) {
				firstCard = false;
			} else {
				opCardsLabel[idx++].setText(String.valueOf(card));
			}
		}

	}

	private void DeleteCards() {
		for (JLabel label : myCardsLabel) {
			label.setText("");
		}
		for (JLabel label : opCardsLabel) {
			label.setText("");
		}
	}

	private int SumMyCards() {
		MyCards = 0;
		for (int card : myCards) {
			MyCards += card;
		}
		return MyCards;
	}

	private int SumOpCards() {
		OpCards = 0;
		for (int card : opCards) {
			OpCards += card;
		}
		return OpCards;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        My1 = new javax.swing.JLabel();
        Op1 = new javax.swing.JLabel();
        My2 = new javax.swing.JLabel();
        AddCard = new javax.swing.JButton();
        Freeze = new javax.swing.JButton();
        My3 = new javax.swing.JLabel();
        My4 = new javax.swing.JLabel();
        My5 = new javax.swing.JLabel();
        Op2 = new javax.swing.JLabel();
        Op3 = new javax.swing.JLabel();
        Op4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Oponent Hand:");

        jLabel2.setText("Your Hand:");

        AddCard.setText("Another Card");
        AddCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddCardActionPerformed(evt);
            }
        });

        Freeze.setText("Freeze");
        Freeze.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FreezeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Freeze, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(AddCard))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(My1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(My2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(My3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Op1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(87, 87, 87)
                                .addComponent(Op2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(My4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(My5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Op3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Op4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 47, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Op3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Op4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Op2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(My2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(My3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(My4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(My5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(Op1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(My1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddCard)
                    .addComponent(Freeze, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void FreezeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FreezeActionPerformed
		AiDecision();
		SumMyCards();
		SumOpCards();
		if (MyCards > 21) {
			JOptionPane.showMessageDialog(this, "You automatcally lose as your cards are more than 21", "Automatic loss", JOptionPane.WARNING_MESSAGE);
		} else if (OpCards > 21) {
			JOptionPane.showMessageDialog(this, "You automatcally win as the oponent's cards are more than 21", "Automatic win", JOptionPane.WARNING_MESSAGE);
		} else if (OpCards > MyCards) {
			JOptionPane.showMessageDialog(this, "You lost (" + MyCards + " vs " + OpCards + ")", "Loss", JOptionPane.WARNING_MESSAGE);
		} else if (OpCards < MyCards) {
			JOptionPane.showMessageDialog(this, "You won (" + MyCards + " vs " + OpCards + ")", "Win", JOptionPane.WARNING_MESSAGE);
		}else if (SumMyCards() < 22) {
			JOptionPane.showMessageDialog(this, "You automatcally win as you have 5 cards", "Automatic win", JOptionPane.WARNING_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "You tied (" + MyCards + " vs " + OpCards + ")", "Tie", JOptionPane.WARNING_MESSAGE);

		}
		GenerateCards();
    }//GEN-LAST:event_FreezeActionPerformed

    private void AddCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddCardActionPerformed
		if (myCards.size() < 5) {
			myCards.add((int) (Math.random() * 10) + 1);
			DisplayCards();
		} else {
			JOptionPane.showMessageDialog(this, "You cannot draw more cards", "Card max reached", JOptionPane.WARNING_MESSAGE);
		}
		AiDecision();
    }//GEN-LAST:event_AddCardActionPerformed

	private void AddOpCard() {
		if (opCards.size() < 5) {
			opCards.add((int) (Math.random() * 10) + 1);
			DisplayCards();
		} else if (SumMyCards() < 22) {
			JOptionPane.showMessageDialog(this, "You automatcally lose as the oponent has 5 cards", "Automatic loss", JOptionPane.WARNING_MESSAGE);
		}
		return;
	}

	private void AiDecision() {
		if (SumOpCards() < 16) {
			AddOpCard();
		}
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(BlackJack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(BlackJack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(BlackJack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(BlackJack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new BlackJack().setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddCard;
    private javax.swing.JButton Freeze;
    private javax.swing.JLabel My1;
    private javax.swing.JLabel My2;
    private javax.swing.JLabel My3;
    private javax.swing.JLabel My4;
    private javax.swing.JLabel My5;
    private javax.swing.JLabel Op1;
    private javax.swing.JLabel Op2;
    private javax.swing.JLabel Op3;
    private javax.swing.JLabel Op4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}