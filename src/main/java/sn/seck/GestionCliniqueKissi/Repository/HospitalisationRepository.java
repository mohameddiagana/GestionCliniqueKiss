package sn.seck.GestionCliniqueKissi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import sn.seck.GestionCliniqueKissi.Model.Hospitalisation;

@Repository()
@CrossOrigin("*")
public interface HospitalisationRepository extends JpaRepository<Hospitalisation, Long> {

}
