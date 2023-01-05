package com.judy.message.message.repository;

import com.judy.message.message.entity.Message;
import com.judy.message.message.entity.ReadYn;
import com.judy.message.message.response.MessageView;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.judy.message.message.entity.QMessage.message;

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

    public List<Message> findAllReceivedMessageByMemberSeq(Long seq) {
        return queryFactory.selectFrom(message)
                .where(isEqualsToRecipientSeq(seq))
                .fetch();
    }

    private BooleanExpression isEqualsToRecipientSeq(Long seq) {
        return ObjectUtils.isEmpty(seq) ? null : message.recipient.seq.eq(seq);
    }

    public List<Message> findAllSentMessageByMemberSeq(Long seq) {
        return queryFactory.selectFrom(message)
                .where(isEqualsToSenderSeq(seq))
                .fetch();
    }

    private BooleanExpression isEqualsToSenderSeq(Long seq) {
        return ObjectUtils.isEmpty(seq) ? null : message.sender.seq.eq(seq);
    }

    public int findAllCount() {
        return queryFactory.selectFrom(message).fetch().size();
    }

    public void deleteMessage(Long seq) {
        queryFactory.delete(message)
                .where(isEqualsToMessageSeq(seq))
                .execute();
    }

    private BooleanExpression isEqualsToMessageSeq(Long seq) {
        return ObjectUtils.isEmpty(seq) ? null : message.seq.eq(seq);
    }

    public Message findByMessageSeq(Long seq) {
        return  em.find(Message.class, seq);
    }

    public Page<MessageView> findByPage(String nickname, Pageable pageable) {
        List<Message> messages = queryFactory.selectFrom(message)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(message.sendDatetime.desc())
                .fetch();
        Long count = queryFactory.select(message.count())
                .from(message)
                .fetchOne();
        List<MessageView> messageViews = new ArrayList<MessageView>();
        for (int i = 0; i < messages.size(); i++) {
            messageViews.add(messages.get(i).toMessageView());
        }
        return new PageImpl<>(messageViews, pageable, count);
    }

    public Page<MessageView> findNewMessage(Long seq) {
        List<Message> messages = queryFactory.selectFrom(message)
                .where(isAfterMessage(seq))
                .where(notReadYet())
                .orderBy(message.sendDatetime.desc())
                .fetch();
        List<MessageView> messageViews = new ArrayList<MessageView>();
        for (int i = 0; i < messages.size(); i++) {
            messageViews.add(messages.get(i).toMessageView());
        }
        return new PageImpl<>(messageViews);
    }

    private BooleanExpression isAfterMessage(Long seq) {
        return ObjectUtils.isEmpty(seq) ? null : message.seq.gt(seq);
    }

    private BooleanExpression notReadYet() {
        return message.readYn.eq(ReadYn.NO);
    }
}
