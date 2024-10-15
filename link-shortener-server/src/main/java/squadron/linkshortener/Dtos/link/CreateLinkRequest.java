package squadron.linkshortener.Dtos.link;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLinkRequest {
    @NotEmpty(message = "long url cannot be empty")
    private String longUrl;
    private String username;
}
