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

    public PostEntity updatePost(String postId, PostUpdate newPost) {
        return updatePostBy("newText", postId, newPost);
    }

    public void deleteById(String postId) {
        if (this.postsRepository.deleteByPostId(postId) == 0) {
            throw new PostNotFoundException(String.format("Unable to delete post with post ID '%s' because it is not found", postId));
        }
    }

    private PostEntity updatePostBy(String updateBy, String postId, PostUpdate newPost) {
        PostEntity postToUpdate = getPostByPostId(postId);
        if (postToUpdate != null) {
            return handleUpdatePost(updateBy, postToUpdate, newPost);
        } else {
            throw new PostNotFoundException(String.format("Unable to update post with post ID '%s' because it is not found", postId));
        }
    }

    private PostEntity handleUpdatePost(String updateBy, PostEntity postToUpdate, PostUpdate newPost) {
        if (updateBy.equals("like")) {
            return updatePostLikes(postToUpdate);
        }
        if (updateBy.equals("newText")) {
            return updatePostWithNewText(postToUpdate, newPost);
        }
        throw new UpdateFailureException(String.format("Unable to update post because of unknown criteria '%s'", updateBy));
    }

    private PostEntity updatePostLikes(PostEntity postToUpdate) {
        postToUpdate.setLikes(postToUpdate.getLikes() + 1);
        return savePost(postToUpdate);
    }

    private PostEntity updatePostWithNewText(PostEntity postToUpdate, PostUpdate newPost) {
        postToUpdate.setBody(newPost.getBody());
        return savePost(postToUpdate);
    }

    private CampaignEntity getCampaignByCampaignId(String campaignId) {
        return campaignRepository.findByCampaignId(campaignId);
    }

    private List<CampaignEntity> getCampaignsBySellerId(String sellerId) {
        return campaignRepository.findBySellerId(sellerId);
    }
}
