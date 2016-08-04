package de.hybris.mentoring.core.services.impl;

import de.hybris.mentoring.core.daos.OrganizationDao;
import de.hybris.mentoring.core.model.OrganizationModel;
import de.hybris.mentoring.core.services.OrganizationService;

import java.util.List;

public class DefaultOrganizationService implements OrganizationService {
    private OrganizationDao organizationDao;

    @Override
    public List<OrganizationModel> getOrganizations() {
        return organizationDao.findAll();
    }

    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }
}
