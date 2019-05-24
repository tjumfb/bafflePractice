package dao;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import orm.User;

public class UserManager {
    private static SqlSessionFactory sessionFactory = null;

    public static SqlSessionFactory getSessionFactory(){
        if(sessionFactory == null){
            try (// 读取mybatis-config.xml文件
                 InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            ){
                // 初始化mybatis，创建SqlSessionFactory类的实例
                sessionFactory = new SqlSessionFactoryBuilder()
                        .build(is);
            } catch (Exception e) {
                // 回滚事务
                e.printStackTrace();
            }
        }
        System.out.println(sessionFactory);
        return sessionFactory;
    }

    public static boolean add(User user){
        SqlSession sqlSession = null;
        try{
            // 初始化mybatis，创建SqlSessionFactory类的实例
            SqlSessionFactory sqlSessionFactory = getSessionFactory();
            System.out.println(sqlSessionFactory);
            sqlSession = sqlSessionFactory.openSession();
            // 插入数据
            sqlSession.insert("main.mapper.UserMapper.save", user);
            // 提交事务
            sqlSession.commit();
        } catch (Exception e) {
            // 回滚事务
            sqlSession.rollback();
            e.printStackTrace();
            return false;
        }finally{
            try {
                // 关闭sqlSession
                if(sqlSession != null) sqlSession.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    public static User selectById(int id){
        SqlSession sqlSession = null;
        User res = null;
        try{
            // 初始化mybatis，创建SqlSessionFactory类的实例
            SqlSessionFactory sqlSessionFactory = getSessionFactory();
            System.out.println(sqlSessionFactory);
            sqlSession = sqlSessionFactory.openSession();
            // 插入数据
            res = sqlSession.selectOne("main.mapper.UserMapper.selectbyid", id);
            // 提交事务
            sqlSession.commit();
        } catch (Exception e) {
            // 回滚事务
            sqlSession.rollback();
            e.printStackTrace();
            return res;
        }finally{
            try {
                // 关闭sqlSession
                if(sqlSession != null) sqlSession.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return res;
    }
    public static boolean update(User user){
        SqlSession sqlSession = null;
        try{
            // 初始化mybatis，创建SqlSessionFactory类的实例
            SqlSessionFactory sqlSessionFactory = getSessionFactory();
            System.out.println(sqlSessionFactory);
            sqlSession = sqlSessionFactory.openSession();
            // 插入数据
            sqlSession.update("main.mapper.UserMapper.update", user);
            // 提交事务
            sqlSession.commit();
        } catch (Exception e) {
            // 回滚事务
            sqlSession.rollback();
            e.printStackTrace();
            return false;
        }finally{
            try {
                // 关闭sqlSession
                if(sqlSession != null) sqlSession.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    public static boolean delete(int id){
        SqlSession sqlSession = null;
        try{
            // 初始化mybatis，创建SqlSessionFactory类的实例
            SqlSessionFactory sqlSessionFactory = getSessionFactory();
            System.out.println(sqlSessionFactory);
            sqlSession = sqlSessionFactory.openSession();
            // 插入数据
            sqlSession.delete("main.mapper.UserMapper.delete", id);
            // 提交事务
            sqlSession.commit();
        } catch (Exception e) {
            // 回滚事务
            sqlSession.rollback();
            e.printStackTrace();
            return false;
        }finally{
            try {
                // 关闭sqlSession
                if(sqlSession != null) sqlSession.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    public static List<Map<String,Object>> selectAll(){
        SqlSession sqlSession = null;
        List<Map<String,Object>> res = new ArrayList<Map<String,Object>>();
        try{
            // 初始化mybatis，创建SqlSessionFactory类的实例
            SqlSessionFactory sqlSessionFactory = getSessionFactory();
            System.out.println(sqlSessionFactory);
            sqlSession = sqlSessionFactory.openSession();
            // 插入数据
            res = sqlSession.selectList("main.mapper.UserMapper.selectall");
            // 提交事务
            sqlSession.commit();
        } catch (Exception e) {
            // 回滚事务
            sqlSession.rollback();
            e.printStackTrace();
            return res;
        }finally{
            try {
                // 关闭sqlSession
                if(sqlSession != null) sqlSession.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return res;
    }
}
