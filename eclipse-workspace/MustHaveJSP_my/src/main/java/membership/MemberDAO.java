package membership;

import common.JDBConnect;

public class MemberDAO extends JDBConnect{
	
    public MemberDAO(String drv, String url, String id, String pw) {
        super(drv, url, id, pw);
    }
    
    public MemberDTO getMemberDTO(String uid, String upass) {
    	MemberDTO dto = new MemberDTO();
    	String query = "SELECT * FROM member WHERE id = ? AND pass =?";
    	
    	try {
    		
            psmt = con.prepareStatement(query); 
            psmt.setString(1, uid);    
            psmt.setString(2, upass);  
            rs = psmt.executeQuery(); 
            
            if (rs.next()) {
            	dto.setId(rs.getString("id"));
            	dto.setPass(rs.getString("pass"));
            	dto.setName(rs.getString(3));
            	dto.setRegidate(rs.getString(4));
            	
            	System.out.println("회원 정보 발견!");
                System.out.println("이름: " + dto.getName());
                System.out.println("가입일: " + dto.getRegidate());
            }
            else {
                System.out.println(">>> [로그인 실패] ID/PW를 확인하세요.");
                System.out.println("--- 현재 DB에 등록된 전체 회원 목록 ---");

                if (rs != null) rs.close();
                
                String allUserQuery = "SELECT * FROM member";
                stmt = con.createStatement(); // Statement 객체 활용
                rs = stmt.executeQuery(allUserQuery);
                
                int count = 0;
                while (rs.next()) {
                    count++;
                    System.out.printf("[%d] ID: %s | PASS: %s | NAME: %s | DATE: %s%n",
                        count,
                        rs.getString("id"),
                        rs.getString("pass"),
                        rs.getString(3),
                        rs.getString(4));
                }
                
                if (count == 0) {
                    System.out.println("알림: 현재 member 테이블이 비어 있습니다.");
                }
            }
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return dto;
    }
    

}
