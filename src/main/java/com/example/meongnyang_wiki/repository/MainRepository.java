package com.example.meongnyang_wiki.repository;




import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class MainRepository {

    private SqlSession sqlSession;

    private String namespace = "mapper.mainMapper";

    public MainRepository(SqlSession sqlSession) { this.sqlSession = sqlSession; }


    public int test(HashMap<String, Object> map) throws Exception {
        return sqlSession.selectOne(namespace + ".test", map);
    }

}
