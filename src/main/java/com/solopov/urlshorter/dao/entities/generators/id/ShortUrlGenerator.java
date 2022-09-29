package com.solopov.urlshorter.dao.entities.generators.id;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Locale;

public class ShortUrlGenerator implements IdentifierGenerator {

    private final static Integer SHORT_LINK_SIZE = 4;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return RandomStringUtils.random(SHORT_LINK_SIZE,true,true).toUpperCase(Locale.ROOT);
    }

}
