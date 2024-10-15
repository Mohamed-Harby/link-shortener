package squadron.linkshortener.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
public class LinkAnalytics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Link link;
    private Timestamp visitDate;
    private String ipAddress;
    private String referrer;
}