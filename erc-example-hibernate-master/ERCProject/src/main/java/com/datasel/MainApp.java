package com.datasel;

import java.awt.Color;
import java.awt.Frame;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.datasel.erc.oracle.DbHelperOracle;
import com.datasel.patient.connection.jdbc.DbHelper;
import com.datasel.patient.entity.Patient;
import com.datasel.patient.panel.PatientPanel;


public class MainApp extends JFrame {
	
	public PatientPanel panel = new PatientPanel();
	
	public static void main(String[] args) {
		
		MainApp main= new MainApp("Patient Curd Example");
		main.add(main.panel);
		main.setSize(1000,700);
		main.setLocationRelativeTo(null);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setVisible(true);
		
	}
	public MainApp(String title) {
		super(title);
	}
}
