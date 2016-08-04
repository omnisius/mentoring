package de.hybris.mentoring.core.job;

import de.hybris.mentoring.core.model.OrganizationModel;
import de.hybris.mentoring.core.services.MailService;
import de.hybris.mentoring.core.services.OrganizationService;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * Created by Bogdan_Bardakov on 8/3/2016.
 */
public class EmailJobPerformable extends AbstractJobPerformable<CronJobModel> {
    public static final String MESSAGE = "message";
    private MailService mailService;
    private OrganizationService organizationService;

    @Override
    public PerformResult perform(final CronJobModel model) {

        List<OrganizationModel> organizationModels = organizationService.getOrganizations();

        if (CollectionUtils.isEmpty(organizationModels)) {
            return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
        }

        mailService.sendEmail(organizationModels, MESSAGE);

        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    public void setOrganizationService(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

}
