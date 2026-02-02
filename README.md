# 숫자 야구 게임

## 📌 프로젝트 소개
1~9 사이의 서로 다른 숫자 3개로 구성된 정답을 컴퓨터가 생성하고, 사용자는 숫자를 입력하여 스트라이크/볼 힌트를 통해 정답을 맞히는 콘솔 게임입니다.  
정답(3 스트라이크)을 맞히면 게임이 종료되며, 사용자는 게임을 재시작(1)하거나 완전히 종료(2)할 수 있습니다.

---

## ✅ 기능 목록

### 게임 흐름
- [x] 게임 시작 시 컴퓨터가 1~9 범위의 서로 다른 숫자 3개를 생성한다.
- [x] 사용자에게 숫자 입력을 요청하고 입력을 받는다.
- [x] 입력한 숫자에 대한 스트라이크/볼/낫싱 힌트를 계산하여 출력한다.
- [x] 3스트라이크가 되면 게임 종료 메시지를 출력한다.
- [x] 게임 종료 후 `1(재시작)` / `2(종료)` 입력을 받아 처리한다.
    - [x] 재시작 시 정답 숫자를 새로 생성하여 게임을 다시 진행한다.
    - [x] 종료를 선택하면 프로그램을 종료한다.

### 입력 검증 / 예외 처리
- [x] 사용자 입력이 잘못된 경우 `[ERROR]`로 시작하는 에러 메시지를 출력하고 게임을 계속 진행한다.
- [x] 숫자 입력 검증
    - [x] 3자리인지 검증한다.
    - [x] 각 자리가 1~9 범위인지 검증한다. (도메인: `BaseballNumber`)
    - [x] 3자리 숫자가 서로 다른 수인지 검증한다. (도메인: `BaseballNumbers`)
- [x] 재시작/종료 입력 검증
    - [x] 1 또는 2만 허용한다.

---

## 🧱 도메인 모델

### `BaseballNumber`
- 한 자리 숫자를 의미하는 값 객체(Value Object)
- 유효 범위: 1~9
- 생성 시 검증을 수행하여 항상 유효한 상태만 유지

### `BaseballNumbers` (일급 컬렉션)
- `BaseballNumber` 3개를 보유하는 일급 컬렉션
- 생성 시 다음 규칙을 검증하여 항상 유효한 상태만 유지
    - 숫자 개수는 3개
    - 서로 다른 숫자(중복 없음)

### `Hint`
- 스트라이크/볼 결과를 표현하는 값 객체

### `BaseballGame`
- 정답 보유 및 입력과의 비교 결과(`Hint`) 생성

---

## 🧩 패키지 구조(예시)

- `game.baseball.domain`
    - `BaseballNumber` : 한 자리 숫자(1~9)
    - `BaseballNumbers` : 3자리 숫자 일급 컬렉션(개수/중복 검증)
    - `Hint` : 스트라이크/볼 결과 값 객체
    - `BaseballGame` : 정답 보유 및 힌트 계산

- `game.baseball.application`
    - `BaseballGameService` : 게임 시작 및 추측 처리(유즈케이스)
    - `GuessCommandParser` : 숫자 입력 커맨드 파싱
    - `RestartCommandParser` : 재시작/종료 커맨드 파싱

- `game.baseball.application.port`
    - `in` : `BaseballGameUseCase`, `command(GuessCommand, RestartCommand)`
    - `out` : `GameInputPort`, `GameOutputPort`, `NumberGeneratorPort`

- `game.baseball.adapter`
    - `in` : `BaseballGameInputView`, `BaseballGameOutputView`, `BaseballGameController`
    - `out` : `RandomNumberGenerator`, `BaseballPrinter`

- `game`
    - `Application` : 프로그램 시작점(의존성 조립)
    - `GameRunner` : 실행 트리거

> 핵심 로직(도메인/애플리케이션)과 UI(System.in/out)를 분리하여, 도메인 로직을 단위 테스트 대상으로 삼습니다.

---

## 🧪 테스트 전략
- 도메인 로직에 대해 단위 테스트를 작성합니다.
- UI 로직(System.in/out, Scanner 등)은 테스트 범위에서 제외합니다.
- JUnit5 + AssertJ를 사용합니다.

---

## ⚙️ 프로그래밍 요구사항 준수
- indent depth는 2까지만 허용합니다.
- `else`, `switch/case`를 사용하지 않습니다.
- Java Stream API를 사용하지 않습니다. (람다는 가능)
- 메서드는 15라인을 넘지 않도록 분리합니다.

---

## ▶️ 실행 방법(예시)
- IDE에서 `Application.main()` 실행
- 또는 Gradle 기반 프로젝트라면:
    - `./gradlew test`
    - `./gradlew run`

---

## ✍️ 커밋/진행 방식
- 기능 구현 전 README에 기능 목록을 작성하고, 기능 단위로 커밋합니다.
