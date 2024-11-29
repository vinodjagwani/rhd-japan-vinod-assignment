/**
 * Author: Vinod Jagwani
 */
package com.user.auth.feature.sequence.service.impl;


import com.user.auth.config.UserAuthConfigProp;
import com.user.auth.exception.UserAuthServiceException;
import com.user.auth.exception.constant.ErrorCodeEnum;
import com.user.auth.feature.sequence.repository.entity.UserSequence;
import com.user.auth.feature.sequence.service.SequenceGeneratorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.user.auth.constant.UserAuthConstants.USER_ID_SEQUENCE_NAME;
import static java.lang.String.valueOf;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.leftPad;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserSequenceGeneratorService implements SequenceGeneratorService {

    MongoOperations mongoOperations;

    UserAuthConfigProp userAuthConfigProp;

    @Transactional(rollbackFor = Exception.class)
    public String generateSequenceId() {
        final String userIdPrefix = getUserIdPrefix();
        final String seqName = USER_ID_SEQUENCE_NAME.concat(userIdPrefix);
        final UserSequence userSequence = mongoOperations.findOne(Query.query(Criteria.where("_id").is(seqName)), UserSequence.class);
        if (nonNull(userSequence) && userAuthConfigProp.getUserIdMaxLimit().longValue() <= userSequence.getSeq()) {
            throw new UserAuthServiceException(ErrorCodeEnum.USER_ID_SEQ_COMPLETE, "user_id_sequence.error_complete " + userAuthConfigProp.getUserIdMaxLimit().longValue());
        }
        final UserSequence createOrUpdateUserSequence = mongoOperations.findAndModify(Query.query(Criteria.where("_id").is(seqName)),
                new Update().set("userIdPrefix", userIdPrefix).inc("seq", 1), FindAndModifyOptions.options().returnNew(true).upsert(true), UserSequence.class);
        final String sequenceValue = nonNull(createOrUpdateUserSequence) ? leftPad(valueOf(createOrUpdateUserSequence.getSeq()), 4, '0') : "1";
        return userIdPrefix.concat(sequenceValue);
    }

    private String getUserIdPrefix() {
        return userAuthConfigProp.getUserIdInitialPrefix()
                .concat(LocalDate.now().format(DateTimeFormatter.ofPattern(userAuthConfigProp.getUserIdDatePrefix())));
    }
}
