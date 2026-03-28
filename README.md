# Algorithm Study Guide (Java Only)

본 레포지토리는 Java 기반 알고리즘 스터디를 위한 공용 저장소입니다.
스터디는 다음 흐름으로 진행됩니다.

주차별 주제 선정 → 개인 풀이 → PR → 코드 리뷰

구현의 완성도보다 문제 접근 방식, 상태 정의, 판단 근거를 공유하는 것을 목표로 합니다.

---

## 1. 스터디 진행 방식
1.	하나의 알고리즘 주제를 선정합니다.
2.	관리자가 주차별 디렉토리 구조를 upstream(main)에 추가합니다.
3.	스터디원은 각자 fork한 저장소에서 문제를 풉니다.
4.	풀이 완료 후 PR을 생성하고 코드 리뷰를 진행합니다.

---

## 2. 레포지토리 구조 (Java 기준)
```
algorithm_study/
├─ .github/
│   └─ PULL_REQUEST_TEMPLATE.md
│
├─ src/
│   └─ main/
│       └─ java/
│           └─ study/
│               ├─ a01_ds/
│               │   ├─ d01/
│               │   │   └─ p1966/
│               │   │       ├─ Main_정민호.java
│               │   │       ├─ Main_홍길동.java
│               │   │       └─ problem.md
│               │   └─ d02/
│               │       └─ ...
│               └─ a02_.../
│                   └─ ...
│
├─ script/
│   ├─ ops/
│   │   └─ pr_merge.sh
│   └─ study/
│       ├─ 01_initialize_repository.sh
│       ├─ 02_before_study.sh
│       ├─ 03_start_solve.sh
│       └─ 04_submit_code.sh
│
├─ ...
├─ .gitignore
└─ README.md
```

### 디렉토리 규칙
- **aXX_알고리즘명** : 알고리즘 주제
- **dXX** : 일차
- **pXXXX** : BOJ 문제 번호
- 각 스터디원은 **pXXXX** 하위에 `Main_본인이름.java` 작성

### Java 패키지 규칙

- `package study.a01_ds.d01.p1966;`
  - 모든 파일은 동일 패키지 유지 
  - 클래스명으로 사람을 구분 (`class Main_정민호`)

---

## 3. 초기 세팅 (필수)

### 1️⃣ 레포지토리 fork
- Organization 저장소를 본인 GitHub 계정으로 fork
    ```
    https://github.com/ssafy15/algorithm_study
    ```

---

### 2️⃣ 로컬 clone 및 upstream 설정

```
git clone https://github.com/본인계정/algorithm_study.git
cd algorithm_study
git remote add upstream https://github.com/ssafy15/algorithm_study.git
```
- origin : 본인 fork
- upstream : 스터디 원본 저장소

---

## 4. IntelliJ / Eclipse 프로젝트 설정

### IntelliJ IDEA
1.	Open → algorithm_study 디렉토리 선택
2.	Project SDK: Java 8 이상
3.	src/main/java 자동 인식
4.	바로 실행 가능 (main 메서드 기준)

### Eclipse
1.	Import → Existing Maven / Gradle 아님
2.	General → Existing Projects into Workspace
3.	JRE 설정 후 실행

---

## 5. 주차 시작 전 동기화 (필수)
```
git fetch upstream
git checkout main
git merge upstream/main
```
- 문제 풀기 전에 반드시 실행

---

## 6. 브랜치 규칙 (중요)

- ❌ main 브랜치에서 직접 작업 금지
```
git checkout -b 1-1/1966
```

### 브랜치 네이밍
- 알고리즘번호-일차/문제번호
- 예시:
  - 1-1/1966 
  - 2-2/2178

---

## 7. 문제 풀이 규칙 (Java)
- **파일명**: `Main_이름.java`
- **입출력**: 자유
- BOJ 기준 단일 main 클래스
```java
class Main_정민호 {
    public static void main(String[] args) throws Exception {
        // 내용
    }
}
```

---

## 8. PR 생성 규칙
### PR 방향
- 본인 fork → upstream main
### PR 제목
- 1-1/1966 정민호
### PR 내용
- [pull_request_template.md](.github/pull_request_template.md)

---

## 9. 코드 리뷰 규칙
- 최소 1개 이상 리뷰
- “왜 이렇게 했는지”에 집중

### ✅ 지향 
- 이 상태를 이렇게 둔 이유는?
- 반례는 없나요?

### ❌ 지양
- 정답 코드 제시
- 단순 칭찬

---
## 10. 자동화 스크립트 (관리자 제공)
- 위치: [study script](scripts/study)
### 스크립트	용도
- `01_initialize_repository.sh`: 최초 세팅
- `02_before_study.sh`: 스터디 시작 전 최신화
- `03_start_solve.sh`: 문제별 브랜치 생성
- `04_submit_code.sh`: 커밋 및 푸쉬 자동화

> Git이 익숙하지 않은 스터디원은 스크립트 사용 권장

---
