package com.solopov.urlshorter.dao.entities.generators.id;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Locale;
import java.util.UUID;

public class ShortUrlGenerator implements IdentifierGenerator {

    private final static Integer SHORT_LINK_SIZE = 4;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return UUID.randomUUID().toString()
                .replace("-", "")
                .substring(0, SHORT_LINK_SIZE)
                .toUpperCase(Locale.ROOT);
    }

}
