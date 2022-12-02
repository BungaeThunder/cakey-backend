# Cakey-backend

This is backend repository for *Cakey app*

## Convention

[Google java style guide](https://google.github.io/styleguide/javaguide.html)를 따른다. \
[IntelliJ에 Google Java Style Guide 적용하기](https://velog.io/@injoon2019/IntelliJ%EC%97%90-Google-Java-Style-Guide-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0)
참고 \
프로젝트에 커밋된 [intellij-java-google-style.xml](intellij-java-google-style.xml)을 이용해도 좋다.

## Docker compose

### Quick start guide

```bash
cp .env.example .env
./scripts/start.sh
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
