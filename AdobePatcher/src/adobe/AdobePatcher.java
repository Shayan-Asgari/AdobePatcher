package adobe;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class AdobePatcher extends JFrame
{
	private final String ltcAddress = "MEzkbbEiunUJv2CoXiKJcHUyhnsQrdxStU";
	private JComboBox<String> comboBox;
	private JFrame cryptoPopUp;
	private JButton patchButton;
	private final String address1 = "C:\\Program Files\\Adobe\\Adobe Photoshop CC 2018\\AMT\\application.xml";
	
	public AdobePatcher()
	{
		
		super("Adobe Patcher v1.0");
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		setSize(600, 350);
		setLayout(null);
		getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		cryptoPopUp = new JFrame();
		//dont want them to initially see the cryptoPopUp
		cryptoPopUp.setVisible(false);
		JLabel adobeLabel = new JLabel();
	
		ImageIcon renderedImage = getResourceImage("data/adobe.png",150,150);
	
		adobeLabel.setIcon(renderedImage);
		adobeLabel.setBounds(16, 25, 150,150);
		adobeLabel.setVisible(true);
		getContentPane().add(adobeLabel);

		// JLabel attributes
		JLabel lblChooseAdobeProduct = new JLabel("Choose Adobe Product:");
		lblChooseAdobeProduct.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		lblChooseAdobeProduct.setForeground(Color.WHITE);
		lblChooseAdobeProduct.setBounds(160, 25, 224, 53);
		getContentPane().add(lblChooseAdobeProduct);
		lblChooseAdobeProduct.setForeground(Color.BLACK);
		lblChooseAdobeProduct.setVisible(true);

		// patchButton attributes
		patchButton = new JButton("Patch!");
		patchButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
		patchButton.setBounds(290, 100, 150, 75);
		getContentPane().add(patchButton);
		PatchActionListener pa = new PatchActionListener();
		patchButton.addActionListener(pa);
		patchButton.setVisible(false);
		patchButton.setBackground(Color.WHITE);
		patchButton.setForeground(Color.BLACK);
		patchButton.setBorderPainted(true);

		// buttons in comboBox
		String[] adobeProducts = { "None", "Adobe PhotoShop"};
		comboBox = new JComboBox<>(adobeProducts);
		comboBox.setBounds(384, 40, 150, 25);
		getContentPane().add(comboBox);
		comboBox.setBackground(Color.WHITE);
		comboBox.setForeground(Color.BLACK);

		ComboBoxAction action = new ComboBoxAction();
		comboBox.addActionListener(action);
		
		JLabel imageOfCreator = new JLabel();
		Image rawImage = new ImageIcon("data/finallionCircular.png").getImage();
		Image renderedImage2 = rawImage.getScaledInstance(75, 60, Image.SCALE_SMOOTH);
		ImageIcon ic3 = new ImageIcon(renderedImage2);
		imageOfCreator.setIcon(ic3);
		imageOfCreator.setBounds(190,160, 75,60);
		getContentPane().add(imageOfCreator);
		
		JLabel socialMedia = new JLabel("Social Media: ");
		socialMedia.setFont(new Font("Times New Roman", Font.BOLD,22));
		socialMedia.setBounds(10, 230, 180, 40);
		socialMedia.setForeground(Color.WHITE);
		getContentPane().add(socialMedia);
		
		JButton instagramIcon = new JButton("Instagram");
		instagramIcon.setToolTipText("Link to Instagram");
		instagramIcon.addActionListener(new HyperLinkAction());
		Image rawImage1 = new ImageIcon("data/ig.png").getImage();
		Image renderedImage3 = rawImage1.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon ic4 = new ImageIcon(renderedImage3);
		instagramIcon.setIcon(ic4);
		instagramIcon.setBounds(150,230, 135,35);
		getContentPane().add(instagramIcon);
		
		JLabel photoDescription= new JLabel("Photo Of Creator: ");
		photoDescription.setFont(new Font("Times New Roman", Font.BOLD,22));
		photoDescription.setBounds(10, 180, 180, 40);
		photoDescription.setForeground(Color.WHITE);
		getContentPane().add(photoDescription);
		
		JLabel donate = new JLabel("Donate! :");
		donate.setFont(new Font("Times New Roman", Font.BOLD,22));

		donate.setForeground(Color.WHITE);
		donate.setBounds(10, 270, 180, 40);
		getContentPane().add(donate);
		
		JButton ltcIcon = new JButton();
		ltcIcon.setText("Litecoin");
		ImageIcon ic2 = (getResourceImage("data/ltc.png",25,25));
		ltcIcon.setIcon(ic2);
		ltcIcon.setBounds(110, 280,125,25 );
		ltcIcon.setToolTipText("Litecoin");

		ltcIcon.addActionListener(new CryptoPopUp());
		getContentPane().add(ltcIcon);
		
		this.repaint();
	}
	
	private class HyperLinkAction implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event) 
		{
			if(event.getActionCommand().equals("Instagram"))
			{
				try
				{
			        Desktop.getDesktop().browse(new URL
			        ("https://www.instagram.com/shayanasgari_/").toURI());
			    }
				catch (Exception e) 
				{
			        JOptionPane.showMessageDialog(null,e.getMessage());
			    }
			}
			
		}
		
	}
	private class DonationAction implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(e.getActionCommand().equals("Litecoin"));
			{
				new CryptoPopUp();
			}
		}
		
	}
	private ImageIcon getResourceImage(String filepath, int x, int y) 
	{
		Image rawImage = new ImageIcon(filepath).getImage();
		Image renderedImage = rawImage.getScaledInstance(x, y, Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(renderedImage);
		return image;
	}

	private class ComboBoxAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent event) 
		{
			if(event.getSource() == comboBox)
			{
				JComboBox cb = (JComboBox)event.getSource();
				String message = (String)cb.getSelectedItem();
				switch(message)
				{
					case "Adobe PhotoShop":
						patchButton.addActionListener(new PatchActionListener());
						patchButton.setVisible(true);
						break;
					default:
						patchButton.setVisible(false);
						break;
				}
			}
		}
		
	}
	private class PatchActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String[] option = {"Close"};
			int returnedValue = editText(address1);
			System.out.println(returnedValue);
			if(returnedValue==1)
			{
				JOptionPane.showOptionDialog
				(null, "Patch was successfully installed.\n Thank you for using the product!", 
						"Successfully Patched", JOptionPane.DEFAULT_OPTION, 
						JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);
				
			}
			else if(returnedValue==-1)
			{
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog
				(null, "Trial Serial Number change was interrupted by unexpected changes. "
				+ "Close all window folder tabs and try patch again.",
				"Patch failed", JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}
			else if(returnedValue==-2)
			{
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog
				(null,
				"Adobe folder was not found. Try reinstalling Adobe product.\nAdobe Patcher will now close "
				+ "(Error 565).",
				"Patch failed", JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}
			else if(returnedValue==-3)
			{
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, "Patch failed. \nLetters were found in the Trial "
				+ "Serial Number.",
				"Patch failed", JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}
			
		}
		
	}
	private class CryptoPopUp implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(e.getActionCommand().equals("Litecoin"))
			{
				cryptoPopUp.setTitle("Litecoin Donation");
				cryptoPopUp.setSize(800,500);
				cryptoPopUp.setLayout(null);
				cryptoPopUp.setLocationRelativeTo(null);
				
				cryptoPopUp.setVisible(true);
				JLabel ltcBackground = new JLabel();
				Image backgroundImage = new ImageIcon("data/ltcbackground.png").getImage();
				Image backgroundScaled =  backgroundImage.getScaledInstance(800,500, Image.SCALE_DEFAULT);
				ImageIcon backgroundIcon = new ImageIcon(backgroundScaled);
				ltcBackground.setIcon(backgroundIcon);
				ltcBackground.setBounds(0,0,800,500);
			
				
				JLabel ltcAddressLabel = new JLabel();
				ltcAddressLabel.setText(ltcAddress);
				ltcAddressLabel.setFont(new Font("Times New Roman", Font.PLAIN,22));
				ltcAddressLabel.setForeground(Color.WHITE);
				ltcAddressLabel.setBounds(275, 250, 400, 50);
				ltcBackground.add(ltcAddressLabel);
				
				JLabel ltcText = new JLabel();
				ltcText.setText("LTC Address: ");
				ltcText.setFont(new Font("Times New Roman", Font.PLAIN,26));
				ltcText.setForeground(Color.WHITE);
				ltcText.setBounds(120, 250, 300, 50);
				ltcBackground.add(ltcText);
			
				
				JLabel qrCode = new JLabel();
				Image qrImage = new ImageIcon("data/ltcqrcode.png").getImage();
				Image qrScaled = qrImage.getScaledInstance(150, 150, Image.SCALE_DEFAULT);
				ImageIcon qrIcon = new ImageIcon(qrScaled);
				qrCode.setIcon(qrIcon);
				qrCode.setBounds(320,300,150,150);
				ltcBackground.add(qrCode);
				
				JButton okayButton = new JButton();
				okayButton.setText("Copy to Clipboard and Close");
				okayButton.setBounds(480, 370, 200, 30);
				ltcBackground.add(okayButton);
				okayButton.addActionListener(new CryptoPopUp());
				
				cryptoPopUp.getContentPane().add(ltcBackground);
				cryptoPopUp.repaint();
			}
			else if(e.getActionCommand().equals("Copy to Clipboard and Close"))
			{
				StringSelection stringSelection = new StringSelection(ltcAddress);
			    Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
			    clpbrd.setContents(stringSelection, null);
			    JOptionPane.showMessageDialog(null, "                      Copied to Clipboard","", JOptionPane.CLOSED_OPTION);
				cryptoPopUp.setVisible(false);
				cryptoPopUp.dispose();
			}
		
		}
	}
	
	private int editText(String address)
	{
		String fileAddress = (address);
		DocumentBuilderFactory docFactory;
		DocumentBuilder docBuilder;
		Document doc;

		try 
		{
			docFactory = DocumentBuilderFactory.newInstance();
			docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.parse(fileAddress);
		} 
		catch (Exception e) 
		{

			return -2;
		}
		
		Random random = new Random();
		int a;
		int b;

		Node trialNumNode = doc.getElementsByTagName("Data").item(18);
		String oldTrialNum = trialNumNode.getTextContent();

		if (!oldTrialNum.matches("[0-9]+")) //To check if trial number contains at least one number between 0-9
			return -3;
			
		String newTrialNumber = oldTrialNum;
		while (newTrialNumber.equals(oldTrialNum)) 
		{
			a = random.nextInt(9);// new number
			b = random.nextInt(23);// index to change
			newTrialNumber = oldTrialNum.substring(0, b) + a + oldTrialNum.substring(b + 1, 24);
		}
		Path path = Paths.get("C:\\Program Files\\Adobe\\Adobe Photoshop CC 2018\\AMT\\application.xml");
		Charset charset = StandardCharsets.UTF_8;
		String content;
		try 
		{
			content = new String(Files.readAllBytes(path), charset);
			content = content.replaceAll(oldTrialNum,newTrialNumber);
			Files.write(path, content.getBytes(charset));
		} 
		catch (IOException e) 
		{
			return -4;
		}
		return 1;
	}
	public static void main(String[] args)
	{
		AdobePatcher adobePatcher = new AdobePatcher();
	}
}

