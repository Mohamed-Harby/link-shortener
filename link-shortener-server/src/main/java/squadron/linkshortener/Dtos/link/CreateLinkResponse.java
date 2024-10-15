package squadron.linkshortener.Dtos.link;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import squadron.linkshortener.models.Link;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLinkResponse {
    private String shortUrl;
}