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

## Docker compose

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
