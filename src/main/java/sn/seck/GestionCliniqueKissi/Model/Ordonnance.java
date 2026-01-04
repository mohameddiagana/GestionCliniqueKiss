package sn.seck.GestionCliniqueKissi.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

import org.springframework.boot.actuate.audit.listener.AuditListener;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Data
@Getter
@Setter
@ToString
@Builder
@EntityListeners(AuditingEntityListener.class)

@Table(name = "ordonnance")

@AllArgsConstructor @NoArgsConstructor
public class Ordonnance {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idordonnance;

    @Column(nullable = false,name = "numOrdonnance",length = 100)
    private String numOrdonnance;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "GMT")
    @Column(name = "dateOrdonnance", nullable =false)
    @CreatedDate
    private Date dateOrdonnance;
    @Column(name = "formatOrdonnance",nullable = false)
    private String formatOrdonnance;

    @Column(name = "prix",nullable = false)
    private double prix;

    @Column(name = "total",nullable = false)
    private double total;
    @Column(name = "quantite",nullable = false)
    private int quantite;

//    public double calculerTotal() {
//        return prix * quantite;
//    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "medecin_id")
    private Medecin medecin;

}
