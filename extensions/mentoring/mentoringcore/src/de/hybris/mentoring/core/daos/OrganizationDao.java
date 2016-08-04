package de.hybris.mentoring.core.daos;

import de.hybris.mentoring.core.model.OrganizationModel;

import java.util.List;

public interface OrganizationDao {

    List<OrganizationModel> findAll();
}