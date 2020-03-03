/**
 * Author: Pat Hurley
 * MasterMind
 */

package masterMind;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This is the gui for my mastermind project
 * it implements a few buttons and images
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class GuiBoard extends JFrame {
	Board board = new Board();
	private JPanel contentPane;
	private JPanel attemptOne;
	private JPanel attemptTwo;
	private JPanel attemptThree;
	private JPanel attemptFour;
	private JPanel attemptFive;
	private JPanel attemptSix;
	private JPanel attemptSeven;
	private JPanel attemptEight;
	private JPanel attemptNine;
	private JPanel attemptTen;
	private JPanel[] panels = new JPanel[10];
	private JButton[] hints = new JButton[4];
	private JButton btnAns1;
	private JButton btnAns2;
	private JButton btnAns3;
	private JButton btnAns4;
	Ball[] guess = new Ball[4];
	private int hint = 0;
	int[] pegs = new int[2];
	private JPanel pegPnl;
	private JPanel answerLbl;
	private JLabel pegLbl1_1;
	private JLabel pegLbl1_2;
	private JLabel pegLbl2_1;
	private JLabel pegLbl2_2;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiBoard frame = new GuiBoard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GuiBoard() {
		setTitle("MasterMind");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel westPanel = createWestPanel();
		contentPane.add(westPanel, BorderLayout.WEST);
		
		JPanel centerPanel = createCenterPanel();
		contentPane.add(centerPanel, BorderLayout.CENTER);
		
		JPanel eastPanel = createEastPanel();
		contentPane.add(eastPanel, BorderLayout.EAST);
	}
	
	/**
	 * Creates my east Panel which houses all my 
	 * color buttons and a submit button to check 
	 * the users guess, it also includes a hint which
	 * will show one color on the answer code it can be
	 * used multiple times to expose the whole code
	 * @return
	 */
	private JPanel createEastPanel() {
		JPanel eastPanel = new JPanel();
		board.generateCode();
		
		eastPanel.setLayout(new GridLayout(9, 0, 0, 0));
		
		JButton btnBlue = new JButton("Blue");
		btnBlue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				board.setGuess(Ball.BLUE);
			}
		});
		btnBlue.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		btnBlue.setOpaque(true);
		btnBlue.setBackground(new Color(30, 144, 255));
		eastPanel.add(btnBlue);
		
		JButton btnGreen = new JButton("Green");
		btnGreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				board.setGuess(Ball.GREEN);
			}
		});
		btnGreen.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		btnGreen.setOpaque(true);
		btnGreen.setBackground(new Color(107, 142, 35));
		eastPanel.add(btnGreen);
		
	JButton btnRed = new JButton("Red");
		btnRed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				board.setGuess(Ball.RED);
			}
		});
		btnRed.setOpaque(true);
		btnRed.setBackground(new Color(220, 20, 60));
		btnRed.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		eastPanel.add(btnRed);
		
		JButton btnYellow = new JButton("Yellow");
		btnYellow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				board.setGuess(Ball.YELLOW);
			}
		});
		btnYellow.setOpaque(true);
		btnYellow.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		btnYellow.setBackground(new Color(255, 215, 0));
		eastPanel.add(btnYellow);
		
		JButton btnBlack = new JButton("Black");
		btnBlack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				board.setGuess(Ball.BLACK);
			}
		});
		btnBlack.setOpaque(true);
		btnBlack.setForeground(new Color(255, 255, 255));
		btnBlack.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		btnBlack.setBackground(new Color(0, 0, 0));
		eastPanel.add(btnBlack);
		
		JButton btnWhite = new JButton("White");
		btnWhite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				board.setGuess(Ball.WHITE);
			}
		});
		btnWhite.setOpaque(true);
		btnWhite.setBackground(new Color(255, 255, 255));
		btnWhite.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		eastPanel.add(btnWhite);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				pegs = board.checkCode(guess);
				if(board.checkWin(guess)) {
					for(int i = 0; i < 4; i++) {
						setBtn(hints[i],board.getCode(i));
					}
					answerLbl.revalidate();
					JOptionPane.showMessageDialog(null, "You Win!\n It took "  + board.getAttempts() + " attempts.");
					dispose();
				}
				if(board.getAttempts()>= 10 && !board.checkWin(guess)) {
					for(int i = 0; i < 4; i++) {
						setBtn(hints[i],board.getCode(i));
					}
					answerLbl.revalidate();
					JOptionPane.showMessageDialog(null, "You Lose!");
					dispose();
				}
				
				System.out.println("Black: " + pegs[0] + " White: " + pegs[1]);
				createPegPnl(panels[board.getAttempts()-1]);
				setPeg(pegs);
				panels[board.getAttempts()-1].revalidate();
				populateBtn(panels[board.getAttempts()]);
			}
		});
		btnSubmit.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		btnSubmit.setOpaque(true);
		btnSubmit.setBackground(new Color(192, 192, 192));
		eastPanel.add(btnSubmit);
		
		JButton btnHint = new JButton("Get A Hint");
		btnHint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setBtn(hints[hint],board.getCode(hint));
				answerLbl.revalidate();
				hint++;	
			}
		});
		btnHint.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		btnHint.setOpaque(true);
		btnHint.setBackground(new Color(192, 192, 192));
		eastPanel.add(btnHint);
		
		return eastPanel;
	}
	
	/**
	 * Creates my center panel that will hold the colors
	 * guessed and the pegs returned for the guesses
	 * @return
	 */
	private JPanel createCenterPanel() {
		JPanel centerPanel = new JPanel();
		centerPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		centerPanel.setLayout(new GridLayout(11, 0, 0, 0));
		
		attemptOne = new JPanel();
		attemptOne.setBorder(new EmptyBorder(3, 0, 0, 0));
		attemptOne.setBackground(new Color(245, 222, 179));
		centerPanel.add(attemptOne);
		attemptOne.setLayout(new GridLayout(0, 5, 0, 0));
		panels[0] = attemptOne;
		populateBtn(panels[0]);
		
		attemptTwo = new JPanel();
		attemptTwo.setBackground(new Color(245, 222, 179));
		centerPanel.add(attemptTwo);
		attemptTwo.setLayout(new GridLayout(0, 5, 0, 0));
		panels[1] = attemptTwo;
		
		attemptThree = new JPanel();
		attemptThree.setBackground(new Color(245, 222, 179));
		centerPanel.add(attemptThree);
		attemptThree.setLayout(new GridLayout(0, 5, 0, 0));
		panels[2] = attemptThree;
		
		attemptFour = new JPanel();
		attemptFour.setBackground(new Color(245, 222, 179));
		centerPanel.add(attemptFour);
		attemptFour.setLayout(new GridLayout(0, 5, 0, 0));
		panels[3] = attemptFour;
		
		attemptFive = new JPanel();
		attemptFive.setBackground(new Color(245, 222, 179));
		centerPanel.add(attemptFive);
		attemptFive.setLayout(new GridLayout(0, 5, 0, 0));
		panels[4] = attemptFive;
		
		attemptSix = new JPanel();
		attemptSix.setBackground(new Color(245, 222, 179));
		centerPanel.add(attemptSix);
		attemptSix.setLayout(new GridLayout(0, 5, 0, 0));
		panels[5] = attemptSix;
		
		attemptSeven = new JPanel();
		attemptSeven.setBackground(new Color(245, 222, 179));
		centerPanel.add(attemptSeven);
		attemptSeven.setLayout(new GridLayout(0, 5, 0, 0));
		panels[6] = attemptSeven;
		
		attemptEight = new JPanel();
		attemptEight.setBackground(new Color(245, 222, 179));
		centerPanel.add(attemptEight);
		attemptEight.setLayout(new GridLayout(0, 5, 0, 0));
		panels[7] = attemptEight;
		
		attemptNine = new JPanel();
		attemptNine.setBackground(new Color(245, 222, 179));
		centerPanel.add(attemptNine);
		attemptNine.setLayout(new GridLayout(0, 5, 0, 0));
		panels[8] = attemptNine; 
		
		attemptTen = new JPanel();
		attemptTen.setBackground(new Color(245, 222, 179));
		centerPanel.add(attemptTen);
		attemptTen.setLayout(new GridLayout(0, 5, 0, 0));
		panels[9] = attemptTen;
		
		JPanel answerLbl = createAnswerLbl();
		centerPanel.add(answerLbl);
		return centerPanel;
		
	}
	
	/**
	 * This creates a peg panel for each guess attempt
	 * which will then be populated once the user submits
	 * their guess
	 */
	private void createPegPnl(JPanel temp) {
		pegPnl = new JPanel();
		pegPnl.setLayout(new GridLayout(2, 0, 0, 0));

		
		JPanel pegPnl1 = new JPanel();
		pegPnl.add(pegPnl1);
		pegPnl1.setLayout(new GridLayout(1, 0, 0, 0));
		
		
		pegLbl1_1 = new JLabel("");
		pegLbl1_1.setOpaque(true);
		pegLbl1_1.setBackground(new Color(245, 222, 179));
		pegLbl1_1.setBorder(new LineBorder(Color.GRAY, 1, true));
		pegPnl1.add(pegLbl1_1);
		
		pegLbl1_2 = new JLabel("");
		pegLbl1_2.setOpaque(true);
		pegLbl1_2.setBackground(new Color(245, 222, 179));
		pegLbl1_2.setBorder(new LineBorder(Color.GRAY, 1, true));
		pegPnl1.add(pegLbl1_2);
		
		JPanel pegPnl2 = new JPanel();
		pegPnl.add(pegPnl2);
		pegPnl2.setLayout(new GridLayout(1, 0, 0, 0));
		
		pegLbl2_1 = new JLabel("");
		pegLbl2_1.setOpaque(true);
		pegLbl2_1.setBackground(new Color(245, 222, 179));
		pegLbl2_1.setBorder(new LineBorder(Color.GRAY, 1, true));
		pegPnl2.add(pegLbl2_1);
		
		pegLbl2_2 = new JLabel("");
		pegLbl2_2.setOpaque(true);
		pegLbl2_2.setBackground(new Color(245, 222, 179));
		pegLbl2_2.setBorder(new LineBorder(Color.GRAY, 1, true));
		pegPnl2.add(pegLbl2_2);
		
		temp.add(pegPnl);
		
	}
	
	/**
	 * Populates the new buttons into each
	 * new row after the user submits their guess
	 */
	private void populateBtn(JPanel temp) {
		JButton btn1 = new JButton("...");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guess[0] = board.getGuess();
				setBtn(btn1, board.getGuess());
			}
		});
		temp.add(btn1);
		
		JButton btn2 = new JButton("...");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guess[1] = board.getGuess();
				setBtn(btn2, board.getGuess());
			}
		});
		temp.add(btn2);
		
		JButton btn3 = new JButton("...");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guess[2] = board.getGuess();
				setBtn(btn3, board.getGuess());
			}
		});
		temp.add(btn3);
		
		JButton btn4 = new JButton("...");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guess[3] = board.getGuess();
				setBtn(btn4, board.getGuess());
			}
		});
		temp.add(btn4);
	}
	
	/**
	 * Creates the Label for the answers
	 * that get populated when the users
	 * click on get hint
	 */
	private JPanel createAnswerLbl() {
		answerLbl = new JPanel();
		answerLbl.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		answerLbl.setBackground(new Color(245, 222, 179));
		answerLbl.setLayout(new GridLayout(0, 5, 0, 0));
		
		btnAns1 = new JButton("...");
		hints[0] = btnAns1;
		answerLbl.add(btnAns1);
		
		btnAns2 = new JButton("...");
		answerLbl.add(btnAns2);
		hints[1] = btnAns2;
		
		btnAns3 = new JButton("...");
		answerLbl.add(btnAns3);
		hints[2] = btnAns3;
		
		btnAns4 = new JButton("...");
		answerLbl.add(btnAns4);
		hints[3] = btnAns4;
	
		return answerLbl;
	}
	
	/**
	 * Creates the west panel that just
	 * shows which turn number the user is on
	 */
	private JPanel createWestPanel() {
		JPanel westPanel = new JPanel();
		
		westPanel.setLayout(new GridLayout(11, 0, 0, 0));
		
		JLabel label_1 = new JLabel("1.");
		westPanel.add(label_1);
		
		JLabel label_2 = new JLabel("2.");
		westPanel.add(label_2);
		
		JLabel label_3 = new JLabel("3.");
		westPanel.add(label_3);
		
		JLabel label_4 = new JLabel("4.");
		westPanel.add(label_4);
		
		JLabel label_5 = new JLabel("5.");
		westPanel.add(label_5);
		
		JLabel label_6 = new JLabel("6.");
		westPanel.add(label_6);
		
		JLabel label_7 = new JLabel("7.");
		westPanel.add(label_7);
		
		JLabel label_8 = new JLabel("8.");
		westPanel.add(label_8);
		
		JLabel label_9 = new JLabel("9.");
		westPanel.add(label_9);
		
		JLabel label_10 = new JLabel("10.");
		westPanel.add(label_10);
		return westPanel;
	}
	
	/**
	 * Sets the color of the button
	 * that the user clicks on
	 */
	private void setBtn(JButton btnTemp, Ball b) {

		switch(b) {
			case BLUE:
				btnTemp.setOpaque(true);
				btnTemp.setIcon(new ImageIcon(GuiBoard.class.getResource("/images/blueCircle.png")));
				btnTemp.setBackground(new Color(245, 222, 179));
				btnTemp.setBorderPainted(false);
				btnTemp.setText("");
				break;
			case GREEN:
				btnTemp.setOpaque(true);
				btnTemp.setIcon(new ImageIcon(GuiBoard.class.getResource("/images/greenCircle.png")));
				btnTemp.setBackground(new Color(245, 222, 179));
				btnTemp.setBorderPainted(false);
				btnTemp.setText("");
				break;
			case YELLOW:
				btnTemp.setOpaque(true);
				btnTemp.setIcon(new ImageIcon(GuiBoard.class.getResource("/images/yellowCircle.png")));
				btnTemp.setBackground(new Color(245, 222, 179));
				btnTemp.setBorderPainted(false);
				btnTemp.setText("");
				break;
			case RED:
				btnTemp.setOpaque(true);
				btnTemp.setIcon(new ImageIcon(GuiBoard.class.getResource("/images/redCircle.png")));
				btnTemp.setBackground(new Color(245, 222, 179));
				btnTemp.setBorderPainted(false);
				btnTemp.setText("");
				break;
			case BLACK:
				btnTemp.setOpaque(true);
				btnTemp.setIcon(new ImageIcon(GuiBoard.class.getResource("/images/blackCircle.png")));
				btnTemp.setBackground(new Color(245, 222, 179));
				btnTemp.setBorderPainted(false);
				btnTemp.setText("");
				break;
			case WHITE:
				btnTemp.setOpaque(true);
				btnTemp.setIcon(new ImageIcon(GuiBoard.class.getResource("/images/whiteCircle.png")));
				btnTemp.setBackground(new Color(245, 222, 179));
				btnTemp.setBorderPainted(false);
				btnTemp.setText("");
				break;
			default :
				break;
		}
	}
	
	/**
	 * Sets the peg Colors for the previous
	 * turn after the user submitted their guess
	 * @param pegs
	 */
	private void setPeg(int [] pegs) {
		if(pegs[1] == 1) {
			pegLbl2_2.setBackground(Color.WHITE);
		}
		if(pegs[1] == 2) {
			pegLbl2_2.setBackground(Color.WHITE);
			pegLbl2_1.setBackground(Color.WHITE);
		}
		if(pegs[1] == 3) {
			pegLbl2_2.setBackground(Color.WHITE);
			pegLbl2_1.setBackground(Color.WHITE);
			pegLbl1_2.setBackground(Color.WHITE);
		}
		if(pegs[1] == 4) {
			pegLbl2_2.setBackground(Color.WHITE);
			pegLbl2_1.setBackground(Color.WHITE);
			pegLbl1_1.setBackground(Color.WHITE);
			pegLbl1_2.setBackground(Color.WHITE);
		}
		if(pegs[0] == 1 ) {
			pegLbl1_1.setBackground(Color.BLACK);
		}
		if(pegs[0] == 2 ) {
			pegLbl1_1.setBackground(Color.BLACK);
			pegLbl1_2.setBackground(Color.BLACK);
		}
		if(pegs[0] == 3 ) {
			pegLbl1_1.setBackground(Color.BLACK);
			pegLbl1_2.setBackground(Color.BLACK);
			pegLbl2_1.setBackground(Color.BLACK);
		}
	}
}

	


