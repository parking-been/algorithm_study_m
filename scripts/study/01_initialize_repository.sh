#!/bin/bash
set -e

ORIGINAL_REPO="ssafy15/algorithm_study"
UPSTREAM_URL="https://github.com/${ORIGINAL_REPO}.git"

# terminal clear
clear

echo ""
echo "================================================== "
echo "⚠️ 이 스크립트는 스터디 초기 세팅용입니다."
echo "  https://github.com/${ORIGINAL_REPO} Repository를"
echo "  Fork한 후, 개인 레포지토리에서 실행하세요."
echo "================================================== "
echo ""

# git 레포지토리 여부 확인
if ! git rev-parse --is-inside-work-tree >/dev/null 2>&1; then
  echo "❌ git 레포지토리가 아닙니다."
  exit 1
fi

# origin remote 확인
ORIGIN_URL=$(git remote get-url origin 2>/dev/null || true)

if [ -z "$ORIGIN_URL" ]; then
  echo "❌ origin remote가 설정되어 있지 않습니다."
  echo ""
  exit 1
fi

# 원본 레포에서 직접 실행 방지
if [[ "$ORIGIN_URL" == *"$ORIGINAL_REPO"* ]]; then
  echo "❌ 원본 스터디 레포지토리에서 직접 실행할 수 없습니다."
  echo "   반드시 fork한 개인 레포지토리에서 실행하세요."
  echo ""
  exit 1
fi

echo "✅ fork된 개인 레포지토리 확인 완료"
echo ""

# upstream 존재 여부 확인
if git remote | grep -q "^upstream$"; then
  echo "ℹ️ upstream remote가 이미 설정되어 있습니다."
  echo ""
else
  echo "👉 upstream remote 추가"
  git remote add upstream "$UPSTREAM_URL"
  echo "✅ upstream remote 추가 완료"
  echo ""
fi

echo "📌 현재 remote 목록:"
git remote -v
echo ""

echo "✅ 초기 세팅 완료"
echo ""
