# member Entity (시간 상속)
1. 회원번호(id)
    저장시 자동증감 (기본값)
2. 회원 이름(name)
   직접 입력 
3. 회원 비밀번호(password)
   직접 입력
4. 회원 이메일(email)
   직접 입력 
5. 역할(role)
   따로 admin요청을 하지않으면 user로 설정 (기본값)

# board Entity (시간 상속)
1. 게시글 고유번호(id)
2. 제목(title)
3. 내용(content)
4. 카테고리(categories)


# comments Entity (시간 상속)
1. 부모글 id (board.id)
2. 댓글 고유번호 (id)
3. 내용(content)




