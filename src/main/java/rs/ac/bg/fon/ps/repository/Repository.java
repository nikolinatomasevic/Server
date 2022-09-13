/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository;

import java.util.List;

/**
 * Predstavlja genericki interfejs koji ima osnovne operacije koje omogucavaju perzistenciju.
 *
 * @author nikolinatomasevic
 */
public interface Repository<T> {

	/**
	 * Pronalazi i vraca novi jedinstveni ID za objekat koji je prosledjen kao ulazni parametar.
	 * 
	 * @param t objekat ciji se ID trazi
	 * @return ID objekta kao ceo broj tipa Long
	 * @throws Exception u slucaju da dodje do greske prilikom pronalaska ID-a
	 */
	Long nadjiID(T t) throws Exception;

	/**
	 * Ucitava i vraca listu objekata tipa klase objekta koji je prosledjen kao ulazni parametar.
	 * Kriterijumi pretrage mogu se proslediti kroz ulazni parametar.
	 * 
	 * @param t kriterijum pretrage
	 * @return lista objekata tipa klase objekta koji je prosledjen kao ulazni parametar
	 * @throws Exception u slucaju da dodje do greske prilikom ucitavanja liste objekata
	 */
	List<T> ucitajListu(T t) throws Exception;

	/**
	 * Pamti (cuva) objekat koji je prosledjen kao ulazni parametar.
	 * 
	 * @param t objekat koji je potrebno sacuvati
	 * @throws Exception u slucaju da dodje do greske prilikom cuvanja obejkta
	 */
	void zapamti(T t) throws Exception;

	/**
	 * Menja objekat ciji su ID i vrednosti koje treba izmeniti prosledjene kao ulazni parametar.
	 * 
	 * @param t objekat koji je potrebno izmeniti
	 * @throws Exception u slucaju da dodje do greske prilikom izmene obejkta
	 */
	void promeni(T t) throws Exception;

}
