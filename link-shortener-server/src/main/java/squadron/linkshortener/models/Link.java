package squadron.linkshortener.models;


import jakarta.persistence.*;
import lombok.Data;

import java.net.URI;
import java.net.URL;
import java.sql.Timestamp;

@Entity
@Data
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalUrl;
    private String shortUrl;
    @ManyToOne
    private User user;
    private Timestamp creaTimestamp;
    private Timestamp expiTimestamp;
}
