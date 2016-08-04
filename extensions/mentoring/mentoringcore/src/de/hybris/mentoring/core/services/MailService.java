package de.hybris.mentoring.core.services;

import de.hybris.mentoring.core.model.OrganizationModel;

import java.util.List;

public interface MailService {

    void sendEmail(List<OrganizationModel> organizationModels, final String message);

}
