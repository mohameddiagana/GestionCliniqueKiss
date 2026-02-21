package sn.seck.GestionCliniqueKissi.Service;

import org.springframework.stereotype.Service;
import sn.seck.GestionCliniqueKissi.Model.Medecin;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface MedecinService {

    Medecin addNewMedecin(Medecin medecin);

     Optional <Medecin> getMedecinById(Long id);
    List<Medecin> listMedecin();
     void deleteMedecinById(Long id);
}
