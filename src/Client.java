import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Client extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private static DataInputStream input;
	private static DataOutputStream output;
	private static ServerSocket socket;

	private JPanel contentPane;
	private JTextField textField;
	private static JTextArea textArea;
	private JButton btnSend;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Client().setVisible(true);
			}
		});

		try {
			// Server port JAVA - Preferences - Networking.
			Socket serverSocket = new Socket("127.0.0.1", 9090);
			System.out.println(serverSocket + "\nServer Connected");

			input = new DataInputStream(serverSocket.getInputStream());
			output = new DataOutputStream(serverSocket.getOutputStream());

			// BufferedReader br = new BufferedReader(new
			// InputStreamReader(System.in));

			String inMessage = "";

			while (inMessage != null) {
				// String inMessage = "";
				inMessage = input.readUTF();

				textArea.setText(textArea.getText() + "\n" + inMessage.toString());
			}
			// close server
			input.close();
			output.close();
			serverSocket.close();
			System.exit(0);
			
		} catch (Exception e) {
			System.out.println("Server does not connect....");

		}

	}

	private void intiateComponents() {
		// TODO Auto-generated method stub

	}

	/**
	 * Create the frame.
	 */
	public Client() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textArea = new JTextArea();
		textArea.setBounds(10, 11, 414, 178);
		contentPane.add(textArea);

		textField = new JTextField();
		textField.setBounds(10, 200, 315, 50);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setColumns(10);

		btnSend = new JButton("send");
		btnSend.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Client Btn");
				try {
					String userOutput = "Client: ";
					userOutput += textField.getText();
					output.writeUTF(userOutput);
					textArea.setText(textArea.getText()+"\n"+userOutput);
				} catch (Exception e) {
					e.printStackTrace();

				}
			}
		});
		btnSend.setBounds(335, 214, 89, 23);
		contentPane.add(btnSend);
	}
}
