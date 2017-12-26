/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author vincent
 */
public class Connect4 extends javax.swing.JFrame {

	private static class Slot {

		char column;
		int row;

		Slot(int col, int row) {
			char tmp = 'a';
			switch (col) {
				case 0:
					tmp = 'a';
					break;
				case 1:
					tmp = 'b';
					break;
				case 2:
					tmp = 'c';
					break;
				case 3:
					tmp = 'd';
					break;
				case 4:
					tmp = 'e';
					break;
				case 5:
					tmp = 'f';
					break;
				case 6:
					tmp = 'g';
					break;
			}
			column = tmp;
			this.row = row;
		}
	}

	enum ChipColor {
		RED,
		BLACK,
		EMPTY
	}
	ChipColor whoseTurn = ChipColor.RED;
	JLabel[] arrayA = new JLabel[6];
	JLabel[] arrayB = new JLabel[6];
	JLabel[] arrayC = new JLabel[6];
	JLabel[] arrayD = new JLabel[6];
	JLabel[] arrayE = new JLabel[6];
	JLabel[] arrayF = new JLabel[6];
	JLabel[] arrayG = new JLabel[6];

	ChipColor[] aEntry = new ChipColor[6];
	ChipColor[] bEntry = new ChipColor[6];
	ChipColor[] cEntry = new ChipColor[6];
	ChipColor[] dEntry = new ChipColor[6];
	ChipColor[] eEntry = new ChipColor[6];
	ChipColor[] fEntry = new ChipColor[6];
	ChipColor[] gEntry = new ChipColor[6];

	/**
	 * Creates new form Connect4
	 */
	public Connect4() throws IOException {
		initComponents();
		arrayA[0] = A1;
		arrayA[1] = A2;
		arrayA[2] = A3;
		arrayA[3] = A4;
		arrayA[4] = A5;
		arrayA[5] = A6;
		arrayB[0] = B1;
		arrayB[1] = B2;
		arrayB[2] = B3;
		arrayB[3] = B4;
		arrayB[4] = B5;
		arrayB[5] = B6;
		arrayC[0] = C1;
		arrayC[1] = C2;
		arrayC[2] = C3;
		arrayC[3] = C4;
		arrayC[4] = C5;
		arrayC[5] = C6;
		arrayD[0] = D1;
		arrayD[1] = D2;
		arrayD[2] = D3;
		arrayD[3] = D4;
		arrayD[4] = D5;
		arrayD[5] = D6;
		arrayE[0] = E1;
		arrayE[1] = E2;
		arrayE[2] = E3;
		arrayE[3] = E4;
		arrayE[4] = E5;
		arrayE[5] = E6;
		arrayF[0] = F1;
		arrayF[1] = F2;
		arrayF[2] = F3;
		arrayF[3] = F4;
		arrayF[4] = F5;
		arrayF[5] = F6;
		arrayG[0] = G1;
		arrayG[1] = G2;
		arrayG[2] = G3;
		arrayG[3] = G4;
		arrayG[4] = G5;
		arrayG[5] = G6;
		ResetChips();
		redrawScreen();
	}

	private void insertCoinInSlot(ChipColor coin, Slot slot) {
		switch(slot.column) {
			case 'a':
				aEntry[slot.row] = coin;
				break;
			case 'b':
				bEntry[slot.row] = coin;
				break;
			case 'c':
				cEntry[slot.row] = coin;
				break;
			case 'd':
				dEntry[slot.row] = coin;
				break;
			case 'e':
				eEntry[slot.row] = coin;
				break;
			case 'f':
				fEntry[slot.row] = coin;
				break;
			case 'g':
				gEntry[slot.row] = coin;
				break;
		}
	}
	private void aiDecision() {
		Slot threat;
		if (((threat = aiDirectThreatHorizontal()) != null) && IsThisPossible(threat.column, threat.row)){
			insertCoinInSlot(ChipColor.BLACK, threat);
			redrawScreen();
		}
	}

	private boolean IsThisPossible(char col, int row) {
		if (row == 5 || checkColorForSLot(col, row + 1) != ChipColor.EMPTY) {
			return true;
		}
		return false;
	}
	
	private Slot aiDirectThreatHorizontal() {
		ChipColor[][] columns = new ChipColor[][]{aEntry, bEntry, cEntry, dEntry, eEntry, fEntry, gEntry};
		for (int row = 0; row < 6; row++) {
			ChipColor currentColor = ChipColor.EMPTY;
			int count = 0;
			boolean countEmpty = false;
			boolean beginEmpty = true;
			Slot threat = null;
			for (int col = 0; col < columns.length; ++col) {
				if (columns[col][row] != ChipColor.EMPTY) {
					if (currentColor == columns[col][row]) {
						++count;
						if (count == 4) {
							JOptionPane.showMessageDialog(this, "Ai detects immediate threat", "Winning", JOptionPane.INFORMATION_MESSAGE);
							return threat;
						}
					} else {
						count = 1;
						currentColor = columns[col][row];
					}
				} else if (!countEmpty) {
					if (currentColor != ChipColor.EMPTY) {
						countEmpty = true;
						count++;
						threat = new Slot(col,row);
						if (count == 4) {
							JOptionPane.showMessageDialog(this, "Ai detects immediate threat", "Winning", JOptionPane.INFORMATION_MESSAGE);
							return threat;
						}
					}
				} else {
					if(beginEmpty) {
						currentColor = ChipColor.EMPTY;
						count = 1;
						threat = new Slot(col, row);
					} else {
						countEmpty = false;
						currentColor = ChipColor.EMPTY;
						count = 0;
					}
				}
			}
		}
		return null;
	}

	private void redrawScreen() {
		for (int i = 0; i < 6; ++i) {
			drawCircle('a', i, aEntry[i]);
			drawCircle('b', i, bEntry[i]);
			drawCircle('c', i, cEntry[i]);
			drawCircle('d', i, dEntry[i]);
			drawCircle('e', i, eEntry[i]);
			drawCircle('f', i, fEntry[i]);
			drawCircle('g', i, gEntry[i]);
		}
	}

	private void drawCircle(char column, int row, ChipColor color) {
		JLabel target = null;
		if ((row < 6) && (row > -1)) {
			switch (column) {
				case 'a':
					target = arrayA[row];
					break;
				case 'b':
					target = arrayB[row];
					break;
				case 'c':
					target = arrayC[row];
					break;
				case 'd':
					target = arrayD[row];
					break;
				case 'e':
					target = arrayE[row];
					break;
				case 'f':
					target = arrayF[row];
					break;
				case 'g':
					target = arrayG[row];
					break;
			}
		}
		if (target != null) {
			URL imageLocation = null;
			switch (color) {
				case RED:
					imageLocation = getClass().getClassLoader().getResource("images/RedCircle.png");
					break;
				case BLACK:
					imageLocation = getClass().getClassLoader().getResource("images/BlackCircle.png");
					break;
				default:
					break;

			}
			target.setIcon(imageLocation != null ? new ImageIcon(imageLocation) : null);
		}
	}

	private boolean WinnerCheck() {
		ChipColor winnerIs = ChipColor.EMPTY;
		if (WinnerHorizontalCheck()) {
			return true;
		} else if (WinnerVerticalCheck()) {
			return true;
		} else if ((winnerIs = WinnerDiagonal1Check()) != ChipColor.EMPTY) {
			JOptionPane.showMessageDialog(this, "And the winner is " + winnerIs, "Winning", JOptionPane.INFORMATION_MESSAGE);
			return true;
		} else if ((winnerIs = WinnerDiagonal2Check()) != ChipColor.EMPTY) {
			JOptionPane.showMessageDialog(this, "And the winner is " + winnerIs, "Winning", JOptionPane.INFORMATION_MESSAGE);
			return true;
		}
		return false;
	}

	private boolean WinnerHorizontalCheck() {
		ChipColor[][] columns = new ChipColor[][]{aEntry, bEntry, cEntry, dEntry, eEntry, fEntry, gEntry};
		for (int row = 0; row < 6; row++) {
			ChipColor currentColor = ChipColor.EMPTY;
			int count = 0;
			for (int col = 0; col < columns.length; ++col) {
				if (columns[col][row] != ChipColor.EMPTY) {
					if (currentColor == columns[col][row]) {
						++count;
						if (count > 3) {
							JOptionPane.showMessageDialog(this, currentColor + " won", "Winning", JOptionPane.INFORMATION_MESSAGE);
							return true;
						}
					} else {
						count = 1;
						currentColor = columns[col][row];
					}
				} else {
					currentColor = ChipColor.EMPTY;
					count = 0;
				}
			}
		}
		return false;
	}

	private boolean WinnerVerticalCheck() {
		ChipColor[][] columns = new ChipColor[][]{aEntry, bEntry, cEntry, dEntry, eEntry, fEntry, gEntry};
		for (int col = 0; col < columns.length; col++) {
			ChipColor currentColor = ChipColor.EMPTY;
			int count = 0;
			for (int row = 0; row < 6; ++row) {
				if (columns[col][row] != ChipColor.EMPTY) {
					if (currentColor == columns[col][row]) {
						++count;
						if (count > 3) {
							JOptionPane.showMessageDialog(this, currentColor + " won", "Winning", JOptionPane.INFORMATION_MESSAGE);
							return true;
						}
					} else {
						count = 1;
						currentColor = columns[col][row];
					}
				} else {
					currentColor = ChipColor.EMPTY;
					count = 0;
				}
			}
		}
		return false;
	}

	private ChipColor checkColorForSLot(char col, int row) {
		if ((row > -1) && (row < 6)) {
			switch (col) {
				case 'a':
					return aEntry[row];
				case 'b':
					return bEntry[row];
				case 'c':
					return cEntry[row];
				case 'd':
					return dEntry[row];
				case 'e':
					return eEntry[row];
				case 'f':
					return fEntry[row];
				case 'g':
					return gEntry[row];
			}
		}
		return ChipColor.EMPTY;
	}

	private ChipColor checkDiag1Slice(char startCol, int startRow) {
		ChipColor rememberColor = ChipColor.EMPTY;
		char col = startCol;
		int row = startRow;
		int count = 0;
		while (true) {
			if ((startRow > -1) && (startRow < 6) && (startCol >= 'a') && (startCol <= 'g')) {
				ChipColor currSlot;
				if ((currSlot = checkColorForSLot(col, row)) != ChipColor.EMPTY) {
					if (currSlot != rememberColor) {
						rememberColor = currSlot;
						count = 1;
					} else {
						count++;
						if (count == 4) {
							return rememberColor;
						}
					}
				} else {
					rememberColor = ChipColor.EMPTY;
					count = 0;
				}
				if (++row > 5) {
					break;
				}
				col += 1;
				if (col > 'g') {
					break;
				}
			} else {
				break;
			}
		}
		return ChipColor.EMPTY;
	}

	private ChipColor checkDiag2Slice(char startCol, int startRow) {
		ChipColor rememberColor = ChipColor.EMPTY;
		char col = startCol;
		int row = startRow;
		int count = 0;
		while (true) {
			if ((startRow > -1) && (startRow < 6) && (startCol >= 'a') && (startCol <= 'g')) {
				ChipColor currSlot;
				if ((currSlot = checkColorForSLot(col, row)) != ChipColor.EMPTY) {
					if (currSlot != rememberColor) {
						rememberColor = currSlot;
						count = 1;
					} else {
						count++;
						if (count == 4) {
							return rememberColor;
						}
					}
				} else {
					rememberColor = ChipColor.EMPTY;
					count = 0;
				}
				if (++row > 5) {
					break;
				}
				col -= 1;
				if (col > 'g') {
					break;
				}
			} else {
				break;
			}
		}
		return ChipColor.EMPTY;
	}

	private ChipColor WinnerDiagonal1Check() {
		ChipColor winnerIs;
		for (int row = 0; row < 6; ++row) {
			if ((winnerIs = checkDiag1Slice('a', row)) != ChipColor.EMPTY) {
				return winnerIs;
			}
		}
		for (char col = 'a'; col <= 'g'; col++) {
			if ((winnerIs = checkDiag1Slice(col, 0)) != ChipColor.EMPTY) {
				return winnerIs;
			}
		}

		return ChipColor.EMPTY;
	}

	private ChipColor WinnerDiagonal2Check() {
		ChipColor winnerIs;
		for (char col = 'a'; col <= 'g'; col++) {
			if ((winnerIs = checkDiag2Slice(col, 0)) != ChipColor.EMPTY) {
				return winnerIs;
			}
		}
		for (int row = 0; row < 6; ++row) {
			if ((winnerIs = checkDiag2Slice('g', row)) != ChipColor.EMPTY) {
				return winnerIs;
			}
		}
		return ChipColor.EMPTY;
	}

	private void ResetChips() {
		for (JLabel label : arrayA) {
			label.setText("");
		}
		for (JLabel label : arrayB) {
			label.setText("");
		}
		for (JLabel label : arrayC) {
			label.setText("");
		}
		for (JLabel label : arrayD) {
			label.setText("");
		}
		for (JLabel label : arrayE) {
			label.setText("");
		}
		for (JLabel label : arrayF) {
			label.setText("");
		}
		for (JLabel label : arrayG) {
			label.setText("");
		}
		for (int i = 0; i < 6; ++i) {
			aEntry[i] = ChipColor.EMPTY;
			bEntry[i] = ChipColor.EMPTY;
			cEntry[i] = ChipColor.EMPTY;
			dEntry[i] = ChipColor.EMPTY;
			eEntry[i] = ChipColor.EMPTY;
			fEntry[i] = ChipColor.EMPTY;
			gEntry[i] = ChipColor.EMPTY;
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        ABt = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        A2 = new javax.swing.JLabel();
        A1 = new javax.swing.JLabel();
        A4 = new javax.swing.JLabel();
        A3 = new javax.swing.JLabel();
        A6 = new javax.swing.JLabel();
        A5 = new javax.swing.JLabel();
        B1 = new javax.swing.JLabel();
        B2 = new javax.swing.JLabel();
        B3 = new javax.swing.JLabel();
        B4 = new javax.swing.JLabel();
        B5 = new javax.swing.JLabel();
        B6 = new javax.swing.JLabel();
        D1 = new javax.swing.JLabel();
        C1 = new javax.swing.JLabel();
        C2 = new javax.swing.JLabel();
        D2 = new javax.swing.JLabel();
        D3 = new javax.swing.JLabel();
        C3 = new javax.swing.JLabel();
        C4 = new javax.swing.JLabel();
        D4 = new javax.swing.JLabel();
        D5 = new javax.swing.JLabel();
        C5 = new javax.swing.JLabel();
        C6 = new javax.swing.JLabel();
        D6 = new javax.swing.JLabel();
        G1 = new javax.swing.JLabel();
        F1 = new javax.swing.JLabel();
        E1 = new javax.swing.JLabel();
        E2 = new javax.swing.JLabel();
        F2 = new javax.swing.JLabel();
        G2 = new javax.swing.JLabel();
        G3 = new javax.swing.JLabel();
        F3 = new javax.swing.JLabel();
        E3 = new javax.swing.JLabel();
        E4 = new javax.swing.JLabel();
        F4 = new javax.swing.JLabel();
        G4 = new javax.swing.JLabel();
        G5 = new javax.swing.JLabel();
        F5 = new javax.swing.JLabel();
        E5 = new javax.swing.JLabel();
        E6 = new javax.swing.JLabel();
        F6 = new javax.swing.JLabel();
        G6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        ABt.setText("Place here");
        ABt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ABtActionPerformed(evt);
            }
        });

        jButton3.setText("Place here");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Place here");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Place here");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Place here");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Place here");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Place here");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        A6.setText("jLabel3");

        B6.setText("jLabel3");

        C6.setText("jLabel3");

        D6.setText("jLabel3");

        E6.setText("jLabel3");

        F6.setText("jLabel3");

        G6.setText("jLabel3");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(A5, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(A6, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(A1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ABt)
                    .addComponent(A2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(A3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(A4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jButton3)
                            .addGap(10, 10, 10)
                            .addComponent(jButton4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton7)
                            .addGap(18, 18, 18)
                            .addComponent(jButton8)
                            .addGap(90, 90, 90))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(57, 57, 57)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(B5, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(B6, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(B1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(B2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(B3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(B4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(C5, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C6, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(D5, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(D6, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(D1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(D2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(D3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(D4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(E5, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(E6, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(E1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(E2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(E3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(E4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(F5, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(F6, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(F1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(F2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(F3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(F4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(G5, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(G6, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(G1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(G2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(G3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(G4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ABt)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton6)
                    .addComponent(jButton5)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(A1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(A2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(A3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(A4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addContainerGap())
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(A5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(A6, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(C1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(C6, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(D1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(D2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(D3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(D4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(D5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(D6, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(B1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(B2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(B3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(B4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(B5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(B6, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(F1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(F2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(F3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(F4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(F5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(F6, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(G1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(G2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(G3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(G4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(G5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(G6, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(E1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(E2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(E3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(E4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(E5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(E6, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setPreferredSize(new java.awt.Dimension(800, 700));

        jLabel1.setText("MENU");

        jButton1.setText("START");
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 376, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(328, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
		remove(jPanel2);
		add(jPanel1);
		validate();
		repaint();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
		insertCoin('g');
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
		insertCoin('f');
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
		insertCoin('d');
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
		insertCoin('e');
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
		insertCoin('c');
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
		insertCoin('b');
    }//GEN-LAST:event_jButton3ActionPerformed

    private void ABtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ABtActionPerformed
		insertCoin('a');
    }//GEN-LAST:event_ABtActionPerformed

	private void insertCoin(char whichCOlumn) {
		ChipColor[] column = null;
		switch (whichCOlumn) {
			case 'a':
				column = aEntry;
				break;
			case 'b':
				column = bEntry;
				break;
			case 'c':
				column = cEntry;
				break;
			case 'd':
				column = dEntry;
				break;
			case 'e':
				column = eEntry;
				break;
			case 'f':
				column = fEntry;
				break;
			case 'g':
				column = gEntry;
				break;
		}
		for (int i = 5; i > -1; --i) {
			if (column[i] == ChipColor.EMPTY) {
				column[i] = whoseTurn;
				break;
			}
		}
		redrawScreen();
		aiDecision();
		if (WinnerCheck()) {
			ResetChips();
			redrawScreen();
			remove(jPanel1);
			add(jPanel2);
			validate();
			repaint();
			return;
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
			java.util.logging.Logger.getLogger(Connect4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Connect4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Connect4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Connect4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Connect4().setVisible(true);
				} catch (IOException ex) {
					Logger.getLogger(Connect4.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel A1;
    private javax.swing.JLabel A2;
    private javax.swing.JLabel A3;
    private javax.swing.JLabel A4;
    private javax.swing.JLabel A5;
    private javax.swing.JLabel A6;
    private javax.swing.JButton ABt;
    private javax.swing.JLabel B1;
    private javax.swing.JLabel B2;
    private javax.swing.JLabel B3;
    private javax.swing.JLabel B4;
    private javax.swing.JLabel B5;
    private javax.swing.JLabel B6;
    private javax.swing.JLabel C1;
    private javax.swing.JLabel C2;
    private javax.swing.JLabel C3;
    private javax.swing.JLabel C4;
    private javax.swing.JLabel C5;
    private javax.swing.JLabel C6;
    private javax.swing.JLabel D1;
    private javax.swing.JLabel D2;
    private javax.swing.JLabel D3;
    private javax.swing.JLabel D4;
    private javax.swing.JLabel D5;
    private javax.swing.JLabel D6;
    private javax.swing.JLabel E1;
    private javax.swing.JLabel E2;
    private javax.swing.JLabel E3;
    private javax.swing.JLabel E4;
    private javax.swing.JLabel E5;
    private javax.swing.JLabel E6;
    private javax.swing.JLabel F1;
    private javax.swing.JLabel F2;
    private javax.swing.JLabel F3;
    private javax.swing.JLabel F4;
    private javax.swing.JLabel F5;
    private javax.swing.JLabel F6;
    private javax.swing.JLabel G1;
    private javax.swing.JLabel G2;
    private javax.swing.JLabel G3;
    private javax.swing.JLabel G4;
    private javax.swing.JLabel G5;
    private javax.swing.JLabel G6;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
