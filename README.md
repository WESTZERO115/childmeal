# childmeal : 결식 아동을 위한 영양식단 추천 서비스 
###### 졸업 프로젝트의 일환으로 2022년 3월부터 6월까지 약 3개월간 , 무단 사용을 금합니다. 
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

<table>
  <tr>
    <td align="center"><img width="100px" alt="image" src="https://user-images.githubusercontent.com/65750746/173902004-f44bbc7c-04d8-4365-8048-52ece33eff81.png"></td>
    <td align="center"><img width="100px" alt="image" src="https://user-images.githubusercontent.com/65750746/173902056-4f80d3ea-7408-4d17-91fe-ac31950498ed.png"></td>
    <td align="center"><img width="100px" alt="image" src="https://user-images.githubusercontent.com/65750746/173902072-a1da86d4-9778-4d47-b7e5-24f89261677a.png"></td>
    <td align="center"><img width="100px" alt="image" src="https://user-images.githubusercontent.com/65750746/173902116-a3e6af99-e544-47b8-b72e-617d94ec5cc5.png"></td>
    <td align="center"><img width="150px" alt="image" src="https://user-images.githubusercontent.com/65750746/173902145-bd07a12e-c925-4cad-87b1-869fc8b9f28e.png"></td>
    <td align="center"><img width="100px" alt="image" src="https://user-images.githubusercontent.com/65750746/173902170-ab91195b-165a-431a-b7ab-dcf106905b70.png"></td>
  </tr>
</table>

## 구성도
<img width="841" alt="스크린샷 2022-06-06 오후 11 24 46" src="https://user-images.githubusercontent.com/65750746/172187706-52a84c6c-2923-4da1-8a31-9b0a9be3e29d.png">

## DB 구조
<details>
<summary>아동 / 아동별 섭취 식단 / 음식별 영양분 / 부모 / 가맹점 / 나이별 하루 영양분 권장 섭취량  </summary>
<div markdown="1">
<table>
  <tr>
    <td align="center"><img width="200" alt="1" src="https://user-images.githubusercontent.com/65750746/174429602-bc3c369f-e36b-4e1a-b95d-f677d22c7117.png"></td>
    <td align="center"><img width="200" alt="2" src="https://user-images.githubusercontent.com/65750746/174429648-99efd170-5351-4996-83b9-1e3d99bca367.png"></td>
    <td align="center"><img width="200" alt="3" src="https://user-images.githubusercontent.com/65750746/174429660-a332b4df-c9f1-428a-9299-852616ea7f6a.png"></td>
  </tr>
    <td align="center"><img width="200" alt="4" src="https://user-images.githubusercontent.com/65750746/174429687-6c6f669a-199f-4c0e-b88e-7dfeb393e938.png"></td>
    <td align="center"><img width="200" alt="5" src="https://user-images.githubusercontent.com/65750746/174429697-6ac18de2-b08d-4257-a94d-9c409b2c029c.png"></td>
    <td align="center"><img width="200" alt="6" src="https://user-images.githubusercontent.com/65750746/174429733-1907d3b2-3939-460a-ba29-3ec89d38d989.png"></td>
  </tr>
</table>
</div>
</details>

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
- [아동] 회원가입 후 메인 화면(현 위치 기반 주변 가맹점)으로 이동
<table>
  <tr>
    <td align="center"><img width="230" height="350" alt="1" src="https://user-images.githubusercontent.com/65750746/174428253-0d6015d3-44e8-49c6-8651-0e5325c9cf44.png"></td>
    <td align="center"><img width="230" height="350" alt="2" src="https://user-images.githubusercontent.com/65750746/174428318-854f2879-d20d-4cfe-8536-055f020b1c29.png"></td>
    <td align="center"><img width="230" height="350" alt="3" src="https://user-images.githubusercontent.com/65750746/174428374-47e2a729-fe97-4cb3-ada7-10fa1a1ca52c.png"></td>
    <td align="center"><img width="230" height="350" alt="4" src="https://user-images.githubusercontent.com/65750746/174428570-3ac415f3-7500-47a9-9e2e-ffc40572629f.png"></td>
  </tr>
</table>

- [아동] 마이페이지 화면에서 식단 기록 후, 식단 내역 확인 & 부족 영양분 확인
<table>
  <tr>
    <td align="center"><img width="230" height="350" alt="5" src="https://user-images.githubusercontent.com/65750746/174428716-f16dddae-d72b-4455-9558-20d58200029f.png"></td>
    <td align="center"><img width="230" height="350" alt="6" src="https://user-images.githubusercontent.com/65750746/174428879-62860996-a71f-4a01-9763-16622f366368.png"></td>
    <td align="center"><img width="230" height="350" alt="7" src="https://user-images.githubusercontent.com/65750746/174428908-267577f6-d106-429d-ae67-8e6fca6644b1.png"></td>
    <td align="center"><img width="230" height="350" alt="8" src="https://user-images.githubusercontent.com/65750746/174428913-c5fa60a6-7a04-44b9-8b90-64edf128b709.png"></td>
  </tr>
  <tr>
    <td align="center"><img width="230" height="350" alt="9" src="https://user-images.githubusercontent.com/65750746/174428986-88a63f5f-81b8-4220-9a70-5d87aed33b9d.png"></td>
    <td align="center"><img width="230" height="350" alt="10" src="https://user-images.githubusercontent.com/65750746/174428997-e0a990f6-68f6-4eca-9336-c490e95af53a.png"></td>
    <td align="center"><img width="230" height="350" alt="11" src="https://user-images.githubusercontent.com/65750746/174429006-ad107dea-6e47-4f53-bafe-52a956889368.png"></td>
  </tr>
</table>

- [아동] 식단 직접 기록 후, 가맹점 음식 기반 식단 추천 받기

- [부모] 회원 가입 후, 아동급식카드를 이용해 자녀 등록 & 자녀 정보 



