package campaign.services;

import campaign.exceptions.UnknownQueryParamException;
import campaign.repositories.CampaignRepository;
import campaign.repositories.model.CampaignEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class CampaignService {
    private CampaignRepository campaignRepository;

    public CampaignService(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    public CampaignEntity saveCampaign(CampaignEntity newCampaign) {
        return campaignRepository.save(newCampaign);
    }

    public List<CampaignEntity> findBy(Map<String, String> queryParams) {
        if (queryParams.get("sellerId") != null) {
            return getCampaignsBySellerId(queryParams.get("sellerId"));
        }
        if (queryParams.get("campaignId") != null) {
            return Collections.singletonList(getCampaignByCampaignId(queryParams.get("campaignId")));
        }
        if (queryParams.isEmpty()) {
            return campaignRepository.findAll();
        }
        throw new UnknownQueryParamException(String.format("Unable to retrieve posts by these query params '%s'", queryParams));
    }
    private CampaignEntity getCampaignByCampaignId(String campaignId) {
        return campaignRepository.findByCampaignId(campaignId);
    }

    private List<CampaignEntity> getCampaignsBySellerId(String sellerId) {
        return campaignRepository.findBySellerId(sellerId);
    }
}
