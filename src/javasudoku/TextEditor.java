/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasudoku;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TextEditor {
	
	private static final int EDIT_ROWS = 20;
	private static final int EDIT_COLS = 40;
	
	private static final int NUM_CHARS = 15;
	
	private static JTextArea editor;

	public TextEditor(String string) {
		editor = new JTextArea (EDIT_ROWS, EDIT_COLS);
		JScrollPane editorScroller = new JScrollPane (editor);
		editorScroller.setBorder(BorderFactory.createTitledBorder ("Editing area"));
		JFrame frame = new JFrame(string);
		Container contentPane = frame.getContentPane();
		contentPane.add (editorScroller, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel filePanel = new JPanel();
		
		JPanel buttonPanel = new JPanel();
		
		buttonPanel.add (filePanel);
		contentPane.add (buttonPanel, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
	}

	public static void DosyadanOkuma(String name) {
            File loadFile = new File(name);
			try {
                            BufferedReader in = new BufferedReader (new FileReader(loadFile));
                            editor.setText("");
                            String nextLine = in.readLine();
                            while (nextLine != null) {
                                editor.append(nextLine + "\n");
					nextLine = in.readLine();
				}
				in.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Could not load the file " + e.getMessage());
			}

	}
}

