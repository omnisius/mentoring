package de.hybris.mentoring.core.daos;

import de.hybris.mentoring.core.model.OrganizationModel;

import java.util.List;

/**
 * Created by Bogdan_Bardakov on 8/3/2016.
 */
public interface OrganizationDao {

    List<OrganizationModel> findAll();
}