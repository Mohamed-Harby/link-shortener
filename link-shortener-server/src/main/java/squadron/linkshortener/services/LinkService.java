package squadron.linkshortener.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import squadron.linkshortener.models.Link;
import squadron.linkshortener.models.User;
import squadron.linkshortener.repos.LinkRepository;
import squadron.linkshortener.repos.UserRepository;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class LinkService {
    private final LinkRepository linkRepository;
    private final UserRepository userRepository;

    public Link createShortUrl(String originalUrl, String username) throws URISyntaxException, MalformedURLException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found!"));

        String shortUrl = generateShortUrl();
        Link link = new Link();
        link.setOriginalUrl(originalUrl);
        link.setUser(user);
        link.setShortUrl(shortUrl);
        link.setCreaTimestamp(new Timestamp(System.currentTimeMillis()));
        return linkRepository.save(link);
    }


    public Link getOriginalLink(String shortUrl) {
        Optional<Link> link = linkRepository.findByShortUrl(shortUrl);
        return link.orElseThrow(() -> new UsernameNotFoundException("link not found!"));
    }

    private String generateShortUrl() {
        final char[] symbols =
                "0123456789aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ"
                        .toCharArray();

        StringBuilder shortUrlBuilder = new StringBuilder();
        for (int i = 0; i < 6; ++i) {
            int randomIndex = (new Random()).nextInt(symbols.length);
            shortUrlBuilder.append(symbols[randomIndex]);
        }

        if (linkRepository.findByShortUrl(shortUrlBuilder.toString()).isPresent()) {
            return generateShortUrl();
        } else return shortUrlBuilder.toString();
    }
}
