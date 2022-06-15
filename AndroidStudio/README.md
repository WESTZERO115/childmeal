안드로이드 스튜디오 파일들입니다.

geocoding_func().java 파일은 AWS_RDS.java파일에서 주소를 위도&경도로 바꾸는 지오코딩 작업을 위해 잠시 이용했다가 삭제한 함수이며, 따로 파일로 작성해놓은 것입니다.

### 기능별
- AWS_RDS.java : 안드로이드 스튜디오 내에서 외부와의 통신을 모두 담당하는 파일
- Socket_Python.java : 안드로이드 스튜디오와 파이썬 서버와의 소켓 프로그래밍

### 아동 파일
- Login_and_SignUp_Kid.java : 아동급식카드 번호로 회원가입 | 로그인하는 화면
- SignUp_Detail_Kid.java : 처음 회원가입 시 기본 인적사항을 등록하는 화면 (이름,몸무게,성별,키)
- MainActivity.java : 안에 프래그먼트 4개 [Frag_home, Frag_recommend, Frag_buy_record, Frag_mypage]
  - Frag_home.java : 아동 메인 화면으로, 지도 뷰가 뜨며 주변 가맹점을 볼 수 있음
  - Frag_recommend.java : 식단 추천 화면
  - Frag_buy_record.java : 아동이 기록한 식단 내역을 볼 수 있는 화면
  - Frag_mypage.java : 아동의 기본 정보(이름,몸무게,성별,키)와 영양분 섭취 현황을 확인하는 화면, 식단 기록 기능이 있는 화면
- Food_image.java & Food_Register.java : 카메라 촬영을 통해 식단을 기록, 이미지 분류 모델 실행, 식단을 직접 기록하는 화면

- datamodel.java & myadapter.java & single_row_design.xml : Frag_buy_record에서 식단 내역을 리사이클러뷰로 나타내기 위한 파일들 
### 부모 파일
- Login_and_SignUp_Parents.java : 아이디 & 비밀번호로 로그인, 회원가입
- Parents_main.java : 부모 메인 화면으로, 자녀들의 리스트뷰 형태로 나타남
- Parents_addchild.java : 아동급식카드 번호로 자녀 추가 등록하는 
