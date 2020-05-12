package co.edu.icesi.fi.tics.tssc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;

@Repository
public interface TsscAdminRepository extends CrudRepository<TsscAdmin, Long>{

	TsscAdmin findByUser(String user);

}