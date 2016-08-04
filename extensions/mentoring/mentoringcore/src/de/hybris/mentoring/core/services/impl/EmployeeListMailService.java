package de.hybris.mentoring.core.services.impl;

import com.google.common.base.Preconditions;
import de.hybris.mentoring.core.model.OrganizationModel;
import de.hybris.mentoring.core.services.MailService;
import de.hybris.platform.acceleratorservices.email.EmailService;
import de.hybris.platform.acceleratorservices.model.email.EmailAddressModel;
import de.hybris.platform.acceleratorservices.model.email.EmailAttachmentModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class EmployeeListMailService implements MailService {
    private static final String MAIL_FROM = "mail.from";
    public static final String MAIL_SMTP_USER = "mail.smtp.user";
    public static final String MAIL_SERVICE_SUBJECT = "mailService.subject";

    public static final String EMPTY_STRING = " ";
    public static final String NO_CUSTOMERS_BODY = "There are no customers in you organization";
    public static final String NO_ORGANIZATION_MESSAGE = "There are no Organisation in organisation models list";

    private EmailService emailService;
    private ConfigurationService configurationService;


    @Override
    public void sendEmail(List<OrganizationModel> organizationModels, String message) {
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(organizationModels), NO_ORGANIZATION_MESSAGE);

        final EmailAddressModel fromAddress = emailService.getOrCreateEmailAddressForEmail(getProperty(MAIL_FROM), getProperty(MAIL_SMTP_USER));

        organizationModels.stream().filter(organizationModel -> Objects.nonNull(organizationModel.getEmail())).forEach(organizationModel -> {
            emailService.send(emailService.createEmailMessage(
                    prepareToAddress(organizationModel),
                    new ArrayList<EmailAddressModel>(),
                    new ArrayList<EmailAddressModel>(),
                    fromAddress, StringUtils.EMPTY,
                    getProperty(MAIL_SERVICE_SUBJECT),
                    getBodyMessage(organizationModel, message),
                    new ArrayList<EmailAttachmentModel>()));
        });
    }

    private List<EmailAddressModel> prepareToAddress(OrganizationModel organizationModel) {
         return Collections.singletonList(emailService.getOrCreateEmailAddressForEmail(organizationModel.getEmail(), organizationModel.getName()));
    }

    private String getProperty(String mailServiceSubject) {
        return configurationService.getConfiguration().getProperty(mailServiceSubject).toString();
    }

    private String getBodyMessage(OrganizationModel organizationModel, String message) {
        if (CollectionUtils.isEmpty(organizationModel.getCustomer())) {
            return NO_CUSTOMERS_BODY;
        }
        StringBuilder bodyMessage = new StringBuilder(message);

        List<CustomerModel> customers = (List<CustomerModel>) organizationModel.getCustomer();
        for (CustomerModel customer : customers) {
            bodyMessage.append(EMPTY_STRING).append(customer.getName());
        }
        return bodyMessage.toString();
    }
    

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }
}
