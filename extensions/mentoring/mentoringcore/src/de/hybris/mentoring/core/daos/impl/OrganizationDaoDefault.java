package de.hybris.mentoring.core.daos.impl;

import de.hybris.mentoring.core.daos.OrganizationDao;
import de.hybris.mentoring.core.model.OrganizationModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;

import java.util.List;

/**
 * Created by Bogdan_Bardakov on 8/3/2016.
 */
public class OrganizationDaoDefault extends DefaultGenericDao<OrganizationModel> implements OrganizationDao {

    public OrganizationDaoDefault(final String typeCode) {
        super(typeCode);
    }

    @Override
    public List<OrganizationModel> findAll() {
        return find();
    }
}
