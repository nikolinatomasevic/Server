/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view;

import rs.ac.bg.fon.ps.domain.Magacioner;
import java.awt.Color;
import java.io.IOException;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import rs.ac.bg.fon.ps.threads.ServerThread;
import rs.ac.bg.fon.ps.threads.PrijavljeniKorisniciThread;

/**
 *
 * @author nikolinatomasevic
 */
public class FrmMain extends javax.swing.JFrame {

	/**
	 * Creates new form FrmMain
	 */
	private ServerThread serverThread;

	public FrmMain() {
		initComponents();
		lblStatusServera.setText("Server nije pokrenut.");
		btnZaustaviServer.setEnabled(false);
		lblTAzurirana.setVisible(false);
		lblTabelaAzurirana.setVisible(false);
		PrijavljeniKorisniciThread tabela = new PrijavljeniKorisniciThread(this, lblTabelaAzurirana);
		tabela.start();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		tblPrijavljeniKorisnici = new javax.swing.JTable();
		jLabel1 = new javax.swing.JLabel();
		btnPokreniServer = new javax.swing.JButton();
		btnZaustaviServer = new javax.swing.JButton();
		lblTAzurirana = new javax.swing.JLabel();
		lblStatusServera = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		lblTabelaAzurirana = new javax.swing.JLabel();
		mbMainMenu = new javax.swing.JMenuBar();
		jMenu2 = new javax.swing.JMenu();
		miKonfiguracijaBazePodataka = new javax.swing.JMenuItem();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Server Program");

		tblPrijavljeniKorisnici.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
		tblPrijavljeniKorisnici.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

		}, new String[] { "Ime i prezime", "Korisnicko ime" }));
		jScrollPane1.setViewportView(tblPrijavljeniKorisnici);

		jLabel1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setText("Prijavljeni korisnici:");

		btnPokreniServer.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
		btnPokreniServer.setText("Pokreni server");
		btnPokreniServer.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnPokreniServerActionPerformed(evt);
			}
		});

		btnZaustaviServer.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
		btnZaustaviServer.setText("Zaustavi server");
		btnZaustaviServer.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnZaustaviServerActionPerformed(evt);
			}
		});

		lblTAzurirana.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
		lblTAzurirana.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblTAzurirana.setText("Tabela azurirana: ");

		lblStatusServera.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
		lblStatusServera.setForeground(new java.awt.Color(255, 0, 0));
		lblStatusServera.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

		jLabel3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
		jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel3.setText("Status servera:");

		lblTabelaAzurirana.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
		lblTabelaAzurirana.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

		jMenu2.setText("Konfiguracija servera");
		jMenu2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

		miKonfiguracijaBazePodataka.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		miKonfiguracijaBazePodataka.setText("Konfiguracija baze podataka");
		miKonfiguracijaBazePodataka.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				miKonfiguracijaBazePodatakaActionPerformed(evt);
			}
		});
		jMenu2.add(miKonfiguracijaBazePodataka);

		mbMainMenu.add(jMenu2);

		setJMenuBar(mbMainMenu);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
						.createSequentialGroup()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
								.addGroup(layout.createSequentialGroup()
										.addComponent(lblTAzurirana, javax.swing.GroupLayout.PREFERRED_SIZE, 153,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addComponent(lblTabelaAzurirana, javax.swing.GroupLayout.PREFERRED_SIZE, 153,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(0, 0, Short.MAX_VALUE)))
						.addContainerGap())
						.addGroup(layout.createSequentialGroup().addGroup(layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 153,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(btnPokreniServer, javax.swing.GroupLayout.PREFERRED_SIZE,
														153, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addComponent(lblStatusServera, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btnZaustaviServer, javax.swing.GroupLayout.DEFAULT_SIZE,
														153, Short.MAX_VALUE)))
								.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 153,
										javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(0, 0, Short.MAX_VALUE)))));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(lblStatusServera, javax.swing.GroupLayout.PREFERRED_SIZE, 32,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32,
								javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
						.addComponent(btnPokreniServer, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
						.addComponent(btnZaustaviServer, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
				.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32,
						javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGap(18, 18, 18)
				.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158,
						javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGap(18, 18, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(lblTabelaAzurirana, javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTAzurirana, javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void miKonfiguracijaBazePodatakaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_miKonfiguracijaBazePodatakaActionPerformed
		FrmKonfiguracijaBazePodataka frmKonfiguracijaBazePodataka = new FrmKonfiguracijaBazePodataka(this, true);
		frmKonfiguracijaBazePodataka.setLocationRelativeTo(null);
		frmKonfiguracijaBazePodataka.setVisible(true);
	}// GEN-LAST:event_miKonfiguracijaBazePodatakaActionPerformed

	private void btnPokreniServerActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnPokreniServerActionPerformed
		if (serverThread == null || !serverThread.isAlive()) {
			try {
				serverThread = new ServerThread();
				serverThread.start();
				btnPokreniServer.setEnabled(false);
				btnZaustaviServer.setEnabled(true);
				lblStatusServera.setForeground(Color.GREEN);
				lblStatusServera.setText("Server je pokrenut.");
				lblTAzurirana.setVisible(true);
				lblTabelaAzurirana.setVisible(true);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}// GEN-LAST:event_btnPokreniServerActionPerformed

	private void btnZaustaviServerActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnZaustaviServerActionPerformed
		if (serverThread.getServerSocket() != null && serverThread.getServerSocket().isBound()) {
			try {
				serverThread.getServerSocket().close();
				btnPokreniServer.setEnabled(true);
				btnZaustaviServer.setEnabled(false);
				lblStatusServera.setForeground(Color.RED);
				lblStatusServera.setText("Server nije pokrenut.");
				DefaultTableModel model = (DefaultTableModel) tblPrijavljeniKorisnici.getModel();
				model.setRowCount(0);
				lblTAzurirana.setVisible(false);
				lblTabelaAzurirana.setVisible(false);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}// GEN-LAST:event_btnZaustaviServerActionPerformed

	/**
	 * @param args the command line arguments
	 */

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnPokreniServer;
	private javax.swing.JButton btnZaustaviServer;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JMenu jMenu2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JLabel lblStatusServera;
	private javax.swing.JLabel lblTAzurirana;
	private javax.swing.JLabel lblTabelaAzurirana;
	private javax.swing.JMenuBar mbMainMenu;
	private javax.swing.JMenuItem miKonfiguracijaBazePodataka;
	private javax.swing.JTable tblPrijavljeniKorisnici;
	// End of variables declaration//GEN-END:variables

	public void pripremiTabeluPrijavljenihKorisnika() {
		DefaultTableModel model = (DefaultTableModel) tblPrijavljeniKorisnici.getModel();
		if (serverThread == null || !serverThread.isAlive()) {
			model.setRowCount(0);
			model.fireTableDataChanged();
		} else {
			model.setRowCount(0);
			List<Magacioner> magacioneri = serverThread.vratiPrijavljeneKorisnike();
			for (Magacioner magacioner : magacioneri) {
				if (magacioner != null) {
					Object[] red = new Object[] { magacioner.getIme() + " " + magacioner.getPrezime(),
							magacioner.getMejl() };
					model.addRow(red);
				}
			}
			model.fireTableDataChanged();
		}
	}
}
