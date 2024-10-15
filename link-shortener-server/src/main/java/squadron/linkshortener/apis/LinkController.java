package squadron.linkshortener.apis;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import squadron.linkshortener.Dtos.link.CreateLinkRequest;
import squadron.linkshortener.Dtos.link.CreateLinkResponse;
import squadron.linkshortener.models.Link;
import squadron.linkshortener.services.LinkService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

@RestController
@RequestMapping("/api/urls")
@RequiredArgsConstructor
public class LinkController {
    private final LinkService linkService;

    @CrossOrigin(origins = "http://localhost:5173") // Allow CORS for this specific endpoint
    @GetMapping("/{shortUrl}")
    public ResponseEntity<?> redirect(@PathVariable String shortUrl) {
        Link link = linkService.getOriginalLink(shortUrl);

        if (link == null) {
            return new ResponseEntity<>("short url not found",
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                .header("Location", link.getOriginalUrl())
                .build();
    }

    @CrossOrigin(origins = "http://localhost:5173") // Allow CORS for this specific endpoint
    @PostMapping
    public ResponseEntity<?> createShortLink(@RequestBody @Valid CreateLinkRequest createLinkRequest) throws MalformedURLException, URISyntaxException {
        String originalLink = createLinkRequest.getLongUrl();

        // validate the original url
        try {
            new URL(originalLink).toURI();
        } catch (MalformedURLException | URISyntaxException e) {
            return new ResponseEntity<>("Invalid URL format", HttpStatus.BAD_REQUEST);
        }

        // create the url
        Link shortLink = linkService.createShortUrl(originalLink, createLinkRequest.getUsername());
        return new ResponseEntity<>(new CreateLinkResponse(shortLink.getShortUrl()), HttpStatus.CREATED);
    }

}
