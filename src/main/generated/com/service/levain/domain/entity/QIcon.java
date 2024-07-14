package com.service.levain.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QIcon is a Querydsl query type for Icon
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QIcon extends EntityPathBase<Icon> {

    private static final long serialVersionUID = 1463894649L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QIcon icon = new QIcon("icon");

    public final NumberPath<Long> iconId = createNumber("iconId", Long.class);

    public final NumberPath<Integer> iconNum = createNumber("iconNum", Integer.class);

    public final QMember member;

    public QIcon(String variable) {
        this(Icon.class, forVariable(variable), INITS);
    }

    public QIcon(Path<? extends Icon> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QIcon(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QIcon(PathMetadata metadata, PathInits inits) {
        this(Icon.class, metadata, inits);
    }

    public QIcon(Class<? extends Icon> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

