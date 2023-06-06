# Cakey-backend

This is backend repository for *Cakey app*

## Convention

[Google java style guide](https://google.github.io/styleguide/javaguide.html)
를 따른다. \
[Spotless](https://github.com/diffplug/spotless)를 사용하여 check 및 reformat 가능.

### Code check

git hook을 이용하여 commit 이전에 자동으로 실행됨. \
**git clone 이후 처음에 hook install 필요 (1회만)**

```bash
./gradlew installGitLocalHook
```

### Code reformat

code reformat이 필요할 시, git commit시에 `BUILD FAILED`가 뜨면서 실패함. \
아래의 스크립트를 실행하면 spotless가 알아서 style guide에 맞게 파일들을 수정해 줌. \
git add 후 다시 commit하면 성공~

```bash
./gradlew spotlessApply
```

## Docker

[Jib](https://github.com/GoogleContainerTools/jib/tree/master/jib-gradle-plugin)을 사용해 빌드한다. \
build.gradle의 jib.to.tags에 [SemVer](https://semver.org/) 형식을 따르는 버전 명시. ex) 0.0.1 \
버전이 바뀐 경우 `git add build.gradle` 이후 커밋해서 빌드한 시점의 코드 상태를 추적할 수 있도록 한다.

```bash
./gradlew jibDockerBuild
```

## Docker compose

로컬 테스트용으로 pc 한 대에 배포하기 위한 환경.

### Quick start guide

```bash
cp .env.example .env
./scripts/start.sh (최초 한번만 실행해도 컨테이너 떠 있음)
```

### Scripts

#### Stop docker compose

```bash
./scripts/stop.sh
```

#### Run mariaDB CLI

```bash
./scripts/mariadb_cli.sh
```
