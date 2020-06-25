package campaign.repositories;

import campaign.repositories.model.CampaignEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CampaignRepository extends MongoRepository<CampaignEntity, String> {
    List<CampaignEntity> findBySellerId(String sellerId);
    CampaignEntity findByCampaignId(String campaignId);

    int deleteByCampaignId(String campaignId);
}
