package com.solopov.urlshorter.dao.entities.generators.id;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Locale;

public class ShortUrlGenerator implements IdentifierGenerator {

    private final static Integer URL_KEY_LENGTH = 4;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return RandomStringUtils.randomAlphanumeric(URL_KEY_LENGTH).toUpperCase(Locale.ROOT);
    }

}
