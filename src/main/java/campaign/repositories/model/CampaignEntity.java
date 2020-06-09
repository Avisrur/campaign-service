package campaign.repositories.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "campaigns")
public class CampaignEntity {
    @Id
    private String campaignId;
    private String sellerId;
    private String name;
    private Integer bid;
    private Status status;
}
