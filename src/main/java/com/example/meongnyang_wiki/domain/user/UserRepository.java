package com.example.meongnyang_wiki.domain.user;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final SqlSession sqlSession;

    private static final String NAMESPACE = "mapper.userMapper";

    public UserMaster selectByEmail(String email) {
        return sqlSession.selectOne(NAMESPACE + ".selectByEmail", email);
    }

    public int insertUser(UserMaster user) {
        return sqlSession.insert(NAMESPACE + ".insertUser", user);
    }

    public int updateUser(UserMaster user) {
        return sqlSession.update(NAMESPACE + ".updateUser", user);
    }
}