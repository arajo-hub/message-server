package com.judy.message.member.repository;

import com.judy.message.member.entity.Member;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.util.StringUtils;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.judy.message.member.entity.QMember.member;

@Repository
@Transactional
public class MemberRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public MemberRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public Member join(Member member) {
        em.persist(member);
        return member;
    }

    public Member findByNickname(String nickname) {
        return queryFactory.selectFrom(member)
                .where(isEqualsToNickname(nickname))
                .fetchFirst();
    }

    private BooleanExpression isEqualsToNickname(String nickname) {
        return StringUtils.hasText(nickname) ? member.nickname.eq(nickname) : null;
    }

    public List<Member> findLikeNickname(String keyword) {
        return queryFactory.selectFrom(member)
                .where(isLikeToNickname(keyword))
                .fetch();
    }

    private BooleanExpression isLikeToNickname(String keyword) {
        return StringUtils.hasText(keyword) ? member.nickname.like("%" + keyword + "%") : null;
    }

}
