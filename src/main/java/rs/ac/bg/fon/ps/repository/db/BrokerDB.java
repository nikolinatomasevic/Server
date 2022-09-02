/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.domain.OpstiDomenskiObjekat;
import rs.ac.bg.fon.ps.domain.StavkaReversa;

/**
 *
 * @author ACER
 */
public class BrokerDB implements RepositoryDB<OpstiDomenskiObjekat> {

	private Connection connection;

	@Override
	public List<OpstiDomenskiObjekat> ucitajListu(OpstiDomenskiObjekat odo) throws Exception {
		try {
			List<OpstiDomenskiObjekat> lista = new ArrayList<>();
			Connection connection = DBConnectionFactory.getInstance().getConnection();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT ").append(odo.vratiNaziveKolonaZaUcitajListu()).append(" FROM ")
					.append(odo.vratiNazivTabele()).append(" ").append(odo.vratiJoinUslov()).append(" ")
					.append(odo.vratiWhereUslovZaUcitajListu()).append(" ").append(odo.vratiOrderByUslov());
			String upit = sb.toString();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(upit);
			while (rs.next()) {
				lista.add(Converter.convert(odo, rs));
			}
			rs.close();
			statement.close();
			return lista;
		} catch (Exception ex) {
			System.out.println("Sistem ne moze da ucita listu " + odo.vratiNazivTabele() + "!\n" + ex.getMessage());
			throw new Exception("Sistem ne moze da ucita listu " + odo.vratiNazivTabele() + "!\n");
		}
	}

	@Override
	public void zapamti(OpstiDomenskiObjekat odo) throws Exception {
		try {
			StringBuilder sb = new StringBuilder();
			connection = DBConnectionFactory.getInstance().getConnection();
			sb.append("INSERT INTO ").append(odo.vratiNazivTabele()).append(" (")
					.append(odo.vratiNaziveKolonaZaZapamti()).append(") VALUES (").append(odo.vratiVrednostiZaZapamti())
					.append(")");
			String upit = sb.toString();
			Statement statement = connection.createStatement();
			statement.executeUpdate(upit);
			statement.close();
		} catch (Exception ex) {
			System.out.println("Sistem ne moze da zapamti objekat " + odo.vratiNazivTabele() + "!\n" + ex.getMessage());
			throw new Exception("Sistem ne moze da zapamti objekat " + odo.vratiNazivTabele() + "!\n");
		}
	}

	@Override
	public void promeni(OpstiDomenskiObjekat odo) throws Exception {
		try {
			StringBuilder sb = new StringBuilder();
			Connection connection = DBConnectionFactory.getInstance().getConnection();
			sb.append("UPDATE ").append(odo.vratiNazivTabele()).append(" SET ")
					.append(odo.vratiNaziveKolonaSaVrednostimaZaPromeni()).append(" WHERE ")
					.append(odo.vratiWhereUslovZaPromeni());
			String upit = sb.toString();
			Statement statement = connection.createStatement();
			statement.executeUpdate(upit);
			statement.close();
		} catch (Exception ex) {
			System.out.println("Sistem ne moze da zapamti objekat " + odo.vratiNazivTabele() + "!\n" + ex.getMessage());
			throw new Exception("Sistem ne moze da zapamti objekat " + odo.vratiNazivTabele() + "!\n");
		}
	}

	@Override
	public Long nadjiID(OpstiDomenskiObjekat odo) throws Exception {
		try {
			StringBuilder sb = new StringBuilder();
			connection = DBConnectionFactory.getInstance().getConnection();
			long id = 0;
			sb.append("SELECT max(").append(odo.vratiNazivKoloneID()).append(") as ID FROM ")
					.append(odo.vratiNazivTabele());
			String upit = sb.toString();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(upit);
			if (rs.next()) {
				id = rs.getLong("ID");
			}
			rs.close();
			statement.close();
			return id + 1;
		} catch (Exception ex) {
			System.out.println(
					"Sistem ne moze da nadje ID za objekat " + odo.vratiNazivTabele() + "!\n" + ex.getMessage());
			throw new Exception("Sistem ne moze da nadje ID za objekat " + odo.vratiNazivTabele() + "!\n");
		}
	}

	public int vratiRazduzenuKolicinu(StavkaReversa stavka) throws Exception {
		try {
			connection = DBConnectionFactory.getInstance().getConnection();
			int razduzenaKolicina = 0;
			String upit = "select razduzenaKolicina from StavkaReversa " + "where revers="
					+ stavka.getRevers().getBrojReversa() + " and rb=" + stavka.getRb();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(upit);
			if (rs.next()) {
				razduzenaKolicina = rs.getInt("razduzenaKolicina");
			}
			rs.close();
			statement.close();
			return razduzenaKolicina;
		} catch (Exception ex) {
			System.out.println("Sistem ne moze da nadje razduzenu kolicinu!\n" + ex.getMessage());
			throw new Exception("Sistem ne moze da nadje razduzenu kolicinu!\n");
		}
	}

}
