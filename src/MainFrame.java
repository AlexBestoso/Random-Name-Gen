/*******************************************************
 ** Original code written by Christopher Kamps        **         
 ** XML file reader and writer written by Alex Bestoso** 
 **                                                   ** 
 *******************************************************/
import java.util.Scanner;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRadioButton;

//imports for reader/writer
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
//imports for reader/writer~

import java.util.Properties.*;

public class MainFrame extends JFrame {

	public JPanel contentPane;
	public JTextField textField;
	public JTextPane textPane;
	public JButton btnNewButton;
	private JButton btnStudent1;
	private JButton btnStudent2;
	private JButton btnStudent3;
	private JButton btnStudent4;
	private JButton btnStudent5;
	private JButton btnStudent6;
	private JButton btnStudent7;
	private JButton btnStudent8;
	private JButton btnStudent9;
	private JButton btnStudent10;
   private JButton btnStudent11;
	private JButton btnStudent12;
	private ArrayList<String> names = new ArrayList<String>();
	private JButton btnNewButton_1;
   
   
	public static void main(String[] args) {
   
         
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainFrame() {
      String choice;
      Scanner userInput = new Scanner(System.in);
	//start-up menu    
      System.out.print("If you are using new names please enter: yes\n If you would like to use old names, press enter\n"); 
      choice = userInput.nextLine();
	//end start-up menu
      if(choice.equals("yes")){
                     //The file writer
         Scanner nameInput = new Scanner(System.in);               
         try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            
            //root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("students");
            doc.appendChild(rootElement);
            
            //student elements
            for(int i =0; i<12; i++){ 
            Element student = doc.createElement("student");
            System.out.println("Enter name " + i + ": ");
            student.setAttribute("name", userInput.nextLine());  
            rootElement.appendChild(student);
            }
            //set attribute to student element
                   
            //write to the xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("students.xml"));
            
            transformer.transform(source, result);
            
            System.out.println("[SAVE SUCCESSFUL]");
            
         }catch(ParserConfigurationException | TransformerException e){
            e.printStackTrace();
         }            
                     //The File Writer~
      }else{
         System.out.println("using old values");
      }               
      
                     //The file reader
                      
      String[] studentArray;
      studentArray = new String[12];
      DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
      try{
         DocumentBuilder dBuilder = builderFactory.newDocumentBuilder();
         Document document = dBuilder.parse(MainFrame.class.getResourceAsStream("/students.xml"));
         
         NodeList rootNodes = document.getElementsByTagName("students");
         Node rootNode = rootNodes.item(0);
         Element rootElement = (Element) rootNode;
         NodeList studentsList = rootElement.getElementsByTagName("student");
         for (int i = 0; i < studentsList.getLength(); i++){
            Node theStudent = studentsList.item(i);
            Element studentElement = (Element) theStudent;
            
            studentArray[i] = studentElement.getAttribute("name");
         }  
      }catch(ParserConfigurationException e){
         e.printStackTrace();
      }catch(SAXException e){
         e.printStackTrace();
      }catch(IOException e){
         e.printStackTrace();
      }
      
                   //The File reader~
          
  
		setTitle("Name Generator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 458, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(155, 11, 114, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNumberOfQuestions = new JLabel("Number of Questions: ");
		lblNumberOfQuestions.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumberOfQuestions.setBounds(10, 14, 135, 14);
		contentPane.add(lblNumberOfQuestions);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 73, 259, 301);
		contentPane.add(scrollPane);
		
		textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		textPane.setEditable(false);
		
		btnNewButton = new JButton("Generate Order");
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				NameArray nameArray = new NameArray(names, Integer.valueOf(textField.getText()));
				ArrayList<String> array = nameArray.getArray();
				for(int loop = 0; loop <= array.size(); loop++){
					
					textPane.setText(textPane.getText() + "Question " + (loop + 1) + ": " + array.get(loop) + "\n");
				}
			}
		});
		btnNewButton.setBounds(10, 39, 259, 23);
		contentPane.add(btnNewButton);
		
      
		btnStudent1 = new JButton(studentArray[0]);
      names.add(studentArray[0]);
		btnStudent1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	   
               btnStudent1.setEnabled(false);
               names.remove(studentArray[0]);
            
			}
		});
		btnStudent1.setBounds(279, 11, 158, 23);
		contentPane.add(btnStudent1);
		
		btnStudent2 = new JButton(studentArray[1]);
      names.add(studentArray[1]);
		btnStudent2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			   btnStudent2.setEnabled(false);
            names.remove(studentArray[1]);
			}
		});
		btnStudent2.setBounds(279, 45, 158, 23);
		contentPane.add(btnStudent2);
		
		btnStudent3 = new JButton(studentArray[2]);
      names.add(studentArray[2]);
		btnStudent3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStudent3.setEnabled(false);
            names.remove(studentArray[2]);
			}
		});
		btnStudent3.setBounds(279, 79, 158, 23);
		contentPane.add(btnStudent3);
		
		btnStudent4 = new JButton(studentArray[3]);
      names.add(studentArray[3]);
		btnStudent4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStudent4.setEnabled(false);
            names.remove(studentArray[3]);
			}
		});
		btnStudent4.setBounds(279, 113, 158, 23);
		contentPane.add(btnStudent4);
		
		btnStudent5 = new JButton(studentArray[4]);
      names.add(studentArray[4]);
		btnStudent5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStudent5.setEnabled(false);
            names.remove(studentArray[4]);
			}
		});
		btnStudent5.setBounds(279, 147, 158, 23);
		contentPane.add(btnStudent5);
		
		btnStudent6 = new JButton(studentArray[5]);
      names.add(studentArray[5]);
		btnStudent6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStudent6.setEnabled(false);
            names.remove(studentArray[5]);
			}
		});
		btnStudent6.setBounds(279, 181, 158, 23);
		contentPane.add(btnStudent6);
		
		btnStudent7 = new JButton(studentArray[6]);
      names.add(studentArray[6]);
		btnStudent7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStudent7.setEnabled(false);
            names.remove(studentArray[6]);
			}
		});
		btnStudent7.setBounds(279, 215, 158, 23);
		contentPane.add(btnStudent7);
		
		btnStudent8 = new JButton(studentArray[7]);
      names.add(studentArray[7]);
		btnStudent8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStudent8.setEnabled(false);
            names.remove(studentArray[7]);
			}
		});
		btnStudent8.setBounds(279, 249, 158, 23);
		contentPane.add(btnStudent8);
		
		btnStudent9 = new JButton(studentArray[8]);
      names.add(studentArray[8]);
		btnStudent9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStudent9.setEnabled(false);
            names.remove(studentArray[8]);
			}
		});
		btnStudent9.setBounds(279, 283, 158, 23);
		contentPane.add(btnStudent9);
		
		btnStudent10 = new JButton(studentArray[9]);
      names.add(studentArray[9]);
		btnStudent10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStudent10.setEnabled(false);
            names.remove(studentArray[9]);
			}
		});
		btnStudent10.setBounds(279, 317, 158, 23);
		contentPane.add(btnStudent10);
      
      
		
		btnStudent11 = new JButton(studentArray[10]);
      names.add(studentArray[10]);
		btnStudent11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStudent11.setEnabled(false);
            names.remove(studentArray[10]);
			}
		});
		btnStudent11.setBounds(279, 351, 158, 23);
		contentPane.add(btnStudent11);  
      
     
      btnStudent12 = new JButton(studentArray[11]);
      names.add(studentArray[11]);
		btnStudent12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStudent12.setEnabled(false);
            names.remove(studentArray[11]);
			}
		});
		btnStudent12.setBounds(279, 385, 158, 23);
		contentPane.add(btnStudent12);      
		
		btnNewButton_1 = new JButton("Reset");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("Reset");
				textPane.setText("Reset");
				btnStudent1.setEnabled(true);
				btnStudent2.setEnabled(true);
				btnStudent3.setEnabled(true);
				btnStudent4.setEnabled(true);
				btnStudent5.setEnabled(true);
				btnStudent6.setEnabled(true);
				btnStudent7.setEnabled(true);
				btnStudent8.setEnabled(true);
				btnStudent9.setEnabled(true);
				btnStudent10.setEnabled(true);
				btnStudent11.setEnabled(true);
            btnStudent12.setEnabled(true);
				names = new ArrayList<String>();
			}
		});     
  
	}
}

class NameArray{

	   ArrayList<String> names, names2, order;
	   int questions;
	   
	   public NameArray(ArrayList<String> namesToUse, int questions){
	   
	      this.questions = questions;
	      names = namesToUse;
	      names2 = new ArrayList<String>();
	      order = new ArrayList<String>();
	      getOrder();
	   }
	   
	   private void getOrder(){
	      for(int loop = 0; loop < questions; loop++){
	         studentGo();
	      }
	   }
	   
	   private void studentGo(){
	      
	      if(names.size() == 0){
	         for(int loop = 0; loop < names2.size(); loop++){
	            names.add(names2.get(0));
	            names2.remove(0);
	         }
	      }
	      
	      int rand = (int)Math.floor(Math.random() * names.size());
	      
	      order.add(names.get(rand));
	      names2.add(names.get(rand));
	      names.remove(names.get(rand));
	   }
	   
	   public ArrayList<String> getArray(){
		   
		   return order;
	   }
}
