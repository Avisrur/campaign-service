package campaign.controllers;

import campaign.dto.CampaignNotFoundException;
import campaign.dto.CampaignRequest;
import campaign.dto.CampaignUpdate;
import campaign.exceptions.UnknownQueryParamException;
import campaign.repositories.model.CampaignEntity;
import campaign.services.CampaignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/campaign")
public class CampaignController {

    private final CampaignService campaignService;

    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @PostMapping
    public ResponseEntity addCampaign(@RequestBody CampaignRequest campaignRequest) {
        log.info("Got request {}", campaignRequest);
        CampaignEntity campaignEntity = campaignService.saveCampaign(campaignRequest.toCampaignEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(campaignEntity);
    }

    @GetMapping
    public ResponseEntity<List<CampaignEntity>> getCampaigns(@RequestParam Map<String, String> queryParams) {
        log.info("About to retrieve campaigns by: {}", queryParams);
        return ResponseEntity.status(HttpStatus.OK).body(campaignService.findBy(queryParams));
    }

//    @PutMapping("/{campaignId}")
//    public ResponseEntity<CampaignEntity> updateCampaign(@PathVariable String campaignId, @RequestBody CampaignUpdate campaignUpdate) {
//        log.info("About to update campaign: {} with new body: {}", campaignId,campaignUpdate);
//        CampaignEntity updatedCampaignEntity = campaignService.updateCampaign(campaignId, campaignUpdate);
//        return ResponseEntity.status(HttpStatus.CREATED).body(updatedCampaignEntity);
//    }

    @DeleteMapping("/{campaignId}")
    public ResponseEntity<Void> deleteCampaign(@PathVariable String campaignId) {
        campaignService.deleteById(campaignId);
        log.info("Campaign with id {} was deleted", campaignId);
        return ResponseEntity.status(204).build();
    }


    @ExceptionHandler({UnknownQueryParamException.class, CampaignNotFoundException.class})
    public void handleBadRequest(RuntimeException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
    }

//    @ExceptionHandler(UpdateFailureException.class)
//    public void handleUnknownUpdateCriteria(RuntimeException e, HttpServletResponse response) throws IOException {
//        response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
//    }
}
