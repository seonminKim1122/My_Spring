package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
<<<<<<< HEAD
import java.util.List;
import java.util.Optional;

public class JdbcMemberRepository implements MemberRepository{

    private DataSource dataSource;

    public JdbcMemberRepository(DataSource dataSource) {
=======
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class JdbcMemberRepository implements MemberRepository {
    private final DataSource dataSource; // DB 에 접근하기 위해 필요, Spring Container 를 통해 DI 가능

    public JdbcMemberRepository(DataSource dataSource) { // Spring Container 로부터 주입 받아야 함
>>>>>>> 32a32180a0e1b25daeca3e6b2e812a1569023eed
        this.dataSource = dataSource;
    }

    @Override
    public Member save(Member member) {
<<<<<<< HEAD
//        String sql = "insert into  member(name) values(?)";
        String sql = "";
=======
        String sql = "insert into member (name) values (?)"; // Unable to resolve table 'member' -> 어플리케이션 실행에 문제 없음
>>>>>>> 32a32180a0e1b25daeca3e6b2e812a1569023eed
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
<<<<<<< HEAD
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, member.getName());

            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();

=======
            conn = getConnection();

            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, member.getName());
            pstmt.executeUpdate(); // DB를 갱신하므로 executeUpdate()
            
            // Member 객체의 id 값 셋팅을 위해서 DB 에서 생성된 id 가 필요
            rs = pstmt.getGeneratedKeys();
>>>>>>> 32a32180a0e1b25daeca3e6b2e812a1569023eed
            if (rs.next()) {
                member.setId(rs.getLong(1));
            } else {
                throw new SQLException("id 조회 실패");
            }
<<<<<<< HEAD
=======

>>>>>>> 32a32180a0e1b25daeca3e6b2e812a1569023eed
            return member;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<Member> findById(Long id) {
<<<<<<< HEAD
        return Optional.empty();
=======
        String sql = "select * from member where id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            rs = pstmt.executeQuery(); // select 쿼리이므로 executeQuery

            if (rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            } else{
                return Optional.empty();
            }
        } catch (Exception e){
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }

>>>>>>> 32a32180a0e1b25daeca3e6b2e812a1569023eed
    }

    @Override
    public Optional<Member> findByName(String name) {
<<<<<<< HEAD
        return Optional.empty();
=======
        String sql = "select * from member where name = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));

                return Optional.of(member);
            } else {
                return Optional.empty();
            }

        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }

>>>>>>> 32a32180a0e1b25daeca3e6b2e812a1569023eed
    }

    @Override
    public List<Member> findAll() {
<<<<<<< HEAD
        return null;
    }

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
=======
        String sql = "select * from member";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            List<Member> members = new ArrayList<>();
            while (rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                members.add(member);
            }

            return members;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }
    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
>>>>>>> 32a32180a0e1b25daeca3e6b2e812a1569023eed
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
<<<<<<< HEAD
            if (pstmt != null) {
                pstmt.close();
            }
=======
            if (pstmt != null) pstmt.close();
>>>>>>> 32a32180a0e1b25daeca3e6b2e812a1569023eed
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
<<<<<<< HEAD
            if (conn != null) {
                close(conn);
            }
=======
            if (conn != null) close(conn);
>>>>>>> 32a32180a0e1b25daeca3e6b2e812a1569023eed
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void close(Connection conn) throws SQLException {
<<<<<<< HEAD
=======
        // DataSourceUtils?
>>>>>>> 32a32180a0e1b25daeca3e6b2e812a1569023eed
        DataSourceUtils.releaseConnection(conn, dataSource);
    }
}
