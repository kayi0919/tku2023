package org.tku.database.custom;

import org.tku.database.entity.User;

import java.util.List;

public interface RegisterRepositoryCustom {
    List<User> findRegisterBySelective(User user);
}
