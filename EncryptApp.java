import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * GUI and main class for Encryption App. Lets user encrypt or decrypt a message with provided key.
 */
public class EncryptApp extends JFrame implements ActionListener{
	
   //GUI Components and Class Variables
	private static final long serialVersionUID = 1L;
   private Encrypt e = new Encrypt();
	private JPanel panel = new JPanel();
	private JButton encBtn = new JButton("Encrypt");
	private JButton slvBtn = new JButton("Solve");
	private JButton clearBtn = new JButton("Clear");
	private JLabel msgLbl = new JLabel("Input:");
	private JLabel keyLbl = new JLabel("  Key:");
	private JTextArea message = new JTextArea(10,10);
	private JTextField key = new JTextField();
	private JTextArea answer = new JTextArea(10,30);
	private JScrollPane scroll;
	private JScrollPane msgScroll;
	
   /*
    * Constructor sets up GUI layout. Links ActionListener to appropriate JComponents
    */
	public EncryptApp(){
		
      //Try to set app icon
		try {
	        this.setIconImage(ImageIO.read(new File("res/EncryptIcon.png")));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
      //Style and size
		message.setColumns(30);
		key.setColumns(30);
		answer.setLineWrap(true);
		answer.setWrapStyleWord(true);
		message.setLineWrap(true);
		message.setWrapStyleWord(true);

		//ActionListener for JButtons
		this.encBtn.addActionListener(this);
		this.slvBtn.addActionListener(this);
		this.clearBtn.addActionListener(this);
		
      //Create scrollable area for text input
		scroll = new JScrollPane(answer);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		//Create scrollable area for text output
		msgScroll = new JScrollPane(message);
		msgScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		msgScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
      //Put everything into JPanel
		panel.add(msgLbl);
		panel.add(msgScroll);
		panel.add(keyLbl);
		panel.add(key);
		panel.add(encBtn);
		panel.add(slvBtn);
		panel.add(clearBtn);
		panel.add(scroll);
		
      //Put JPanel into this JFrame
		add(panel);
		
      //Setup JFrame attributes
		setSize(390,500);
		setTitle("Encrypter");
		setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setResizable(false);
      setVisible(true);
	}
	
   /*
    * Three buttons. Mainly calls appropriate logic from Encrypt class
    */
	public void actionPerformed(ActionEvent event) {
      
      //Get input from text areas
		String gMsg = message.getText();
		String gKey = key.getText();
		
      //Reset text areas to have a white background
		message.setBackground(Color.WHITE);
		key.setBackground(Color.WHITE);
		answer.setBackground(Color.WHITE);
		
      //'ENCRYPT' button
		if(event.getSource() == encBtn){
			
         //No input entered; highlight message text area
			if (gMsg.equals("")){
				message.setBackground(Color.RED);
				answer.setText("");
			}
         
         //No key entered; highlight key text area
			if(gKey.equals("")){
				key.setBackground(Color.RED);
				answer.setText("");
			}
         
         //Input and key entered; encrypt and display
			if(!(gKey.equals("") || gMsg.equals(""))){
				answer.setText(e.encrypt(gMsg,gKey));
			}
			
		}
      
      //'SOLVE' button
		else if(event.getSource() == slvBtn){
         
         //No input entered; highlight message text area
			if (gMsg.equals("")){
				message.setBackground(Color.RED);
				answer.setText("");
			}
         
         //No key entered; highlight key text area
			if(gKey.equals("")){
				key.setBackground(Color.RED);
				answer.setText("");
			}
         
         //Input and key entered; decrypt and display
			if(!(gKey.equals("") || gMsg.equals(""))){
				answer.setText(e.solve(gMsg,gKey));
			}
		}
      
      //'CLEAR' button
		else if(event.getSource() == clearBtn){
         
         //Remove all text from text areas
			message.setText("");
			key.setText("");
			answer.setText("");
		}
	}
	
   
   //Create instance of EncryptApp
	public static void main(String[] args){
		@SuppressWarnings("unused")
		EncryptApp app = new EncryptApp();
	}
}
