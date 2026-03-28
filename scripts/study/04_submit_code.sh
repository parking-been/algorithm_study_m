#!/bin/bash
set -e

clear

echo ""
echo "=================================================="
echo "📌 문제 풀이 결과를 커밋합니다."
echo "=================================================="
echo ""

CURRENT_BRANCH=$(git branch --show-current)

# main 방지
if [[ "$CURRENT_BRANCH" == "main" ]]; then
  echo "❌ main 브랜치에서는 커밋할 수 없습니다."
  echo ""
  exit 1
fi

# # 브랜치명 형식 검증 (숫자-숫자/숫자)
if [[ ! "$CURRENT_BRANCH" =~ ^[0-9]+-[0-9]+/[0-9]+$ ]]; then
  echo "❌ 브랜치명이 규칙에 맞지 않습니다: $CURRENT_BRANCH"
  echo "   규칙: {알고리즘번호}-{일차}/{문제번호}"
  echo ""
  exit 1
fi

# Git 저장소 루트로 이동
REPO_ROOT=$(git rev-parse --show-toplevel)
cd "$REPO_ROOT"

# 파일 추가
git add .

# 변경 사항 확인
echo "📂 변경된 파일:"
git status --short
echo ""

# 변경 없으면 중단
if git diff --quiet && git diff --cached --quiet; then
  echo "❌ 커밋할 변경 사항이 없습니다."
  echo ""
  exit 1
fi

USER_FILE=".study_user"

if [ -f "$USER_FILE" ]; then
  AUTHOR_NAME=$(cat "$USER_FILE")
else
  read -p "👉 본인 이름(한국어 실명)을 입력하세요: " AUTHOR_NAME
  echo ""

  if [ -z "$AUTHOR_NAME" ]; then
    echo "❌ 이름이 입력되지 않았습니다."
    echo ""
    exit 1
  fi

  echo "$AUTHOR_NAME" > "$USER_FILE"
  echo "ℹ️ 입력한 이름이 $USER_FILE 에 저장되었습니다."
  echo ""
fi

COMMIT_MSG="$CURRENT_BRANCH $AUTHOR_NAME"

echo "▶ 커밋 메시지:"
echo "  \"$COMMIT_MSG\""
echo ""

read -p "👉 이 메시지로 커밋할까요? (Y/N): " CONFIRM
echo ""

if [[ ! "$CONFIRM" =~ ^[Yy]$ ]]; then
  echo "❌ 사용자가 취소했습니다."
  echo ""
  exit 1
fi

# commit
git commit -m "$COMMIT_MSG"

# push upstream
git push --set-upstream origin "$CURRENT_BRANCH"

echo ""
echo "✅ 커밋 및 푸시 완료"
echo "👉 다음 단계: PR 생성"
echo ""
