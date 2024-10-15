package squadron.linkshortener.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import squadron.linkshortener.models.Link;

import java.util.Optional;

public interface LinkRepository extends JpaRepository<Link, Long> {
    Optional<Link> findByShortUrl(String shortUrl);
}