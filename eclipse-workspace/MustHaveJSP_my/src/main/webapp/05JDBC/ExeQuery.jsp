<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="common.JDBConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
       pageEncoding="UTF-8"%>  
<html>
<head><title>JDBC</title></head>
<body>
    <h2>회원 목록 조회 테스트(executeQuery() 사용)</h2>
    <%

    JDBConnect jdbc = new JDBConnect();

   
    String sql = "SELECT id, pass, name, regidate FROM member";  
    jdbc.stmt = jdbc.con.createStatement();
    

    jdbc.rs = jdbc.stmt.executeQuery(sql);

    while (jdbc.rs.next()) { 
        String id = jdbc.rs.getString(1);
        String pw = jdbc.rs.getString(2);
        String name = jdbc.rs.getString("name");
        java.sql.Date regidate = jdbc.rs.getDate("regidate");
        
        out.println(String.format("%s %s %s %s", id, pw, name, regidate) + "<br/>"); 
    }

    jdbc.close();
    %>
</body>
</html>