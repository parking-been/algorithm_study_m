#!/bin/bash
set -e

clear

echo ""
echo "=================================================="
echo "📌 문제 풀이용 브랜치를 생성합니다."
echo "  브랜치 규칙: {알고리즘번호}-{일차}/{문제번호}"
echo "  예시: 1-1/1260"
echo "=================================================="
echo ""

# main 브랜치 여부 확인
CURRENT_BRANCH=$(git branch --show-current)
if [[ "$CURRENT_BRANCH" != "main" ]]; then
  echo "❌ 현재 브랜치가 main이 아닙니다: $CURRENT_BRANCH"
  echo "   문제 풀이는 반드시 main에서 시작해야 합니다."
  echo ""
  exit 1
fi

# 입력 받기
read -p "👉 번호 (숫자만 입력, 예: 1): " ALGORITHM
read -p "👉 일차 (숫자만 입력, 예: 1): " DAY
read -p "👉 문제 번호 (예: 1000): " PROBLEM

echo ""

# 입력값 검증
if [[ ! "$ALGORITHM" =~ ^[0-9]+$ || ! "$DAY" =~ ^[0-9]+$ || ! "$PROBLEM" =~ ^[0-9]+$ ]]; then
  echo "❌ 숫자만 입력해야 합니다."
  echo ""
  exit 1
fi

BRANCH_NAME=$(printf "%1d-%1d/%s" "$ALGORITHM" "$DAY" "$PROBLEM")

echo "▶ 생성될 브랜치명: $BRANCH_NAME"
read -p "👉 이 브랜치로 시작할까요? (Y/N): " CONFIRM
echo ""

if [[ ! "$CONFIRM" =~ ^[Yy]$ ]]; then
  echo "❌ 사용자가 취소했습니다."
  echo ""
  exit 1
fi

# 브랜치 존재 여부 확인
if git show-ref --verify --quiet "refs/heads/$BRANCH_NAME"; then
  echo "❌ 이미 존재하는 브랜치입니다."
  echo ""
  exit 1
fi

# 브랜치 생성
git checkout -b "$BRANCH_NAME"

echo ""
echo "✅ 브랜치 생성 완료"
echo "현재 브랜치는 $BRANCH_NAME 입니다."
echo "👉 이제 문제 풀이를 시작하세요."
echo ""
