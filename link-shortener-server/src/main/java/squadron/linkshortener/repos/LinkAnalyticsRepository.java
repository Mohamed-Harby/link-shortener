package squadron.linkshortener.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import squadron.linkshortener.models.LinkAnalytics;

public interface LinkAnalyticsRepository extends JpaRepository<LinkAnalytics, Long> {
}