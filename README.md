# 보안 분석 웹 애플리케이션 예제

이 저장소는 React와 Spring Boot를 활용한 Fortify SCA 연동 보안 분석 웹 애플리케이션의 기본 구조를 제공합니다.

## 폴더 구조
- `backend` : Spring Boot 기반 REST API
- `frontend` : React SPA 프로젝트

## 실행 순서
1. MariaDB 실행 후 `application.yml`의 DB 설정을 맞춰 주세요.
2. **백엔드**
   ```bash
   cd backend
   mvn package
   java -jar target/security-analysis-0.0.1-SNAPSHOT.jar
   ```
3. **프론트엔드**
   ```bash
   cd frontend
   npm install
   npm start
   ```

자세한 내용은 각 폴더의 README를 참고하세요.

## 주요 API 예시
로그인 후 발급되는 JWT 토큰을 Authorization 헤더에 넣어 호출합니다.

| 메서드 | URL | 설명 |
| ------ | -------------------------- | ---------------- |
| `POST` | `/api/auth/login` | 로그인, JWT 발급 |
| `GET` | `/api/projects` | 프로젝트 목록 조회 |
| `POST` | `/api/projects` | 새 프로젝트 등록 |
| `POST` | `/api/projects/{id}/scan` | Fortify 분석 실행 |
| `GET` | `/api/projects/{id}/results` | 프로젝트 분석 결과 |
| `GET` | `/api/vulnerabilities/categories` | 취약점 카테고리 목록 |
| `GET` | `/api/users/me` | 현재 로그인 사용자 조회 |
