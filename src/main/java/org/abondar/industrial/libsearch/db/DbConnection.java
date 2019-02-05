package org.abondar.industrial.libsearch.db;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

public class DbConnection {

    private DataMapper mapper;
    private SqlSession sqlSession;

    public DbConnection() throws IOException {
        var is = getClass().getClassLoader().getResourceAsStream("configuration.xml");
        var factory = new SqlSessionFactoryBuilder().build(is);

        sqlSession = factory.openSession();
        mapper = sqlSession.getMapper(DataMapper.class);
    }

    public DataMapper getMapper() {
        return mapper;
    }

    public void commitSession(){
        sqlSession.commit();
    }
}
