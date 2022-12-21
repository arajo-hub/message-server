package com.judy.message.message.repository;

import com.judy.message.message.entity.Message;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class MessageRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public MessageRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public Message send(Message message) {
        em.persist(message);
        return message;
    }

}
