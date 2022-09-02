/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.threads;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import rs.ac.bg.fon.ps.view.FrmMain;

/**
 *
 * @author ACER
 */
public class PrijavljeniKorisniciThread extends Thread {

	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy. HH:mm:ss");
	FrmMain frmMain;
	JLabel lblTabelaAzurirana;

	public PrijavljeniKorisniciThread(FrmMain frmMain, JLabel lblTabelaAzurirana) {
		this.frmMain = frmMain;
		this.lblTabelaAzurirana = lblTabelaAzurirana;
	}

	@Override
	public void run() {
		while (true) {

			frmMain.pripremiTabeluPrijavljenihKorisnika();
			lblTabelaAzurirana.setText(sdf.format(new Date()));
			try {
				sleep(10000);
			} catch (InterruptedException ex) {
				Logger.getLogger(PrijavljeniKorisniciThread.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

}
