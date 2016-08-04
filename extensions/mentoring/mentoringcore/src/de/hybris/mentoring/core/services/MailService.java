package de.hybris.mentoring.core.services;

import de.hybris.mentoring.core.model.OrganizationModel;

import java.util.List;

/**
 * Created by Bogdan_Bardakov on 8/3/2016.
 */
public interface MailService {

    void sendEmail(List<OrganizationModel> organizationModels, final String message);

}
