package SoccerApp.utility;

import java.util.List;
import java.util.Optional;

public interface ICRUD<T>{
	T save(T t);
	List<T> saveAll(List<T> t);
	Optional<T> update(T t);
	List<T> findAll();
	Optional<T> findByID(String id);
	boolean deleteByID(String id);
}