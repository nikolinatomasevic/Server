/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository;

import java.util.List;

/**
 *
 * @author ACER
 */
public interface Repository<T> {

	Long nadjiID(T t) throws Exception;

	List<T> ucitajListu(T t) throws Exception;

	void zapamti(T t) throws Exception;

	void promeni(T t) throws Exception;

}
