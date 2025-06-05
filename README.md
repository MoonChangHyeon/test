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
