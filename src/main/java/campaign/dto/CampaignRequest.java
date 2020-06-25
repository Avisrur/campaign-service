package campaign.dto;

import campaign.repositories.model.CampaignEntity;
import campaign.repositories.model.Status;

import java.util.UUID;

public class CampaignRequest {
    String name;
    Integer bid;
    String sellerId;

    public CampaignEntity toCampaignEntity() {
        return CampaignEntity.builder()
                .campaignId(UUID.randomUUID().toString())
                .name(name)
                .bid(bid)
                .sellerId(sellerId)
                .status(Status.ACTIVE).build();
    }
}
