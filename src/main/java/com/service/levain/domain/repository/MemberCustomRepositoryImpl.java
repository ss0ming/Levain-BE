//package com.service.levain.domain.repository;
//
//import com.querydsl.core.BooleanBuilder;
//import com.querydsl.core.QueryFactory;
//import com.querydsl.core.types.Projections;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import com.service.levain.domain.dto.member.request.SearchReqDto;
//import com.service.levain.domain.dto.member.response.SearchMemberResDto;
//import com.service.levain.domain.entity.QMember;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Queue;
//
//@Repository
//@RequiredArgsConstructor
//public class MemberCustomRepositoryImpl implements MemberCustomRepository {
//    QMember qMember = QMember.member;
//    private final JPAQueryFactory queryFactory;
//
//    @Override
//    public Page<SearchMemberResDto> searchMember(SearchReqDto search, Pageable pageable) {
//        List<SearchMemberResDto> searchList = queryFactory
//                .select(Projections.constructor(SearchMemberResDto.class,
//                        qMember.nickname,
//                        qMember.username,
//                        qMember.reward))
//                .from(qMember)
//                .where(searchCondition(search))
//                .orderBy(qMember.username.desc())
//                .fetch();
//
//        Long count = queryFactory
//                .select(qMember.count())
//                .from(qMember)
//                .where(searchCondition(search))
//                .fetchOne();
//
//
//        return new PageImpl<>(searchList, pageable, count);
//    }
//
//    private BooleanBuilder searchCondition(SearchReqDto search) {
//        BooleanBuilder builder = null;
//        System.out.println("username" + search.getSearchType());
//        if(search != null) {
//            if(!search.getSearchType().isEmpty() && !search.getSearchKeyword().isEmpty()) {
//                switch (search.getSearchType()) {
//                    case "username":
//                        builder = new BooleanBuilder(qMember.username.contains(search.getSearchKeyword()));
//                        break;
//                    case "nickname":
//                        builder= new BooleanBuilder(qMember.nickname.contains(search.getSearchKeyword()));
//                        break;
//                }
//            }
//        }
//
//        return builder;
//    }
//}
