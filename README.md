# childmeal : 결식 아동을 위한 영양식단 추천 서비스 
###### 졸업 프로젝트의 일환으로, 무단 사용을 금합니다. 
###### 현 아동급식카드 제도의 문제점에서 착안한 서비스입니다.
## 주요 기능
1) 아동
- 식단 기록
- 부족 영양분 확인 및 식단 추천
- 가맹점 조회

2) 보호자
- 다자녀 등록
- 자녀의 영양 상태 확인

## 적용 기술
1) AWS RDS (MySQL) 구축 
2) 데이터베이스는 Workbench를 이용해 시각화
3) 안드로이드 스튜디오를 이용해 어플리케이션 개발
4) 카카오맵 API로 지도 구현, REST API를 이용해 GeoCoding (JSON 데이터 파싱)
5) 웹 크롤링을 통해 구글 이미지 데이터 수집 (음식 당 약 400개)
6) Teachable Machine으로 이미지 분류 모델 생성
7) TensorFlow Light 모델로 변환하여 안드로이드 스튜디오에 적재
8) 유저케이스를 위주로 [ 부족 영양분 > 거리 > 아이의 선호도 ] 순으로 우선 순위를 매겨 식단&식당(가맹점) 추천알고리즘 구현
9) AWS EC2인스턴스로 파이썬 서버를 구축하여 안드로이드 스튜디오와 통신하는 소켓 프로그래밍
10) EKS에 EFK(ElasticSearch + Fluentd + Kibana) 스택을 설치하여 모니터링

## 구성도
<img width="841" alt="스크린샷 2022-06-06 오후 11 24 46" src="https://user-images.githubusercontent.com/65750746/172187706-52a84c6c-2923-4da1-8a31-9b0a9be3e29d.png">


## USER CASE (페르소나)
<details>
<summary>아동 & 보호자 & 지자체 담당 직원</summary>
<div markdown="1">
<img width="800" alt="스크린샷 2022-05-16 오후 9 22 32" src="https://user-images.githubusercontent.com/65750746/168591490-94d4918a-a772-4b3d-b851-69180ce95f57.png">
<img width="800" alt="스크린샷 2022-05-16 오후 9 22 48" src="https://user-images.githubusercontent.com/65750746/168591522-592f2b0f-22f7-4e73-8b46-9785808d44b1.png">
<img width="800" alt="스크린샷 2022-05-16 오후 9 23 03" src="https://user-images.githubusercontent.com/65750746/168591571-3815e279-cbd7-44e0-a96d-8ddb8b977504.png">
</div>
</details>

## 결과물 
