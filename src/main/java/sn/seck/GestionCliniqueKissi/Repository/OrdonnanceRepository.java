package sn.seck.GestionCliniqueKissi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import sn.seck.GestionCliniqueKissi.Model.Ordonnance;

import java.util.UUID;
@Repository
@CrossOrigin()
public interface OrdonnanceRepository extends JpaRepository<Ordonnance, UUID> {

    @Query(value = "SELECT SUM(o.prix * o.quantite) FROM Ordonnance o",nativeQuery = true)
    Double calculerTotal();
}
