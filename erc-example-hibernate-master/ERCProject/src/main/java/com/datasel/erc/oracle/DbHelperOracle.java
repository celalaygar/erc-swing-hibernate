package com.datasel.erc.oracle;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import com.datasel.patient.entity.Patient;

public class DbHelperOracle {
	private Session session;

	public DbHelperOracle() {
		session = DbConnection.getSession();
		System.out.println("Connected to the database! with hibernate");
	}

	public ArrayList<Patient> getAll() {
		try {
			Criteria main = session.createCriteria(Patient.class);
			main.addOrder(Order.asc("patientid"));
			return (ArrayList<Patient>) main.list();
		} catch (Exception e) {
			System.out.println("Problem getAll patient : " + e);

			return new ArrayList<>();
		}
	}

	public void save(Patient patient) {
		try {
			session.beginTransaction();
			session.save(patient);
		} catch (HibernateException ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
		}
	}
	public void savePatientFromObject(Patient patient) {
		String sql = "INSERT INTO AAPATIENT (patientid, name, lastname, age, gender, city) VALUES ("
		+patient.getPatientid()+",'"
		+patient.getName()+"','"
		+patient.getLastname()+"','"
		+patient.getAge()+"','"
		+patient.getGender()+"','"
		+patient.getCity()+"')";
		try {
			session.beginTransaction();
			// String sql ="INSERT INTO AAPATIENT VALUES (?,?,?,?,?,?)";

			Query query = session.createQuery(sql);
			int result = query.executeUpdate();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			System.out.println("problem save override : " + e);
		} finally {
			session.getTransaction().commit();
			JOptionPane.showMessageDialog(null,
					"This Patient is saved in Database : ");
		}
		
		
//		try {
//			session.beginTransaction();
//			session.save(patient);
//		} catch (HibernateException ex) {
//			JOptionPane.showMessageDialog(
//					null,
//					"This Patient is already saved in Database : "
//							+ patient.getName() + " " + patient.getLastname());
//			ex.printStackTrace();
//			session.getTransaction().rollback();
//		} finally {
//			session.getTransaction().commit();
//			JOptionPane.showMessageDialog(null,
//					"This Patient is saved in Database : ");
//		}
	}
	public void update(Patient patient) {
		session.beginTransaction();
		try {
			session.update(patient);
		} catch (HibernateException ex) {
			System.out.println("problem : update patient : " + ex);
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
		}
	}

	public void delete(ArrayList<Patient> patients) {
		for (Patient patient : patients) {
			if (patient.isSelected()) {
				session.beginTransaction();
				session.delete(patient);
				session.getTransaction().commit();
			}
		}
	}

	public void close() {
		session.close();
	}
}
